package ru.kazan.kpfu.itis.master.astafyev.app.controllers.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.kazan.kpfu.itis.master.astafyev.app.controllers.entities.Article;
import ru.kazan.kpfu.itis.master.astafyev.app.controllers.entities.Comment;
import ru.kazan.kpfu.itis.master.astafyev.app.controllers.entities.User;
import ru.kazan.kpfu.itis.master.astafyev.app.controllers.repositories.CommentRepository;

import java.util.List;

/*****
 * @author Igor Astafyev
 * May, 2019
 *****/

@Service
public class CommentService {

    @Autowired
    private CommentRepository commentRepository;

    @Transactional
    public void saveNewComment(Comment comment) {
        commentRepository.saveNewComment(comment);
    }

    @Transactional
    public List<Comment> getAllComments() {
        return commentRepository.getAllComments();
    }

    @Transactional
    public List<Comment> getAllCommentsOfArticle(Article article) {
        return commentRepository.getAllCommentsOfArticle(article);
    }

    @Transactional
    public List<Comment> getAllCommentsByUser(User user) {
        return commentRepository.getAllCommentsByUser(user);
    }

    @Transactional
    public void deleteCommentFromArticleById(long id) {
        commentRepository.deleteCommentById(id);
    }

}
