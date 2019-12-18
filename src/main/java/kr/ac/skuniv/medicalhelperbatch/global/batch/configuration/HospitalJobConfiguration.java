package kr.ac.skuniv.medicalhelperbatch.global.batch.configuration;

import kr.ac.skuniv.medicalhelperbatch.domain.hospital.dto.HospitalDto;
import kr.ac.skuniv.medicalhelperbatch.global.batch.partition.HospitalPartition;
import kr.ac.skuniv.medicalhelperbatch.global.batch.reader.HospitalItemReader;
import kr.ac.skuniv.medicalhelperbatch.global.batch.writer.HospitalItemWriter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.JobScope;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.item.ParseException;
import org.springframework.batch.item.UnexpectedInputException;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;

import javax.sql.DataSource;

@Slf4j
@Configuration
public class HospitalJobConfiguration {

    private JobBuilderFactory jobBuilderFactory;
    private StepBuilderFactory stepBuilderFactory;

    private HospitalItemReader hospitalItemReader;
    private HospitalItemWriter hospitalItemWriter;
    private HospitalPartition hospitalPartition;

    private DataSource dataSource;

    public HospitalJobConfiguration(JobBuilderFactory jobBuilderFactory, StepBuilderFactory stepBuilderFactory, HospitalItemReader hospitalItemReader, HospitalItemWriter hospitalItemWriter, HospitalPartition hospitalPartition, DataSource dataSource) {
        this.jobBuilderFactory = jobBuilderFactory;
        this.stepBuilderFactory = stepBuilderFactory;
        this.hospitalItemReader = hospitalItemReader;
        this.hospitalItemWriter = hospitalItemWriter;
        this.hospitalPartition = hospitalPartition;
        this.dataSource = dataSource;
    }

    @Bean
    public Job apiCallJob(){
        log.warn("-------api call job");
        return jobBuilderFactory.get("hospitalApiCallJob")
                .start(apiCallPartitionStep())
                .build();
    }

    @Bean
    public Step apiCallPartitionStep()
            throws UnexpectedInputException, ParseException {
        return stepBuilderFactory.get("apiCallPartitionStep")
                .partitioner("apiCallPartitionStep", hospitalPartition)
                .step(hospitalApiCallStep())
                .build();
    }

    @Bean
    public Step hospitalApiCallStep() {
        return stepBuilderFactory.get("hospitalApiCallStep")
                .<HospitalDto, HospitalDto>chunk(100)
                .reader(hospitalItemReader)
                .writer(hospitalItemWriter)
                .transactionManager(jpaTransactionManager())
                .build();
    }

    @Bean
    @Primary
    public PlatformTransactionManager jpaTransactionManager() {
        final JpaTransactionManager transactionManager = new JpaTransactionManager();
        transactionManager.setDataSource(dataSource);
        return transactionManager;
    }

}
