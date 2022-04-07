package com.example.demo.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.aop.interceptor.AsyncUncaughtExceptionHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.concurrent.Executor;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * "@Async" 注解 异步任务自定义线程池装配类
 * <p>
 * 在需要使用异步线程的方法上添加 @Async(value = "自定义ThreadPoolTaskExecutor的 Bean 的方法名")
 *
 * @author Huy Cheung
 * @date 2022/04/07
 */
@Slf4j
@EnableAsync
@Configuration
public class AsyncThreadPoolExecutorConfig {
    /**
     * 允许线程空闲时间（单位：默认为秒）
     */
    private static final int keepAliveTime = 60;
    /**
     * 队列容量
     */
    private static final int queueCapacity = 50;
    /**
     * 线程名前缀
     */
    private static final String threadNamePrefix = "Async-Executor-";

    @Bean
    public Executor asyncExecutor() {
        int cpuNum = Runtime.getRuntime().availableProcessors();

        // 1. 实例化异步任务线程池
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        // 2. 设置核心线程池大小
        executor.setCorePoolSize(cpuNum);
        // 3. 设置最大线程数
        executor.setMaxPoolSize(cpuNum * 2);
        // 4. 设置线程等待队列大小
        executor.setQueueCapacity(queueCapacity);
        // 5. 设置活跃时间：秒
        executor.setKeepAliveSeconds(keepAliveTime);
        // 6. 设置线程名字前缀
        executor.setThreadNamePrefix(threadNamePrefix);
        // setRejectedExecutionHandler：当线程池已经达到 max size 的时候，如何处理新任务
        // AbortPolicy          默认的拒绝策略，会 throw RejectedExecutionException 拒绝
        // CallerRunsPolicy     提交任务的主线程自己去执行该任务
        // DiscardOldestPolicy  丢弃最老的任务，其实就是把最早进入工作队列的任务丢弃，然后把新任务加入到工作队列
        // DiscardPolicy        相当大胆的策略，直接丢弃任务，没有任何异常抛出
        executor.setRejectedExecutionHandler(new ThreadPoolExecutor.CallerRunsPolicy());
        // 7. 设置等待终止时间：秒
        executor.setAwaitTerminationSeconds(keepAliveTime);
        // 8. 进程结束前，等待线程队列中的任务执行完成
        executor.setWaitForTasksToCompleteOnShutdown(true);
        // 9. 手动初始化线程池
        executor.initialize();
        log.info("@Async 业务处理线程配置成功，核心线程池：[{}]，最大线程池：[{}]，队列容量：[{}]，线程名称前缀：[{}]", cpuNum, cpuNum * 2, queueCapacity, threadNamePrefix);
        return executor;
    }

    @Bean
    public AsyncUncaughtExceptionHandler getAsyncUncaughtExceptionHandler() {
        return (throwable, method, objects) -> {
            log.error("@Async 业务 ----> 异常精简信息：[{}]，异常Throwable：{}", throwable.getMessage(), throwable);
            log.error("@Async 业务 ----> 触发异常的方法名称：{}", method.getName());
        };
    }
}