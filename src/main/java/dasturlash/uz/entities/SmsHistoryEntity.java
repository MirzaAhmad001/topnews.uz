package dasturlash.uz.entities;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Setter
@Entity
@Table(name = "SmsHistory")
public class SmsHistoryEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(name = "to_account")
    private String toAccount;

    @Column(name = "body")
    private String body;

    @Column(name = "code")
    private Integer code;

    @Column(name = "created_date")
    @CreationTimestamp
    private LocalDateTime createdDate;
}
