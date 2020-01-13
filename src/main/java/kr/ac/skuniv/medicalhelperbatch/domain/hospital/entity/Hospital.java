package kr.ac.skuniv.medicalhelperbatch.domain.hospital.entity;

import kr.ac.skuniv.medicalhelperbatch.domain.comment.hospitalComment.entity.HospitalComment;
import kr.ac.skuniv.medicalhelperbatch.domain.common.JpaBasePersistable;
import kr.ac.skuniv.medicalhelperbatch.domain.hospital.dto.HospitalItemDto;
import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter @Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "hospital")
@AttributeOverride(name = "id", column = @Column(name="hospital_id"))
public class Hospital extends JpaBasePersistable {

    private String name;
    private String tel;
    private String address;
    private String hospitalCode;
    private String hospitalCodeName;
    private int doctorCount;
    private String openDate;
    private int generalDoctorCount;
    private String hospitalUrl;
    private int internCount;
    private String postNo;
    private int residentCount;
    private int specialDoctorCount;
    private String stateCode; // 시군구코드
    private String cityCode; // 시도 코드
    private String cityCodeName;
    private String xPos;
    private String yPos;

    @OneToMany(mappedBy = "hospital", cascade = CascadeType.ALL)
    private List<HospitalComment> hospitalComment = new ArrayList<>();


    public static Hospital of(HospitalItemDto hospitalItemDto){
        return Hospital.builder()
                .address(hospitalItemDto.getAddress())
                .cityCode(hospitalItemDto.getCityCode())
                .cityCodeName(hospitalItemDto.getCityCodeName())
                .doctorCount(hospitalItemDto.getDoctorCount())
                .generalDoctorCount(hospitalItemDto.getGeneralDoctorCount())
                .hospitalUrl(hospitalItemDto.getHospitalUrl())
                .hospitalCode(hospitalItemDto.getHospitalCode())
                .hospitalCodeName(hospitalItemDto.getHospitalCodeName())
                .internCount(hospitalItemDto.getInternCount())
                .name(hospitalItemDto.getName())
                .openDate(hospitalItemDto.getOpenDate())
                .postNo(hospitalItemDto.getPostNo())
                .residentCount(hospitalItemDto.getResidentCount())
                .specialDoctorCount(hospitalItemDto.getSpecialDoctorCount())
                .stateCode(hospitalItemDto.getStateCode())
                .tel(hospitalItemDto.getTel())
                .xPos(hospitalItemDto.getXPos())
                .yPos(hospitalItemDto.getYPos())
                .build();
    }
}
