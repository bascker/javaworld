package com.bascker.library.curator;

import com.bascker.bsutil.PropertiesUtils;
import com.bascker.bsutil.Sample;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.retry.RetryNTimes;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Curator Client 操作，对应 zkCli.sh 命令行操作
 *
 * @author bascker
 */
public class ZkClientSample implements Sample {

    private static final Logger LOGGER = LoggerFactory.getLogger(ZkClientSample.class);

    private static final String ZK_ADDRS = PropertiesUtils.load("zk.properties").getProperty("zookeeper.ips");
    private static final String ZK_PATH = "/curator";

    public static void main(String[] args) {
        final ZkClientSample sample = new ZkClientSample();
        sample.start();
    }

    @Override
    public void start(final Object... args) {
        // 1. connect to zk
        final CuratorFramework zkClient = CuratorFrameworkFactory.newClient(ZK_ADDRS, new RetryNTimes(10, 3000));
        zkClient.start();
        LOGGER.info("Connect to zookeeper {}", ZK_ADDRS);

        try {
            // 2.create znode
            LOGGER.info("$ create /curator/hello");
            zkClient.create().creatingParentsIfNeeded().forPath(ZK_PATH, "hello".getBytes());

            // 3.get znode and data
            LOGGER.info("$ ls /");
            LOGGER.info(zkClient.getChildren().forPath("/").toString());

            LOGGER.info("$ get {}", ZK_PATH);
            LOGGER.info(new String(zkClient.getData().forPath(ZK_PATH)));

            // 4.remove znode
            LOGGER.info("$ delete {}", ZK_PATH);
            zkClient.delete().forPath(ZK_PATH);

            LOGGER.info("$ ls /");
            LOGGER.info(zkClient.getChildren().forPath("/").toString());
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

}
