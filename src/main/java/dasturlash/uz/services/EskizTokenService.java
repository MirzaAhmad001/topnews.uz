package dasturlash.uz.services;


import dasturlash.uz.dto.EskizTokenDTO;
import dasturlash.uz.dto.sms.SmsRequestDTO;
import dasturlash.uz.entities.EskizTokenEntity;
import dasturlash.uz.repository.EskizTokenRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

@Service
public class EskizTokenService {
    @Autowired
    private EskizTokenRepository repository;
    @Autowired
    private RestTemplate restTemplate;
    private final WebClient webClient = WebClient.builder()
            .baseUrl("https://notify.eskiz.uz")
            .defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
            .build();


    public String getTheLastWorkingToken() {
        EskizTokenEntity tokenEntity = repository.findByEmail("mirzaahmadsodiqov.001@gmail.com");
        if (tokenEntity != null) {
            if (tokenEntity.getCreatedDate().plusDays(29).isAfter(LocalDateTime.now())) {
                return tokenEntity.getToken();
            } else {
                HttpHeaders headers = new HttpHeaders();
                headers.setBearerAuth(tokenEntity.getToken());
                headers.set("Content-Type", "application/json");


                HttpEntity<String> requestEntity = new HttpEntity<>(null, headers);

                EskizTokenDTO dto = webClient.patch()
                        .uri("/api/auth/refresh")
                        .header(HttpHeaders.AUTHORIZATION, "Bearer " + tokenEntity.getToken())
                        .retrieve()
                        .bodyToMono(EskizTokenDTO.class)
                        .block();
                tokenEntity.setCreatedDate(LocalDateTime.now());
                tokenEntity.setToken(Objects.requireNonNull(dto).getData().getToken());
                repository.save(tokenEntity);
                return Objects.requireNonNull(dto).getData().getToken();
            }
        } else {
            String url = "https://notify.eskiz.uz/api/auth/login";

            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);

            Map<String, Object> body1 = new HashMap<>();
            body1.put("email", "mirzaahmadsodiqov.001@gmail.com");
            body1.put("password", "HUWysA76NDfm3PwG85EaArq8bP1J2Xy6TnL7tIoR");

            HttpEntity<Map<String, Object>> request = new HttpEntity<>(body1, headers);

            ResponseEntity<EskizTokenDTO> response = restTemplate.exchange(url, HttpMethod.POST, request, EskizTokenDTO.class);

            if (response.getStatusCode().is2xxSuccessful()) {
                EskizTokenEntity tkEntity = new EskizTokenEntity();
                tkEntity.setEmail("mirzaahmadsodiqov.001@gmail.com");
                tkEntity.setPassword("HUWysA76NDfm3PwG85EaArq8bP1J2Xy6TnL7tIoR");
                tkEntity.setCreatedDate(LocalDateTime.now());
                tkEntity.setToken(Objects.requireNonNull(response.getBody()).getData().getToken());
                repository.save(tkEntity);
                return response.getBody().getData().getToken();
            }
        }
        return "There is some issue with Eskiz token";
    }
}
