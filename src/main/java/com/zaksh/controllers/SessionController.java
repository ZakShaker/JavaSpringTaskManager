package com.zaksh.controllers;

import com.zaksh.model.pojo.User;
import com.zaksh.services.interfaces.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.ModelAndView;

import java.util.logging.Logger;

@Controller
@SessionAttributes("userId")
public class SessionController {
    private static Logger LOGGER = Logger.getLogger(SessionController.class.getName());
    private final UserService userService;

    @Autowired
    public SessionController(UserService userService) {
        this.userService = userService;
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    protected ModelAndView login(@ModelAttribute User user, ModelMap model) {
        LOGGER.info("Login POST");

        try {
            User loginedUser = userService.auth(user.getLogin(), user.getPassword());

            if (loginedUser != null) {
                System.out.println("logined user is found ");
                LOGGER.info(loginedUser.getLogin() + " has successfully logged in.");
                model.addAttribute("userId", loginedUser.getId());
                return new ModelAndView("redirect:/my_dashboard", model);
            } else {
                return new ModelAndView("redirect:/login_error");
            }
        } catch (Exception e) {
            LOGGER.warning(e.getMessage());
        }
        return new ModelAndView("redirect:/index");
    }

    @RequestMapping(value = "/register", method = RequestMethod.POST)
    protected ModelAndView register(@ModelAttribute User user, ModelMap model) {
        LOGGER.info("Register Post");

        try {
            User registeredUser = userService.register(user.getLogin(), user.getPassword());

            if (registeredUser != null) {
                LOGGER.info("User " + registeredUser.getLogin() + " has registered.");
                model.addAttribute("userId", registeredUser.getId());
                return new ModelAndView("redirect:/my_dashboard", model);
            }
        } catch (Exception e) {
            LOGGER.warning("Error registering user: " + user.getLogin() + e.getMessage());
        }
        return new ModelAndView("redirect:/register_error");
    }
}
