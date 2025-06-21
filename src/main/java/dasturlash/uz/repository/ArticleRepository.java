package dasturlash.uz.repository;

import dasturlash.uz.entity.ArticleEntity;
import org.springframework.data.repository.CrudRepository;

public interface ArticleRepository extends CrudRepository<ArticleEntity, String> {
}
