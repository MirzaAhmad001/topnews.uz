package dasturlash.uz.services;

import dasturlash.uz.dto.ArticleDTO;
import dasturlash.uz.entities.ArticleCategoryEntity;
import dasturlash.uz.entities.ArticleEntity;
import dasturlash.uz.entities.ArticleSectionEntity;
import dasturlash.uz.entities.CategoryEntity;
import dasturlash.uz.exceptions.AppBadException;
import dasturlash.uz.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class ArticleService {
    @Autowired
    private ArticleRepository articleRepository;

    @Autowired
    private Sectionrepository sectionRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private ArticleSectionRepository articleSectionRepository;

    @Autowired
    private ArticleCategoryRepository articleCategoryRepository;
    //Create
    public ArticleDTO createArticle(ArticleDTO article) {
        articleRepository.findByTitle(article.getTitle());
        ArticleEntity articleEntity = new ArticleEntity();
        articleEntity.setTitle(article.getTitle());
        articleEntity.setDescription(article.getDescription());
        articleEntity.setContent(article.getContent());
        articleEntity.setModeratorId(article.getModeratorId());
        articleEntity.setCreatedDate(article.getCreatedDate());
        articleEntity.setPublishedDate(article.getPublishedDate());
        articleEntity.setPublisherId(article.getPublisherId());
        articleEntity.setPublishedState(article.getPublishedState());
        articleEntity.setReadTime(article.getReadTime());
        articleEntity.setImageId(article.getImageId());
        articleEntity.setRegionId(article.getRegionId());
        articleEntity.setSharedCount(article.getSharedCount());
        articleEntity.setVisible(article.getVisible());
        articleRepository.save(articleEntity);
        createArticleCategory(article.getCategoryIds(), articleEntity.getId());
        createArticleSection(article.getSectionIds(), articleEntity.getId());

        return article;
    }

    private void createArticleSection(List<Integer> sectionIds, UUID id) {
        sectionIds.forEach(ci -> {
            ArticleSectionEntity entity = new ArticleSectionEntity();
            entity.setSection(sectionRepository.findById(ci).orElseThrow(() -> new AppBadException("category not found")));
            entity.setArticle(articleRepository.findById(id).orElseThrow(() -> new AppBadException("article not found")));
            articleSectionRepository.save(entity);
        });
    }

    private void createArticleCategory(List<Integer> categoryIds, UUID articleId) {
        categoryIds.forEach(ci -> {
            ArticleCategoryEntity entity = new ArticleCategoryEntity();
            entity.setCategory(categoryRepository.findById(ci).orElseThrow(() -> new AppBadException("category not found")));
            entity.setArticle(articleRepository.findById(articleId).orElseThrow(() -> new AppBadException("article not found")));
            articleCategoryRepository.save(entity);
        });
    }

    private ArticleDTO toDTO(ArticleEntity entity) {
        ArticleDTO dto = new ArticleDTO();
        dto.setId(entity.getId());
        dto.setTitle(entity.getTitle());
        dto.setDescription(entity.getDescription());
        dto.setCreatedDate(entity.getCreatedDate());
        dto.setModeratorId(entity.getModeratorId());
        dto.setPublishedDate(entity.getPublishedDate());
        dto.setPublisherId(entity.getPublisherId());
        dto.setPublishedState(entity.getPublishedState());
        dto.setReadTime(entity.getReadTime());
        dto.setSharedCount(entity.getSharedCount());
        dto.setViewCount(entity.getViewCount());
        dto.setRegionId(entity.getRegionId());
        dto.setVisible(entity.getVisible());
        return dto;
    }
}
