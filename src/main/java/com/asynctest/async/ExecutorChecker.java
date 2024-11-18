package com.asynctest.async;

import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.stereotype.Component;
import java.util.concurrent.Executor;

@Component
public class ExecutorChecker {

    @Autowired
    @Qualifier("taskExecutor")
    private Executor executor;

    @PostConstruct
    public void checkExecutor() {
        System.out.println("Executor class: " + executor.getClass().getName());

        if (executor instanceof ThreadPoolTaskExecutor) {
            ThreadPoolTaskExecutor taskExecutor = (ThreadPoolTaskExecutor) executor;
            System.out.println("Core pool size: " + taskExecutor.getCorePoolSize());
            System.out.println("Max pool size: " + taskExecutor.getMaxPoolSize());
            System.out.println("Queue capacity: " + taskExecutor.getQueueCapacity());
        }
    }
}
