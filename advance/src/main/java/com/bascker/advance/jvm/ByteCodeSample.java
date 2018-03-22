package com.bascker.advance.jvm;

import com.bascker.bsutil.Sample;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.atomic.AtomicLong;

/**
 * ByteCodeSample: use to analysis jvm binary code
 * 1.Step
 *  1.1 execute command `javac -g ByteCodeSample.java` to create a ByteCodeSample.class
 *  1.2 execute command `javap -s -l -v -c -p ByteCodeSample > ByteCodeSample.byte` to decompile class file
 */
public class ByteCodeSample implements Sample {

    private static final Logger LOG = LoggerFactory.getLogger(ByteCodeSample.class);
    private static final String NAME = "ByteCodeSample";
    private static int a = 0;
    private final boolean b = true;
    private int c = 5;
    private AtomicLong mAtomicLong = new AtomicLong(6);

    public static void main(String[] args) {
        final ByteCodeSample sample = new ByteCodeSample();
        sample.start();
    }

    @Override
    public void start(final Object... args) {
        LOG.info("a ++, a = " + (a ++));
        a = 0;
        LOG.info("++ a, a =" + (++ a));

        ByteCodeSample.showName();
        showAtomicLong();

        if (b) {
            LOG.info("b = " + b + ", c = " + c);
        }
    }

    public static void showName () {
        System.out.println(NAME);
    }

    public final void showAtomicLong () {
        System.out.println(getAtomicLong());
    }

    public long getAtomicLong() {
        return mAtomicLong.getAndIncrement();
    }
}
