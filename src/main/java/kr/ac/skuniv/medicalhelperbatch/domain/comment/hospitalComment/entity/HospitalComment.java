package kr.ac.skuniv.medicalhelperbatch.domain.comment.hospitalComment.entity;

import kr.ac.skuniv.medicalhelperbatch.domain.common.JpaBasePersistable;
import kr.ac.skuniv.medicalhelperbatch.domain.hospital.entity.Hospital;
import kr.ac.skuniv.medicalhelperbatch.domain.member.entity.Member;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "hospitalComment")
@AttributeOverride(name = "id", column = @Column(name="hospitalComment_id"))
public class HospitalComment extends JpaBasePersistable {
    private int score; // 5점 만점
    private String comment;

    @ManyToOne
    @JoinColumn(name = "member_id")
    private Member member;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "hospital_id")
    private Hospital hospital;

    @Builder
    public HospitalComment(int score, String comment, Member member, Hospital hospital) {
        this.score = score;
        this.comment = comment;
        this.member = member;
        this.hospital = hospital;
    }
}
