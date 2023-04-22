package cc;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
@Slf4j
public class BannerApplication {
    public static void main(String[] args) {
        SpringApplication.run(BannerApplication.class,args);
        log.info("*****************************************");
        log.info("***********Banner服务启动成功**************");
        log.info("*****************************************");
    }
}