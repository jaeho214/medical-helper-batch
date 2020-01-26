package kr.ac.skuniv.medicalhelperbatch.global.batch.configuration;

import kr.ac.skuniv.medicalhelperbatch.domain.hospital.entity.Hospital;
import kr.ac.skuniv.medicalhelperbatch.domain.hospital.repository.HospitalRepository;
import kr.ac.skuniv.medicalhelperbatch.global.batch.hospital.configuration.HospitalJobConfiguration;
import kr.ac.skuniv.medicalhelperbatch.global.batch.hospital.partition.HospitalPartition;
import kr.ac.skuniv.medicalhelperbatch.global.batch.hospital.reader.HospitalItemReader;
import kr.ac.skuniv.medicalhelperbatch.global.batch.hospital.writer.HospitalItemWriter;
import org.junit.After;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.batch.core.BatchStatus;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.test.JobLauncherTestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
//아래 지정 클래스만 불러와서 테스트
@SpringBootTest(classes = {HospitalJobConfiguration.class, TestHospitalJobConfig.class, HospitalItemReader.class, HospitalItemWriter.class, HospitalPartition.class})
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
        JobParameters jobParameters = new JobParametersBuilder()
                .addString("version", "20")
                .toJobParameters();

        //when
        JobExecution jobExecution = jobLauncherTestUtils.launchJob(jobParameters);

        //then
        assertThat(jobExecution.getStatus()).isEqualTo(BatchStatus.COMPLETED);
        List<Hospital> hospitals = hospitalRepository.findAll();
        System.out.println(hospitals.get(0).getName());
    }


}