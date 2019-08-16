package com.example.demo.job;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.JobScope;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor //생성자 di
@Configuration
public class SimpleJobConfiguration2 {

	private final JobBuilderFactory jobBuilderFactory;
	private final StepBuilderFactory stepBuilderFactory;
	
	@Bean
	public Job simpleJob2() {
		return jobBuilderFactory.get("simpleJob2")
				.start(simple2Step1())
				.next(simple2Step2())
				.build();
	}
	
	@Bean
	@JobScope
	public Step simple2Step1() {
		return stepBuilderFactory.get("simpleStep1")
				.tasklet((contribution, chunkContext) -> {
					log.info(">>>>> 스프링 배치 작업을 시작합니다. 2Step1");
					return RepeatStatus.FINISHED;
					//log.info("requestDate={}"+requestDate, requestDate);
					//log.info("예외를 발생시킵니다.");
					//throw new IllegalArgumentException("step1에서 실패합니다.");
				}).build();
	}
	
	@Bean
	@JobScope
	public Step simple2Step2() {
		return stepBuilderFactory.get("simpleStep2")
				.tasklet((contribution, chunkContext) -> {
					log.info(">>>>> 스프링 배치 작업을 시작합니다. 2Step2");
					//log.info("requestDate={}"+requestDate, requestDate);
					return RepeatStatus.FINISHED;
				}).build();
	}
}
