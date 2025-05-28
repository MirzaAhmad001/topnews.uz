package dasturlash.uz.controller;


import dasturlash.uz.dto.ProfileDTO;
import dasturlash.uz.dto.ProfileFilterDTO;
import dasturlash.uz.dto.RegionDTO;
import dasturlash.uz.dto.RegionResponseDTO;
import dasturlash.uz.services.ProfileService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("profile")
public class ProfileController {

    @Autowired
    private ProfileService profileService;

    @GetMapping("/paginationList")
    public ResponseEntity<Page<ProfileDTO>> getProfilesByAdmin(@RequestParam(value = "password") String password, @RequestParam(value = "size") int size, @RequestParam(value = "page") int page) {
        return ResponseEntity.ok(profileService.paginationList(size, page - 1, password));
    }

    @PostMapping("")
    public  ResponseEntity<ProfileDTO> createProfile(@Valid @RequestBody ProfileDTO dto) {
        return ResponseEntity.ok(profileService.create(dto));
    }

    @PutMapping("/updateByUser")
    public  ResponseEntity<ProfileDTO> updateProfileByUser(@RequestParam String username, @RequestParam String password, @Valid @RequestBody ProfileDTO dto) {
        return ResponseEntity.ok(profileService.update(username, password, dto));
    }

    @PutMapping("/updateByAdmin")
    public  ResponseEntity<ProfileDTO> updateProfileByAdmin(@RequestParam("username") String adminUsername, @Valid @RequestBody ProfileDTO dto, @RequestParam("password") String password) {
        return ResponseEntity.ok(profileService.updateByAdmin(adminUsername, password, dto));
    }

    @DeleteMapping("/delete")
    public  ResponseEntity<ProfileDTO> deleteProfile(@RequestParam Integer userId, @RequestParam String username, @RequestParam String password) {
        return ResponseEntity.ok(profileService.delete(userId, username, password));
    }

    @GetMapping("/filter")
    public ResponseEntity<Page<ProfileDTO>> filter(@RequestParam("size") int size, @RequestParam("page") int page, @RequestBody ProfileFilterDTO filterDTO) {
        return ResponseEntity.ok(profileService.filter(filterDTO, page - 1, size));
    }

}
