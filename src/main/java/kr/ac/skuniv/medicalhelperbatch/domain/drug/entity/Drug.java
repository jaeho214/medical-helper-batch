package kr.ac.skuniv.medicalhelperbatch.domain.drug.entity;

import kr.ac.skuniv.medicalhelperbatch.domain.common.JpaBasePersistable;
import kr.ac.skuniv.medicalhelperbatch.domain.drug.dto.DrugUpdateRequest;
import kr.ac.skuniv.medicalhelperbatch.global.config.BooleanToYNConverter;
import lombok.*;

import javax.persistence.*;

@Entity
@Getter @Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "drug")
@AttributeOverride(name = "id", column = @Column(name="drug_id"))
public class Drug extends JpaBasePersistable {

    private int day;

    @Convert(converter = BooleanToYNConverter.class)
    private boolean breakfast = false;

    @Convert(converter = BooleanToYNConverter.class)
    private boolean lunch = false;

    @Convert(converter = BooleanToYNConverter.class)
    private boolean dinner = false;

    @Builder
    public Drug(int day, boolean breakfast, boolean lunch, boolean dinner) {
        this.day = day;
        this.breakfast = breakfast;
        this.lunch = lunch;
        this.dinner = dinner;
    }

    public void updateDrug(DrugUpdateRequest drugUpdateRequest){
        this.day = drugUpdateRequest.getDay();
        this.breakfast = drugUpdateRequest.isBreakfast();
        this.lunch = drugUpdateRequest.isLunch();
        this.dinner = drugUpdateRequest.isDinner();
    }
}
