package kr.ac.skuniv.medicalhelperbatch.global.batch.drug.exit.configuration;

import kr.ac.skuniv.medicalhelperbatch.domain.drug.entity.Drug;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.*;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.JobScope;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.core.launch.support.SimpleJobLauncher;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.database.JpaPagingItemReader;
import org.springframework.batch.item.database.builder.JpaPagingItemReaderBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

import javax.persistence.EntityManagerFactory;
import java.time.LocalDateTime;
import java.util.Date;

@Slf4j
@Configuration
@EnableScheduling
public class DrugTimeBreakConfiguration {
    public static final String JOB_NAME = "DrugTimeBreakJob";

    private final EntityManagerFactory drugTimeBreakEntityManagerFactory;
    private final StepBuilderFactory drugTimeBreakStepBuilderFactory;
    private final JobBuilderFactory drugTimeBreakJobBuilderFactory;
    private final SimpleJobLauncher drugTimeBreakJobLauncher;

    public DrugTimeBreakConfiguration(EntityManagerFactory drugTimeBreakEntityManagerFactory,
                                      StepBuilderFactory drugTimeBreakStepBuilderFactory,
                                      JobBuilderFactory drugTimeBreakJobBuilderFactory,
                                      SimpleJobLauncher drugTimeBreakJobLauncher) {
        this.drugTimeBreakEntityManagerFactory = drugTimeBreakEntityManagerFactory;
        this.drugTimeBreakStepBuilderFactory = drugTimeBreakStepBuilderFactory;
        this.drugTimeBreakJobBuilderFactory = drugTimeBreakJobBuilderFactory;
        this.drugTimeBreakJobLauncher = drugTimeBreakJobLauncher;
    }

    private final int chunkSize = 10;

    @Bean
    public Job DrugTimeBreakJob(){
        return drugTimeBreakJobBuilderFactory.get(JOB_NAME)
                .start(DrugTimeBreakStep())
                .build();
    }

    @Bean
    @JobScope
    public Step DrugTimeBreakStep() {
        return drugTimeBreakStepBuilderFactory.get("DrugTimeBreakStep")
                .<Drug, Drug> chunk(chunkSize)
                .reader(DrugTimeBreakReader())
                .writer(DrugTimeBreakWriter())
                .build();
    }

    @Bean
    @StepScope
    public JpaPagingItemReader<Drug> DrugTimeBreakReader() {
        return new JpaPagingItemReaderBuilder<Drug>()
                .name("DrugTimeBreakReader")
                .entityManagerFactory(drugTimeBreakEntityManagerFactory)
                .pageSize(chunkSize)
                .queryString("SELECT d FROM Drug d")
                .build();
    }

    private ItemWriter<Drug> DrugTimeBreakWriter(){
        return items -> {
            for(Drug drug : items){
                if(drug.getDeadline().isEqual(LocalDateTime.now())){
                    log.info(drug.getDeadline().toString());
                    drug.breakDrug();
                }
            }
        };
    }

    @Scheduled(cron = "0 0 0 * * ?")
    public void perform() throws Exception{
        log.info("Job Started at : " + new Date());
        JobParameters parameters = new JobParametersBuilder().addString("jobId", String.valueOf(System.currentTimeMillis()))
                .toJobParameters();

        JobExecution execution = drugTimeBreakJobLauncher.run(DrugTimeBreakJob(), parameters);

        log.info("Job finished with status :" + execution.getStatus());
    }
}
