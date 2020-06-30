package web.service;

import web.model.Role;
import web.model.User;

import java.util.List;

public interface UserService {
    List<User> getAllUsers();

    void addUser(User user, String role);

    void deleteUser(Long id);

    void updateUser(User user);

    User getUserById(Long id);

    List<Role> getAllRoles();

    User getUserByLogin(String login);

    boolean validateUser(User user);

    boolean existUserByLogin(String login);

    List<Role> getRolesByUserId(long id);
}
