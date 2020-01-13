package kr.ac.skuniv.medicalhelperbatch.global.util.api.pharmacy;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum PharmacyApiRequest {
    PHARMACY("http://apis.data.go.kr/B551182/pharmacyInfoService/getParmacyBasisList?serviceKey=%s&pageNo=%d");
    private String uri;
}
