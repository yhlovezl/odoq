package com.xiaoniucr.string;

import sun.misc.BASE64Decoder;

import java.io.FileOutputStream;
import java.io.OutputStream;

/**
 * @author yanghl
 * @date 2019/9/20 14:08
 */
public class Base64ToFile {





    /**
     * base64字符串转换成图片
     * @param imgStr		base64字符串
     * @param imgFilePath	图片存放路径
     * @return
     *
     * @author ZHANGJL
     * @dateTime 2018-02-23 14:42:17
     */
    public static boolean base64ToImage(String imgStr,String imgFilePath) {

        // 对字节数组字符串进行Base64解码并生成图片
        if (imgStr == null || "".equals(imgStr)) // 图像数据为空
            return false;

        BASE64Decoder decoder = new BASE64Decoder();
        try {
            // Base64解码
            byte[] b = decoder.decodeBuffer(imgStr);
            for (int i = 0; i < b.length; ++i) {
                if (b[i] < 0) {// 调整异常数据
                    b[i] += 256;
                }
            }
            OutputStream out = new FileOutputStream(imgFilePath);
            out.write(b);
            out.flush();
            out.close();

            return true;
        } catch (Exception e) {
            return false;
        }
    }


    public static void main(String[] args) {

        //解码之前data:image/jpeg;base64,得去掉
        String str = "data:image/jpeg;base64,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";
        base64ToImage(str,"D:\\img\\1.jpg");

    }

}
