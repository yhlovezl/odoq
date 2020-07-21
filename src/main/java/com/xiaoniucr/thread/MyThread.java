package com.xiaoniucr.thread;

/**
 * @author yanghl
 * @date 2020/7/21 10:59
 */
public class MyThread extends Thread{

    private int ticket = 10;

    @Override
    public void run() {
        for(int i = 0; i < 20;i ++){
            if(this.ticket>0){
                System.out.println(this.getName()+" 卖票：ticket"+this.ticket--);
            }
        }
    }
}
