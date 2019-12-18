package kr.ac.skuniv.medicalhelperbatch.global.util.api.hopital;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum HospitalApiRequest {
    HOSPITAL("http://apis.data.go.kr/B551182/hospInfoService/getHospBasisList?serviceKey=%s&pageNo=%d");
    private String uri;
}
