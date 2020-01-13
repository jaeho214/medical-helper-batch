package kr.ac.skuniv.medicalhelperbatch.domain.treatment.entity;

import kr.ac.skuniv.medicalhelperbatch.domain.common.JpaBasePersistable;
import kr.ac.skuniv.medicalhelperbatch.domain.drug.entity.Drug;
import kr.ac.skuniv.medicalhelperbatch.domain.member.entity.Member;
import kr.ac.skuniv.medicalhelperbatch.domain.reservation.Reservation;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "treatment")
@AttributeOverride(name = "id", column = @Column(name="treatment_id"))
public class Treatment extends JpaBasePersistable {

    private String title; // 진단명
    private String solution; // 받은 처방 기록
    private String doctor; // 의사명

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "reservation_no")
    private Reservation reservation;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "drug_id")
    private Drug drug;

    @Builder
    public Treatment(String title, String solution, String doctor, Reservation reservation, Member member, Drug drug) {
        this.title = title;
        this.solution = solution;
        this.doctor = doctor;
        this.reservation = reservation;
        this.member = member;
        this.drug = drug;
    }}