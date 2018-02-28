package kafapp.zk;


import org.apache.zookeeper.*;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.concurrent.CountDownLatch;


public class ZKService {

    // Method to connect zookeeper ensemble.
    public static ZooKeeper connect(String host, final CountDownLatch connectedSignal) throws IOException,InterruptedException {
        ZooKeeper zoo = new ZooKeeper(host,5000,new Watcher() {

            public void process(WatchedEvent we) {

                if (we.getState() == Event.KeeperState.SyncConnected) {
                    connectedSignal.countDown();
                }
            }
        });
        connectedSignal.await();
        return zoo;
    }

    // Method to disconnect from zookeeper server
    public static void close(ZooKeeper zk) throws InterruptedException {
        zk.close();
    }

    // Method to create znode in zookeeper ensemble
    public static void create(ZooKeeper zk, String path, byte[] data) throws KeeperException,InterruptedException {
        zk.create(path, data, ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT);
    }

}
