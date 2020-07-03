package web.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import web.dao.RoleDAO;
import web.dao.UserDAO;
import web.model.Role;
import web.model.User;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class UserServiceImp implements UserService {
    @Autowired
    private UserDAO userDAO;

    @Autowired
    private RoleDAO roleDAO;

    @Transactional(readOnly = true)
    @Override
    public List<User> getAllUsers() {
        return userDAO.getAllUsers();
    }

    @Transactional
    @Override
    public void addUser(User user, String role) {
        if (existUserByUsername(user.getUsername())) {
            /*Check for validation users */
            if (validateUser(user)) {
                User curUser = getUserByUsername(user.getUsername());
                List<Role> curRoles = getRolesById(curUser.getId());

                /*Check for the same roles*/
                for (Role r : curRoles) {
                    if (r.getName().equals(role)) {
                        return;
                    }
                }

                /*Added current user with new adding role*/
                curRoles.add(roleDAO.getRoleByName(role));
                curUser.setRoles(new HashSet<>(curRoles));
                userDAO.addUser(curUser);

            }
        } else {
            Set<Role> roles = new HashSet<>();
            roles.add(roleDAO.getRoleByName(role));
            user.setRoles(roles);
            userDAO.addUser(user);
        }
    }

    @Transactional
    @Override
    public void deleteUser(Long id) {
        userDAO.deleteUser(id);
    }

    @Transactional
    @Override
    public void updateUser(User user) {
        userDAO.updateUser(user);
    }

    @Transactional(readOnly = true)
    @Override
    public User getUserById(Long id) {
        return userDAO.getUserById(id);
    }

    @Transactional(readOnly = true)
    @Override
    public User getUserByUsername(String username) {
        return userDAO.getUserByUsername(username);
    }

    @Transactional(readOnly = true)
    @Override
    public List<Role> getAllRoles() {
        return roleDAO.getAllRoles();
    }

    @Transactional(readOnly = true)
    @Override
    public List<Role> getRolesById(long id) {
        return roleDAO.getRolesById(id);
    }

    @Override
    public boolean validateUser(User user) {
        List<User> users = getAllUsers();
        for (User u : users) {
            if (u.equals(user)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean existUserByUsername(String username) {
        List<User> users = getAllUsers();
        for (User u : users) {
            if (u.getUsername().equals(username)) {
                return true;
            }
        }
        return false;
    }
}
