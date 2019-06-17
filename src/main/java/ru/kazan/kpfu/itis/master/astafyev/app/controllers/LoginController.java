package ru.kazan.kpfu.itis.master.astafyev.app.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;

/*****
 * @author Igor Astafyev
 * May, 2019
 *****/

@Controller
public class LoginController {

    @Autowired
    private HttpServletRequest request;

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String renderLoginPage(ModelMap model) {
        model.put("login", "failed");
        return "redirect:/";
    }
}
