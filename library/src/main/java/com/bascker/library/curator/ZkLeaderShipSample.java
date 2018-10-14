package com.bascker.library.curator;

import com.bascker.bsutil.PropertiesUtils;
import com.bascker.bsutil.Sample;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.framework.recipes.leader.LeaderSelector;
import org.apache.curator.framework.recipes.leader.LeaderSelectorListener;
import org.apache.curator.framework.state.ConnectionState;
import org.apache.curator.retry.RetryNTimes;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Zk 的 Leader 选举操作, 利用 ZK 可以实现服务集群的选主。Curator 提供 LeaderSelector 监听器实现 Leader 选举，
 * 同一时刻只有一个 Listener 才能进入 takeLeaderShip()。当该 Listener 从 takeLeaderShip() 退出，即某一节点
 * 故障时，将重新选举 Leader。
 *
 * @author bascker
 */
public class ZkLeaderShipSample implements Sample {

    private static final Logger LOGGER = LoggerFactory.getLogger(ZkLeaderShipSample.class);

    private static final String ZK_ADDRS = PropertiesUtils.load("zk.properties").getProperty("zookeeper.ips");
    private static final String ZK_PATH = "/curator";


    public static void main(String[] args) {
        final ZkLeaderShipSample sample = new ZkLeaderShipSample();
        sample.start();
    }


    @Override
    public void start(final Object... args) {
        final LeaderSelectorListener listener = getLeaderSelectorListener();
        final Runnable action = () -> registerListener(listener);

        new Thread(action, "th1").start();
        new Thread(action, "th2").start();
        new Thread(action, "th3").start();

        try {
            Thread.sleep(Integer.MAX_VALUE);
        } catch (InterruptedException e) {
            LOGGER.error(e.getMessage());
        }
    }

    private LeaderSelectorListener getLeaderSelectorListener() {
        return new LeaderSelectorListener() {

            /**
             * 同一时刻仅有一个 Listener 进入
             * @param curatorFramework
             * @throws Exception
             */
            @Override
            public void takeLeadership(final CuratorFramework curatorFramework) throws Exception {
                LOGGER.info("{} takeLeaderShip", Thread.currentThread().getName());

                Thread.sleep(2000);
                LOGGER.info("{} relinquish takeLeaderShip", Thread.currentThread().getName());
            }

            @Override
            public void stateChanged(final CuratorFramework curatorFramework, final ConnectionState connectionState) {
            }
        };
    }

    private void registerListener(final LeaderSelectorListener listener) {
        final CuratorFramework zkClient = CuratorFrameworkFactory.newClient(ZK_ADDRS, new RetryNTimes(10, 3000));
        zkClient.start();

        // 确保节点存在
        zkClient.create().creatingParentsIfNeeded();

        final LeaderSelector leaderSelector = new LeaderSelector(zkClient, ZK_PATH, listener);
        leaderSelector.autoRequeue();
        leaderSelector.start();
    }

}
