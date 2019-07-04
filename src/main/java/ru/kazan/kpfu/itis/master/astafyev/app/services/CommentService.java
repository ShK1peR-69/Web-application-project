package ru.kazan.kpfu.itis.master.astafyev.app.services;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.kazan.kpfu.itis.master.astafyev.app.entities.Article;
import ru.kazan.kpfu.itis.master.astafyev.app.entities.Comment;
import ru.kazan.kpfu.itis.master.astafyev.app.entities.User;
import ru.kazan.kpfu.itis.master.astafyev.app.repositories.CommentRepository;

import java.util.List;

/*****
 * @author Igor Astafyev
 * May, 2019
 *****/

@Service
public class CommentService {

    private final CommentRepository commentRepository;

    public CommentService(CommentRepository commentRepository) {
        this.commentRepository = commentRepository;
    }

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
