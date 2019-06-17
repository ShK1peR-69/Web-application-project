package ru.kazan.kpfu.itis.master.astafyev.app.controllers.repositories;

import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import ru.kazan.kpfu.itis.master.astafyev.app.controllers.entities.Article;
import ru.kazan.kpfu.itis.master.astafyev.app.controllers.entities.Comment;
import ru.kazan.kpfu.itis.master.astafyev.app.controllers.entities.User;

import java.util.List;

/*****
 * @author Igor Astafyev
 * May, 2019
 * Connection with DB for "Articles"
 *****/

@Repository
public class ArticleRepository {


    @Autowired
    SessionFactory sessionFactory;

    @Autowired
    CommentRepository commentRepository;


    @SuppressWarnings("unchecked")
    public List<Article> getAll() {
        return sessionFactory.getCurrentSession().createCriteria(Article.class).list();
    }

    public void saveNewArticle(Article article) {
        sessionFactory.getCurrentSession().save(article);
    }

    public Article getArticleById(long id) {
        return (Article) sessionFactory.getCurrentSession().createCriteria(Article.class)
                .add(Restrictions.eq("id", id)).uniqueResult();
    }

    public Article findArticle(Article article) {
        String title = article.getTitle();
        String text = article.getText();
        String image = article.getImage();
        String sport = article.getSport();
        String source = article.getSource();
        User user = article.getAuthor();
        return (Article) sessionFactory.getCurrentSession().createCriteria(Article.class)
                .add(Restrictions.eq("title", title)).add(Restrictions.eq("text", text))
                .add(Restrictions.eq("image", image)).add(Restrictions.eq("sport", sport))
                .add(Restrictions.eq("source", source)).add(Restrictions.eq("author", user))
                .uniqueResult();
    }

    @SuppressWarnings("unchecked")
    public List<Article> getArticlesSortingBySport(String sport) {
        return sessionFactory.getCurrentSession().createCriteria(Article.class)
                .add(Restrictions.eq("sport", sport)).list();
    }

    public void deleteArticleById(long id) {
        Article article = (Article) sessionFactory.getCurrentSession().createCriteria(Article.class)
                .add(Restrictions.eq("id", id)).uniqueResult();

        List<Comment> commentList = commentRepository.getAllCommentsOfArticle(article);
        if (!commentList.isEmpty()) {
            for (Comment c : commentList) {
                commentRepository.deleteCommentById(c.getId());
            }
        }
        sessionFactory.getCurrentSession().delete(article);
    }

    @SuppressWarnings("unchecked")
    public List<Article> getArticlesByAuthor(User user) {
        return sessionFactory.getCurrentSession().createCriteria(Article.class)
                .add(Restrictions.eq("author", user)).list();

    }
}