package com.xiaoniucr.test.string;

import com.alibaba.fastjson.JSONObject;

import java.util.List;

public class LengthTest {

    public static void main(String[] args) {

        String str = "截屏2019-11-15上午11.15.22.png";
        System.out.println(str.lastIndexOf("."));


        String deptName = "会议流程及培训流程（权限关联测试，请勿动）";
        System.out.println(deptName.length());


        System.out.println("模版组需求整合");


        String userName = "";

        String code = "xxx_1112";

        System.out.println(code.substring(0,code.lastIndexOf("_")));


        String date = "[\"2020-05-15\"]";
        List<String> dataList = JSONObject.parseArray(date,String.class);
        System.out.println(dataList.size());

        String kl = "测试_001";
        System.out.println(kl.substring(kl.length()-3));

    }
}
