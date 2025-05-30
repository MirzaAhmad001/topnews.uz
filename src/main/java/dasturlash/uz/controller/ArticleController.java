package dasturlash.uz.controller;


import dasturlash.uz.dto.ArticleDTO;
import dasturlash.uz.dto.CategoryDTO;
import dasturlash.uz.services.ArticleService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("article/v1")
public class ArticleController {
    @Autowired
    private ArticleService articleService;

    @PostMapping("")
    public ResponseEntity<ArticleDTO> create(@Valid @RequestBody ArticleDTO dto) {
        return ResponseEntity.ok(articleService.createArticle(dto));
    }
}
