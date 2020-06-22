package web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import web.model.User;
import web.service.UserService;

@Controller
public class AdminController {
    @Autowired
    private UserService userService;

    @GetMapping(value = "/admin")
    public ModelAndView listUsers(ModelAndView model) {
        model.setViewName("admin");
        model.addObject("userService", userService);
        model.addObject("curUserEmail", userService.curUserEmail());
        model.addObject("curUserRoles", userService.curUserRoles());

        return model;
    }

    @PostMapping(value = "/admin/add")
    public String addUser(@RequestParam String firstName, @RequestParam String lastName,
                          @RequestParam int age, @RequestParam String email,
                          @RequestParam String password, @RequestParam String role) {

        User user = new User(firstName, lastName, age, email, password);

        userService.addUser(user, role);

        return "redirect:/admin";
    }

    @GetMapping(value = "/admin/delete")
    public String deleteUser(@RequestParam long idDelete) {
        userService.deleteUser(idDelete);

        return "redirect:/admin";
    }

    @PostMapping(value = "/admin/edit")
    public String updateUser(@RequestParam long idEdit, @RequestParam String firstNameEdit,
                             @RequestParam String lastNameEdit, @RequestParam int ageEdit,
                             @RequestParam String emailEdit, @RequestParam String passwordEdit,
                             @RequestParam String roleEdit) {

        User user = new User(idEdit, firstNameEdit, lastNameEdit, ageEdit, emailEdit, passwordEdit);

        userService.updateUser(user, roleEdit);

        return "redirect:/admin";
    }

    @GetMapping("/admin/user")
    public ModelAndView getAdminUserInfoPage(ModelAndView model) {
        User user = userService.getUserByEmail(userService.curUserEmail());
        model.setViewName("adminUserInfo");
        model.addObject("user", user);
        model.addObject("curUserEmail", userService.curUserEmail());
        model.addObject("curUserRoles", userService.curUserRoles());

        return model;
    }

    @GetMapping("/admin/getUser")
    @ResponseBody
    public User getUser(long id) {
        return userService.getUserById(id);
    }
}
