package org.wirvsvirus.mdatw.coronafakedetector.articles;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.lang.reflect.AnnotatedType;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class ArticleService {

    @Autowired
    private ArticleRepository articleRepository;

    public List<Article> findAll() {
        return articleRepository.findAll();
    }

    public Article findBySHA256(String SHA256) {
        return articleRepository.findAllBySHA265(SHA256);
    }

    public boolean alreadySeenArticle(String SHA256) {
        if (articleRepository.existsBySHA265(SHA256)) {
            Article article = articleRepository.findAllBySHA265(SHA256);
            article.increaseTimesChecked();
            article.setLastAccessed(LocalDateTime.now());
            articleRepository.save(article);

            return true;
        }
        return false;
    }

    public void insert(Article article) {
        articleRepository.save(article);
    }

    public void update(Article article) {
        article.setLastAccessed(LocalDateTime.now());
        articleRepository.save(article);
    }

    public void delete(Article article) {
        articleRepository.delete(article);
    }
}
