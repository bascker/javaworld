package com.bascker.library.cglib;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Hello CGLIB
 *
 * @author bascker
 */
public class HelloCglib {

    private static final Logger LOG = LoggerFactory.getLogger(HelloCglib.class);

    public final String sayNo () {
        return "nope, i don't like you";
    }

    public String sayWhat () {
        return "What a beautiful girl";
    }

    @Override
    public String toString() {
        return "Hello CGLIB";
    }
}
