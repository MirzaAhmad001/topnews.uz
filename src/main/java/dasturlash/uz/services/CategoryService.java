package dasturlash.uz.services;

import dasturlash.uz.dto.CategoryDTO;
import dasturlash.uz.dto.CategoryResponseDTO;
import dasturlash.uz.dto.RegionResponseDTO;
import dasturlash.uz.entities.CategoryEntity;
import dasturlash.uz.exceptions.AppBadException;
import dasturlash.uz.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CategoryService {
    @Autowired
    private CategoryRepository categoryRepository;

    public List<CategoryDTO> getAllCategories() {
        Iterable<CategoryEntity> entities = categoryRepository.findAll();
        List<CategoryDTO> dtos = new ArrayList<>();

        entities.forEach(entity -> dtos.add(toDTO(entity)));
        return dtos;
    }

    private CategoryDTO toDTO(CategoryEntity e) {
        CategoryDTO dto = new CategoryDTO();
        dto.setId(e.getId());
        dto.setNameUz(e.getNameUz());
        dto.setNameRu(e.getNameRu());
        dto.setNameEn(e.getNameEn());
        dto.setOrderNumber(e.getOrderNumber());
        dto.setKey(e.getKey());
        dto.setCreateDate(e.getCreatedDate());

        return dto;
    }

    public CategoryDTO create(CategoryDTO dto) {
        CategoryEntity entityKey = categoryRepository.findByKey(dto.getKey());
        if (entityKey != null) {
            throw new AppBadException("there is data with this key, alter the key");
        }

        CategoryEntity entityOrderNumber = categoryRepository.findByOrderNumber(dto.getOrderNumber());
        if(entityOrderNumber != null) {
            throw new AppBadException("there is data with this key, alter the order number");
        }

        CategoryEntity entity = new CategoryEntity();
        entity.setNameUz(dto.getNameUz());
        entity.setNameRu(dto.getNameRu());
        entity.setNameEn(dto.getNameEn());
        entity.setOrderNumber(dto.getOrderNumber());
        entity.setKey(dto.getKey());
        entity.setCreatedDate(LocalDateTime.now());

        categoryRepository.save(entity);
        dto.setId(entity.getId());
        return dto;
    }

    public CategoryDTO update(CategoryDTO dto, Integer id) {
        CategoryEntity entityKey = categoryRepository.findByKey(dto.getKey());
        if (entityKey != null) {
            throw new AppBadException("there is data with this key, alter the key");
        }

        CategoryEntity entityOrderNumber = categoryRepository.findByOrderNumber(dto.getOrderNumber());
        if(entityOrderNumber != null) {
            throw new AppBadException("there is data with this key, alter the order number");
        }

        Optional<CategoryEntity> entity = categoryRepository.findById(id);
        if (entity.isPresent()) {
            CategoryEntity entity1 = entity.get();
            entity1.setNameUz(dto.getNameUz());
            entity1.setNameRu(dto.getNameRu());
            entity1.setNameEn(dto.getNameEn());
            entity1.setOrderNumber(dto.getOrderNumber());
            entity1.setKey(dto.getKey());
            categoryRepository.save(entity1);
            return dto;
        }
        return null;
    }

    public CategoryDTO delete(Integer id) {
        Optional<CategoryEntity> optional = categoryRepository.findById(id);
        if (optional.isPresent()) {
            CategoryEntity entity = optional.get();
            categoryRepository.delete(entity);
            return toDTO(entity);
        }
        return null;
    }

    public List<CategoryResponseDTO> getListByLanguage(String language) {
        Iterable<CategoryEntity> entities = categoryRepository.findAll();
        List<CategoryResponseDTO> categoryResponseDTOS = new ArrayList<>();

        entities.forEach(entity -> {
            CategoryResponseDTO dto = new CategoryResponseDTO();
            dto.setId(entity.getId());
            dto.setKey(entity.getKey());

            switch (language) {

                case "English": {
                    dto.setName(entity.getNameEn());
                    categoryResponseDTOS.add(dto);
                    break;
                }

                case "O'zbekcha": {
                    dto.setName(entity.getNameUz());
                    categoryResponseDTOS.add(dto);
                    break;
                }

                case "Russian": {
                    dto.setName(entity.getNameRu());
                    categoryResponseDTOS.add(dto);
                    break;
                }

            }

        });
        return categoryResponseDTOS;
    }
}
