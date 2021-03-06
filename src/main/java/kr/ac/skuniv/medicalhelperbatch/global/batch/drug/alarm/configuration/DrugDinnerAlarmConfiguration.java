package kr.ac.skuniv.medicalhelperbatch.global.batch.drug.alarm.configuration;

import kr.ac.skuniv.medicalhelperbatch.domain.drug.entity.Drug;
import kr.ac.skuniv.medicalhelperbatch.domain.drug.repository.DrugRepository;
import kr.ac.skuniv.medicalhelperbatch.domain.treatment.entity.Treatment;
import kr.ac.skuniv.medicalhelperbatch.domain.treatment.repository.TreatmentRepository;
import kr.ac.skuniv.medicalhelperbatch.global.fcm.FCMService;
import kr.ac.skuniv.medicalhelperbatch.global.fcm.NotificationRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.*;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.JobScope;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.core.launch.support.SimpleJobLauncher;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.data.RepositoryItemReader;
import org.springframework.batch.item.data.builder.RepositoryItemReaderBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Slf4j
@Configuration
@EnableScheduling
public class DrugDinnerAlarmConfiguration {

    public static final String JOB_NAME = "DrugDinnerAlarmJob";

    private final StepBuilderFactory drugDinnerAlarmStepBuilderFactory;
    private final JobBuilderFactory drugDinnerAlarmJobBuilderFactory;
    private final SimpleJobLauncher drugDinnerAlarmJobLauncher;
    private final FCMService fcmService;
    private final DrugRepository drugRepository;
    private final TreatmentRepository treatmentRepository;

    public DrugDinnerAlarmConfiguration(StepBuilderFactory drugDinnerAlarmStepBuilderFactory,
                                        JobBuilderFactory drugDinnerAlarmJobBuilderFactory,
                                        SimpleJobLauncher drugDinnerAlarmJobLauncher,
                                        FCMService fcmService,
                                        DrugRepository drugRepository,
                                        TreatmentRepository treatmentRepository) {
        this.drugDinnerAlarmStepBuilderFactory = drugDinnerAlarmStepBuilderFactory;
        this.drugDinnerAlarmJobBuilderFactory = drugDinnerAlarmJobBuilderFactory;
        this.drugDinnerAlarmJobLauncher = drugDinnerAlarmJobLauncher;
        this.fcmService = fcmService;
        this.drugRepository = drugRepository;
        this.treatmentRepository = treatmentRepository;
    }

    private final int chunkSize = 10;

    @Bean
    public Job DrugDinnerAlarmJob(){
        return drugDinnerAlarmJobBuilderFactory.get(JOB_NAME)
                .start(DrugDinnerAlarmStep())
                .build();
    }

    @Bean
    @JobScope
    public Step DrugDinnerAlarmStep() {
        return drugDinnerAlarmStepBuilderFactory.get("DrugDinnerAlarmStep")
                .<Drug, Drug> chunk(chunkSize)
                .reader(DrugDinnerAlarmReader())
                .writer(DrugDinnerAlarmWriter())
                .build();
    }

    @Bean
    @StepScope
    public RepositoryItemReader<Drug> DrugDinnerAlarmReader() {
        List parameter = new ArrayList();
        parameter.add(true);
        return new RepositoryItemReaderBuilder<Drug>()
                .name("DrugDinnerAlarmReader")
                .repository(drugRepository)
                .methodName("findByDinner")
                .arguments(true)
                .pageSize(chunkSize)
                .build();
    }

    private ItemWriter<Drug> DrugDinnerAlarmWriter(){
        return items -> {
            for(Drug drug : items){
                Treatment treatment = treatmentRepository.findByDrug(drug).get();
                fcmService.send(NotificationRequest.setDrugTimeNotification(treatment.getMember()));
            }
        };
    }

    @Scheduled(cron = "0 0 19 * * ?")
    public void perform() throws Exception{
        log.info("Job Started at : " + new Date());
        JobParameters parameters = new JobParametersBuilder().addString("jobId", String.valueOf(System.currentTimeMillis()))
                .toJobParameters();

        JobExecution execution = drugDinnerAlarmJobLauncher.run(DrugDinnerAlarmJob(), parameters);

        log.info("Job finished with status :" + execution.getStatus());
    }
}
