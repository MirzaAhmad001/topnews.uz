package dasturlash.uz.controller;

import dasturlash.uz.dto.CategoryDTO;
import dasturlash.uz.dto.CategoryResponseDTO;
import dasturlash.uz.dto.RegionDTO;
import dasturlash.uz.dto.RegionResponseDTO;
import dasturlash.uz.services.CategoryService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("category/v1")
public class CategoryController {
    @Autowired
    private CategoryService categoryService;

    @GetMapping("")
    public ResponseEntity<List<CategoryDTO>> getAllRegions() {
        return ResponseEntity.ok(categoryService.getAllCategories());
    }

    @PostMapping("")
    public  ResponseEntity<CategoryDTO> create(@Valid @RequestBody CategoryDTO dto) {
        return ResponseEntity.ok(categoryService.create(dto));
    }

    @PutMapping("/{id}")
    public  ResponseEntity<CategoryDTO> update(@PathVariable Integer id, @Valid @RequestBody CategoryDTO dto) {
        return ResponseEntity.ok(categoryService.update(dto, id));
    }

    @DeleteMapping("/{id}")
    public  ResponseEntity<CategoryDTO> delete(@PathVariable Integer id) {
        return ResponseEntity.ok(categoryService.delete(id));
    }

    @GetMapping("/{language}")
    public ResponseEntity<List<CategoryResponseDTO>> getAllRegionsByLanguage(@PathVariable String language) {
        return ResponseEntity.ok(categoryService.getListByLanguage(language));
    }
}
