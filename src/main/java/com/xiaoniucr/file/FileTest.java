package com.xiaoniucr.file;


import org.apache.tomcat.util.http.fileupload.IOUtils;

import java.io.*;

public class FileTest {

    private static String PATH = "D:\\文档";


    public static void main(String[] args) {

        String url = uploadFile(PATH,"test.docx");
        System.out.println(url);

    }

    public static String uploadFile(String path,String fileName){

        File file = new File(path);
        if(!file.exists()){
            file.mkdirs();
        }
        File f = new File(PATH,fileName);

        try {
            f.createNewFile();
            InputStream in = new FileInputStream(f);
            IOUtils.copy(in,new FileOutputStream(PATH + File.separator+"test1.docx"));
        } catch (Exception e) {
            e.printStackTrace();
        }

        return f.getName();


    }



}
