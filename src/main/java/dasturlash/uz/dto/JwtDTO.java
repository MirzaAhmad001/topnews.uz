package dasturlash.uz.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class JwtDTO {
    private String username;
    private String role;
    private Integer code;

    public JwtDTO() {}

    public JwtDTO(String username, String role) {
        this.username = username;
        this.role = role;
    }
}


