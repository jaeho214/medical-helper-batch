package kr.ac.skuniv.medicalhelperbatch.domain.hospital.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.xml.bind.annotation.*;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@XmlRootElement(name = "body")
@XmlAccessorType(XmlAccessType.FIELD)
public class HospitalBodyDto {

    @XmlElementWrapper(name = "items")
    @XmlElement(name = "item")
    private List<HospitalItemDto> item;

    @XmlElement(name = "numOfRows")
    private int numOfRows;

    @XmlElement(name = "pageNo")
    private int pageNo;

    @XmlElement(name = "totalCount")
    private int totalCount;
}
