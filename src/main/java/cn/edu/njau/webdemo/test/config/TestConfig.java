package cn.edu.njau.webdemo.test.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;

@Configuration
public class TestConfig implements CommandLineRunner {

    @Value("${ffmpeg.dir}")
    private String ffmpegDir;

    @Override
    public void run(String... args) throws Exception {
        System.out.println(ffmpegDir);
    }

}
