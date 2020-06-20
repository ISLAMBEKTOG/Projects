package web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import web.model.User;
import web.service.UserService;

@Controller
public class AdminController {
    @Autowired
    private UserService userService;

    @GetMapping(value = "/admin")
    public ModelAndView listUsers(ModelAndView model) {
        model.setViewName("main");
        model.addObject("userService", userService);

        return model;
    }

    @PostMapping(value = "/admin/add")
    public String addUser(@RequestParam String login,
                          @RequestParam String password,
                          @RequestParam int age,
                          @RequestParam String role) {

        User user = new User(login, password, age);

        userService.addUser(user, role);

        return "redirect:/admin";
    }

    @GetMapping(value = "/admin/delete")
    public String deleteUser(@RequestParam long id) {
        userService.deleteUser(id);

        return "redirect:/admin";
    }

    @GetMapping(value = "/admin/edit")
    public ModelAndView updateUserPage(@RequestParam long id, ModelAndView model) {
        User user = userService.getUserById(id);

        model.setViewName("update");
        model.addObject("userId", id);
        model.addObject("user", user);

        return model;
    }

    @PostMapping(value = "admin/edit/submit")
    public String updateUser(@RequestParam long id,
                             @RequestParam String login,
                             @RequestParam String password,
                             @RequestParam int age) {

        User user = new User(id, login, password, age);

        userService.updateUser(user);

        return "redirect:/admin";
    }
}
