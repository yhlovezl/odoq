package com.xiaoniucr.jdkdynamicproxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * @author yanghl
 * @date 2020/7/16 16:30
 */
public class Test {

    public static void main(String[] args) {

        MaotaiJiu maotaiJiu = new MaotaiJiu();
        InvocationHandler jingxiao1 = new GuitaiA(maotaiJiu);

        SellWine dynamicProxt = (SellWine) Proxy.newProxyInstance(MaotaiJiu.class.getClassLoader(),MaotaiJiu.class.getInterfaces(),jingxiao1);
        Method[] methods = dynamicProxt.getClass().getMethods();
        if(methods != null){
            for(Method m : methods){
                System.out.println(m.getName());
            }
        }
        dynamicProxt.sellWine();
    }
}
