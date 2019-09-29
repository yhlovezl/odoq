package com.xiaoniucr.test.list;

import java.util.HashSet;
import java.util.Set;

/**
 * @author yanghl
 * @date 2019/9/26 15:19
 */
public class SetTest {

    public static void main(String[] args) {


        Set<String> set = new HashSet<>();
        set.add("111");
        set.add("222");
        set.add("333");

        Set<String> comm = new HashSet<>();
        comm.add("111");
        set.remove(comm);

        set.forEach(s -> System.out.println(s));
    }
}
