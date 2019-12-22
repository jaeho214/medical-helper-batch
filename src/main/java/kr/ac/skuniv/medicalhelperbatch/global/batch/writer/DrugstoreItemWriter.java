package kr.ac.skuniv.medicalhelperbatch.global.batch.writer;

import kr.ac.skuniv.medicalhelperbatch.domain.drugstore.dto.DrugstoreDto;
import kr.ac.skuniv.medicalhelperbatch.domain.drugstore.dto.DrugstoreItemDto;
import kr.ac.skuniv.medicalhelperbatch.domain.drugstore.entity.Drugstore;
import kr.ac.skuniv.medicalhelperbatch.domain.drugstore.repository.DrugstoreRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.item.ItemWriter;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.context.annotation.Configuration;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@StepScope
@Configuration
public class DrugstoreItemWriter implements ItemWriter<DrugstoreDto>{
    private List<Drugstore> drugstoreList = new ArrayList<>();
    private DrugstoreRepository drugstoreRepository;

    public DrugstoreItemWriter(DrugstoreRepository drugstoreRepository) {
        this.drugstoreRepository = drugstoreRepository;
    }

    @Transactional
    public void saveDrugstore(DrugstoreDto item){
        for(DrugstoreItemDto drugstoreItemDto : item.getBody().getItem()){
            Drugstore drugstore = Drugstore.of(drugstoreItemDto);
            drugstoreList.add(drugstore);
        }
    }


    @Override
    public void write(List<? extends DrugstoreDto> items) throws Exception {
        for(DrugstoreDto item : items){
            saveDrugstore(item);
        }
        drugstoreRepository.saveAll(drugstoreList);
        //영속성 컨텍스트의 변경 내용을 DB에 반영하는 것
        drugstoreRepository.flush();
        drugstoreList.clear();
    }

}
