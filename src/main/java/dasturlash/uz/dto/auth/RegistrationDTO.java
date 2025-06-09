package dasturlash.uz.dto.auth;


import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RegistrationDTO {
    private String name;
    private String username;
    private String password;
    private String surname;
}
