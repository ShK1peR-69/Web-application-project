package ru.kazan.kpfu.itis.master.astafyev.app.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import ru.kazan.kpfu.itis.master.astafyev.app.entities.Comment;
import ru.kazan.kpfu.itis.master.astafyev.app.services.ArticleService;
import ru.kazan.kpfu.itis.master.astafyev.app.services.CommentService;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;

/*****
 * @author Igor Astafyev
 * May, 2019
 * Work with article
 *****/

@Controller
public class ArticleController {
    private final HttpServletRequest request;
    private final ArticleService articleService;
    private final CommentService commentService;

    public ArticleController(HttpServletRequest request,
                             ArticleService articleService,
                             CommentService commentService) {
        this.request = request;
        this.articleService = articleService;
        this.commentService = commentService;
    }

    @RequestMapping(value = "/article/{id}", method = RequestMethod.GET)
    public String renderArticlePage(@PathVariable("id") long id) {
        request.setAttribute("article", articleService.getArticleById(id));
        ArrayList<Comment> comments = (ArrayList<Comment>) commentService.getAllCommentsOfArticle(
                articleService.getArticleById(id));
        request.setAttribute("comments", comments);
        return "articlepage";
    }
}
