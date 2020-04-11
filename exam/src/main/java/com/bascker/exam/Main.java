package com.bascker.exam;

import com.bascker.bsutil.bean.Person;
import com.bascker.exam.base.MyThread;

import java.util.concurrent.TimeUnit;

public class Main {

    public static void main(String[] args) throws InterruptedException {
        int i = 0;
        while (true) {
            Person p = new Person();
            p.setAge(10);
            p.setName("i" + i);

            TimeUnit.SECONDS.sleep(2);
        }
    }

}
