package com.weiliai.pattern.p2p.dynamic.simple;

import sun.misc.ProxyGenerator;

import java.io.FileOutputStream;
import java.io.IOException;

/**
 * @Author: Doug Li
 * @Date 2020/2/28
 * @Describe: TODO
 */
public class JDKProxyTest {

    public static void main(String[] args) throws IOException {
        final Person obj =(Person) new JDKMeipo().getTarget(new Customer());
        obj.findLove();;

        //通过反编译工具可以查看代码
        final byte[] bytes = ProxyGenerator.generateProxyClass("$Proxy0", new Class[]{Person.class});
        final FileOutputStream os = new FileOutputStream("d://$Proxy0.class");
        os.write(bytes);
        os.close();
    }
}
