package dasturlash.uz.services;

import dasturlash.uz.entities.EmailHistoryEntity;
import dasturlash.uz.entities.SmsHistoryEntity;
import dasturlash.uz.repository.SmsHistoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;

@Service
public class SmsHistoryService {
    @Autowired
    private SmsHistoryRepository smsHistoryRepository;

    public void create(String userTo, String message, Integer code) {
        SmsHistoryEntity sms = new SmsHistoryEntity();
        sms.setBody(message);
        sms.setCreatedDate(LocalDateTime.now());
        sms.setCode(code);
        sms.setToAccount(userTo);
        smsHistoryRepository.save(sms);
    }

    public boolean isSmsSendToAccount(String account, int code) {
        Optional<SmsHistoryEntity> optional = smsHistoryRepository.findLastByAccount(account);
        if (optional.isEmpty()) {
            return false;
        }
        SmsHistoryEntity entity = optional.get();
        if (!entity.getCode().equals(code)) {
            return false;
        }
        LocalDateTime extDate = entity.getCreatedDate().plusMinutes(2);
        if (LocalDateTime.now().isAfter(extDate)) {
            return false;
        }
        return true;
    }
}
