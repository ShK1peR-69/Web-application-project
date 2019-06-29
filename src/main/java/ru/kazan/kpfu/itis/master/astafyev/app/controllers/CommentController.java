package ru.kazan.kpfu.itis.master.astafyev.app.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import ru.kazan.kpfu.itis.master.astafyev.app.entities.Article;
import ru.kazan.kpfu.itis.master.astafyev.app.entities.Comment;
import ru.kazan.kpfu.itis.master.astafyev.app.services.ArticleService;
import ru.kazan.kpfu.itis.master.astafyev.app.services.CommentService;
import ru.kazan.kpfu.itis.master.astafyev.app.services.UserService;
import ru.kazan.kpfu.itis.master.astafyev.app.security.MyUserDetail;
import ru.kazan.kpfu.itis.master.astafyev.app.util.Methods;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;

/*****
 * @author Igor Astafyev
 * May, 2019
 * Comments
 *****/

@Controller
public class CommentController {

    @Autowired
    private HttpServletRequest request;

    @Autowired
    private UserService userService;

    @Autowired
    private ArticleService articleService;

    @Autowired
    private CommentService commentService;


    @ResponseBody
    @RequestMapping(value = "/comment/add/{id}", method = RequestMethod.POST)
    public String addCommentToArticle(@PathVariable("id") long id) {
        String text = request.getParameter("comment");
        String date = Methods.convertDateToStringFormat(new Date());
        Article article = articleService.getArticleById(id);
        MyUserDetail user = (MyUserDetail) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        commentService.saveNewComment(new Comment(text, date, user.getUser(), article));
        return date;
    }
}