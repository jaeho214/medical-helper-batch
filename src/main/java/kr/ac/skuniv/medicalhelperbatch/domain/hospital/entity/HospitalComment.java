package kr.ac.skuniv.medicalhelperbatch.domain.hospital.entity;

import kr.ac.skuniv.medicalhelperbatch.domain.member.entity.Member;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class HospitalComment {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long hcNo;

    private Float score; // 5점 만점
    private String comment;

    @ManyToOne
    @JoinColumn(name = "member_id")
    private Member member;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "hospitalNo")
    private Hospital hospital;

    @Builder
    public HospitalComment(Float score, String comment, Member member, Hospital hospital) {
        this.score = score;
        this.comment = comment;
        this.member = member;
        this.hospital = hospital;
    }
}
