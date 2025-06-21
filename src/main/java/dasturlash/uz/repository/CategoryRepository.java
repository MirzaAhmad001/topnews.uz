package dasturlash.uz.repository;

import dasturlash.uz.entity.CategoryEntity;
import dasturlash.uz.enums.AppLanguageEnum;
import dasturlash.uz.mapper.CategoryMapper;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface CategoryRepository extends CrudRepository<CategoryEntity, Integer> {

    @Query("from CategoryEntity where visible = true order by orderNumber asc")
    List<CategoryEntity> getAllByOrderSorted();

    @Transactional
    @Modifying
    @Query("update CategoryEntity set visible = false where id = ?1")
    int updateVisibleById(Integer id);

    Optional<CategoryEntity> findByIdAndVisibleIsTrue(Integer id);

    Optional<CategoryEntity> findByCategoryKey(String key);

    @Query("SELECT c.id AS id, " +
            "CASE :lang " +
            "   WHEN 'uz' THEN c.nameUz " +
            "   WHEN 'ru' THEN c.nameRu " +
            "   WHEN 'en' THEN c.nameEn " +
            "END AS name, " +
            "c.orderNumber AS orderNumber, " +
            "c.categoryKey AS categoryKey " +
            "FROM CategoryEntity c " +
            "WHERE c.visible = true order by orderNumber asc")
    List<CategoryMapper> getByLang(@Param("lang") AppLanguageEnum lang);
}
