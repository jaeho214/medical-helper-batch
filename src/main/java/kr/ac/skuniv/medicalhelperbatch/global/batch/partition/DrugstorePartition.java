package kr.ac.skuniv.medicalhelperbatch.global.batch.partition;

import kr.ac.skuniv.medicalhelperbatch.global.util.api.drugstore.DrugstoreApiRequest;
import org.springframework.batch.core.partition.support.Partitioner;
import org.springframework.batch.item.ExecutionContext;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class DrugstorePartition implements Partitioner {
    @Override
    public Map<String, ExecutionContext> partition(int gridSize) {
        Map<String, ExecutionContext> map = new HashMap<>();
        int i = 0;
        for(DrugstoreApiRequest myEnum : DrugstoreApiRequest.values()){
            ExecutionContext context = new ExecutionContext();
            context.putString("drugstoreUri", myEnum.getUri());
            map.put("drugstorePartition_" + i, context);
            i++;
        }
        return map;
    }
}
