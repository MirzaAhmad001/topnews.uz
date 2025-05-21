package dasturlash.uz.entities;

import dasturlash.uz.enums.RegionNameEn;
import dasturlash.uz.enums.RegionNameRu;
import dasturlash.uz.enums.RegionNameUz;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@Table(name = "Region")
public class RegionEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "order_number")
    private Integer orderNumber;

    @Column
    private RegionNameUz nameUz;

    @Column
    private RegionNameRu nameRu;

    @Column
    private RegionNameEn nameEn;

    @Column
    private Boolean visible;

    @Column
    private String key;

    @Column(name = "created_date")
    private LocalDateTime createdDate;







    //id,order_number,nameUz, nameRu, nameEn, key, visible,created_date
}