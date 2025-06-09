package dasturlash.uz.repository;

import dasturlash.uz.entities.EskizTokenEntity;
import org.springframework.data.repository.CrudRepository;

import java.util.UUID;

public interface EskizTokenRepository extends CrudRepository<EskizTokenEntity, UUID> {
    public EskizTokenEntity findByEmail(String email);
}
