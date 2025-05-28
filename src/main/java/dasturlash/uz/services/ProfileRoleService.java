package dasturlash.uz.services;

import dasturlash.uz.entities.ProfileRoleEntity;
import dasturlash.uz.enums.ProfileRole;
import dasturlash.uz.repository.ProfileRoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProfileRoleService {
    @Autowired
    ProfileRoleRepository profileRoleRepository;

    public void merge(Integer profileId, List<ProfileRole> newList) {
        List<ProfileRole> oldList = profileRoleRepository.getRoleByProfileId(profileId);
        newList.stream().filter(m -> !oldList.contains(m)).forEach(pe -> create(profileId, pe));
        oldList.stream().filter(n -> !newList.contains(n)).forEach(pe -> profileRoleRepository.deleteRoleByProfileId(profileId));
    }

    private void create(Integer profileId, ProfileRole pe) {
        ProfileRoleEntity entity = new ProfileRoleEntity();
        entity.setProfileId(profileId);
        entity.setRole(pe);
        profileRoleRepository.save(entity);
    }
}
