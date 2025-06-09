package dasturlash.uz.repository;

import dasturlash.uz.entities.ArticleSectionEntity;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Repository
public interface ArticleSectionRepository extends CrudRepository<ArticleSectionEntity, Integer> {
    List<ArticleSectionEntity> findByArticleId(UUID articleId);

    @Transactional
    @Modifying
    void deleteAllBySectionId(Integer sectionId);

    void deleteByArticleId(UUID articleId);
}
