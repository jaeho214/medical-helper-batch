package kr.ac.skuniv.medicalhelperbatch.global.util.api.drugstore;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum DrugstoreApiRequest {
    DRUGSTORE("http://apis.data.go.kr/B551182/pharmacyInfoService/getParmacyBasisList?serviceKey=%s&pageNo=%d");
    private String uri;
}
