package com.bascker.advance.jvm;

import java.util.concurrent.atomic.AtomicLong;

/**
 * ByteCodeSample: use to analysis jvm binary code
 * 1.Step
 *  1.1 execute command `javac -g ByteCodeSample.java` to create a ByteCodeSample.class
 *  1.2 execute command `javap -s -l -v -c -p ByteCodeSample > ByteCodeSample.byte` to decompile class file
 */
public class ByteCodeSample {

    private static final String NAME = "ByteCodeSample";
    private static int a = 0;
    private final boolean b = true;
    private int c = 5;
    private AtomicLong mAtomicLong = new AtomicLong(6);

    public static void showName () {
        System.out.println(NAME);
    }

    public final void showAtomicLong () {
        System.out.println(getAtomicLong());
    }

    public long getAtomicLong() {
        return mAtomicLong.getAndIncrement();
    }

    public static void main(String[] args) {
        System.out.println("a ++, a = " + (a ++));
        a = 0;
        System.out.println("++ a, a =" + (++ a));

        ByteCodeSample.showName();
        ByteCodeSample sample = new ByteCodeSample();
        sample.showAtomicLong();

        if (sample.b) {
            System.out.println("b = " + sample.b + ", c = " + sample.c);
        }
    }

}
