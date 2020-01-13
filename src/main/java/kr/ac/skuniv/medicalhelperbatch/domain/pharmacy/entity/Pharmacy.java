package kr.ac.skuniv.medicalhelperbatch.domain.pharmacy.entity;

import kr.ac.skuniv.medicalhelperbatch.domain.pharmacy.dto.PharmacyItemDto;
import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter @Setter
@Builder
@RequiredArgsConstructor
@AllArgsConstructor
public class Pharmacy {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long drugstoreNo;

    private String name;
    private String tel;
    private String address;
    private String pharmacyCode;
    private String pharmacyCodeName;
    private String openDate;
    private String postNo;
    private String stateCode; // 시군구코드
    private String stateCodeName;
    private String cityCode; // 시도 코드
    private String cityCodeName;
    private String localName;
    private String xPos;
    private String yPos;

    @OneToMany
    @JoinColumn(name = "dcNo")
    private List<PharmacyComment> pharmacyComment = new ArrayList<>();

    public static Pharmacy of(PharmacyItemDto pharmacyItemDto){
        return Pharmacy.builder()
                .name(pharmacyItemDto.getName())
                .tel(pharmacyItemDto.getTel())
                .address(pharmacyItemDto.getAddress())
                .pharmacyCode(pharmacyItemDto.getPharmacyCode())
                .pharmacyCodeName(pharmacyItemDto.getPharmacyCodeName())
                .openDate(pharmacyItemDto.getOpenDate())
                .postNo(pharmacyItemDto.getPostNo())
                .stateCode(pharmacyItemDto.getStateCode())
                .stateCodeName(pharmacyItemDto.getStateCodeName())
                .cityCode(pharmacyItemDto.getCityCode())
                .cityCodeName(pharmacyItemDto.getCityCodeName())
                .localName(pharmacyItemDto.getLocalName())
                .xPos(pharmacyItemDto.getXPos())
                .yPos(pharmacyItemDto.getYPos())
                .build();
    }


}