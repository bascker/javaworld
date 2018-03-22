package com.bascker.general.concurrent;

import com.bascker.bsutil.Sample;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Random;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.stream.IntStream;

/**
 * Barrier: 栅栏, 模拟公司出游活动, 所有同事全部达到指定地点后，才开车出发
 *
 * @author bascker
 */
public class BarrierSample implements Sample {

    private static final Logger LOG = LoggerFactory.getLogger(BarrierSample.class);
    private final CyclicBarrier mArriveBarrier;
    private final CyclicBarrier mAboardBarrier;
    private final String[] mNames;

    public static void main(String[] args) {
        final BarrierSample sample = new BarrierSample(new String[]{"bascker", "jerry", "john", "paul", "lisa", "jessica"});
        sample.start();
    }

    public BarrierSample (final String[] names) {
        mNames = names;
        mArriveBarrier = new CyclicBarrier(mNames.length, () -> LOG.info("所有同事已到达，大家快上车，准备开车了!"));
        mAboardBarrier = new CyclicBarrier(mNames.length, () -> LOG.info("所有同事已上车，发车出游罗~"));
    }

    /**
     * 上车动作
     */
    class Aboard implements Runnable {

        private final String mName;

        public Aboard (final String name) {
            mName = name;
        }

        @Override
        public void run() {
            try {
                final Random random = new Random();
                final int costTime = random.nextInt(3) + 1;
                Thread.sleep(costTime * 1000);
                LOG.info(mName + "\t花了" + (costTime * 10) +  "分钟到达出发点");
                mArriveBarrier.await();

                Thread.sleep(costTime * 1000);
                LOG.info(mName + "\t已上车");
                mAboardBarrier.await();

            } catch (InterruptedException | BrokenBarrierException e) {
                LOG.error("Aboard.run", e);
            }
        }
    }

    /**
     * 出发
     */
    @Override
    public void start (final Object... args) {
        IntStream.range(0, mNames.length).forEach(i -> new Thread(new Aboard(mNames[i])).start());
    }

}
