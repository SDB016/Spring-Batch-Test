package com.batchtest.job;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Slf4j
@RequiredArgsConstructor
@Configuration
public class SimpleJobConfiguration {

    private final JobBuilderFactory jobBuilderFactory;
    private final StepBuilderFactory stepBuilderFactory;

    @Bean
    public Job simpleJob() {
        return jobBuilderFactory.get("simpleJob") // "simpleJob"이란 이름의 Batch Job 을 생성
                .start(simpleStep1())
                .build();
    }

    @Bean
    public Step simpleStep1() {
        return stepBuilderFactory.get("simpleStep1") // "simpleStep1" 이란 이름의 Batch Step 을 생성
                .tasklet((contribution, chunkContext) -> { // step 안에서 수행될 기능들을 명시, step 안에서 단일로 수행될 커스텀 기능 선언
                    log.info(">>>> This is Step1");
                    return RepeatStatus.FINISHED;
                })
                .build();
    }


}
