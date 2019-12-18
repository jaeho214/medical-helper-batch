package kr.ac.skuniv.medicalhelperbatch.global.batch.partition;

import kr.ac.skuniv.medicalhelperbatch.domain.hospital.entity.Hospital;
import kr.ac.skuniv.medicalhelperbatch.global.util.api.hopital.HospitalApiRequest;
import org.springframework.batch.core.partition.support.Partitioner;
import org.springframework.batch.item.ExecutionContext;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class HospitalPartition implements Partitioner {
    @Override
    public Map<String, ExecutionContext> partition(int gridSize) {
        Map<String, ExecutionContext> map = new HashMap<>();
        int i = 0;
        for(HospitalApiRequest myEnum : HospitalApiRequest.values()){
            ExecutionContext context = new ExecutionContext();
            context.putString("url", myEnum.getUri());
            map.put("partition_" + i, context);
            i++;
        }
        return map;
    }
}
