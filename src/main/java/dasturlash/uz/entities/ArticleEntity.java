package dasturlash.uz.entities;


import dasturlash.uz.enums.PublishedState;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.sql.Time;
import java.time.LocalDateTime;
import java.util.UUID;


@Getter
@Setter
@Entity
public class ArticleEntity {

    public ArticleEntity() {
        this.id = UUID.randomUUID();
    }

    @Id
    private UUID id;
    private String title;
    private String description;
    private String content;
    private String sharedCount;
    private Integer imageId;
    @Column(name = "region_id")
    private Integer regionId;
    @JoinColumn(insertable=false, updatable=false)
    @ManyToOne(fetch = FetchType.LAZY)
    private RegionEntity region;
    private Integer moderatorId;
    private Integer publisherId;
    private PublishedState publishedState;
    private String readTime;
    private LocalDateTime createdDate;
    private LocalDateTime publishedDate;
    private Boolean visible = true;
    private Integer viewCount;
}
