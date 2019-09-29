package com.xiaoniucr.test.string;

/**
 * @author yanghl
 * @date 2019/9/27 10:31
 */
public class SubstringTest {

    public static void main(String[] args) {

        String str = "external-doc/transfor/e4ec2112c8434116a03356c1435914b7.pdf";
        System.out.println(str.substring(str.lastIndexOf("/")));
    }
}
