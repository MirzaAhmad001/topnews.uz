package dasturlash.uz.repository;

import dasturlash.uz.entities.ProfileRoleEntity;
import dasturlash.uz.enums.ProfileRole;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface ProfileRoleRepository extends CrudRepository<ProfileRoleEntity, Long> {

    @Query("select p.role from ProfileRoleEntity p where p.profileId = ?1")
    List<ProfileRole> getRoleByProfileId(Integer profileId);

    @Transactional
    void deleteRoleByProfileId(Integer profileId);

    @Transactional
    void deleteAllByProfileId(Integer profileId);

    @Transactional
    @Modifying
    @Query("Delete from ProfileRoleEntity where profileId =?1")
    void deleteById(Integer profileId);

}
