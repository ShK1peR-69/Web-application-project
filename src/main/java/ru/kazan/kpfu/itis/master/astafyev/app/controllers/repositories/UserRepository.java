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
 * Connection with DB for "Users"
 *****/

@Repository
public class UserRepository {

    @Autowired
    private SessionFactory sessionFactory;

    @Autowired
    private CommentRepository commentRepository;

    @Autowired
    private ArticleRepository articleRepository;

    @SuppressWarnings("unchecked")
    public List<User> getAllUsers() {
        return sessionFactory.getCurrentSession().createCriteria(User.class).list();
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
