package dasturlash.uz.controller;

import dasturlash.uz.dto.CategoryDTO;
import dasturlash.uz.dto.CategoryResponseDTO;
import dasturlash.uz.dto.SectionDTO;
import dasturlash.uz.dto.SectionResponseDTO;
import dasturlash.uz.services.SectionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("section")
public class SectionController {
    @Autowired
    private SectionService sectionService;

    @GetMapping("")
    public ResponseEntity<List<SectionDTO>> getAllSections() {
        return ResponseEntity.ok(sectionService.getAllSections());
    }

    @PostMapping("")
    public  ResponseEntity<SectionDTO> create(@RequestBody SectionDTO dto) {
        return ResponseEntity.ok(sectionService.create(dto));
    }

    @PutMapping("/{id}")
    public  ResponseEntity<SectionDTO> update(@PathVariable Integer id, @RequestBody SectionDTO dto) {
        return ResponseEntity.ok(sectionService.update(dto, id));
    }

    @DeleteMapping("/{id}")
    public  ResponseEntity<SectionDTO> delete(@PathVariable Integer id) {
        return ResponseEntity.ok(sectionService.delete(id));
    }

    @GetMapping("/{language}")
    public ResponseEntity<List<SectionResponseDTO>> getAllRegionsByLanguage(@PathVariable String language) {
        return ResponseEntity.ok(sectionService.getListByLanguage(language));
    }
}
