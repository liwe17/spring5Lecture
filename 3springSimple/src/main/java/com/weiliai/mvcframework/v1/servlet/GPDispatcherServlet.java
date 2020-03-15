package com.weiliai.mvcframework.v1.servlet;

import com.weiliai.mvcframework.annotation.GPAutowired;
import com.weiliai.mvcframework.annotation.GPController;
import com.weiliai.mvcframework.annotation.GPRequestMapping;
import com.weiliai.mvcframework.annotation.GPService;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

/**
 * @Author: Doug Li
 * @Date 2020/3/15
 * @Describe: 1.0版本
 */
public class GPDispatcherServlet extends HttpServlet {

    private Map<String,Object> mapping = new HashMap<>();


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException{
        //get方式统一转换为post方式处理
        this.doPost(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException {
        try{
            //对方法进行调度处理
            doDispatch(req,resp);
        }catch (Exception ex){
            ex.printStackTrace();
        }
    }

    private void doDispatch(HttpServletRequest req, HttpServletResponse resp) throws Exception{
        //获取请求URL
        String url = req.getRequestURI();
        //获取请求上下文路径
        String contextPath = req.getContextPath();
        //只保留需要的url后半段
        url = url.replace(contextPath,"").replaceAll("/+","/");
        if(!this.mapping.containsKey(url)){
            //匹配不到,返回404
            resp.getWriter().write("404 Not Found!");
            return;
        }
        //根据url,获取待执行的方法,并执行
        Method method = (Method) this.mapping.get(url);
        Map<String,String[]> params = req.getParameterMap();
        method.invoke(this.mapping.get(method.getDeclaringClass().getName()),new Object[]{req,resp,params.get("name")[0]});
    }

    @Override
    public void init(ServletConfig config) throws ServletException {
        //初始化
        InputStream is = null;
        try{
            //获取web.xml和配置文件的内容
            Properties configContext = new Properties();
            is = this.getClass().getClassLoader().getResourceAsStream(config.getInitParameter("contextConfigLocation"));
            configContext.load(is);
            String scanPackage = configContext.getProperty("scanPackage");
            //所描指定包下的所有类文件
            doScanner(scanPackage);
            //根据全路径类名,进行处理
            for(String className : mapping.keySet()){
                if(!className.contains(".")){
                    continue;
                }
                Class<?> clazz = Class.forName(className);
                //1.处理GPController标注的类,设置请求url为key,method对象为value
                if(clazz.isAnnotationPresent(GPController.class)){
                    mapping.put(className,clazz.newInstance());
                    String baseUrl="";
                    if(clazz.isAnnotationPresent(GPRequestMapping.class)){
                        GPRequestMapping requestMapping = clazz.getAnnotation(GPRequestMapping.class);
                        baseUrl = requestMapping.value();
                    }
                    Method[] methods = clazz.getMethods();
                    for(Method method:methods){
                        if(!method.isAnnotationPresent(GPRequestMapping.class)){
                            continue;
                        }
                        GPRequestMapping requestMapping = method.getAnnotation(GPRequestMapping.class);
                        String url = (baseUrl+"/"+requestMapping.value().replaceAll("/+","/"));
                        mapping.put(url,method);
                        System.out.println("Mapped "+url+","+method);
                    }
                }else if (clazz.isAnnotationPresent(GPService.class)){
                    //处理GPService标注的类,将bean的名字作为key,实例化对象作为value
                    GPService service = clazz.getAnnotation(GPService.class);
                    String beanName = service.value();
                    if("".equals(beanName)){
                        beanName = clazz.getName();
                    }
                    Object instance = clazz.newInstance();
                    mapping.put(beanName,instance);
                    //将实例化的对象存入
                    for(Class<?> i : clazz.getInterfaces()){
                        mapping.put(i.getName(),instance);
                    }
                }else{
                    continue;
                }
            }
            for(Object object :mapping.values()){
                if(object == null){
                    continue;
                }
                Class<?> clazz = object.getClass();
                if(!clazz.isAnnotationPresent(GPAutowired.class)){
                    continue;
                }
                Field[] fields = clazz.getDeclaredFields();
                for (Field field : fields) {
                    if(!field.isAnnotationPresent(GPAutowired.class)){
                        continue;
                    }
                    GPAutowired autowired = field.getAnnotation(GPAutowired.class);
                    String beanName = autowired.value();
                    if("".equals(beanName)){
                        beanName = field.getType().getName();
                    }
                    field.setAccessible(true);
                    field.set(mapping.get(clazz.getName()),mapping.get(beanName));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if(is !=null){
                try {
                    is.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            System.err.println("GP MVC FRAMEWORK IS INIT");
        }
    }


    private void doScanner(String scanPackage) {
        //获取本地磁盘路径,并将全路径类名当作key,存入map
        URL url = this.getClass().getClassLoader().getResource("/" + scanPackage.replaceAll("\\.", "/"));
        File classDir = new File(url.getFile());
        for(File file :classDir.listFiles()){
            if(file.isDirectory()){
                doScanner(scanPackage+"."+file.getName());
            }else{
                String clazzName = (scanPackage+"."+file.getName().replace(".class",""));
                mapping.put(clazzName,null);
            }

        }
    }
}
