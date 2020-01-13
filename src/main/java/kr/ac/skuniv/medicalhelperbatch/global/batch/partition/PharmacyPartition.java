package kr.ac.skuniv.medicalhelperbatch.global.batch.partition;

import kr.ac.skuniv.medicalhelperbatch.global.util.api.pharmacy.PharmacyApiRequest;
import org.springframework.batch.core.partition.support.Partitioner;
import org.springframework.batch.item.ExecutionContext;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class PharmacyPartition implements Partitioner {
    @Override
    public Map<String, ExecutionContext> partition(int gridSize) {
        Map<String, ExecutionContext> map = new HashMap<>();
        int i = 0;
        for(PharmacyApiRequest myEnum : PharmacyApiRequest.values()){
            ExecutionContext context = new ExecutionContext();
            context.putString("pharmacyUri", myEnum.getUri());
            map.put("pharmacyPartition_" + i, context);
            i++;
        }
        return map;
    }
}
