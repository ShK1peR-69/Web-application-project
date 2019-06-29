package ru.kazan.kpfu.itis.master.astafyev.app.repositories;

import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import ru.kazan.kpfu.itis.master.astafyev.app.entities.Article;
import ru.kazan.kpfu.itis.master.astafyev.app.entities.Comment;
import ru.kazan.kpfu.itis.master.astafyev.app.entities.User;

import java.util.List;

/*****
 * @author Igor Astafyev
 * May, 2019
 * Connection with DB for "Comments"
 *****/

@Repository
public class CommentRepository {

    @Autowired
    private SessionFactory sessionFactory;

    @SuppressWarnings("unchecked")
    public List<Comment> getAllComments() {
        return sessionFactory.getCurrentSession().createCriteria(Comment.class).list();
    }

    public void saveNewComment(Comment comment) {
        sessionFactory.getCurrentSession().save(comment);
    }

    @SuppressWarnings("unchecked")
    public List<Comment> getAllCommentsOfArticle(Article article) {
        return sessionFactory.getCurrentSession().createCriteria(Comment.class)
                .add(Restrictions.eq("article", article)).list();
    }

    @SuppressWarnings("unchecked")
    public List<Comment> getAllCommentsByUser(User user) {
        return sessionFactory.getCurrentSession().createCriteria(Comment.class)
                .add(Restrictions.eq("author", user)).list();
    }

    public void deleteCommentById(long id) {
        Comment comment = (Comment) sessionFactory.getCurrentSession().createCriteria(Comment.class)
                .add(Restrictions.eq("id", id)).uniqueResult();
        System.out.println("!!!!!!!!!!!!!!!!!!!!!!111  " + comment);
        sessionFactory.getCurrentSession().delete(comment);
    }

}
