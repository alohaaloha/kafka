package kafapp;

import kafapp.zk.ZKService;
import org.apache.zookeeper.ZooKeeper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.concurrent.CountDownLatch;

@SpringBootApplication
public class AppStartup {

    // declare zookeeper instance to access ZooKeeper ensemble
    private static ZooKeeper zk;
    static final CountDownLatch connectedSignal = new CountDownLatch(1);

    public static void main(String[] args) {

        SpringApplication.run(AppStartup.class, args);

        System.out.println("------------------------------------------------------");
        System.out.println("-----------------------STARTED------------------------");
        System.out.println("------------------------------------------------------");

        // znode path
        String path = "/MySecondZnode"; // Assign path to znode

        // data in byte array
        byte[] data = "My first zookeeper app222".getBytes(); //Declare data

        try {
            zk = ZKService.connect("localhost", connectedSignal);
            ZKService.create(zk, path, data); // Create the data to the specified path
            ZKService.close(zk);
        } catch (Exception e) {
            System.out.println(e.getMessage()); //Catch error message
        }

    }

}
