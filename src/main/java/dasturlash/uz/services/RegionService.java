package dasturlash.uz.services;

import dasturlash.uz.dto.RegionDTO;
import dasturlash.uz.dto.RegionResponseDTO;
import dasturlash.uz.entities.RegionEntity;
import dasturlash.uz.enums.Language;
import dasturlash.uz.repository.RegionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class RegionService {

    @Autowired
    RegionRepository regionRepository;

    public List<RegionDTO> getAllRegions() {
        Iterable<RegionEntity> regions = regionRepository.findAll();
        List<RegionDTO> regionDTOs = new ArrayList<>();

        regions.forEach(region -> regionDTOs.add(toDTO(region)));
        return regionDTOs;
    }

    private RegionDTO toDTO(RegionEntity regionEntity) {
        RegionDTO regionDTO = new RegionDTO();
        regionDTO.setId(regionEntity.getId());
        regionDTO.setNameUz(regionEntity.getNameUz());
        regionDTO.setNameRu(regionEntity.getNameRu());
        regionDTO.setNameEn(regionEntity.getNameEn());
        regionDTO.setOrderNumber(regionEntity.getOrderNumber());
        regionDTO.setKey(regionEntity.getKey());
        regionDTO.setCreateDate(regionEntity.getCreatedDate());

        return regionDTO;
    }

    public RegionDTO create(RegionDTO regionDTO) {
        RegionEntity regionEntity = new RegionEntity();
        regionEntity.setNameUz(regionDTO.getNameUz());
        regionEntity.setNameRu(regionDTO.getNameRu());
        regionEntity.setNameEn(regionDTO.getNameEn());
        regionEntity.setOrderNumber(regionDTO.getOrderNumber());
        regionEntity.setKey(regionDTO.getKey());
        regionEntity.setCreatedDate(regionDTO.getCreateDate());

        regionRepository.save(regionEntity);
        regionDTO.setId(regionEntity.getId());

        return regionDTO;
    }

    public RegionDTO update(RegionDTO regionDTO, Integer id) {
        Optional<RegionEntity> regionEntity = regionRepository.findById(id);
        if (regionEntity.isPresent()) {
            RegionEntity entity = regionEntity.get();
            entity.setNameUz(regionDTO.getNameUz());
            entity.setNameRu(regionDTO.getNameRu());
            entity.setNameEn(regionDTO.getNameEn());
            entity.setOrderNumber(regionDTO.getOrderNumber());
            entity.setKey(regionDTO.getKey());
            entity.setCreatedDate(regionDTO.getCreateDate());
            regionRepository.save(entity);
            return regionDTO;
        }
        return null;
    }

    public RegionDTO delete(Integer id) {
        Optional<RegionEntity> regionEntity = regionRepository.findById(id);
        if (regionEntity.isPresent()) {
            RegionEntity entity = regionEntity.get();
            regionRepository.delete(entity);
            return toDTO(entity);
        }
        return null;
    }

    public List<RegionResponseDTO> getListByLanguage(Language language) {
        Iterable<RegionEntity> entities = regionRepository.findAll();
        List<RegionResponseDTO> regionResponseDTOs = new ArrayList<>();

        entities.forEach(entity -> {
            RegionResponseDTO dto = new RegionResponseDTO();
            dto.setId(entity.getId());
            dto.setKey(entity.getKey());

            switch (language) {

                case EN: {
                    dto.setName(entity.getNameEn().toString());
                    regionResponseDTOs.add(dto);
                    break;
                }

                case UZ : {
                    dto.setName(entity.getNameUz().toString());
                    regionResponseDTOs.add(dto);
                    break;
                }

                case RU : {
                    dto.setName(entity.getNameRu().toString());
                    regionResponseDTOs.add(dto);
                    break;
                }

            }
        });
        return regionResponseDTOs;
    }

}
