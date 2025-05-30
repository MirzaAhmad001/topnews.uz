package dasturlash.uz.entities;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class ArticleSectionEntity {
    @Id
    @GeneratedValue
    private Integer id;

    @JoinColumn
    @ManyToOne
    private ArticleEntity article;

    @JoinColumn
    @ManyToOne
    private SectionEntity section;
}
