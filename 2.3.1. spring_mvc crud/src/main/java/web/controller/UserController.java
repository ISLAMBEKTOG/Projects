package web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import web.model.User;
import web.service.UserService;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
@RequestMapping("/")
public class UserController {
    @Autowired
    private UserService userService;

    @GetMapping(value = "/users")
    public ModelAndView listUsers() {
        ModelAndView modelAndView = new ModelAndView();

        List<User> users = userService.getAllUsers();
        modelAndView.setViewName("main");
        modelAndView.addObject("userList", users);

        return modelAndView;
    }

    @PostMapping(value = "/users/add")
    public String addUser(HttpServletRequest request) {
        String name = request.getParameter("name");
        int age = Integer.parseInt(request.getParameter("age"));
        String gender = request.getParameter("gender");

        User user = new User(name, age, gender);

        userService.addUser(user);

        return "redirect:/users";
    }

    @GetMapping(value = "/users/delete")
    public String deleteUser(HttpServletRequest request) {
        Long id = Long.parseLong(request.getParameter("id"));
        userService.deleteUser(id);

        return "redirect:/users";
    }

    @GetMapping(value = "/users/edit")
    public ModelAndView updateUserPage(HttpServletRequest request) {
        ModelAndView modelAndView = new ModelAndView();

        Long id = Long.parseLong(request.getParameter("id"));
        User user = userService.getUserById(id);

        modelAndView.setViewName("update");
        modelAndView.addObject("userId", id);
        modelAndView.addObject("user", user);

        return modelAndView;
    }

    @PostMapping(value = "users/edit/submit")
    public String updateUser(HttpServletRequest request) {
        Long id = Long.parseLong(request.getParameter("id"));
        String name = request.getParameter("name");
        int age = Integer.parseInt(request.getParameter("age"));
        String gender = request.getParameter("gender");

        User user = new User(name, age, gender);

        userService.updateUser(id, user);

        return "redirect:/users";
    }
}
