package dasturlash.uz.controller;


import dasturlash.uz.dto.RegionDTO;
import dasturlash.uz.dto.RegionResponseDTO;
import dasturlash.uz.services.RegionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/region/v1")
public class RegionController {
    @Autowired
    private RegionService regionService;

    @GetMapping("")
    public ResponseEntity<List<RegionDTO>> getAllRegions() {
        return ResponseEntity.ok(regionService.getAllRegions());
    }

    @PostMapping("")
    public  ResponseEntity<RegionDTO> createRegion(@RequestBody RegionDTO regionDTO) {
        return ResponseEntity.ok(regionService.create(regionDTO));
    }

    @PutMapping("/{id}")
    public  ResponseEntity<RegionDTO> updateRegion(@PathVariable int id, @RequestBody RegionDTO regionDTO) {
        return ResponseEntity.ok(regionService.update(regionDTO, id));
    }

    @DeleteMapping("/{id}")
    public  ResponseEntity<RegionDTO> deleteRegion(@PathVariable int id) {
        return ResponseEntity.ok(regionService.delete(id));
    }

    @GetMapping("/{language}")
    public ResponseEntity<List<RegionResponseDTO>> getAllRegionsByLanguage(@PathVariable("language") String language) {
        return ResponseEntity.ok(regionService.getListByLanguage(language));
    }
}
