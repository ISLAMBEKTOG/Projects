package web.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import web.model.Role;
import web.model.User;
import web.model.UserForm;
import web.service.UserService;

import java.util.List;

@RestController
public class UserController {
    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping(value = "/read")
    public ResponseEntity<List<User>> read() {
        final List<User> users = userService.readAllUsers();

        return users != null && !users.isEmpty()
                ? new ResponseEntity<>(users, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping(value = "/read/{id}")
    public ResponseEntity<User> readUser(@PathVariable(name = "id") long id) {
        final User user = userService.readUser(id);

        return user != null
                ? new ResponseEntity<>(user, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping(value = "/read/{id}/roles")
    public ResponseEntity<List<Role>> readRoles(@PathVariable(name = "id") long id) {
        final List<Role> roles = userService.getRolesByUserId(id);

        return roles != null && !roles.isEmpty()
                ? new ResponseEntity<>(roles, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PostMapping(value = "/save")
    public ResponseEntity<?> create(@RequestBody UserForm userForm) {
        userService.createUser(userForm);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }


    @PutMapping(value = "/update")
    public ResponseEntity<?> update(@RequestBody UserForm userForm) {
        userService.updateUser(userForm);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping(value = "/delete/{id}")
    public ResponseEntity<?> delete(@PathVariable(name = "id") long id) {
        userService.deleteUser(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping(value = "/authorizedUser")
    public ResponseEntity<User> authorizedUser() {
        final User user = userService.getUserByEmail(userService.curUserEmail());

        return user != null
                ? new ResponseEntity<>(user, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}
