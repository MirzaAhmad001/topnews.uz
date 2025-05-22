package dasturlash.uz.dto;

import dasturlash.uz.enums.RegionNameEn;
import dasturlash.uz.enums.RegionNameRu;
import dasturlash.uz.enums.RegionNameUz;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;


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

    public LocalDateTime getCreateDate() {
        return createDate;
    }

    public void setCreateDate(LocalDateTime createDate) {
        this.createDate = createDate;
    }
}
