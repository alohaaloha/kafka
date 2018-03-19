package kafapp.zookeeper;


import org.apache.zookeeper.*;

import java.io.IOException;
import java.util.concurrent.CountDownLatch;


public class ZKManager {

    // declare zookeeper instance to access ZooKeeper ensemble
    private static ZooKeeper zk;

    private static final CountDownLatch connectedSignal = new CountDownLatch(1);

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

    // test
    public static void testZK(){
        // znode path
        String path = "/MyNode"; // Assign path to znode

        // data in byte array
        byte[] data = "My Data In a Node".getBytes(); //Declare data

        // conn & create & close
        try {
            zk = ZKManager.connect("localhost", connectedSignal);
            ZKManager.create(zk, path, data); // Create the data to the specified path
            ZKManager.close(zk);
        } catch (Exception e) {
            System.out.println(e.getMessage()); //Catch error message
        }
    }

}
