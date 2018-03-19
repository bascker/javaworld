package com.bascker.advance.jvm;

import com.bascker.bsutil.bean.Person;

/**
 * Escape Analysis: 逃逸分析
 *
 * 1.判断对象的作用域是否有可能逃逸出函数体
 *  1.1 若没有，则JVM可能采取栈上分配技术，将对象分配在栈上，以提高系统性能
 *
 * 2.在 Server 模式下，才可以启用逃逸分析，使用 -server 标记开启 server 模式
 * 3.-XX:+DoEscapeAnalysis: 启用逃逸分析技术
 * 4.-XX:+EliminateAllocations: 开启标量替换(默认打开)，允许对象打散分配在栈上
 * 5.运行时加上配置: -server -Xmx10m -Xms10m -XX:+DoEscapeAnalysis -XX:+PrintGC -XX:+EliminateAllocations
 *
 * @see
 * @author bascker
 */
public class EscapeAnalysis {

    private static Person mPerson;

    public static void escape () {
        // 由于对象 mPerson 是类变量，可被任何线程访问，因此属于逃逸对象，会在堆上进行分配
        mPerson = new Person();
        initPerson(mPerson);
    }

    public static void noEscape () {
        // person 以局部变量的形式存在，且没有被 noEscape() 返回或任何形式公开，因此它未发生逃逸
        // person 可能就会被 JVM 进行栈上分配
        final Person person = new Person();
        initPerson(person);
    }

    private static void initPerson (final Person person) {
        person.setName("bascker");
    }

    public static void main(String[] args) {
        for (int i = 0; i < 50; i ++) {
            noEscape();
            escape();
        }
    }

}
