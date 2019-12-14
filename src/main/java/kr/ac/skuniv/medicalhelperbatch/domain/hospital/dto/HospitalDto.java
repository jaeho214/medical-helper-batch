package kr.ac.skuniv.medicalhelperbatch.domain.hospital.dto;

import kr.ac.skuniv.medicalhelperbatch.domain.common.HeaderDto;
import lombok.Getter;
import lombok.ToString;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@ToString
@Getter
@XmlRootElement(name = "response")
public class HospitalDto {
    @XmlElement(name = "body")
    private HospitalBodyDto body;

    @XmlElement(name = "header")
    private HeaderDto header;
}
