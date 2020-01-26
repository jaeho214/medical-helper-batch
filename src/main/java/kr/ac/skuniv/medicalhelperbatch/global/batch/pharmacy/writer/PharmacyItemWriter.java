package kr.ac.skuniv.medicalhelperbatch.global.batch.pharmacy.writer;

import kr.ac.skuniv.medicalhelperbatch.domain.pharmacy.dto.PharmacyDto;
import kr.ac.skuniv.medicalhelperbatch.domain.pharmacy.dto.PharmacyItemDto;
import kr.ac.skuniv.medicalhelperbatch.domain.pharmacy.entity.Pharmacy;
import kr.ac.skuniv.medicalhelperbatch.domain.pharmacy.repository.PharmacyRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.item.ItemWriter;
import org.springframework.context.annotation.Configuration;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@StepScope
@Configuration
public class PharmacyItemWriter implements ItemWriter<PharmacyDto>{
    private List<Pharmacy> pharmacyList = new ArrayList<>();
    private PharmacyRepository pharmacyRepository;

    public PharmacyItemWriter(PharmacyRepository pharmacyRepository) {
        this.pharmacyRepository = pharmacyRepository;
    }

    @Transactional
    public void savePharmacy(PharmacyDto item){
        for(PharmacyItemDto pharmacyItemDto : item.getBody().getItem()){
            Pharmacy pharmacy = Pharmacy.of(pharmacyItemDto);
            pharmacyList.add(pharmacy);
        }
    }


    @Override
    public void write(List<? extends PharmacyDto> items) throws Exception {
        for(PharmacyDto item : items){
            savePharmacy(item);
        }
        pharmacyRepository.saveAll(pharmacyList);
        //영속성 컨텍스트의 변경 내용을 DB에 반영하는 것
        pharmacyRepository.flush();
        pharmacyList.clear();
    }

}
