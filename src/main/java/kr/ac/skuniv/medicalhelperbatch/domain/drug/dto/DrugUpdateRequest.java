package kr.ac.skuniv.medicalhelperbatch.domain.drug.dto;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class DrugUpdateRequest {
    private Long id;
    private int day;
    private boolean breakfast;
    private boolean lunch;
    private boolean dinner;

    public DrugUpdateRequest(Long id, int day, boolean breakfast, boolean lunch, boolean dinner) {
        this.id = id;
        this.day = day;
        this.breakfast = breakfast;
        this.lunch = lunch;
        this.dinner = dinner;
    }
}
