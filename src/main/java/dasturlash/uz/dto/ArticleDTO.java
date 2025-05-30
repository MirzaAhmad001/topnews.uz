package dasturlash.uz.dto;

import dasturlash.uz.entities.RegionEntity;
import dasturlash.uz.enums.PublishedState;
import jakarta.persistence.Column;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.sql.Time;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;


@Getter
@Setter
public class ArticleDTO {
    private UUID id;
    @NotNull
    private String title;
    @NotNull
    private String description;
    @NotNull
    private String content;
    private String sharedCount;
    private Integer imageId;
    private Integer regionId;
    private Integer moderatorId;
    private Integer publisherId;
    private PublishedState publishedState;
    private String readTime;
    private LocalDateTime createdDate;
    private LocalDateTime publishedDate;
    private Boolean visible = true;
    private Integer viewCount;
    private List<Integer> categoryIds = new ArrayList<>();
    private List<Integer> sectionIds = new ArrayList<>();
}
