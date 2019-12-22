package kr.ac.skuniv.medicalhelperbatch.global.batch.configuration;

import kr.ac.skuniv.medicalhelperbatch.domain.drugstore.dto.DrugstoreDto;
import kr.ac.skuniv.medicalhelperbatch.global.batch.partition.DrugstorePartition;
import kr.ac.skuniv.medicalhelperbatch.global.batch.reader.DrugstoreItemReader;
import kr.ac.skuniv.medicalhelperbatch.global.batch.writer.DrugstoreItemWriter;
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
public class DrugstoreConfiguration {
    private JobBuilderFactory jobBuilderFactory;
    private StepBuilderFactory stepBuilderFactory;

    private DrugstoreItemReader drugstoreItemReader;
    private DrugstoreItemWriter drugstoreItemWriter;
    private DrugstorePartition drugstorePartition;

    private DataSource dataSource;

    public DrugstoreConfiguration(JobBuilderFactory jobBuilderFactory ,
                                  StepBuilderFactory stepBuilderFactory ,
                                  DrugstoreItemReader drugstoreItemReader,
                                  DrugstoreItemWriter drugstoreItemWriter,
                                  DrugstorePartition drugstorePartition,
                                  DataSource dataSource) {
        this.jobBuilderFactory = jobBuilderFactory;
        this.stepBuilderFactory = stepBuilderFactory;
        this.drugstoreItemReader = drugstoreItemReader;
        this.drugstoreItemWriter = drugstoreItemWriter;
        this.drugstorePartition = drugstorePartition;
        this.dataSource = dataSource;
    }

    @Bean
    public Job DrugstoreApiCallJob(){
        log.warn("-------Drugstore api call job");
        return jobBuilderFactory.get("drugstoreApiCallJob")
                .start(drugstoreApiCallPartitionStep())
                .build();
    }

    @Bean
    public Step drugstoreApiCallPartitionStep()
            throws UnexpectedInputException, ParseException {
        return stepBuilderFactory.get("drugstoreApiCallPartitionStep")
                .partitioner("drugstoreApiCallPartitionStep", drugstorePartition)
                .step(drugstoreApiCallStep())
                .build();
    }

    @Bean
    public Step drugstoreApiCallStep() {
        return stepBuilderFactory.get("drugstoreApiCallStep")
                .<DrugstoreDto, DrugstoreDto>chunk(100)
                .reader(drugstoreItemReader)
                .writer(drugstoreItemWriter)
                .transactionManager(drugstoreJpaTransactionManager())
                .build();
    }

    @Bean
    public PlatformTransactionManager drugstoreJpaTransactionManager() {
        final JpaTransactionManager transactionManager = new JpaTransactionManager();
        transactionManager.setDataSource(dataSource);
        return transactionManager;
    }
}
