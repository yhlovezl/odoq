package com.xiaoniucr.test.string;

import com.alibaba.fastjson.JSON;
import com.xiaoniucr.test.bean.FileNumber;
import com.xiaoniucr.test.bean.OptionEntity;

import java.util.ArrayList;
import java.util.List;

/**
 * @author yanghl
 * @date 2019/12/16 11:18
 */
public class JsonParseTest {


    public static void main(String[] args) {

        FileNumber fn1 = new FileNumber();
        fn1.setType(1L);
        fn1.setValue("1212");

        FileNumber fn2 = new FileNumber();
        fn2.setType(-1L);
        fn2.setValue("-");

        FileNumber fn3 = new FileNumber();
        fn3.setType(2L);
        fn3.setValue("13322");

        OptionEntity oe1 = new OptionEntity();
        oe1.setId(1L);
        oe1.setValue("1212");

        OptionEntity oe2 = new OptionEntity();
        oe2.setId(2L);
        oe2.setValue("13322");


        OptionEntity oe3 = new OptionEntity();
        oe3.setId(3L);
        oe3.setValue("22");

        List<OptionEntity> opList = new ArrayList<>();
        opList.add(oe1);
        opList.add(oe2);
        opList.add(oe3);
        fn3.setOptionList(opList);



        FileNumber fn4 = new FileNumber();
        fn4.setType(-1L);


        FileNumber fn5 = new FileNumber();
        fn5.setType(4L);
        fn5.setValue("44434");


        FileNumber fn6 = new FileNumber();
        fn6.setType(-1L);
        fn6.setValue("~");

        FileNumber fn7 = new FileNumber();
        fn7.setType(-2L);
        fn7.setValue("001");

        List<FileNumber> fnList = new ArrayList<>();
        fnList.add(fn1);
        fnList.add(fn2);
        fnList.add(fn3);
        fnList.add(fn4);
        fnList.add(fn5);
        fnList.add(fn6);
        fnList.add(fn7);

        System.out.println(JSON.toJSON(fnList));




        String jsontStr = "[{\n" +
                "\t\"type\": 1,\n" +
                "\t\"value\": \"1212\"\n" +
                "}, {\n" +
                "\t\"type\": -1,\n" +
                "\t\"value\": \"-\"\n" +
                "}, {\n" +
                "\t\"optionList\": [{\n" +
                "\t\t\"id\": 1,\n" +
                "\t\t\"value\": \"1212\"\n" +
                "\t}, {\n" +
                "\t\t\"id\": 2,\n" +
                "\t\t\"value\": \"13322\"\n" +
                "\t}, {\n" +
                "\t\t\"id\": 3,\n" +
                "\t\t\"value\": \"22\"\n" +
                "\t}],\n" +
                "\t\"type\": 2,\n" +
                "\t\"value\": \"13322\"\n" +
                "}, {\n" +
                "\t\"type\": -1\n" +
                "}, {\n" +
                "\t\"type\": 4,\n" +
                "\t\"value\": \"44434\"\n" +
                "}, {\n" +
                "\t\"type\": -1,\n" +
                "\t\"value\": \"~\"\n" +
                "}, {\n" +
                "\t\"type\": -2,\n" +
                "\t\"value\": \"001\"\n" +
                "}]";


        List<FileNumber> fileNumberList = JSON.parseArray(jsontStr,FileNumber.class);

        System.out.println(fileNumberList.size());




    }
}
