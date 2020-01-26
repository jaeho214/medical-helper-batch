package kr.ac.skuniv.medicalhelperbatch;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.net.URISyntaxException;

@RunWith(SpringRunner.class)
public class RestTemplateTest {
    @Autowired
    RestTemplate restTemplate;


    private String serviceKey = "Bl%2FTxlM0GcHiLxmHAKwmr%2FBdTudvvnR0kS3jG93iHkEZ%2BxmsQ%2BKMtiTZbPyPMumWgm82KcoLuLlyRWrcUXQxzA%3D%3D";
    int pageNum = 1;
    String uri = "http://apis.data.go.kr/B551182/pharmacyInfoService/getParmacyBasisList?serviceKey=%s&pageNo=%d";

    @Test
    public void test(){
        StringBuilder sb = new StringBuilder();
        URI restUri;
        try{
            //ctx 에서 가져온 uri 형식에 serviceKey 와 pageNum 을 적용시켜 리턴
            restUri = new URI(sb.append(String.format(uri, serviceKey, pageNum)).toString());
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
    }
}
