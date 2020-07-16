package com.xiaoniucr.test.string;

import java.math.BigDecimal;
import java.text.DecimalFormat;

/**
 * @author yanghl
 * @date 2019/10/29 11:48
 */
public class BigDemicalUtil {


    public static BigDecimal divWithScale(double v1, double v2, int scale){
        BigDecimal b1 = new BigDecimal(Double.toString(v1));
        BigDecimal b2 = new BigDecimal(Double.toString(v2));
        return b1.divide(b2, scale, BigDecimal.ROUND_HALF_UP);
    }

    public static void main(String[] args) {

        Integer size = 11262;
        System.out.println(BigDemicalUtil.divWithScale(size,1024*1024,2).doubleValue()+"M");

    }
}
