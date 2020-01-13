package kr.ac.skuniv.medicalhelperbatch.domain.reservation;

import kr.ac.skuniv.medicalhelperbatch.domain.common.JpaBasePersistable;
import kr.ac.skuniv.medicalhelperbatch.domain.hospital.entity.Hospital;
import kr.ac.skuniv.medicalhelperbatch.domain.member.entity.Member;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "reservation")
@AttributeOverride(name = "id", column = @Column(name="reservation_id"))
public class Reservation extends JpaBasePersistable {

    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm")
    private LocalDateTime reserveDate;
    private String symptom;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "hospital_no")
    private Hospital hospital;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    @Builder
    public Reservation(Hospital hospital, LocalDateTime reserveDate, String symptom, Member member) {
        this.hospital = hospital;
        this.reserveDate = reserveDate;
        this.symptom = symptom;
        this.member = member;
    }
}
