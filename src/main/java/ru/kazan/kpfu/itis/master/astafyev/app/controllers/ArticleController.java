package ru.kazan.kpfu.itis.master.astafyev.app.controllers;

import org.springframework.beans.factory.annotation.Autowired;
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

    @Autowired
    private HttpServletRequest request;

    @Autowired
    private ArticleService articleService;

    @Autowired
    private CommentService commentService;

    @RequestMapping(value = "/article/{id}", method = RequestMethod.GET) // /article/{id}
    public String renderArticlePage(@PathVariable("id") long id) {
        request.setAttribute("article", articleService.getArticleById(id));
        ArrayList<Comment> comments = (ArrayList<Comment>) commentService.getAllCommentsOfArticle(
                articleService.getArticleById(id));
        request.setAttribute("comments", comments);
        return "articlepage";
    }
}
