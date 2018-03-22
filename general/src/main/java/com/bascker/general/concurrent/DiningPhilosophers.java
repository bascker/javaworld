package com.bascker.general.concurrent;

import com.bascker.bsutil.Sample;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 哲学家就餐问题案例
 *
 * 1.哲学家就餐:
 *  1.1 描述: 一圆桌前坐着5位哲学家，两个人中间有一只筷子，桌子中央有面条。哲学家思考问题，当饿了的时候拿起左右两只筷子吃饭，
 *           必须拿到两只筷子才能吃饭。
 *  1.2 问题: 当5个哲学家都拿起自己右手边的筷子，准备拿左手边的筷子时产生死锁现象
 *
 * 2.解决:
 *  2.1 每个哲学家必须确定自己左右手的筷子都可用的时候，才能同时拿起两只筷子进餐，吃完之后同时放下两只筷子
 *  2.2 实现: 利用 Object 的 wait() + notifyAll() 方法
 *
 * @author bascker
 */
public class DiningPhilosophers implements Sample {

    private static final Logger LOG = LoggerFactory.getLogger(DiningPhilosophers.class);

    public DiningPhilosophers () {}

    public static void main(String[] args) {
        final DiningPhilosophers sample = new DiningPhilosophers();
        sample.start();
    }

    @Override
    public void start (final Object... args) {
        final Fork fork = new Fork();
        // 所有哲学家线程，都是操作 fork 对象的锁
        new Philosopher("0", fork).start();
        new Philosopher("1", fork).start();
        new Philosopher("2", fork).start();
        new Philosopher("3", fork).start();
        new Philosopher("4", fork).start();
    }

    /**
     * 筷子
     */
    class Fork {
        // 5 只筷子
        private boolean[] used = {false, false, false, false, false};

        /**
         * 只有当左右手的筷子都未被使用时，才允许获取筷子，且必须同时获取左右手筷子
         */
        public synchronized void take () {
            final String name = Thread.currentThread().getName();
            final int i = Integer.parseInt(name);

            while (used[i] || used[(i + 1) % 5]) {
                try {
                    // 若其左右手的筷子有人在使用，则等待
                    wait();
                } catch (InterruptedException e) {
                    LOG.error("Fork.take", e);
                }
            }

            // 获取左手筷子
            used[i] = true;
            // 获取右手筷子
            used[(i + 1) % 5] = true;
        }

        /**
         * 放下筷子
         */
        public synchronized void put () {
            final String name = Thread.currentThread().getName();
            final int i = Integer.parseInt(name);

            // 放下左手筷子
            used[i] = false;
            // 放下右手筷子
            used[(i + 1) % 5] = false;

            // 唤醒其他等待筷子的哲学家
            notifyAll();
        }
    }

    /**
     * 哲学家
     */
    class Philosopher extends Thread {

        private String mName;
        private Fork mFork;

        public Philosopher (String name, Fork fork) {
            super(name);
            mName = name;
            mFork = fork;
        }

        public void eating () {
            LOG.info(mName + ": I'm eating");
            try {
                sleep(1000);
            } catch (InterruptedException e) {
                LOG.error("Philosopher.eating", e);
            }
        }

        public void thinking () {
            LOG.info(mName + ": I'm thinking");
            try {
                sleep(1000);
            } catch (InterruptedException e) {
                LOG.error("Philosopher.thinking", e);
            }
        }

        @Override
        public void run() {
            while (true) {
                thinking();
                mFork.take();
                eating();
                mFork.put();
            }
        }
    }

}
