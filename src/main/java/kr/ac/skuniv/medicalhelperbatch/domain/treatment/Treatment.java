package kr.ac.skuniv.medicalhelperbatch.domain.treatment;

import kr.ac.skuniv.medicalhelperbatch.domain.hospital.entity.Hospital;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Treatment {
    @Id
    private Long tno;

    private String solution; // 받은 처방 기록
    private String doctor; // 의사명

    @OneToOne
    @JoinColumn(name = "hospital_no")
    private Hospital hospital;

    public Treatment(String solution, String doctor, Hospital hospital) {
        this.solution = solution;
        this.doctor = doctor;
        this.hospital = hospital;
    }
}