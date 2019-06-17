package ru.kazan.kpfu.itis.master.astafyev.app.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import ru.kazan.kpfu.itis.master.astafyev.app.controllers.entities.User;
import ru.kazan.kpfu.itis.master.astafyev.app.controllers.services.ArticleService;
import ru.kazan.kpfu.itis.master.astafyev.app.controllers.services.UserService;
import ru.kazan.kpfu.itis.master.astafyev.app.security.MyUserDetail;
import ru.kazan.kpfu.itis.master.astafyev.app.util.Methods;

import javax.servlet.http.HttpServletRequest;

/*****
 * @author Igor Astafyev
 * May, 2019
 * Controller for user profile
 *****/

@Controller
public class ProfileController {

    @Autowired
    private HttpServletRequest request;

    @Autowired
    private UserService userService;

    @Autowired
    private ArticleService articleService;

    @RequestMapping(value = "/profile", method = RequestMethod.GET)
    public String renderProfilePage() {
        MyUserDetail user = (MyUserDetail) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        request.getSession().setAttribute("user", user.getUser());
        request.setAttribute("user", user.getUser());
        request.setAttribute("articles", articleService.getArticlesByAuthor(user.getUser()));
        return "profile";
    }

    @ResponseBody
    @RequestMapping(value = "/profile/change-password", method = RequestMethod.POST)
    public String changeUserPassword(ModelMap model) {
        MyUserDetail user = (MyUserDetail) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String new_pass = request.getParameter("new_pass");

        User updateUser = user.getUser();
        updateUser.setPassword(Methods.hashPass(new_pass));
        userService.addNewUser(updateUser);
        return "ok";
    }

}
