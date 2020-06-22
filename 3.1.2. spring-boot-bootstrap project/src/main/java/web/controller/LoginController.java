package web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;
import web.model.User;
import web.service.UserService;

@Controller
public class LoginController {
    @Autowired
    private UserService userService;

    @GetMapping("/")
    public String getLoginPage() {
        return "login";
    }

    @GetMapping("/user")
    public ModelAndView getUserPage(ModelAndView model) {
        User user = userService.getUserByEmail(userService.curUserEmail());
        model.setViewName("user");
        model.addObject("user", user);
        model.addObject("curUserEmail", userService.curUserEmail());
        model.addObject("curUserRoles", userService.curUserRoles());

        return model;
    }
}
