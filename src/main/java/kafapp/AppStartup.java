package kafapp;

import kafapp.zk.ZKManager;
import org.apache.zookeeper.ZooKeeper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.concurrent.CountDownLatch;

@SpringBootApplication
public class AppStartup {

    public static void main(String[] args) {

        SpringApplication.run(AppStartup.class, args);

        System.out.println("------------------------------------------------------");
        System.out.println("-----------------------STARTED------------------------");
        System.out.println("------------------------------------------------------");

        ZKManager.testZK();
    }

}
