package kr.ac.skuniv.medicalhelperbatch.global.batch.configuration;

import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.test.JobLauncherTestUtils;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

import javax.batch.operations.*;
import javax.batch.runtime.JobExecution;
import javax.batch.runtime.JobInstance;
import javax.batch.runtime.StepExecution;
import java.util.List;
import java.util.Properties;
import java.util.Set;

@Configuration
@EnableBatchProcessing //배치환경 자동 설정
@EnableAutoConfiguration
public class TestHospitalJobConfig {

    @Bean
    public RestTemplate restTemplate(){
        return new RestTemplate();
    }

}
