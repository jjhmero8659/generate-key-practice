package com.practice.duplicatekeythread.service;

import com.practice.duplicatekeythread.config.BasicConfig;
import com.practice.duplicatekeythread.config.ParallelStreamConfig;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.test.context.ContextConfiguration;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ForkJoinPool;
import java.util.stream.IntStream;

import static org.junit.jupiter.api.Assertions.*;
import static org.assertj.core.api.Assertions.assertThat;
@ExtendWith(MockitoExtension.class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS) //@BeforeAll 을 Static 으로 선언하지 않기 위함
class DuplicateKeyServiceTest {

//    private ForkJoinPool forkJoinPool;

    @BeforeAll
    void setup() {
        MockitoAnnotations.openMocks(this);
//        AnnotationConfigApplicationContext annotationConfigApplicationContext = new AnnotationConfigApplicationContext(ParallelStreamConfig.class);
//        this.forkJoinPool = annotationConfigApplicationContext.getBean(ForkJoinPool.class);
    }


    @Test
    void 멀티스레드_작동_테스트() {
        //given
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.SSS");
        Integer makeNum = 10000;

        //when
        IntStream.range(0, makeNum).parallel().forEach(
                data -> {
                    String currentTime = LocalDateTime.now().format(formatter);
                    System.out.println("현재 시간: " + currentTime + " - " + Thread.currentThread().getName());
                });

        //then
    }


    @Test
    void 멀티스레드_랜덤_UUID_중복검사_HASH() { //
        //given
        Integer makeNum = 10000;
        String initStr = "jjh-";
        Set<String> set = new HashSet<>();

        //when
        for (int i=0; i<makeNum; i++){
            IntStream.range(0, makeNum).parallel().forEach(
                    data -> {
                        set.add(initStr + UUID.randomUUID());
                    });

            //then
            assertThat(makeNum).isEqualTo(set.size());
        }

    }

    @Test
    void 멀티스레드_랜덤_UUID_중복검사_Concurrent() {
        //given
        Integer makeNum = 10000;
        String initStr = "jjh-";
        Set<String> set = ConcurrentHashMap.newKeySet();

        //when
        for (int i=0; i<makeNum; i++){
            IntStream.range(0, makeNum).parallel().forEach(
                    data -> {
                        set.add(initStr + UUID.randomUUID());
                    });

            //then
            assertThat(makeNum).isEqualTo(set.size());

            set.clear();
        }
    }
}