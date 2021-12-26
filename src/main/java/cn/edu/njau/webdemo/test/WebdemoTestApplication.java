package cn.edu.njau.webdemo.test;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

@EnableAsync
@SpringBootApplication
public class WebdemoTestApplication {

    public static void main(String[] args) {
        SpringApplication.run(WebdemoTestApplication.class, args);
    }

}
