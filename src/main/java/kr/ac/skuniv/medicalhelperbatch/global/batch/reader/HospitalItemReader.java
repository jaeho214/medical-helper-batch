package kr.ac.skuniv.medicalhelperbatch.global.batch.reader;

import kr.ac.skuniv.medicalhelperbatch.domain.hospital.dto.HospitalDto;
import kr.ac.skuniv.medicalhelperbatch.global.util.api.hopital.HospitalApiRequest;
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
public class HospitalItemReader implements ItemReader<HospitalDto>, StepExecutionListener {
    @Value("${serviceKey}")
    private String serviceKey;
    private String uri;


    private static int pageNum=4701;
    private final RestTemplate restTemplate;
    @Override
    public HospitalDto read() throws Exception, UnexpectedInputException, ParseException, NonTransientResourceException {
        log.warn("-----api calling");
        URI uri = getUri();
        pageNum ++;
        return restTemplate.getForObject(uri, HospitalDto.class);
    }

    private URI getUri(){
        StringBuilder sb = new StringBuilder();
        URI restUri;
        try{
            restUri = new URI(sb.append(String.format(uri, serviceKey, pageNum)).toString());
            log.warn(restUri.toString());
            return restUri;
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public void beforeStep(StepExecution stepExecution) {
        ExecutionContext ctx = stepExecution.getExecutionContext();
        uri = (String) ctx.get("url");
    }

    @Override
    public ExitStatus afterStep(StepExecution stepExecution) {
        return null;
    }
}
