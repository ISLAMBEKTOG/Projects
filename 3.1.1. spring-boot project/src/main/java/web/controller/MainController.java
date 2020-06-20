package web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import web.model.User;
import web.service.UserService;

@Controller
@RequestMapping("/")
public class MainController {
    @Autowired
    private UserService userService;

    @GetMapping("/login")
    public String loginPage() {
        return "login";
    }

    @GetMapping("/user")
    public ModelAndView userPage(@RequestParam String login,
                                 ModelAndView model){
        model.setViewName("user");
        User user = userService.getUserByLogin(login);
        model.addObject("userInfo", user);
        model.addObject("userService", userService);

        return model;
    }
}
