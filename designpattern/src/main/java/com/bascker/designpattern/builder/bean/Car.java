package com.bascker.designpattern.builder.bean;

import java.util.List;

/**
 * Car: 抽象产品类，通常是实现了模板方法模式
 *
 * @author bascker
 */
public abstract class Car {

    // 车辆各动作执行顺序
    private List<String> mSequence;

    public Car(final List<String> sequence) {
        mSequence = sequence;
    }

    /**
     * 发车
     */
    protected abstract void start();

    /**
     * 停车
     */
    protected abstract void stop();

    /**
     * 鸣笛
     */
    protected abstract void alarm();

    /**
     * 熄火
     */
    protected abstract void engineBoom();

    final public void run(){
        mSequence.stream().forEach(action -> {
            switch (action) {
                case "start":
                    start();
                    break;
                case "stop":
                    stop();
                    break;
                case "alarm":
                    alarm();
                    break;
                case "engineBoom":
                    engineBoom();
                    break;
            }
        });
    }



}