package dasturlash.uz.services;


import dasturlash.uz.dto.ProfileDTO;
import dasturlash.uz.dto.ProfileFilterDTO;
import dasturlash.uz.entities.CategoryEntity;
import dasturlash.uz.entities.ProfileEntity;
import dasturlash.uz.enums.ProfileRole;
import dasturlash.uz.exceptions.AppBadException;
import dasturlash.uz.repository.CustomProfileRepository;
import dasturlash.uz.repository.ProfileRepository;
import dasturlash.uz.repository.ProfileRoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

@Service
public class ProfileService {

    @Autowired
    private ProfileRepository profileRepository;

    @Autowired
    private ProfileRoleService profileRoleService;

    @Autowired
    private ProfileRoleRepository profileRoleRepository;

    @Autowired
    private CustomProfileRepository customProfileRepository;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    // create
    public ProfileDTO create(ProfileDTO dto) {
        ProfileEntity entity1 = profileRepository.findByUsernameAndVisibleTrue(dto.getUsername());
        if (entity1 != null) {
            throw new AppBadException("User already exists");
        }

        ProfileEntity entity = new ProfileEntity();
        entity.setName(dto.getName());
        entity.setSurname(dto.getSurname());
        entity.setUsername(dto.getUsername());
        entity.setPassword(bCryptPasswordEncoder.encode(dto.getPassword()));
        entity.setVisible(Boolean.TRUE);
        entity.setCreatedDate(LocalDateTime.now());
        profileRepository.save(entity);

        profileRoleService.merge(entity.getId(), dto.getRoles());

        ProfileDTO response = toDTO(entity);
        response.setRoles(dto.getRoles());
        return response;
    }


    // for any profile
    public ProfileDTO update(String username, String password, ProfileDTO dto) {
        ProfileEntity entity1 = profileRepository.findByUsernameAndVisibleTrue(username);
        if (entity1 == null) {
            throw new AppBadException("User not found");
        }
        if (!bCryptPasswordEncoder.encode(entity1.getPassword()).matches(password)) {
            throw new AppBadException("Wrong password");
        }

        entity1.setName(dto.getName());
        entity1.setSurname(dto.getSurname());
        entity1.setUsername(dto.getUsername());
        entity1.setPassword(bCryptPasswordEncoder.encode(dto.getPassword()));
        entity1.setStatus(dto.getStatus());
        entity1.setPhotoId(dto.getPhotoId());
        profileRepository.save(entity1);

        profileRoleService.merge(entity1.getId(), dto.getRoles());

        ProfileDTO response = toDTO(entity1);
        response.setRoles(dto.getRoles());
        return response;
    }

    // update By admin
    public ProfileDTO updateByAdmin(String username, String adminPassword, ProfileDTO dto) {
        ProfileEntity adminEntity = profileRepository.findByUsernameAndVisibleTrue(username);
        if (adminEntity == null) {
            throw new AppBadException("username not found");
        }
        if (!bCryptPasswordEncoder.encode(adminEntity.getPassword()).matches(adminPassword)){
            throw new AppBadException("Wrong password");
        }
        List<ProfileRole> adminRoles = profileRoleRepository.getRoleByProfileId(adminEntity.getId());
        if (!adminRoles.contains(ProfileRole.ROLE_ADMIN)) {
            throw new AppBadException("You do not have admin role");
        }

        ProfileEntity userEntity = profileRepository.findByUsernameAndVisibleTrue(dto.getUsername());
        if (userEntity == null) {
            throw new AppBadException("User not found");
        }

        userEntity.setPassword(dto.getPassword());
        userEntity.setUsername(dto.getUsername());
        userEntity.setName(dto.getName());
        userEntity.setSurname(dto.getSurname());
        userEntity.setStatus(dto.getStatus());
        userEntity.setPhotoId(dto.getPhotoId());
        profileRepository.save(userEntity);

        profileRoleService.merge(userEntity.getId(), dto.getRoles());
        return toDTO(userEntity);
    }

    // get Profile list by admin

    public PageImpl<ProfileDTO> paginationList(int size, int page, String password) {
        ProfileEntity admin = profileRepository.findByPassword(password);
        if (admin == null) {
            throw new AppBadException("Invalid password");
        }
        Sort sort = Sort.by("createdDate").descending(); // order by createDate desc
        PageRequest pageRequest = PageRequest.of(page, size, sort); // order by createDate desc limit ? offset ?
        Page<ProfileEntity> pageObj = profileRepository.findAll(pageRequest);// select * from ...

        List<ProfileEntity> entityList = pageObj.getContent();
        Long totalElement = pageObj.getTotalElements();

        List<ProfileDTO> dtoList = new LinkedList<>();
        entityList.forEach(entity -> dtoList.add(toDTO(entity)));

        return new PageImpl<ProfileDTO>(dtoList, pageRequest, totalElement);
    }

    //delete
    public ProfileDTO delete(Integer id, String username, String password) {
        ProfileEntity admin = profileRepository.findByUsernameAndVisibleTrue(username);
        if (admin == null) {
            throw new AppBadException("username not found");
        }
        List<ProfileRole> roles = profileRoleRepository.getRoleByProfileId(admin.getId());
        if (!roles.contains(ProfileRole.ROLE_ADMIN)) {
            throw new AppBadException("You do not have admin role");
        }
        if (!bCryptPasswordEncoder.encode(admin.getPassword()).matches(password)) {
            throw new AppBadException("Wrong password");
        }
        Optional<ProfileEntity> optional = profileRepository.findById(id);
        if (optional.isPresent()) {
            profileRoleRepository.deleteAllByProfileId(id);
            profileRepository.deleteById(id);
            return toDTO(optional.get());
        }
        return null;
    }

    //filter


    private ProfileDTO toDTO(ProfileEntity entity) {
        ProfileDTO dto = new ProfileDTO();
        dto.setId(entity.getId());
        dto.setName(entity.getName());
        dto.setSurname(entity.getSurname());
        dto.setUsername(entity.getUsername());
        dto.setStatus(entity.getStatus());
        dto.setCreatedDate(entity.getCreatedDate());
        dto.setVisible(entity.getVisible());
        dto.setPhotoId(entity.getPhotoId());

        return dto;
    }

    public Page<ProfileDTO> filter(ProfileFilterDTO filterDTO, int page, int size) {
        Page<ProfileEntity> pageObj = customProfileRepository.filter(filterDTO, page, size);

        List<ProfileEntity> entityList = pageObj.getContent();
        Long totalAmount = pageObj.getTotalElements();

        List<ProfileDTO> dtoList = new LinkedList<>();
        entityList.forEach(entity -> dtoList.add(toDTO(entity)));

        return new PageImpl<ProfileDTO>(dtoList, PageRequest.of(page, size), totalAmount);
    }


}
