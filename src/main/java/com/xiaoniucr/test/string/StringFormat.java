package com.xiaoniucr.test.string;

import java.io.*;

/**
 * @author yanghl
 * @date 2019/9/21 16:04
 */
public class StringFormat {

    public static void main(String[] args) {

        //填充到固定三位


        // 根据新闻路径，读取新闻文件内容，显示在页面上
        String thingPath = "D:\\11.txt";//获取文章路径

        // 读取文件内容，写到String中
        int len = 0;
        StringBuffer str = new StringBuffer("");
        File file = new File(thingPath);
        try {
            FileInputStream is = new FileInputStream(file);
            InputStreamReader isr = new InputStreamReader(is,"GBK");
            BufferedReader in = new BufferedReader(isr);
            String line = null;
            while ((line = in.readLine()) != null)
            {
                if (len != 0) // 处理换行符的问题
                {
                    str.append(line);
                }
                else
                {
                    str.append(line);
                }
                len++;
            }
            in.close();
            is.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
        String content=str.toString();//内容
        System.out.println("content:"+content);
    }
}
