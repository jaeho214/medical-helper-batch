package kr.ac.skuniv.medicalhelperbatch.global.batch.configuration;

import kr.ac.skuniv.medicalhelperbatch.domain.hospital.entity.Hospital;
import kr.ac.skuniv.medicalhelperbatch.domain.hospital.repository.HospitalRepository;
import kr.ac.skuniv.medicalhelperbatch.global.batch.reader.HospitalItemReader;
import kr.ac.skuniv.medicalhelperbatch.global.batch.writer.HospitalItemWriter;
import org.junit.After;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.batch.core.*;
import org.springframework.batch.test.JobLauncherTestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDate;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hibernate.type.InstantType.FORMATTER;

@RunWith(SpringRunner.class)
//아래 지정 클래스만 불러와서 테스트
@SpringBootTest(classes = {HospitalJobConfiguration.class, TestHospitalJobConfig.class, HospitalItemReader.class, HospitalItemWriter.class})
class HospitalJobConfigurationTest {

    @Autowired
    private HospitalRepository hospitalRepository;

    @Autowired
    //현재 Bean에 올라간 Job만을 주입받음
    private JobLauncherTestUtils jobLauncherTestUtils;

    @After
    public void tearDown(){
        hospitalRepository.deleteAllInBatch();
    }

    @Test
    public void reader() throws Exception {
        //given
        LocalDate today = LocalDate.of(2019,12,15);

        JobParameters jobParameters = new JobParametersBuilder()
                .addString("today", today.format(FORMATTER))
                .toJobParameters();

        //when
        JobExecution jobExecution = jobLauncherTestUtils.launchJob(jobParameters);

        //then
        assertThat(jobExecution.getStatus()).isEqualTo(BatchStatus.COMPLETED);
        List<Hospital> hospitals = hospitalRepository.findAll();
        System.out.println(hospitals.get(0).getName());
    }



}