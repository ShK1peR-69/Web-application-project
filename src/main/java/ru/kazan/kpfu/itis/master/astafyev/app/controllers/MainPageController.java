package ru.kazan.kpfu.itis.master.astafyev.app.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import ru.kazan.kpfu.itis.master.astafyev.app.services.ArticleService;

import javax.servlet.http.HttpServletRequest;

/*****
 * @author Igor Astafyev
 * May, 2019
 * Returns Main pages with articles
 *****/

@Controller
public class MainPageController {

    private final HttpServletRequest request;
    private final ArticleService articleService;

    public MainPageController(HttpServletRequest request,
                              ArticleService articleService) {
        this.request = request;
        this.articleService = articleService;
    }

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String renderMainPage(@RequestParam("login") String error) {
        if (error != null) {
            request.setAttribute("login", "failed");
        }
        request.setAttribute("articles", articleService.getAllArticles());
        return "mainpage";
    }

    @RequestMapping(value = "/football", method = RequestMethod.GET)
    public String renderFootballPage() {
        request.setAttribute("articles", articleService.getArticlesSortingBySport("Футбол"));
        return "footballpage";
    }

    @RequestMapping(value = "/hockey", method = RequestMethod.GET)
    public String renderHockeyPage() {
        request.setAttribute("articles", articleService.getArticlesSortingBySport("Хоккей"));
        return "hockeypage";
    }

    @RequestMapping(value = "/basketball", method = RequestMethod.GET)
    public String renderBasketballPage() {
        request.setAttribute("articles", articleService.getArticlesSortingBySport("Баскетбол"));
        return "basketballpage";
    }

    @RequestMapping(value = "/volleyball", method = RequestMethod.GET)
    public String renderVolleyballPage() {
        request.setAttribute("articles", articleService.getArticlesSortingBySport("Волейбол"));
        return "volleyballpage";
    }

    @RequestMapping(value = "/tennis", method = RequestMethod.GET)
    public String renderTennisPage() {
        request.setAttribute("articles", articleService.getArticlesSortingBySport("Теннис"));
        return "tennispage";
    }
}