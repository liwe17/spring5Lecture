package com.weiliai.pattern.decorator.passport.old;

/**
 * @Author: Doug Li
 * @Date 2020/3/12
 * @Describe: TODO
 */
public interface ISignInForThirdService extends ISignInService {

    ResultMsg loginForQQ(String id);


    ResultMsg loginForWhChat(String id);

    ResultMsg loginForToken(String token);

    ResultMsg loginForTelephone(String telephone,String code);

    ResultMsg loginForRegister(String username,String password);
}
