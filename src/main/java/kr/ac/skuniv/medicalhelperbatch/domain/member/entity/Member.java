package kr.ac.skuniv.medicalhelperbatch.domain.member.entity;

import kr.ac.skuniv.medicalhelperbatch.domain.drugstore.entity.DrugstoreComment;
import kr.ac.skuniv.medicalhelperbatch.domain.hospital.entity.HospitalComment;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;


@Entity
@Getter
@Setter
@NoArgsConstructor
public class Member {
    @Id
    private String userId;

    private String password;
    private String name;
    private String phone;
    private String birth;
    private String sex;
    private String address;

    private String fcmToken;

    @OneToMany(mappedBy = "member", fetch = FetchType.LAZY)
    private List<DrugstoreComment> drugstoreComment = new ArrayList<>();

    @OneToMany(mappedBy = "member", fetch = FetchType.LAZY)
    private List<HospitalComment> hospitalComments = new ArrayList<>();

    @Builder
    public Member(String userId, String password, String name, String phone, String birth, String sex, String address, String fcmToken) {
        this.userId = userId;
        this.password = password;
        this.name = name;
        this.phone = phone;
        this.birth = birth;
        this.sex = sex;
        this.address = address;
        this.fcmToken = fcmToken;
    }
}
