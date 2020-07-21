package com.xiaoniucr.cglibdynamicproxy;

import org.springframework.cglib.core.DebuggingClassWriter;

/**
 * @author yanghl
 * @date 2020/7/17 10:43
 */
public class Test {


    public static void main(String[] args) {
        System.setProperty(DebuggingClassWriter.DEBUG_LOCATION_PROPERTY, "D:\\classes");
        SellWineCglib proxy = new SellWineCglib();
        SellWineImpl sellWine = (SellWineImpl) proxy.getProxyInstance(new SellWineImpl());
        sellWine.sellMaotai();
    }
}
