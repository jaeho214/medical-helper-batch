package kr.ac.skuniv.medicalhelperbatch.domain.hospital.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;

@ToString
@NoArgsConstructor
@Data
@XmlAccessorType(XmlAccessType.FIELD)
public class HospitalItemDto {
    @XmlElement(name = "addr")
    private String address;

    @XmlElement(name = "clCd")
    private String hospitalCode;

    @XmlElement(name = "clCdNm")
    private String hospitalCodeName;

    @XmlElement(name = "drTotCnt")
    private int doctorCount;

    @XmlElement(name = "estbDd")
    private String openDate;

    @XmlElement(name = "gdrCnt")
    private int generalDoctorCount;

    @XmlElement(name = "hospUrl")
    private String hospitalUrl;

    @XmlElement(name = "intnCnt")
    private int internCount;

    @XmlElement(name = "postNo")
    private String postNo;

    @XmlElement(name = "resdntCnt")
    private int residentCount;

    @XmlElement(name = "sdrCnt")
    private int specialDoctorCount;

    @XmlElement(name = "sgguCd")
    private String stateCode; // 시군구코드

    @XmlElement(name = "sidoCd")
    private String cityCode; // 시도 코드

    @XmlElement(name = "sidoCdNm")
    private String cityCodeName;

    @XmlElement(name = "telno")
    private String tel;

    @XmlElement(name = "XPos")
    private String xPos;

    @XmlElement(name = "YPos")
    private String yPos;

    @XmlElement(name = "yadmNm")
    private String name;

    @XmlElement(name = "ykiho")
    private String unknownCode;
}
