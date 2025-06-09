package dasturlash.uz.dto.auth;


import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class AuthorizationDTO {
    @NotBlank(message = "username required")
    private String username;
    @NotBlank(message = "pswd required")
    private String password;
}
