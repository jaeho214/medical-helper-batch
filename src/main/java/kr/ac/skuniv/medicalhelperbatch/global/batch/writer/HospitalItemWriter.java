package kr.ac.skuniv.medicalhelperbatch.global.batch.writer;

import kr.ac.skuniv.medicalhelperbatch.domain.hospital.dto.HospitalDto;
import kr.ac.skuniv.medicalhelperbatch.domain.hospital.dto.HospitalItemDto;
import kr.ac.skuniv.medicalhelperbatch.domain.hospital.entity.Hospital;
import kr.ac.skuniv.medicalhelperbatch.domain.hospital.repository.HospitalRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.item.ItemWriter;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

import java.util.List;

@Slf4j
@StepScope
@Configuration
@RequiredArgsConstructor
@PropertySource("classpath:serviceKey.yml")
public class HospitalItemWriter implements ItemWriter<HospitalDto>, InitializingBean {

    private Hospital hospital;
    private List<Hospital> hospitalList;

    private HospitalRepository hospitalRepository;

    public void saveHospital(HospitalDto item){
        for(HospitalItemDto hospitalItemDto : item.getBody().getItem()){
            hospital = Hospital.of(hospitalItemDto);
            hospitalList.add(hospital);
        }
    }

    @Override
    public void write(List<? extends HospitalDto> items) throws Exception {
        for(HospitalDto item : items){
            saveHospital(item);
        }
        hospitalRepository.saveAll(hospitalList);
        hospitalRepository.flush();
        hospitalList.clear();
    }

    @Override
    public void afterPropertiesSet() throws Exception {

    }
}
