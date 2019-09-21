package com.xiaoniucr.string;

/**
 * @author yanghl
 * @date 2019/9/21 16:04
 */
public class StringFormat {

    public static void main(String[] args) {

        //填充到固定三位
        String str = String.format(String.format("%03d",1));

        //填充字符
        String s1 = String.format("%s","哈哈");

        //填充数字
        String s2 = String.format("%x",1);



    }
}
