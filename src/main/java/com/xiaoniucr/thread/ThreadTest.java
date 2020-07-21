package com.xiaoniucr.thread;

/**
 * @author yanghl
 * @date 2020/7/21 11:05
 */
public class ThreadTest {

    public static void main(String[] args) {
        MyThread t1 = new MyThread();
        MyThread t2 = new MyThread();
        MyThread t3 = new MyThread();
        MyThread t4 = new MyThread();
        MyThread t5 = new MyThread();
        MyThread t6 = new MyThread();
        t1.start();
        t2.start();
        t3.start();
        t4.start();
        t5.start();
        t6.start();
    }
}
