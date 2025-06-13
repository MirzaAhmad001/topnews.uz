package dasturlash.uz.controller;

import dasturlash.uz.dto.AttachDTO;
import dasturlash.uz.services.AttachService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/attach")
public class AttachController {
    @Autowired
    private AttachService attachService;

    /*
    @PostMapping("/upload")
    public String upload(@RequestParam("file") MultipartFile file) {
        String fileName = attachService.saveToSystem(file);
        return fileName;
    }
    */

    @GetMapping("/open/{fileName}")
    public ResponseEntity<Resource> open(@PathVariable String fileName) throws Exception {
        return attachService.open(fileName);
    }

    @GetMapping("/open2/{fileId}")
    public ResponseEntity<Resource> open2(@PathVariable String fileId) throws Exception {
        return attachService.open2(fileId);
    }


    @PostMapping("/upload")
    public ResponseEntity<AttachDTO> create(@RequestParam("file") MultipartFile file) {
        return ResponseEntity.ok(attachService.upload(file));
    }

}
