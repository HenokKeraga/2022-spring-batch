package com.woinahen.helloworld;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class Config {
    @Autowired
    JobBuilderFactory jobBuilderFactory;
    @Autowired
    StepBuilderFactory stepBuilderFactory;

    @Bean
    public Step step(){
        return  stepBuilderFactory.get("step1")
                .tasklet(hellowTasklet())
                .build();
    }

    private Tasklet hellowTasklet() {

        return new Tasklet() {
            @Override
            public RepeatStatus execute(StepContribution contribution, ChunkContext chunkContext) throws Exception {
                System.out.println("hello world");
                return RepeatStatus.FINISHED;
            }
        };
    }

    @Bean
    public Job job(){
        return  jobBuilderFactory.get("job1")
                .start(step())
                .build();
    }
}
