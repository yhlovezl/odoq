package com.xiaoniucr.cglibdynamicproxy;


import org.springframework.cglib.proxy.Enhancer;
import org.springframework.cglib.proxy.MethodInterceptor;
import org.springframework.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

/**
 * @author yanghl
 * @date 2020/7/17 10:36
 */
public class SellWineCglib implements MethodInterceptor {

    private Object target;

    public Object getProxyInstance(Object target){
        this.target = target;
        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(this.target.getClass());
        enhancer.setCallback(this);
        return enhancer.create();
    }


    @Override
    public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {
        System.out.println("开始买酒啦！");
        Object result = methodProxy.invokeSuper(o,objects);
        System.out.println("卖完啦！");
        return result;
    }
}
