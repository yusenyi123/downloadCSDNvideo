package cn.edu.njau.webdemo.test.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.aop.interceptor.AsyncUncaughtExceptionHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.AsyncConfigurer;

import java.util.concurrent.Executor;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

@Configuration
@Slf4j
public class AsyncConfig implements AsyncConfigurer {

    @Override
    public Executor getAsyncExecutor() {
        return commonExecutor();
    }

    @Bean
    public Executor commonExecutor() {
        return new ThreadPoolExecutor(
                4,
                8,
                1,
                TimeUnit.MINUTES,
                new LinkedBlockingDeque<>(1024),
                new ThreadPoolExecutor.CallerRunsPolicy()
        );
    }

    @Override
    public AsyncUncaughtExceptionHandler getAsyncUncaughtExceptionHandler() {
        return (ex, method, params) -> log.error("线程池运行任务发生异常：" + ex.getMessage(), ex);
    }
}