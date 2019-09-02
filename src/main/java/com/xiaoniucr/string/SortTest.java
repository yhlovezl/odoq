package com.xiaoniucr.string;

import org.thymeleaf.expression.Lists;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class SortTest {

    public static void main(String[] args) {


        List list = new ArrayList();
        list.add("1.1");
        list.add("1.3");
        list.add("1.2");

        list.sort(Comparator.comparing(String :: valueOf));

        list.forEach(item -> System.out.println(item));


        Integer a =123;

        Integer b = 123;
        System.out.println(a == b);
    }
}
