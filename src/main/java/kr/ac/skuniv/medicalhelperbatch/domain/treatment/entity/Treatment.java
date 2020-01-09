package kr.ac.skuniv.medicalhelperbatch.domain.treatment.entity;

import kr.ac.skuniv.medicalhelperbatch.domain.member.entity.Member;
import kr.ac.skuniv.medicalhelperbatch.domain.reservation.Reservation;
import kr.ac.skuniv.medicalhelperbatch.domain.treatment.entity.Drug;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Treatment {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long tno;

    private String title; // 진단명
    private String solution; // 받은 처방 기록
    private String doctor; // 의사명

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "reservation_no")
    private Reservation reservation;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    @OneToOne(mappedBy = "treatment", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "drug_no")
    private Drug drug;

    @Builder
    public Treatment(String title, String solution, String doctor, Reservation reservation, Member member, Drug drug) {
        this.title = title;
        this.solution = solution;
        this.doctor = doctor;
        this.reservation = reservation;
        this.member = member;
        this.drug = drug;
    }
}