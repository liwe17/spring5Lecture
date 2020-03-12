package com.weiliai.pattern.decorator.passport.old;

/**
 * @Author: Doug Li
 * @Date 2020/3/12
 * @Describe: TODO
 */
public class SignInForThirdService implements ISignInForThirdService {

    private ISignInService signInService;

    public SignInForThirdService(ISignInService signInService) {
        this.signInService = signInService;
    }

    @Override
    public ResultMsg loginForQQ(String id) {
        return null;
    }

    @Override
    public ResultMsg loginForWhChat(String id) {
        return null;
    }

    @Override
    public ResultMsg loginForToken(String token) {
        return null;
    }

    @Override
    public ResultMsg loginForTelephone(String telephone, String code) {
        return null;
    }

    @Override
    public ResultMsg loginForRegister(String username, String password) {
        return null;
    }

    @Override
    public ResultMsg register(String username, String password) {
        return this.signInService.register(username,password);
    }

    @Override
    public ResultMsg login(String username, String password) {
        return this.signInService.login(username,password);
    }
}
