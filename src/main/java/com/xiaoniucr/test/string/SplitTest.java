package com.xiaoniucr.test.string;

/**
 * @author yanghl
 * @date 2019/12/25 11:08
 */
public class SplitTest {

    public static void main(String[] args) {

        String str = "18,0,,11,001";
        String[] strs = str.split(",");
        System.out.println(strs.length);

        String path = "1.jpg";
        System.out.println(path.substring(path.lastIndexOf(".")));
    }
}
