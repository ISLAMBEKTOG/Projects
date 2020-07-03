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

    User getUserByUsername(String username);

    boolean validateUser(User user);

    boolean existUserByUsername(String username);

    List<Role> getRolesById(long id);
}
