package com.weiliai.mvcframework.v2.servlet;

import com.weiliai.mvcframework.annotation.*;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.net.URL;
import java.util.*;

/**
 * @Author: Doug Li
 * @Date 2020/3/15
 * @Describe: 2.0版本
 * 初始化步骤,按功能拆分方法
 */
public class GPDispatcherServlet2 extends HttpServlet {

    //保存application.properties配置文件中的内容
    private Properties configContext = new Properties();

    //保存扫描的所有的类名
    private List<String> classNames = new ArrayList<>();

    //传说中的IoC容器,为了简化程序,暂时不考虑使用ConcurrentHashMap,只关注设计思想和原理
    private Map<String, Object> ioc = new HashMap<>();

    //保存所有的url和method的对应关系
    private Map<String, Method> handlerMapping = new HashMap<>();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //get方式统一转换为post方式处理
        this.doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            //对方法进行调度处理
            doDispatch(req, resp);
        } catch (Exception ex) {
            ex.printStackTrace();
            resp.getWriter().write("500 Exception,Detail : "+Arrays.toString(ex.getStackTrace()));
        }
    }

    private void doDispatch(HttpServletRequest req, HttpServletResponse resp) throws Exception {
        //获取请求URL
        String url = req.getRequestURI();
        //获取请求上下文路径
        String contextPath = req.getContextPath();
        //只保留需要的url后半段
        url = url.replace(contextPath, "").replaceAll("/+", "/");
        if (!this.handlerMapping.containsKey(url)) {
            //匹配不到,返回404
            resp.getWriter().write("404 Not Found!");
            return;
        }
        //根据url,获取待执行的方法,并执行
        Method method = this.handlerMapping.get(url);
        Class<?>[] parameterTypes = method.getParameterTypes();
        Map<String, String[]> paramsMap = req.getParameterMap();
        //保存参数位置
        Object[] paramValues = new Object[parameterTypes.length];
        //根据参数位置,动态赋值
        for (int i = 0; i < parameterTypes.length; i++) {
            Class<?> parameterType = parameterTypes[i];
            if(parameterType == HttpServletRequest.class){
                paramValues[i]=req;
            }else if(parameterType == HttpServletResponse.class){
                paramValues[i]=resp;
            }else if (parameterType == String.class){
                //提取方法中加了注解的参数
                Annotation[][] annotations = method.getParameterAnnotations();
                for(int j = 0 ;j < annotations.length ; j++){
                    for (Annotation annotation : annotations[j]) {
                        if(annotation instanceof GPRequestParam){
                            String paramName =  ((GPRequestParam) annotation).value();
                            if(!"".equals(paramName.trim())){
                                String value = Arrays.toString(paramsMap.get(paramName)).replaceAll("\\[|\\]","").replaceAll("\\s",",");
                                paramValues[i] = value;
                            }
                        }
                    }
                }
            }
        }
        //投机取巧的方式
        //通过反射获取method所在的class,获取class之后还要获得class的名称
        //在调用toLowerFirstCase获取beanName
        String beanName = toLowerFirstCase(method.getDeclaringClass().getSimpleName());
        method.invoke(ioc.get(beanName), new Object[]{req, resp, paramsMap.get("name")[0]});
    }

    @Override
    public void init(ServletConfig config) throws ServletException {
        //初始化
        //1.加载配置文件
        doLoadConfig(config.getInitParameter("contextConfigLocation"));
        //2.扫描相关类
        doScanner(configContext.getProperty("scanPackage"));
        //3.初始化扫描的类,并且将它们放到IoC容器中
        doInstance();
        //4.完成依赖注入
        doAutowired();
        //5.初始化handlerMapping
        initHandlerMapping();

        System.err.println("GP MVC FRAMEWORK IS INIT");
    }

    private void initHandlerMapping() {
        if (ioc.isEmpty()) {
            return;
        }
        for (Map.Entry<String, Object> entry : ioc.entrySet()) {
            Class<?> clazz = entry.getValue().getClass();
            if (!clazz.isAnnotationPresent(GPController.class)) {
                continue;
            }

            //保存写在类上面的@GPRequestMapping("/demo")
            String baseUrl = "";
            if (clazz.isAnnotationPresent(GPRequestMapping.class)) {
                GPRequestMapping requestMapping = clazz.getAnnotation(GPRequestMapping.class);
                baseUrl = requestMapping.value();
            }

            //默认获取所有的public类型的方法
            for (Method method : clazz.getMethods()) {
                if (!method.isAnnotationPresent(GPRequestMapping.class)) {
                    continue;
                }
                GPRequestMapping requestMapping = method.getAnnotation(GPRequestMapping.class);
                //优化
                String url = ("/" + baseUrl + "/" + requestMapping.value()).replaceAll("/+", "/");
                handlerMapping.put(url, method);
                System.out.println("Mapped :" + url + "," + method);
            }

        }

    }

    //自动进行依赖注入
    private void doAutowired() {
        if (ioc.isEmpty()) {
            return;
        }

        for (Map.Entry<String, Object> entry : ioc.entrySet()) {
            //获取所有的字段,包括private,protected,default类型
            //正常来说,普通的oop编程,只能获取public类型的字段
            Field[] fields = entry.getValue().getClass().getDeclaredFields();
            for (Field field : fields) {
                if (!field.isAnnotationPresent(GPAutowired.class)) {
                    continue;
                }
                GPAutowired autowired = field.getAnnotation(GPAutowired.class);
                //如果用户没有自定义beanName,默认就根据类型注入
                String beanName = autowired.value().trim();
                if ("".equals(beanName)) {
                    beanName = toLowerFirstCase(field.getType().getName());
                }

                //暴力访问
                field.setAccessible(true);
                try {
                    //反射赋值
                    field.set(entry.getValue(), ioc.get(beanName));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }


    private void doInstance() {
        //初始化,为DI做准备
        if (classNames.isEmpty()) {
            return;
        }
        try {
            for (String className : classNames) {
                Class<?> clazz = Class.forName(className);
                //什么样的需要实例化
                //加了注解类的才需要实例化,怎么判断
                //为简化代码,只用@GPController和@GPService举例
                if (clazz.isAnnotationPresent(GPController.class)) {
                    Object instance = clazz.newInstance();
                    //Spring默认首字母小写
                    String beanName = toLowerFirstCase(clazz.getSimpleName());
                    ioc.put(beanName, instance);
                } else if (clazz.isAnnotationPresent(GPService.class)) {
                    //1.自定义的beanName
                    GPService service = clazz.getAnnotation(GPService.class);
                    String beanName = service.value().trim();
                    //2.默认类名首字母小写
                    if ("".equals(beanName)) {
                        beanName = toLowerFirstCase(clazz.getSimpleName());
                    }
                    Object instance = clazz.newInstance();
                    ioc.put(beanName, instance);

                    //3.根据类型自动赋值,这是投机取巧的方式
                    for (Class<?> i : clazz.getInterfaces()) {
                        if (ioc.containsKey(i.getName())) {
                            throw new Exception("The " + i.getName() + " is exist!");
                        }
                        //把接口的类型直接当作key
                        ioc.put(i.getName(), instance);
                    }
                } else {
                    continue;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    //将类名首字母小写
    private String toLowerFirstCase(String simpleName) {
        char[] chars = simpleName.toCharArray();
        chars[0] += 32;
        return String.valueOf(chars);
    }

    //加载配置文件
    private void doLoadConfig(String contextConfigLocation) {
        //直接通过类路径找到spring主配置文件所在的路径
        //并且将其读取出来放到Properties对象中
        //相当于将scanPackage-com.weiliai 保存到内存中
        InputStream fis = this.getClass().getClassLoader().getResourceAsStream(contextConfigLocation);
        try {
            configContext.load(fis);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (null != fis) {
                try {
                    fis.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }


    }

    private void doScanner(String scanPackage) {
        //scanPackage=com.weiliai,存储的是包路径
        //转换为文件路径,实际上就是把.,替换为/
        URL url = this.getClass().getClassLoader().getResource("/" + scanPackage.replaceAll("\\.", "/"));
        File classDir = new File(url.getFile());
        for (File file : classDir.listFiles()) {
            if (file.isDirectory()) {
                doScanner(scanPackage + "." + file.getName());
            } else {
                if (!file.getName().endsWith(".class")) {
                    continue;
                }
                String clazzName = (scanPackage + "." + file.getName().replace(".class", ""));
                classNames.add(clazzName);
            }

        }
    }
}
