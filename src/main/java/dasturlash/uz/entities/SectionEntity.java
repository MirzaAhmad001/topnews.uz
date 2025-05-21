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
@Table(name = "section")
public class SectionEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "order_number")
    private Integer orderNumber;

    @Column (name = "name_uz")
    private String nameUz;

    @Column(name = "name_ru")
    private String nameRu;

    @Column(name = "name_en")
    private String nameEn;

    @Column
    private String key;

    @Column
    private Boolean visible;

    @Column(name = "created_date")
    private LocalDateTime createdDate;

    @Column
    private Integer imageId;



    //Section(Bo'lim)
    //        id,order_number,nameUz, nameRu, nameEn, key, visible,created_date, image_id (optional)
    // (Asosiy,Muharrir tanlovi,Dolzarb, Maqola, Foto yangilik,Interview,Biznes,Surushturuv,Video Yangilik, E'lonlar)

}
