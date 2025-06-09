package dasturlash.uz.repository;

import dasturlash.uz.entities.EmailHistoryEntity;
import dasturlash.uz.entities.SmsHistoryEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface SmsHistoryRepository extends CrudRepository<SmsHistoryEntity, UUID> {

    @Query("from SmsHistoryEntity where toAccount = ?1 order by createdDate desc limit 1")
    Optional<SmsHistoryEntity> findLastByAccount(String account);
}
