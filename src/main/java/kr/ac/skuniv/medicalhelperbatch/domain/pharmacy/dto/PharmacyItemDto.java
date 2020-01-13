package kr.ac.skuniv.medicalhelperbatch.domain.pharmacy.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;

@NoArgsConstructor
@Data
@XmlAccessorType(XmlAccessType.FIELD)
public class PharmacyItemDto {
    @XmlElement(name = "addr")
    private String address;

    @XmlElement(name = "clCd")
    private String pharmacyCode;

    @XmlElement(name = "clCdNm")
    private String pharmacyCodeName;

    @XmlElement(name = "estbDd")
    private String openDate;

    @XmlElement(name = "sgguCd")
    private String stateCode; // 시군구코드

    @XmlElement(name = "sgguCdNm")
    private String stateCodeName; // 시군구명

    @XmlElement(name = "sidoCd")
    private String cityCode; // 시도 코드

    @XmlElement(name = "sidoCdNm")
    private String cityCodeName;

    @XmlElement(name = "emdongNm")
    private String localName; // 읍면동 명

    @XmlElement(name = "postNo")
    private String postNo;

    @XmlElement(name = "telno")
    private String tel;

    @XmlElement(name = "XPos")
    private String xPos;

    @XmlElement(name = "YPos")
    private String yPos;

    @XmlElement(name = "ykiho")
    private String unknownCode;

    @XmlElement(name = "yadmNm")
    private String name;
}
