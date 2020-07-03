package web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import web.model.User;
import web.service.UserService;

@Controller
@RequestMapping("/")
public class UserController {
    @Autowired
    private UserService userService;

    @GetMapping(value = "/admin")
    public ModelAndView listUsers() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("main");
        modelAndView.addObject("userService", userService);

        return modelAndView;
    }

    @PostMapping(value = "/admin/add")
    public String addUser(@RequestParam String username,
                          @RequestParam String password,
                          @RequestParam int age,
                          @RequestParam String role) {

        User user = new User(username, password, age);

        userService.addUser(user, role);

        return "redirect:/admin";
    }

    @GetMapping(value = "/admin/delete")
    public String deleteUser(@RequestParam long id) {
        userService.deleteUser(id);

        return "redirect:/admin";
    }

    @GetMapping(value = "/admin/edit")
    public ModelAndView updateUserPage(@RequestParam long id) {
        ModelAndView modelAndView = new ModelAndView();

        User user = userService.getUserById(id);

        modelAndView.setViewName("update");
        modelAndView.addObject("userId", id);
        modelAndView.addObject("user", user);

        return modelAndView;
    }

    @PostMapping(value = "admin/edit/submit")
    public String updateUser(@RequestParam long id,
                             @RequestParam String username,
                             @RequestParam String password,
                             @RequestParam int age) {

        User user = new User(id, username, password, age);

        userService.updateUser(user);

        return "redirect:/admin";
    }
}
