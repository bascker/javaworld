package com.bascker.base;

import com.bascker.bsutil.Sample;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Java 对象创建时的执行顺序:
 * 1.父类和子类的 static 代码块
 * 2.初始化父类成员变量
 * 3.执行父类的普通代码块
 * 4.执行父类的构造函数
 * 5.初始化子类成员变量
 * 6.执行子类的普通代码块
 * 7.执行子类的构造函数
 *
 * @author bascker
 */
public class OrderOfCreateObjSample implements Sample {

    private static final Logger LOG = LoggerFactory.getLogger(OrderOfCreateObjSample.class);

    public static void main(String[] args) {
        final OrderOfCreateObjSample sample = new OrderOfCreateObjSample();
        sample.start();
    }

    @Override
    public void start(final Object... args) {
        final Object obj = new Son();
        ((Parent) obj).show();
    }

    static class Parent {
        private static int count = 1;

        static {
            LOG.info("Parent StaticBlock, count = " + count);
        }

        {
            count++;
            LOG.info("Parent Block, count ++, count = " + count);
        }

        public Parent() {
            LOG.info("Parent Constructor, count = " + count);
        }

        public void show() {
            LOG.info("Parent.show, count = " + count);
        }

    }

    static class Son extends Parent {
        static {
            // super 关键字与 static 冲突
            LOG.info("Son StaticBlock");
        }

        {
            LOG.info("Son Block, super.count = " + super.count);
        }

        public Son() {
            LOG.info("Son Constructor, super.count = " + super.count);
        }

        @Override
        public void show() {
            LOG.info("Son.show, super.count = " + super.count);
        }
    }

}
