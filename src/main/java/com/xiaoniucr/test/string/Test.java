package com.xiaoniucr.test.string;

import com.alibaba.fastjson.JSONObject;
import com.xiaoniucr.entity.Cat;
import com.xiaoniucr.entity.Node;
import com.xiaoniucr.utils.DateUtil;
import org.apache.commons.lang.StringUtils;

import java.text.ParseException;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collectors;

/**
 * @author yanghl
 * @date 2020/2/2 16:25
 */
public class Test {

    public static void main(String[] args) {


        List<Node> list = new ArrayList<>();
        Node node1 = new Node();
        node1.setId("_34799");
        Node node2 = new Node();
        node2.setId("_34800");
        Node node3 = new Node();
        node3.setId("_34801");
        Node node4 = new Node();
        node4.setId("_34802");
        Node node5 = new Node();
        node5.setId("_34800");
        list.add(node1);
        list.add(node2);
        list.add(node3);
        list.add(node4);
        list.add(node5);
        List<String> list2 = list.stream().map(Node :: getId).distinct().collect(Collectors.toList());
        System.out.println(StringUtils.join(list2,","));




    }

    /**
     * @param inputDate 要解析的字符串
     * @param patterns 可能出现的日期格式
     * @return 解析出来的日期，如果没有匹配的返回null
     */
    public static Date parseDate(String inputDate, String[] patterns) {
        SimpleDateFormat df = new SimpleDateFormat();
        for(String pattern:patterns){
            df.applyPattern(pattern);
            df.setLenient(false);//设置解析日期格式是否严格解析日期
            ParsePosition pos = new ParsePosition(0);
            Date date = df.parse(inputDate, pos);
            if(date!=null){
                return date;
            }
        }
        return null;
    }




}
