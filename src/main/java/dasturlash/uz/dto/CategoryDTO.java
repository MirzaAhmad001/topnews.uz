package dasturlash.uz.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;



@Getter
@Setter
public class CategoryDTO {
    private Integer id;
    @Positive(message = "you can only give positive numbers")
    private Integer orderNumber;
    @NotNull(message = "O'zbekcha nom bo'lishi shart")
    private String nameUz;
    @NotNull(message = "наименования категории объязательно")
    private String nameRu;
    @NotNull(message = "name must be fill")
    private String nameEn;
    private Boolean visible;
    @NotNull(message = "key must have some value")
    private String key;
    private LocalDateTime createDate;
}
