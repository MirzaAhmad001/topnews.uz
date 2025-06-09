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
@Table(name = "article")
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
    @Enumerated(EnumType.STRING)
    @Column(name = "published_state")
    private PublishedState publishedState = PublishedState.NOT_PUBLISHED;
    private String readTime;
    private LocalDateTime createdDate;
    private LocalDateTime publishedDate;
    private Boolean visible = true;
    private Integer viewCount;
}
