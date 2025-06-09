package dasturlash.uz.services;

import dasturlash.uz.dto.sms.SmsRequestDTO;
import dasturlash.uz.exceptions.AppBadException;
import dasturlash.uz.util.JwtUtil;
import dasturlash.uz.util.RandomUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.RequestEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class SmsSendService {
    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private SmsHistoryService smsHistoryService;
    @Autowired
    private EskizTokenService eskizTokenService;

    public void sendRegistrationSMS(String phone) {
        if (!phone.matches("^998\\d{9}$")) {
            throw new AppBadException("Invalid phone number");
        }
        Integer smsCode = RandomUtil.fiveDigit();
        String body = "Bu Eskiz dan test";

        /*String body = "Click there: http://localhost:8081/api/v1/auth/registration/sms/verification/%s";
        String jwtToken = JwtUtil.encodeForRegistration(phone, smsCode);
        body = String.format(body, jwtToken);
        */

        sendSms(phone, body);
        smsHistoryService.create(phone, body, smsCode);
    }

    private void sendSms(String phone, String message) {
        SmsRequestDTO body = new SmsRequestDTO();
        body.setMobile_phone(phone);
        body.setMessage(message);

        String url = "https://notify.eskiz.uz/api/message/sms/send";
        String currentToken = eskizTokenService.getTheLastWorkingToken();

        HttpHeaders headers = new HttpHeaders();
        headers.set("Content-Type", "application/json");
        headers.set("Authorization", "Bearer " + currentToken);

        RequestEntity<SmsRequestDTO> request = RequestEntity
                .post(url)
                .headers(headers)
                .body(body);

        restTemplate.exchange(request, String.class);

    }
    // "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJleHAiOjE3NTE5NzQ1MjksImlhdCI6MTc0OTM4MjUyOSwicm9sZSI6InRlc3QiLCJzaWduIjoiMTAwZWZhMjBjNDdlMjFhYjQwZjI5YWExYTU4ODk3ZDNhNTA5YzIyY2VkMDc3ZmNlOWU1MDkwNjdmMDEwOTA0NiIsInN1YiI6IjExMjgxIn0.HTPlb7G2liheGWaCCIZIz0h6j5-DMpK9fnPLpDrgjkk"
    //  eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJleHAiOjE3NTIwNjUyNTUsImlhdCI6MTc0OTQ3MzI1NSwicm9sZSI6InRlc3QiLCJzaWduIjoiMTAwZWZhMjBjNDdlMjFhYjQwZjI5YWExYTU4ODk3ZDNhNTA5YzIyY2VkMDc3ZmNlOWU1MDkwNjdmMDEwOTA0NiIsInN1YiI6IjExMjgxIn0.ElW2z6ycaJKZQrM-9MoMBsGYEjs1cBGsjlRx0IoBYGM
    //  eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJleHAiOjE3NTIwNjUyNTUsImlhdCI6MTc0OTQ3MzI1NSwicm9sZSI6InRlc3QiLCJzaWduIjoiMTAwZWZhMjBjNDdlMjFhYjQwZjI5YWExYTU4ODk3ZDNhNTA5YzIyY2VkMDc3ZmNlOWU1MDkwNjdmMDEwOTA0NiIsInN1YiI6IjExMjgxIn0.ElW2z6ycaJKZQrM-9MoMBsGYEjs1cBGsjlRx0IoBYGM
    //  eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJleHAiOjE3NTIwNjgyMTUsImlhdCI6MTc0OTQ3NjIxNSwicm9sZSI6InRlc3QiLCJzaWduIjoiMTAwZWZhMjBjNDdlMjFhYjQwZjI5YWExYTU4ODk3ZDNhNTA5YzIyY2VkMDc3ZmNlOWU1MDkwNjdmMDEwOTA0NiIsInN1YiI6IjExMjgxIn0.UTakfCQZTO12tD0h5f6gqGNzSBXzxpelK3GWHAOj2Gk
}
