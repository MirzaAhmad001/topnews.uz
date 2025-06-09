package dasturlash.uz.entities;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
@Getter
@Setter
@Entity
@Table(name = "article_category")
public class ArticleCategoryEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;


    @JoinColumn
    @ManyToOne
    private ArticleEntity article;

    @JoinColumn
    @ManyToOne
    private CategoryEntity category;

}
