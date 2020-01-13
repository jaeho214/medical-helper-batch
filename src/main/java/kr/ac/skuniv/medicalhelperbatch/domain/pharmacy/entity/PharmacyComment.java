package kr.ac.skuniv.medicalhelperbatch.domain.pharmacy.entity;

import kr.ac.skuniv.medicalhelperbatch.domain.member.entity.Member;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class PharmacyComment {

    private Float score; // 5점 만점
    private String comment;

    @ManyToOne
    @JoinColumn(name = "member_id")
    private Member member;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "drugstore_id")
    private Pharmacy pharmacy;

    @Builder
    public PharmacyComment(Float score, String comment, Member member, Pharmacy pharmacy) {
        this.score = score;
        this.comment = comment;
        this.member = member;
        this.pharmacy = pharmacy;
    }
}