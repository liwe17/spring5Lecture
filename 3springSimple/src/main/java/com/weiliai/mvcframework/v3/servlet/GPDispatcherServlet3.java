package com.weiliai.mvcframework.v3.servlet;

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
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @Author: Doug Li
 * @Date 2020/3/15
 * @Describe: 3.0版本
 * 改造HandlerMapping,支持url强制转换,支持正则
 */
public class GPDispatcherServlet3 extends HttpServlet {

    //保存application.properties配置文件中的内容
    private Properties configContext = new Properties();

    //保存扫描的所有的类名
    private List<String> classNames = new ArrayList<>();

    //传说中的IoC容器,为了简化程序,暂时不考虑使用ConcurrentHashMap,只关注设计思想和原理
    private Map<String, Object> ioc = new HashMap<>();

    //保存所有的url和method的对应关系
    private List<Handler> handlerMapping = new ArrayList<>();

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
            resp.getWriter().write("500 Exception,Detail : " + Arrays.toString(ex.getStackTrace()));
        }
    }

    private void doDispatch(HttpServletRequest req, HttpServletResponse resp) throws Exception {
        Handler handler = getHandler(req);
        if (handler == null) {
            //匹配不到,返回404
            resp.getWriter().write("404 Not Found!");
            return;
        }
        //根据url,获取待执行的方法,并执行
        Class<?>[] parameterTypes = handler.method.getParameterTypes();
        //保存参数位置
        Object[] paramValues = new Object[parameterTypes.length];
        Map<String, String[]> paramsMap = req.getParameterMap();
        for (Map.Entry<String, String[]> param : paramsMap.entrySet()) {
            String value = Arrays.toString(param.getValue()).replaceAll("\\[|\\]", "").replaceAll("\\s", ",");
            if (!handler.paramIndexMapping.containsKey(param.getKey())) {
                continue;
            }
            int index = handler.paramIndexMapping.get(param.getKey());
            paramValues[index] = convert(parameterTypes[index], value);
        }

        if (handler.paramIndexMapping.containsKey(HttpServletRequest.class.getName())) {
            int reqIndex = handler.paramIndexMapping.get(HttpServletRequest.class.getName());
            paramValues[reqIndex] = req;
        }
        if (handler.paramIndexMapping.containsKey(HttpServletResponse.class.getName())) {
            int respIndex = handler.paramIndexMapping.get(HttpServletResponse.class.getName());
            paramValues[respIndex] = resp;
        }

        Object returnValue = handler.method.invoke(handler.controller, paramValues);
        if (returnValue == null || returnValue instanceof Void) {
            return;
        }

        resp.getWriter().write(returnValue.toString());

    }


    //url传过来参数都是String类型的,由于HTTP基于字符串协议
    //只需要把String转换任意类型
    private Object convert(Class<?> type, String value) {
        if (Integer.class == type) {
            return Integer.valueOf(value);
        }
        //有其他类型,继续添加,例如Double
        return value;
    }

    private Handler getHandler(HttpServletRequest req) {
        if (handlerMapping.isEmpty()) {
            return null;
        }
        //获取请求URL
        String url = req.getRequestURI();
        //获取请求上下文路径
        String contextPath = req.getContextPath();
        //只保留需要的url后半段
        url = url.replace(contextPath, "").replaceAll("/+", "/");
        for (Handler handler : handlerMapping) {
            Matcher matcher = handler.pattern.matcher(url);
            if (!matcher.matches()) {
                return null;
            }
            return handler;
        }
        
        return null;

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
                String regex = ("/" + baseUrl + "/" + requestMapping.value()).replaceAll("/+", "/");
                Pattern pattern = Pattern.compile(regex);
                handlerMapping.add(new Handler(entry.getValue(), method, pattern));
                System.out.println("Mapped :" + regex + "," + method);
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

    /**
     * handler 记录controller中的requestMapping和method的对应关系
     * 内部类
     */
    private class Handler {

        protected Object controller; //保存方法对应的实例

        protected Method method; //保存映射的方法

        protected Pattern pattern;

        protected Map<String, Integer> paramIndexMapping; //参数顺序

        public Handler(Object controller, Method method, Pattern pattern) {
            this.controller = controller;
            this.method = method;
            this.pattern = pattern;
            this.paramIndexMapping = new HashMap<>();
            this.putParamIndexMapping(method);
        }

        private void putParamIndexMapping(Method method) {
            //提取方法中加了注解的参数
            Annotation[][] pa = method.getParameterAnnotations();
            for (int i = 0; i < pa.length; i++) {
                for (Annotation a : pa[i]) {
                    if (a instanceof GPRequestParam) {
                        String paramName = ((GPRequestParam) a).value();
                        if (!"".equals(paramName.trim())) {
                            paramIndexMapping.put(paramName, i);
                        }
                    }
                }
            }
            //抽取方法中的request和response参数
            Class<?>[] parameterTypes = method.getParameterTypes();
            for (int i = 0; i < parameterTypes.length; i++) {
                Class<?> type = parameterTypes[i];
                if (type == HttpServletRequest.class || type == HttpServletResponse.class) {
                    paramIndexMapping.put(type.getName(), i);
                }
            }
        }
    }
}
