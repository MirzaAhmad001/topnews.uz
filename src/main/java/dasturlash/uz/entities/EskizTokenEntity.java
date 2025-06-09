package dasturlash.uz.entities;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Setter
@Entity
@Table(name = "eskiz_token")
public class EskizTokenEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(name = "email")
    private String email =  "mirzaahmadsodiqov.001@gmail.com";
    @Column(name = "password")
    private String password = "HUWysA76NDfm3PwG85EaArq8bP1J2Xy6TnL7tIoR";
    @Column(name = "token", columnDefinition = "TEXT")
    private String token;
    @Column(name = "created_date")
    private LocalDateTime createdDate;
}
