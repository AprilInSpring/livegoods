package cc;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class BuyTimeApplication {
    public static void main(String[] args) {
        SpringApplication.run(BuyTimeApplication.class,args);
    }
}