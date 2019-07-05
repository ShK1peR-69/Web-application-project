package ru.kazan.kpfu.itis.master.astafyev.app.controllers;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import ru.kazan.kpfu.itis.master.astafyev.app.entities.Article;
import ru.kazan.kpfu.itis.master.astafyev.app.entities.Comment;
import ru.kazan.kpfu.itis.master.astafyev.app.security.MyUserDetail;
import ru.kazan.kpfu.itis.master.astafyev.app.services.ArticleService;
import ru.kazan.kpfu.itis.master.astafyev.app.services.CommentService;
import ru.kazan.kpfu.itis.master.astafyev.app.util.Methods;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/*****
 * @author Igor Astafyev
 * May, 2019
 * Methods of user with ADMIN role
 *****/

@Controller
@RequestMapping(value = "/admin")
public class AdminController {

    private final HttpServletRequest request;
    private final ArticleService articleService;
    private final CommentService commentService;
    private final Methods methods;

    public AdminController(HttpServletRequest request,
                           ArticleService articleService,
                           CommentService commentService,
                           Methods methods) {
        this.request = request;
        this.articleService = articleService;
        this.commentService = commentService;
        this.methods = methods;
    }

    @RequestMapping(value = "/main", method = RequestMethod.GET)
    public String renderAdminPage() {
        request.setAttribute("articles", articleService.getAllArticles());
        return "adminmainpage";
    }

    @RequestMapping(value = "/article/{id}", method = RequestMethod.GET)
    public String renderAdminArticlePage(@PathVariable("id") long id) {
        request.setAttribute("article", articleService.getArticleById(id));
        ArrayList<Comment> comments = (ArrayList<Comment>) commentService.getAllCommentsOfArticle(
                articleService.getArticleById(id));
        request.setAttribute("comments", comments);
        return "adminarticlepage";
    }

    @RequestMapping(value = "/new-article", method = RequestMethod.GET)
    public String renderAdminNewArticlePage() {
        return "newarticle";
    }

    @RequestMapping(value = "/add-new-article", method = RequestMethod.POST)
    public String addingNewArticle(
            @RequestParam(value = "photo", required = false) MultipartFile file,
            @RequestParam("title") String title,
            @RequestParam("text") String text,
            @RequestParam("content") String content,
            @RequestParam("sport") String sport,
            ModelMap model) throws IOException {
        String nowDate = methods.convertDateToStringFormat(new Date());
        MyUserDetail user = (MyUserDetail) SecurityContextHolder
                .getContext().getAuthentication().getPrincipal();
        String source = user.getUser().getName() + " " + user.getUser().getSecondName();

        if (content != null) {
            source = methods.chooseArticleSource(source, content);
        }
        if (file != null) {
            if (!file.isEmpty()) {
                methods.addImageToLocalFolder(file, request);
                content = "/resources/images/tmp/" + file.getOriginalFilename();
            }
        }

        if (content == null || content.equals("")) {
            content = "/resources/images/sportTime.jpg";
        } else {
            content = methods.returnNameOfArticleSource(source, content);
        }
        if (source.equals("Change")) {
            source = "ВКонтакте";
        }

        Article article = new Article(sport, title, text, content, nowDate, source, user.getUser());
        List<Article> articles = articleService.getAllArticles();
        for (Article a : articles) {
            if (a.equals(article)) {
                model.put("error-msg", "Статья уже была добавлена ранее");
                return "newarticle";
            }
        }
        articleService.saveNewArticle(article);
        Article articlePage = articleService.findOneArticle(article);
        return "redirect:/admin/article/" + articlePage.getId();
    }

    @RequestMapping(value = "/comment/delete/{article_id}/{comment_id}", method = RequestMethod.GET)
    public String deleteCommentFromArticle(@PathVariable long article_id,
                                           @PathVariable long comment_id) {
        commentService.deleteCommentFromArticleById(comment_id);
        request.setAttribute("article", articleService.getArticleById(article_id));
        request.setAttribute("comments",
                commentService.getAllCommentsOfArticle(articleService.getArticleById(article_id)));
        return "redirect:/admin/article/" + article_id;
    }

    @ResponseBody
    @RequestMapping(value = "/article/delete/{id}", method = RequestMethod.POST)
    public String deleteArticle(@PathVariable long id) {
        articleService.deleteArticleById(id);
        return "ok";
    }
}