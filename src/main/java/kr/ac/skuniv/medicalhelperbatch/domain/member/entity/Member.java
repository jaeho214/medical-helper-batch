package kr.ac.skuniv.medicalhelperbatch.domain.member.entity;

import kr.ac.skuniv.medicalhelperbatch.domain.comment.pharmacyComment.entity.PharmacyComment;
import kr.ac.skuniv.medicalhelperbatch.domain.comment.hospitalComment.entity.HospitalComment;
import kr.ac.skuniv.medicalhelperbatch.domain.common.JpaBasePersistable;
import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;


@Entity
@Getter @Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "member")
@AttributeOverride(name = "id", column = @Column(name="member_id"))
public class Member extends JpaBasePersistable {

    @Column(name = "email", unique = true)
    private String email;

    private String password;
    private String name;
    private String phone;
    private String birth;
    private String sex;
    private String address;

    private String fcmToken;

    @OneToMany(mappedBy = "member", fetch = FetchType.LAZY)
    private List<PharmacyComment> pharmacyComment = new ArrayList<>();

    @OneToMany(mappedBy = "member", fetch = FetchType.LAZY)
    private List<HospitalComment> hospitalComments = new ArrayList<>();

    @Builder
    public Member(String email, String password, String name, String phone, String birth, String sex, String address, String fcmToken) {
        this.email = email;
        this.password = password;
        this.name = name;
        this.phone = phone;
        this.birth = birth;
        this.sex = sex;
        this.address = address;
        this.fcmToken = fcmToken;
    }
}
