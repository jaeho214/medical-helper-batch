package kr.ac.skuniv.medicalhelperbatch.domain.drugstore.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.xml.bind.annotation.*;
import java.util.List;

@Getter @Setter
@NoArgsConstructor
@XmlRootElement(name = "body")
@XmlAccessorType(XmlAccessType.FIELD)
public class DrugstoreBodyDto {
    @XmlElementWrapper(name = "items")// items안에
    @XmlElement(name = "item")//item들이 들어있음
    private List<DrugstoreItemDto> item;

    @XmlElement(name = "numOfRows")
    private int numOfRows;

    @XmlElement(name = "pageNo")
    private int pageNo;

    @XmlElement(name = "totalCount")
    private int totalCount;
}
