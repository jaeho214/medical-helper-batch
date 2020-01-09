package kr.ac.skuniv.medicalhelperbatch.domain.treatment.entity;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Drug {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long drugNo;

    @Convert
    private boolean breakfast = false;

    @Convert
    private boolean lunch = false;

    @Convert
    private boolean dinner = false;
}
