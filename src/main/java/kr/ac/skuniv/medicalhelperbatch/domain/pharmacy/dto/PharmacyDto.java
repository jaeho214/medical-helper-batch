package kr.ac.skuniv.medicalhelperbatch.domain.pharmacy.dto;

import kr.ac.skuniv.medicalhelperbatch.domain.common.HeaderDto;
import lombok.Getter;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@Getter
//response라는 태그 내에
@XmlRootElement(name = "response")
public class PharmacyDto {
    //바디와
    @XmlElement(name = "body")
    private PharmacyBodyDto body;
    //헤더가 존재
    @XmlElement(name = "header")
    private HeaderDto header;
}
