package ru.kazan.kpfu.itis.master.astafyev.app.controllers;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import ru.kazan.kpfu.itis.master.astafyev.app.entities.User;
import ru.kazan.kpfu.itis.master.astafyev.app.security.MyUserDetail;
import ru.kazan.kpfu.itis.master.astafyev.app.services.ArticleService;
import ru.kazan.kpfu.itis.master.astafyev.app.services.UserService;
import ru.kazan.kpfu.itis.master.astafyev.app.util.Methods;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/*****
 * @author Igor Astafyev
 * May, 2019
 * Controller for user profile
 *****/

@Controller
public class ProfileController {
    private final HttpServletRequest request;
    private final UserService userService;
    private final ArticleService articleService;
    private final Methods methods;

    public ProfileController(HttpServletRequest request, UserService userService,
                             ArticleService articleService, Methods methods) {
        this.request = request;
        this.userService = userService;
        this.articleService = articleService;
        this.methods = methods;
    }

    @RequestMapping(value = "/profile", method = RequestMethod.GET)
    public String renderProfilePage() {
        MyUserDetail user = (MyUserDetail) SecurityContextHolder
                .getContext().getAuthentication().getPrincipal();
        HttpSession session = request.getSession();
        session.setAttribute("user", user.getUser());
        session.setAttribute("user", user.getUser());
        session.setAttribute("articles", articleService.getArticlesByAuthor(user.getUser()));
        return "profile";
    }

    @ResponseBody
    @RequestMapping(value = "/profile/change-password", method = RequestMethod.POST)
    public String changeUserPassword(@RequestParam("new_pass") String new_pass) {
        MyUserDetail user = (MyUserDetail) SecurityContextHolder
                .getContext().getAuthentication().getPrincipal();
        User updateUser = user.getUser();
        updateUser.setPassword(methods.hashPass(new_pass));
        userService.addNewUser(updateUser);
        return "ok";
    }
}