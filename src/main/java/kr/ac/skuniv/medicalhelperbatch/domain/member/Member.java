package kr.ac.skuniv.medicalhelperbatch.domain.member;

import kr.ac.skuniv.medicalhelperbatch.domain.drugstore.entity.DrugstoreComment;
import kr.ac.skuniv.medicalhelperbatch.domain.hospital.entity.HospitalComment;
import kr.ac.skuniv.medicalhelperbatch.domain.reservation.Reservation;
import kr.ac.skuniv.medicalhelperbatch.domain.treatment.Treatment;
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

    @OneToMany(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private List<Treatment> treatments = new ArrayList<>();

    @OneToMany(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private List<Reservation> reservations = new ArrayList<>();

    @OneToMany(mappedBy = "member", fetch = FetchType.LAZY)
    private List<DrugstoreComment> drugstoreComment = new ArrayList<>();

    @OneToMany(mappedBy = "member", fetch = FetchType.LAZY)
    private List<HospitalComment> hospitalComments = new ArrayList<>();

}
