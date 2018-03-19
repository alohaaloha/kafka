package kafapp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class AppStartup {

    public static void main(String[] args) {

        SpringApplication.run(AppStartup.class, args);

        System.out.println("------------------------------------------------------");
        System.out.println("-----------------------STARTED------------------------");
        System.out.println("------------------------------------------------------");

        //zookeeper test
        //ZKManager.testZK();

    }

}
