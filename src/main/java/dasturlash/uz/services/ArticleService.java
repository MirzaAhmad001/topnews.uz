package dasturlash.uz.services;

import dasturlash.uz.dto.ArticleDTO;
import dasturlash.uz.entities.*;
import dasturlash.uz.enums.ProfileRole;
import dasturlash.uz.enums.ProfileStatus;
import dasturlash.uz.enums.PublishedState;
import dasturlash.uz.exceptions.AppBadException;
import dasturlash.uz.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
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
    @Autowired
    private ProfileService profileService;
    @Autowired
    private ProfileRepository profileRepository;
    @Autowired
    private ProfileRoleRepository profileRoleRepository;

    //Create
    public ArticleDTO createArticle(ArticleDTO article) {
        ArticleEntity existing = articleRepository.findByTitle(article.getTitle());
        if (existing != null) {
            throw new AppBadException("Title already exists");
        }

        ArticleEntity entity = new ArticleEntity();
        entity.setTitle(article.getTitle());
        entity.setDescription(article.getDescription());
        entity.setContent(article.getContent());

        Optional<ProfileEntity> publisherEntity = profileRepository.findById(article.getPublisherId());
        if (publisherEntity.isEmpty() || !profileRoleRepository.getRoleByProfileId(article.getPublisherId()).contains(ProfileRole.ROLE_PUBLISHER)) {
            throw new AppBadException("Publisher is not found");
        }
        entity.setPublisherId(article.getPublisherId());

        Optional<ProfileEntity> moderatorEntity = profileRepository.findById(article.getModeratorId());
        if (moderatorEntity.isEmpty() || !profileRoleRepository.getRoleByProfileId(article.getModeratorId()).contains(ProfileRole.ROLE_MODERATOR)) {
            throw new AppBadException("Moderator is not found");
        }
        entity.setModeratorId(article.getModeratorId());

        entity.setCreatedDate(LocalDateTime.now());
        entity.setPublishedDate(article.getPublishedDate());
        entity.setReadTime(article.getReadTime());
        entity.setImageId(article.getImageId());
        entity.setRegionId(article.getRegionId());
        entity.setSharedCount(article.getSharedCount());
        entity.setVisible(article.getVisible());
        articleRepository.save(entity);
        createArticleCategory(article.getCategoryIds(), entity.getId());
        createArticleSection(article.getSectionIds(), entity.getId());

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

    private void mergeArticleSections(UUID articleId, List<Integer> newSectionIdList) {
        List<ArticleSectionEntity> oldSections = articleSectionRepository.findByArticleId(articleId);
        List<Integer> oldSectionIds = new ArrayList<>();
        oldSections.forEach(oldSection -> oldSectionIds.add(oldSection.getId()));
        List<Integer> deleteList = new ArrayList<>();
        newSectionIdList.stream().filter(n -> !oldSectionIds.contains(n)).forEach(deleteList::add);
        deleteList.forEach(d -> articleSectionRepository.deleteAllBySectionId(d));
        createArticleSection(newSectionIdList, articleId);
    }

    private void createArticleCategory(List<Integer> categoryIds, UUID articleId) {
        categoryIds.forEach(ci -> {
            ArticleCategoryEntity entity = new ArticleCategoryEntity();
            entity.setCategory(categoryRepository.findById(ci).orElseThrow(() -> new AppBadException("category not found")));
            entity.setArticle(articleRepository.findById(articleId).orElseThrow(() -> new AppBadException("article not found")));
            articleCategoryRepository.save(entity);
        });
    }

    private void mergeArticleCategories(UUID articleId, List<Integer> newCategoryIdList) {
        List<ArticleCategoryEntity> oldSections = articleCategoryRepository.findByArticleId(articleId);
        List<Integer> oldCategoryIds = new ArrayList<>();
        oldSections.forEach(oldSection -> oldCategoryIds.add(oldSection.getId()));
        List<Integer> deleteList = new ArrayList<>();
        newCategoryIdList.stream().filter(n -> !oldCategoryIds.contains(n)).forEach(deleteList::add);
        deleteList.forEach(d -> articleSectionRepository.deleteAllBySectionId(d));
        createArticleSection(newCategoryIdList, articleId);
    }

    public ArticleDTO update(ArticleDTO article) {
        ArticleEntity entity = articleRepository.findByTitle(article.getTitle());
        if (entity == null) {
            throw new AppBadException("article with this title not found");
        }

        entity.setDescription(article.getDescription());
        entity.setContent(article.getContent());

        Optional<ProfileEntity> publisherEntity = profileRepository.findById(article.getPublisherId());
        if (publisherEntity.isEmpty() || !profileRoleRepository.getRoleByProfileId(article.getPublisherId()).contains(ProfileRole.ROLE_PUBLISHER)) {
            throw new AppBadException("Publisher is not found");
        }
        entity.setPublisherId(article.getPublisherId());

        Optional<ProfileEntity> moderatorEntity = profileRepository.findById(article.getModeratorId());
        if (moderatorEntity.isEmpty() || !profileRoleRepository.getRoleByProfileId(article.getModeratorId()).contains(ProfileRole.ROLE_MODERATOR)) {
            throw new AppBadException("Moderator is not found");
        }
        entity.setModeratorId(article.getModeratorId());

        entity.setPublishedDate(article.getPublishedDate());
        entity.setReadTime(article.getReadTime());
        entity.setImageId(article.getImageId());
        entity.setRegionId(article.getRegionId());
        entity.setSharedCount(article.getSharedCount());
        entity.setVisible(article.getVisible());
        articleRepository.save(entity);

        //mergeArticleSections(entity.getId(), article.getSectionIds());
        //mergeArticleCategories(entity.getId(), article.getCategoryIds());
        return article;
    }

    public String delete(UUID articleId) {
        List<ArticleSectionEntity> articleSections = articleSectionRepository.findByArticleId(articleId);
        List<ArticleCategoryEntity> articleCategories = articleCategoryRepository.findByArticleId(articleId);
        articleSectionRepository.deleteAll(articleSections);
        articleCategoryRepository.deleteAll(articleCategories);
        articleRepository.deleteById(articleId);
        return "Article deleted";
    }

    public String changeStatus(UUID articleId, PublishedState publishedState) {
        ArticleEntity entity = articleRepository.findById(articleId).orElseThrow(() -> new AppBadException("article not found"));
        entity.setPublishedState(publishedState);
        articleRepository.save(entity);
        return "Article status changed to " + publishedState.toString();
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
