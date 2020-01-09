package kr.ac.skuniv.medicalhelperbatch.global.fcm;


import kr.ac.skuniv.medicalhelperbatch.domain.member.entity.Member;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Getter
@ToString
@NoArgsConstructor
public class NotificationRequest {

    private String title;
    private String message;
    private String token;

    @Builder
    public NotificationRequest(String title, String message, String token) {
        this.title = title;
        this.message = message;
        this.token = token;
    }

    public static NotificationRequest setDrugTimeNotification(Member member){
        return NotificationRequest.builder()
                .title("약 먹을 시간")
                .message(member.getName() + "님 약 먹으실 시간이에요!")
                .token(member.getFcmToken())
                .build();
    }

}
