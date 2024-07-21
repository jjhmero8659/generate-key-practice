package com.practice.duplicatekeythread.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ForkJoinWorkerThread;

@Configuration
public class ParallelStreamConfig {

//    @Bean
//    public ForkJoinPool forkJoinPool() {
//
//        // 커스텀 ForkJoinPool 생성 (스레드 수는 5로 설정)
//        ForkJoinPool customThreadPool = new ForkJoinPool(5, pool -> {
//            ForkJoinWorkerThread worker = ForkJoinPool.defaultForkJoinWorkerThreadFactory.newThread(pool);
//            worker.setName("Thread " + worker.getPoolIndex());
//            return worker;
//        },
//                null,
//                false
//        );
//
//        return customThreadPool;
//    }
}
