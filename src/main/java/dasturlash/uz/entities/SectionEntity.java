package dasturlash.uz.entities;


import dasturlash.uz.enums.RegionNameEn;
import dasturlash.uz.enums.RegionNameRu;
import dasturlash.uz.enums.RegionNameUz;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;


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

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Integer getOrderNumber() {
        return orderNumber;
    }

    public void setOrderNumber(Integer orderNumber) {
        this.orderNumber = orderNumber;
    }

    public String getNameUz() {
        return nameUz;
    }

    public void setNameUz(String nameUz) {
        this.nameUz = nameUz;
    }

    public String getNameRu() {
        return nameRu;
    }

    public void setNameRu(String nameRu) {
        this.nameRu = nameRu;
    }

    public String getNameEn() {
        return nameEn;
    }

    public void setNameEn(String nameEn) {
        this.nameEn = nameEn;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public Boolean getVisible() {
        return visible;
    }

    public void setVisible(Boolean visible) {
        this.visible = visible;
    }

    public LocalDateTime getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(LocalDateTime createdDate) {
        this.createdDate = createdDate;
    }

    public Integer getImageId() {
        return imageId;
    }

    public void setImageId(Integer imageId) {
        this.imageId = imageId;
    }


    //Section(Bo'lim)
    //        id,order_number,nameUz, nameRu, nameEn, key, visible,created_date, image_id (optional)
    // (Asosiy,Muharrir tanlovi,Dolzarb, Maqola, Foto yangilik,Interview,Biznes,Surushturuv,Video Yangilik, E'lonlar)

}
