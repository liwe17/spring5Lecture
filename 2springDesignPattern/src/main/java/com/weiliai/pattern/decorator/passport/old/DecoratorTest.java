package com.weiliai.pattern.decorator.passport.old;

/**
 * @Author: Doug Li
 * @Date 2020/3/12
 * @Describe: TODO
 */
public class DecoratorTest {

    public static void main(String[] args) {
        ISignInForThirdService signInService = new SignInForThirdService(new SignInService());
        signInService.loginForQQ("QQ");
    }

}
