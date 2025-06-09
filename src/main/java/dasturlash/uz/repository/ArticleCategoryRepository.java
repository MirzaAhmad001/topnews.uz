package dasturlash.uz.repository;

import dasturlash.uz.entities.ArticleCategoryEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface ArticleCategoryRepository extends CrudRepository<ArticleCategoryEntity, Integer> {
    List<ArticleCategoryEntity> findByArticleId(UUID articleId);
}
