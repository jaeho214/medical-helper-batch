package kr.ac.skuniv.medicalhelperbatch.global.fcm;

import kr.ac.skuniv.medicalhelperbatch.domain.drug.entity.Drug;
import kr.ac.skuniv.medicalhelperbatch.domain.drug.repository.DrugRepository;
import kr.ac.skuniv.medicalhelperbatch.domain.member.entity.Member;
import kr.ac.skuniv.medicalhelperbatch.domain.treatment.entity.Treatment;
import kr.ac.skuniv.medicalhelperbatch.domain.treatment.repository.TreatmentRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ExecutionException;
import java.util.stream.Collectors;

@Service
public class NotificationService {

    private static final Logger logger = LoggerFactory.getLogger(NotificationService.class);
    private final Map<String, String> tokenMap = new HashMap<>();

    private final FCMService fcmService;
    private final DrugRepository drugRepository;
    private final TreatmentRepository treatmentRepository;

    public NotificationService(FCMService fcmService, DrugRepository drugRepository, TreatmentRepository treatmentRepository) {
        this.fcmService = fcmService;
        this.drugRepository = drugRepository;
        this.treatmentRepository = treatmentRepository;
    }

    public void register(final String userId, final String token){
        tokenMap.put(userId, token);
    }

    public void deleteToken(final String userId){
        tokenMap.remove(userId);
    }

    public String getToken(final String userId){
        return tokenMap.get(userId);
    }

    public void sendNotification(final NotificationRequest request){
        try{
            fcmService.send(request);
        } catch (InterruptedException | ExecutionException e) {
            logger.error(e.getMessage());
        }
    }

    //아침, 점심, 저녁마다 True인 Drug를 리턴
    public List<Drug> notificationBreakfast(boolean when){
        return drugRepository.findByBreakfast(when);
    }
    public List<Drug> notificationLunch(boolean when){
        return drugRepository.findByBreakfast(when);
    }
    public List<Drug> notificationDinner(boolean when){
        return drugRepository.findByBreakfast(when);
    }

    //그 약들의 주인 Member들 리턴
    public List<Member> getTokenFromDrug(List<Drug> drugs){
        return drugs.stream()
                .map(drug -> treatmentRepository.findByDrug(drug))
                .map(Optional::get)
                .map(Treatment::getMember)
                .collect(Collectors.toList());
    }

    //그 Member들의 토큰과 이름은 꺼내서 NotificationRequest에 저장
    public List<NotificationRequest> readyToNotification(List<Member> users){
        return users.stream()
                .map(NotificationRequest::setDrugTimeNotification)
                .collect(Collectors.toList());
    }

    //TODO: 이제 NofiticationRequest 을 send에 매개변수로 넣어서 알람 보내면 됨, 스케줄러 사용해서 시간별로 보내기.




}
