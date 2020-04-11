package com.bascker.exam.base;

public class MyThread extends Thread {

    @Override
    public void run() {
        System.out.println("Thread");
    }

    public static void main(String[] args) {
        MyThread t1 = new MyThread();
        MyThread t2 = new MyThread();

        // one two Thread Thread
        // 主线程先执行, 子线程后执行: 一般主线程会先抢到资源，主线程运行后，遇到start开启多线程。子线程交替执行
        t1.start();
        System.out.println("one");
        t2.start();
        System.out.println("two");
    }

}
