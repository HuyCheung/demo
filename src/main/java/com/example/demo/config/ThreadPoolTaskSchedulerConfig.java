package com.example.demo.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.SchedulingConfigurer;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;
import org.springframework.scheduling.config.ScheduledTaskRegistrar;

import java.util.concurrent.ThreadPoolExecutor;

/**
 * 线程池任务配置
 * 线程池配置
 * <p>
 * 默认情况下，在创建了线程池后，线程池中的线程数为0，当有任务来之后，就会创建一个线程去执行任务，
 * 当线程池中的线程数目达到corePoolSize后，就会把到达的任务放到缓存队列当中；
 * 当队列满了，就继续创建线程，当线程数量大于等于maxPoolSize后，开始使用拒绝策略拒绝
 *
 * @author Huy Cheung
 * @date 2022/04/07
 */
@Slf4j
@Configuration
@EnableScheduling
public class ThreadPoolTaskSchedulerConfig implements SchedulingConfigurer {
    /**
     * 线程数
     */
    private static final int poolSize = 10;
    /**
     * 允许线程空闲时间（单位：默认为秒）
     */
    private static final int keepAliveTime = 60;
    /**
     * 线程名前缀
     */
    private static final String threadNamePrefix = "Scheduler-Executor-";


    @Override
    public void configureTasks(ScheduledTaskRegistrar taskRegistrar) {
        // 未自定义线程池，线程池没有界限，有出现 OOM 的风险
        // taskRegistrar.setScheduler(Executors.newScheduledThreadPool(5));

        // 自定义线程池
        taskRegistrar.setScheduler(taskExecutor());
    }


    /**
     * bean的名称，默认为首字母小写的方法名
     *
     * @return {@link ThreadPoolTaskExecutor}
     */
    @Bean
    public ThreadPoolTaskScheduler taskExecutor() {
        // 获取当前机器的核数 (核心线程数(默认线程数))
        ThreadPoolTaskScheduler scheduler = new ThreadPoolTaskScheduler();
        // 设置线程池大小
        scheduler.setPoolSize(poolSize);
        // 设置等待终止时间：秒
        scheduler.setAwaitTerminationSeconds(keepAliveTime);
        // 设置线程名称
        scheduler.setThreadNamePrefix(threadNamePrefix);
        // 进程结束前，等待线程队列中的任务执行完成
        scheduler.setWaitForTasksToCompleteOnShutdown(true);
        // 线程池对拒绝任务的处理策略
        // CallerRunsPolicy()：交由调用方线程运行，比如 main 线程。
        // AbortPolicy()：直接抛出异常。
        // DiscardPolicy()：直接丢弃。
        // DiscardOldestPolicy()：丢弃队列中最老的任务。
        scheduler.setRejectedExecutionHandler(new ThreadPoolExecutor.CallerRunsPolicy());
        // 7. 设置异常输出格式
        scheduler.setErrorHandler(throwable -> log.error("调度任务发生异常", throwable));
        log.info("@Schedule 业务处理线程配置成功，核心线程池：[{}]，程名称前缀：[{}]", poolSize, threadNamePrefix);
        return scheduler;
    }
}