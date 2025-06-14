package dasturlash.uz.services;

import dasturlash.uz.dto.CategoryResponseDTO;
import dasturlash.uz.dto.SectionDTO;
import dasturlash.uz.dto.SectionResponseDTO;
import dasturlash.uz.entities.SectionEntity;
import dasturlash.uz.repository.Sectionrepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class SectionService {

    @Autowired
    private Sectionrepository sectionrepository;

    public List<SectionDTO> getAllSections() {
        Iterable<SectionEntity> entities = sectionrepository.findAll();
        List<SectionDTO> dtos = new ArrayList<>();

        entities.forEach(entity -> dtos.add(toDTO(entity)));
        return dtos;
    }

    private SectionDTO toDTO(SectionEntity e) {
        SectionDTO dto = new SectionDTO();
        dto.setId(e.getId());
        dto.setNameUz(e.getNameUz());
        dto.setNameRu(e.getNameRu());
        dto.setNameEn(e.getNameEn());
        dto.setOrderNumber(e.getOrderNumber());
        dto.setKey(e.getKey());
        dto.setCreateDate(e.getCreatedDate());

        return dto;
    }

    public SectionDTO create(SectionDTO dto) {
        SectionEntity entity = new SectionEntity();
        entity.setNameUz(dto.getNameUz());
        entity.setNameRu(dto.getNameRu());
        entity.setNameEn(dto.getNameEn());
        entity.setOrderNumber(dto.getOrderNumber());
        entity.setKey(dto.getKey());
        entity.setCreatedDate(LocalDateTime.now());

        sectionrepository.save(entity);
        dto.setId(entity.getId());

        return dto;
    }

    public SectionDTO update(SectionDTO dto, Integer id) {
        Optional<SectionEntity> entity = sectionrepository.findById(id);


        if (entity.isPresent()) {
            SectionEntity entity1 = entity.get();
            entity1.setNameUz(dto.getNameUz());
            entity1.setNameRu(dto.getNameRu());
            entity1.setNameEn(dto.getNameEn());
            entity1.setOrderNumber(dto.getOrderNumber());
            entity1.setKey(dto.getKey());
            sectionrepository.save(entity1);
            return dto;
        }
        return null;
    }

    public SectionDTO delete(Integer id) {
        Optional<SectionEntity> optional = sectionrepository.findById(id);
        if (optional.isPresent()) {
            SectionEntity entity = optional.get();
            sectionrepository.delete(entity);
            return toDTO(entity);
        }
        return null;
    }

    public List<SectionResponseDTO> getListByLanguage(String language) {
        Iterable<SectionEntity> entities = sectionrepository.findAll();
        List<SectionResponseDTO> dtos = new ArrayList<>();

        entities.forEach(entity -> {
            SectionResponseDTO dto = new SectionResponseDTO();
            dto.setId(entity.getId());
            dto.setKey(entity.getKey());

            switch (language) {

                case "English": {
                    dto.setName(entity.getNameEn());
                    dtos.add(dto);
                    break;
                }

                case "O'zbekcha": {
                    dto.setName(entity.getNameUz());
                    dtos.add(dto);
                    break;
                }

                case "Russian": {
                    dto.setName(entity.getNameRu());
                    dtos.add(dto);
                    break;
                }

            }

        });
        return dtos;
    }
}
