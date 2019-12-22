package kr.ac.skuniv.medicalhelperbatch.global.batch.reader;

import kr.ac.skuniv.medicalhelperbatch.domain.drugstore.dto.DrugstoreDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.ExitStatus;
import org.springframework.batch.core.StepExecution;
import org.springframework.batch.core.StepExecutionListener;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.item.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.net.URISyntaxException;

@Slf4j
@Configuration
@Component
@StepScope
@RequiredArgsConstructor
@PropertySource("classpath:serviceKey.yml")
public class DrugstoreItemReader implements ItemReader<DrugstoreDto>, StepExecutionListener {
    @Value("${serviceKey}")
    private String serviceKey;
    private String uri;

    private static int pageNum=1;
    private final RestTemplate restTemplate;

    @Override
    public void beforeStep(StepExecution stepExecution) {
        //partition 에서 넣어준 context를 가져와서
        ExecutionContext ctx = stepExecution.getExecutionContext();
        //uri 추출
        uri = (String) ctx.get("drugstoreUri");
    }

    @Override
    public ExitStatus afterStep(StepExecution stepExecution) {
        return null;
    }

    @Override
    public DrugstoreDto read() throws Exception, UnexpectedInputException, ParseException, NonTransientResourceException {
        log.warn("-----Drugstore Api Calling...");
        URI uri = getUri();
        pageNum++;
        if(pageNum > 2270)
            return null;
        return restTemplate.getForObject(uri, DrugstoreDto.class);
    }

    private URI getUri() {
        StringBuilder sb = new StringBuilder();
        URI restUri;
        try{
            //ctx 에서 가져온 uri 형식에 serviceKey 와 pageNum 을 적용시켜 리턴
            restUri = new URI(sb.append(String.format(uri, serviceKey, pageNum)).toString());
            log.warn(restUri.toString());
            return restUri;
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
        return null;
    }
}
