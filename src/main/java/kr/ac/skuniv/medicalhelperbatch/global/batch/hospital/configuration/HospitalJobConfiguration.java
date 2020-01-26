package kr.ac.skuniv.medicalhelperbatch.global.batch.hospital.configuration;

import kr.ac.skuniv.medicalhelperbatch.domain.hospital.dto.HospitalDto;
import kr.ac.skuniv.medicalhelperbatch.global.batch.hospital.partition.HospitalPartition;
import kr.ac.skuniv.medicalhelperbatch.global.batch.hospital.reader.HospitalItemReader;
import kr.ac.skuniv.medicalhelperbatch.global.batch.hospital.writer.HospitalItemWriter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.item.ParseException;
import org.springframework.batch.item.UnexpectedInputException;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
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
    public Job HospitalApiCallJob(){
        log.warn("-------Hospital api call job");
        return jobBuilderFactory.get("hospitalApiCallJob")
                .start(hospitalApiCallPartitionStep())
                .build();
    }

    @Bean
    public Step hospitalApiCallPartitionStep()
            throws UnexpectedInputException, ParseException {
        return stepBuilderFactory.get("apiCallPartitionStep")
                .partitioner("apiCallPartitionStep", hospitalPartition)
                .step(hospitalApiCallStep())
                .build();
    }

    @Bean
    public Step hospitalApiCallStep() {
        return stepBuilderFactory.get("hospitalApiCallStep")
                .<HospitalDto, HospitalDto>chunk(100)//1페이지씩 100번 읽어와서 100페이지 한번에 write
                .reader(hospitalItemReader)
                .writer(hospitalItemWriter)
                .transactionManager(HospitalJpaTransactionManager())
                .build();
    }

    @Bean
    public PlatformTransactionManager HospitalJpaTransactionManager() {
        final JpaTransactionManager transactionManager = new JpaTransactionManager();
        transactionManager.setDataSource(dataSource);
        return transactionManager;
    }

}
