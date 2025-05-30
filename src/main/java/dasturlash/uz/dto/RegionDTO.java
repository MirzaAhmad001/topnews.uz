package dasturlash.uz.dto;

import dasturlash.uz.enums.RegionNameEn;
import dasturlash.uz.enums.RegionNameRu;
import dasturlash.uz.enums.RegionNameUz;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;



@Getter
@Setter
public class RegionDTO {
    private Integer id;
    @Positive(message = "you can only give positive numbers")
    private Integer orderNumber;
    @NotNull(message = "O'zbekcha nom bo'lishi shart")
    private RegionNameUz nameUz;
    @NotNull(message = "наименования категории объязательно")
    private RegionNameRu nameRu;
    @NotNull(message = "name must be fill")
    private RegionNameEn nameEn;
    private Boolean visible;
    @NotNull(message = "key must have some value")
    private String key;
    private LocalDateTime createDate;
}
