package dasturlash.uz.repository;

import dasturlash.uz.entities.ArticleEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface ArticleRepository extends CrudRepository<ArticleEntity, UUID> {
    ArticleEntity findByTitle(String title);
}
