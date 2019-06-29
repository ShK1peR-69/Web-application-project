package ru.kazan.kpfu.itis.master.astafyev.app.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.kazan.kpfu.itis.master.astafyev.app.entities.Article;
import ru.kazan.kpfu.itis.master.astafyev.app.entities.User;
import ru.kazan.kpfu.itis.master.astafyev.app.repositories.ArticleRepository;

import java.util.List;

/*****
 * @author Igor Astafyev
 * May, 2019
 *****/

@Service
public class ArticleService {

    @Autowired
    ArticleRepository articleRepository;

    @Transactional
    public List<Article> getAllArticles() {
        return articleRepository.getAll();
    }

    @Transactional
    public void saveNewArticle(Article article) {
        articleRepository.saveNewArticle(article);
    }

    @Transactional
    public Article getArticleById(long id) {
        return articleRepository.getArticleById(id);
    }

    @Transactional
    public Article findOneArticle(Article article) {
        return articleRepository.findArticle(article);
    }

    @Transactional
    public List<Article> getArticlesSortingBySport(String sport) {
        return articleRepository.getArticlesSortingBySport(sport);
    }

    @Transactional
    public void deleteArticleById(long id) {
        articleRepository.deleteArticleById(id);
    }

    @Transactional
    public List<Article> getArticlesByAuthor(User user) {
        return articleRepository.getArticlesByAuthor(user);
    }
}
