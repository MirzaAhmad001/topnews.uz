package dasturlash.uz.dto.article;

import dasturlash.uz.dto.CategoryDTO;
import dasturlash.uz.dto.SectionDTO;
import dasturlash.uz.enums.ArticleStatus;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
public class ArticleDTO {
    private String id;
    private String title;
    private String description;
    private String content;
    private Long sharedCount;

    private String imageId;
    private Integer regionId;

    private Integer moderatorId;
    private Integer publisherId;

    private ArticleStatus status;

    private Integer readTime; // in second
    private Integer viewCount; // in second
    private LocalDateTime publishedDate;
    private List<CategoryDTO> categoryList;
    private List<SectionDTO> sectionList;
}
