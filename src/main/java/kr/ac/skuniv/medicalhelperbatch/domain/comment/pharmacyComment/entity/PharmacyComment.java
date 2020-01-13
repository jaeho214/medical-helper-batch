package kr.ac.skuniv.medicalhelperbatch.domain.comment.pharmacyComment.entity;

import kr.ac.skuniv.medicalhelperbatch.domain.common.JpaBasePersistable;
import kr.ac.skuniv.medicalhelperbatch.domain.member.entity.Member;
import kr.ac.skuniv.medicalhelperbatch.domain.pharmacy.entity.Pharmacy;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "pharmacyComment")
@AttributeOverride(name = "id", column = @Column(name="pharmacyComment_id"))
public class PharmacyComment extends JpaBasePersistable {

    private int score; // 5점 만점
    private String comment;

    @ManyToOne
    @JoinColumn(name = "member_id")
    private Member member;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "pharmacy_id")
    private Pharmacy pharmacy;

    @Builder
    public PharmacyComment(int score, String comment, Member member, Pharmacy pharmacy) {
        this.score = score;
        this.comment = comment;
        this.member = member;
        this.pharmacy = pharmacy;
    }
}