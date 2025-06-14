package dasturlash.uz.repository;

import dasturlash.uz.entities.ProfileEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.Optional;

public interface ProfileRepository extends CrudRepository<ProfileEntity, Integer>, PagingAndSortingRepository<ProfileEntity, Integer> {

    ProfileEntity findByPassword(String password);

    ProfileEntity findByUsernameAndVisibleTrue(String username);

    Optional<ProfileEntity> findByUsernameAndVisibleIsTrue(String username);
}
