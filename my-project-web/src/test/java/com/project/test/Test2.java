package com.project.test;

import java.time.LocalDate;

/**
 * Created by qiaowentao on 2017/5/8.
 */
public class Test2 implements Runnable {

    @Override
    public void run() {
        System.out.println(Thread.currentThread().getName()+"开始执行");
        for(int i=0; i<5; i++){
            System.out.println(Thread.currentThread().getName()+"开始执行到："+i);
        }
    }

    public static void main(String[] args) {
        /*Test2 test2 = new Test2();
        Thread thread1 = new Thread(test2,"线程1");
        Thread thread2 = new Thread(test2,"线程2");
        thread1.start();
        thread2.start();*/

        LocalDate localDate = LocalDate.now();
        System.out.println(localDate);

        Runnable runnable = () -> System.out.println("匿名内部类");

        new Thread(runnable).start();

        new Thread(() -> System.out.println("lambda 表达式")).start();

    }

}
