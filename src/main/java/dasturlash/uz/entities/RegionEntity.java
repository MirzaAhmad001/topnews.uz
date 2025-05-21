package dasturlash.uz.entities;

import dasturlash.uz.enums.RegionNameEn;
import dasturlash.uz.enums.RegionNameRu;
import dasturlash.uz.enums.RegionNameUz;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

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

    public RegionNameUz getNameUz() {
        return nameUz;
    }

    public void setNameUz(RegionNameUz nameUz) {
        this.nameUz = nameUz;
    }

    public RegionNameRu getNameRu() {
        return nameRu;
    }

    public void setNameRu(RegionNameRu nameRu) {
        this.nameRu = nameRu;
    }

    public RegionNameEn getNameEn() {
        return nameEn;
    }

    public void setNameEn(RegionNameEn nameEn) {
        this.nameEn = nameEn;
    }

    public Boolean getVisible() {
        return visible;
    }

    public void setVisible(Boolean visible) {
        this.visible = visible;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public LocalDateTime getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(LocalDateTime createdDate) {
        this.createdDate = createdDate;
    }

    //id,order_number,nameUz, nameRu, nameEn, key, visible,created_date
}