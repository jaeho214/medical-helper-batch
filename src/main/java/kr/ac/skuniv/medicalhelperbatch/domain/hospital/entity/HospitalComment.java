package kr.ac.skuniv.medicalhelperbatch.domain.hospital.entity;

import kr.ac.skuniv.medicalhelperbatch.domain.member.Member;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor
public class HospitalComment {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long hcNo;

    private Float score; // 5점 만점
    private String comment;

    @ManyToOne
    @JoinColumn(name = "member_id")
    private Member member;

    public HospitalComment(Float score, String comment, Member member) {
        this.score = score;
        this.comment = comment;
        this.member = member;
    }
}
