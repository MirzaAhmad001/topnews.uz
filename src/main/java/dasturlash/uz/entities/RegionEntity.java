package dasturlash.uz.entities;

import dasturlash.uz.enums.RegionNameEn;
import dasturlash.uz.enums.RegionNameRu;
import dasturlash.uz.enums.RegionNameUz;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Setter
@Getter
@Entity
@Table(name = "Region")
public class RegionEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column
    private Integer orderNumber;

    @Enumerated(EnumType.STRING)
    @Column
    private RegionNameUz nameUz;

    @Enumerated(EnumType.STRING)
    @Column
    private RegionNameRu nameRu;

    @Enumerated(EnumType.STRING)
    @Column
    private RegionNameEn nameEn;

    @Column
    private Boolean visible = true;

    @Column
    private String key;

    @Column
    private LocalDateTime createdDate;
}