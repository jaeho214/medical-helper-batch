package kr.ac.skuniv.medicalhelperbatch.global.batch.configuration;

import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.test.JobLauncherTestUtils;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
@EnableBatchProcessing //배치환경 자동 설정
@EnableAutoConfiguration
public class TestHospitalJobConfig {
    @Bean
    //이 Bean을 이용해서 JobParameters를 사용한 Job 실행 등이 이루어짐
    public JobLauncherTestUtils jobLauncherTestUtils(){
        return new JobLauncherTestUtils();
    }

    @Bean
    public RestTemplate restTemplate(){
        return new RestTemplate();
    }
}
