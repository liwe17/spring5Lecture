package com.weiliai.pattern.strategy.pay;

import java.util.HashMap;
import java.util.Map;

/**
 * @Author: Doug Li
 * @Date 2020/3/3
 * @Describe: TODO
 */
public class PayStrategy {

    public static final String ALI_PAY = "AliPay";
    public static final String JD_PAY = "JDPay";
    public static final String UNION_PAY = "UnionPay";
    public static final String WECHAT_PAY = "WechatPay";
    public static final String DEFAULT_PAY = ALI_PAY;

    private static Map<String,Payment> payStrategy = new HashMap<>();

    static {
        payStrategy.put(ALI_PAY,new AliPay());
        payStrategy.put(JD_PAY,new JDPay());
        payStrategy.put(UNION_PAY,new UnionPay());
        payStrategy.put(WECHAT_PAY,new WechatPay());
    }

    public static Payment get(String payKey){
        if(!payStrategy.containsKey(payKey)){
            return payStrategy.get(payKey);
        }
        return payStrategy.get(DEFAULT_PAY);
    }

}
