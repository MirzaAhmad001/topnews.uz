package dasturlash.uz.entities;


import dasturlash.uz.enums.ProfileRole;
import dasturlash.uz.enums.ProfileStatus;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.List;


@Getter
@Setter
@Entity
@Table(name = "profile")
public class ProfileEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "surname", nullable = false)
    private String surname;

    @Column(name = "username", nullable = false)
    private String username;

    @Column(name = "password", nullable = false)
    private String password;

    @Enumerated(EnumType.STRING)
    @Column
    private ProfileStatus status = ProfileStatus.NOT_ACTIVE;

    @Column
    private LocalDateTime createdDate;

    @Column(nullable = false)
    private Boolean visible = true;

    @Column(name = "photo_id")
    private Integer photoId;

    @Column(name = "attempt_count")
    private Integer attemptCount = 0 ;
}
