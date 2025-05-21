package dasturlash.uz.dto;

import dasturlash.uz.enums.RegionNameEn;
import dasturlash.uz.enums.RegionNameRu;
import dasturlash.uz.enums.RegionNameUz;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;


@Getter
@Setter
public class RegionDTO {
    private Integer id;
    private Integer orderNumber;
    private RegionNameUz nameUz;
    private RegionNameRu nameRu;
    private RegionNameEn nameEn;
    private Boolean visible;
    private String key;
    private LocalDateTime createDate;
}
