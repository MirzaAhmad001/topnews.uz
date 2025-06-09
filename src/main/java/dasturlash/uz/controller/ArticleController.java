package dasturlash.uz.controller;


import dasturlash.uz.dto.ArticleDTO;
import dasturlash.uz.dto.CategoryDTO;
import dasturlash.uz.enums.PublishedState;
import dasturlash.uz.services.ArticleService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("article/v1")
public class ArticleController {
    @Autowired
    private ArticleService articleService;

    @PostMapping("")
    public ResponseEntity<ArticleDTO> create(@Valid @RequestBody ArticleDTO dto) {
        return ResponseEntity.ok(articleService.createArticle(dto));
    }

    @PutMapping("")
    public ResponseEntity<ArticleDTO> update(@Valid @RequestBody ArticleDTO dto) {
        return ResponseEntity.ok(articleService.update(dto));
    }

    @DeleteMapping("")
    public ResponseEntity<String> delete(@RequestParam(name = "articleId") UUID articleId) {
        return ResponseEntity.ok(articleService.delete(articleId));
    }

    @PutMapping("/changeStatus")
    public ResponseEntity<String> changeStatus(@RequestParam(name = "articleId") UUID articleId,
                                               @RequestParam(name = "state") PublishedState state) {
        return ResponseEntity.ok(articleService.changeStatus(articleId, state));
    }
}
