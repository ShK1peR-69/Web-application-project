package ru.kazan.kpfu.itis.master.astafyev.app.controllers;


import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import ru.kazan.kpfu.itis.master.astafyev.app.entities.User;
import ru.kazan.kpfu.itis.master.astafyev.app.services.UserService;
import ru.kazan.kpfu.itis.master.astafyev.app.util.Methods;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/*****
 * @author Igor Astafyev
 * May, 2019
 * Registration
 *****/

@Controller
public class RegistrationController {

    private final HttpServletRequest request;
    private final Methods methods;
    private final UserService userService;

    public RegistrationController(HttpServletRequest request,
                                  Methods methods,
                                  UserService userService) {
        this.request = request;
        this.methods = methods;
        this.userService = userService;
    }


    @RequestMapping(value = "/registration", method = RequestMethod.GET)
    public String renderRegistrationPage() {
        return "registration";
    }

    @RequestMapping(value = "/new-registration", method = RequestMethod.POST)
    public String newUserRegistration(ModelMap model) {
        String first_name = request.getParameter("first_name");
        String second_name = request.getParameter("second_name");
        String email = request.getParameter("email");
        String first_pass = request.getParameter("one_password");
        String second_pass = request.getParameter("two_password");

        if (!first_pass.equals(second_pass)) {
            model.put("error_msg", "пароли не совпадают");
            return "registration";
        }
        first_pass = methods.hashPass(first_pass);

        User user = new User(first_name, second_name, email, first_pass, "ROLE_USER");
        userService.addNewUser(user);
        request.getSession().setAttribute("first_name", first_name);
        request.getSession().setAttribute("second_name", second_name);
        request.getSession().setAttribute("email", email);
        request.getSession().setAttribute("user", user);
        String login_url = "j_spring_security_check?j_username=" + email + "&j_password=" + second_pass;
        return "forward:/" + login_url;
    }

    @ResponseBody
    @RequestMapping(value = "/check-mail", method = RequestMethod.POST)
    public String checkNewUserEmail() {
        String email = request.getParameter("email");
        List<User> users = userService.getAllUsers();
        for (User u : users) {
            if (u.getEmail().equals(email)) {
                return "error";
            }
        }
        return "ok";
    }
}
