package dasturlash.uz.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;



public class CategoryDTO {
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
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

    public LocalDateTime getCreateDate() {
        return createDate;
    }

    public void setCreateDate(LocalDateTime createDate) {
        this.createDate = createDate;
    }

    private Integer id;
    private Integer orderNumber;
    private String nameUz;
    private String nameRu;
    private String nameEn;
    private Boolean visible;
    private String key;
    private LocalDateTime createDate;
}
