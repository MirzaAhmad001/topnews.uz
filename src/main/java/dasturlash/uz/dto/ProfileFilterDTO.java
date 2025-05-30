package dasturlash.uz.dto;

import dasturlash.uz.enums.ProfileRole;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;


@Getter
@Setter
public class ProfileFilterDTO {
    private String name;
    private String surname;
    private String userName;
    private ProfileRole roles;
    private LocalDateTime createdDateFrom;
    private LocalDateTime createdDateTo;
}
