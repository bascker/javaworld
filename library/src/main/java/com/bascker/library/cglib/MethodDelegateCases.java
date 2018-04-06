package com.bascker.library.cglib;

import net.sf.cglib.reflect.MethodDelegate;
import org.junit.Assert;
import org.junit.Test;

/**
 * MethodDelegate Cases
 *
 * 1.MethodDelegate
 *  1.1 主要用来对方法进行代理
 *
 * @author bascker
 */
public class MethodDelegateCases {

    interface MainDelegate {
        String hello ();
    }

    @Test
    public void base () {
        final HelloCglib helloCglib = new HelloCglib();
        final MainDelegate delegate = (MainDelegate) MethodDelegate.create(helloCglib, "sayWhat", MainDelegate.class);
        Assert.assertEquals("What a beautiful girl", delegate.hello());
    }

}
