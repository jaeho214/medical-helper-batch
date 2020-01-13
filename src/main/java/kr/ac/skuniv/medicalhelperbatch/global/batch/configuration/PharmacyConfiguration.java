package kr.ac.skuniv.medicalhelperbatch.global.batch.configuration;

import kr.ac.skuniv.medicalhelperbatch.domain.pharmacy.dto.PharmacyDto;
import kr.ac.skuniv.medicalhelperbatch.global.batch.partition.PharmacyPartition;
import kr.ac.skuniv.medicalhelperbatch.global.batch.reader.PharmacyItemReader;
import kr.ac.skuniv.medicalhelperbatch.global.batch.writer.PharmacyItemWriter;
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
public class PharmacyConfiguration {
    private JobBuilderFactory jobBuilderFactory;
    private StepBuilderFactory stepBuilderFactory;

    private PharmacyItemReader pharmacyItemReader;
    private PharmacyItemWriter pharmacyItemWriter;
    private PharmacyPartition pharmacyPartition;

    private DataSource dataSource;

    public PharmacyConfiguration(JobBuilderFactory jobBuilderFactory ,
                                 StepBuilderFactory stepBuilderFactory ,
                                 PharmacyItemReader pharmacyItemReader,
                                 PharmacyItemWriter pharmacyItemWriter,
                                 PharmacyPartition pharmacyPartition,
                                 DataSource dataSource) {
        this.jobBuilderFactory = jobBuilderFactory;
        this.stepBuilderFactory = stepBuilderFactory;
        this.pharmacyItemReader = pharmacyItemReader;
        this.pharmacyItemWriter = pharmacyItemWriter;
        this.pharmacyPartition = pharmacyPartition;
        this.dataSource = dataSource;
    }

    @Bean
    public Job pharmacyApiCallJob(){
        log.warn("-------pharmacy api call job");
        return jobBuilderFactory.get("pharmacyApiCallJob")
                .start(pharmacyApiCallPartitionStep())
                .build();
    }

    @Bean
    public Step pharmacyApiCallPartitionStep()
            throws UnexpectedInputException, ParseException {
        return stepBuilderFactory.get("pharmacyApiCallPartitionStep")
                .partitioner("pharmacyApiCallPartitionStep", pharmacyPartition)
                .step(pharmacyApiCallStep())
                .build();
    }

    @Bean
    public Step pharmacyApiCallStep() {
        return stepBuilderFactory.get("drugstoreApiCallStep")
                .<PharmacyDto, PharmacyDto>chunk(100)
                .reader(pharmacyItemReader)
                .writer(pharmacyItemWriter)
                .transactionManager(pharmacyJpaTransactionManager())
                .build();
    }

    @Bean
    public PlatformTransactionManager pharmacyJpaTransactionManager() {
        final JpaTransactionManager transactionManager = new JpaTransactionManager();
        transactionManager.setDataSource(dataSource);
        return transactionManager;
    }
}
