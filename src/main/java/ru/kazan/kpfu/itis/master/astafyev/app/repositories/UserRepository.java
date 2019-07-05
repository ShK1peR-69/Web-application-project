package ru.kazan.kpfu.itis.master.astafyev.app.repositories;

import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;
import ru.kazan.kpfu.itis.master.astafyev.app.entities.Article;
import ru.kazan.kpfu.itis.master.astafyev.app.entities.Comment;
import ru.kazan.kpfu.itis.master.astafyev.app.entities.User;

import java.util.List;

/*****
 * @author Igor Astafyev
 * May, 2019
 * Connection with DB for "Users"
 *****/

@Repository
public class UserRepository {
    private final SessionFactory sessionFactory;
    private final CommentRepository commentRepository;
    private final ArticleRepository articleRepository;

    public UserRepository(SessionFactory sessionFactory,
                          CommentRepository commentRepository,
                          ArticleRepository articleRepository) {
        this.sessionFactory = sessionFactory;
        this.commentRepository = commentRepository;
        this.articleRepository = articleRepository;
    }

    @SuppressWarnings("unchecked")
    public List<User> getAllUsers() {
        return sessionFactory.getCurrentSession()
                .createCriteria(User.class).list();
    }

    public void addNewUser(User user) {
        sessionFactory.getCurrentSession().saveOrUpdate(user);
    }

    public User getUserById(long id) {
        return (User) sessionFactory.getCurrentSession().createCriteria(User.class)
                .add(Restrictions.eq("id", id)).uniqueResult();
    }

    public User getUserByEmail(String email) {
        return (User) sessionFactory.getCurrentSession().createCriteria(User.class)
                .add(Restrictions.eq("email", email)).uniqueResult();
    }

    public void deleteUserFromDB(User user) {
        List<Comment> commentList = commentRepository.getAllCommentsByUser(user);
        if (!commentList.isEmpty()) {
            for (Comment c : commentList) {
                commentRepository.deleteCommentById(c.getId());
            }
        }

        List<Article> articleList = articleRepository.getArticlesByAuthor(user);
        if (!articleList.isEmpty()) {
            for (Article a : articleList) {
                articleRepository.deleteArticleById(a.getId());
            }
        }

        sessionFactory.getCurrentSession().delete(user);
    }
}
