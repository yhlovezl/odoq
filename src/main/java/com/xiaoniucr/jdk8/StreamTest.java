package com.xiaoniucr.jdk8;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class StreamTest {

    public static void main(String[] args) {

        List<String> list = new ArrayList<String>();
        list.add("张三");
        list.add("李四");
        list.add("王五");
        //按照条件进行过滤
        List<String> listofnew = list.stream().filter(item -> "张三".equals(item)).collect(Collectors.toList());
//        listofnew.forEach(item -> System.out.println(item));

        List<Cat> catList = new ArrayList<>();
        catList.add(new Cat("tom",20,10));
        catList.add(new Cat("dom",18,15));
        catList.add(new Cat("lucy",21,11));
        //对象按照某个属性字段进行排序
        catList.sort(Comparator.comparing(Cat :: getHeight));
        //倒叙
        catList.sort(Comparator.comparing(Cat :: getWeight).reversed());
        //升序
        List<Cat> sortList = catList.stream().sorted((s1,s2) -> s1.getWeight().compareTo(s2.getHeight())).collect(Collectors.toList());
//        sortList.forEach(c -> System.out.println(c.getWeight()));

        //多条件排序
        catList.sort(Comparator.comparing(Cat :: getWeight).thenComparing(Cat :: getHeight));
        catList.forEach(cat -> System.out.println(cat.getName()));

        //对象属性值转大写
        List<Cat> catofnew =  catList.stream().map(cat -> new Cat(cat.getName().toUpperCase(),cat.getHeight(),cat.getWeight())).collect(Collectors.toList());
        //过滤体重字段并返回
        List<Integer> weights = catofnew.stream().map(Cat :: getWeight).collect(Collectors.toList());
        //lambda迭代
//        catofnew.forEach(cat -> System.out.println(cat.getName()));


    }
}
