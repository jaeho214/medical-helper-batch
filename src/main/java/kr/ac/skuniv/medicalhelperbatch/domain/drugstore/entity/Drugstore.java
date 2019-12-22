package kr.ac.skuniv.medicalhelperbatch.domain.drugstore.entity;

import kr.ac.skuniv.medicalhelperbatch.domain.drugstore.dto.DrugstoreItemDto;
import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter @Setter
@Builder
@RequiredArgsConstructor
@AllArgsConstructor
public class Drugstore {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long drugstoreNo;

    private String name;
    private String tel;
    private String address;
    private String drugstoreCode;
    private String drugstoreCodeName;
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
    private List<DrugstoreComment> drugstoreComment = new ArrayList<>();

    public static Drugstore of(DrugstoreItemDto drugstoreItemDto){
        return Drugstore.builder()
                .name(drugstoreItemDto.getName())
                .tel(drugstoreItemDto.getTel())
                .address(drugstoreItemDto.getAddress())
                .drugstoreCode(drugstoreItemDto.getDrugstoreCode())
                .drugstoreCodeName(drugstoreItemDto.getDrugstoreCodeName())
                .openDate(drugstoreItemDto.getOpenDate())
                .postNo(drugstoreItemDto.getPostNo())
                .stateCode(drugstoreItemDto.getStateCode())
                .stateCodeName(drugstoreItemDto.getStateCodeName())
                .cityCode(drugstoreItemDto.getCityCode())
                .cityCodeName(drugstoreItemDto.getCityCodeName())
                .localName(drugstoreItemDto.getLocalName())
                .xPos(drugstoreItemDto.getXPos())
                .yPos(drugstoreItemDto.getYPos())
                .build();
    }


}