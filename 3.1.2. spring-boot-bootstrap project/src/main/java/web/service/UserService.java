package web.service;

import web.model.Role;
import web.model.User;

import java.util.List;

public interface UserService {
    /* Crud operations */
    List<User> getAllUsers();

    void addUser(User user, String role);

    void deleteUser(long id);

    void updateUser(User user, String role);

    User getUserById(long id);

    User getUserByEmail(String email);

    /* Works with roles */
    List<Role> getAllRoles();

    List<Role> getRolesById(long id);

    /* Checking user */
    boolean validateUser(User user);

    boolean existUserByEmail(String email);

    /* For authorized user */
    String curUserEmail();

    String curUserRoles();
}
