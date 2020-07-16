package com.xiaoniucr.test.list;

import com.xiaoniucr.entity.Person;

import java.util.ArrayList;
import java.util.List;

/**
 * @author yanghl
 * @date 2019/12/11 9:37
 */
public class ListTest {

    public static void main(String[] args) {

        Person p1 = new Person();
        p1.setName("1");
        p1.setPwd("2");

        Person p2 = new Person();
        p2.setName("2");
        p2.setPwd("2");

        Person p3 = new Person();
        p3.setName("3");
        p3.setPwd("2");

        List<Person> list1 = new ArrayList<>();
        list1.add(p1);
        list1.add(p2);
        list1.add(p3);

        List<Person> list2 = new ArrayList<>();
        list2.add(p1);

        list1.removeAll(list2);

        System.out.println(list1.size());
    }
}
