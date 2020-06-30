package web.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import web.model.Role;
import web.model.User;
import web.repositories.RoleRepository;
import web.repositories.UserRepository;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class UserServiceImp implements UserService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Transactional(readOnly = true)
    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @Transactional
    @Override
    public void addUser(User user, String role) {
        if (existUserByLogin(user.getLogin())) {
            /*Check for validation users */
            if (validateUser(user)) {
                User curUser = getUserByLogin(user.getLogin());
                List<Role> curRoles = getRolesByUserId(curUser.getId());

                /*Check for the same roles*/
                for (Role r : curRoles) {
                    if (r.getName().equals(role)) {
                        return;
                    }
                }

                /*Added current user with new adding role*/
                curRoles.add(roleRepository.getRoleByName(role));
                curUser.setRoles(new HashSet<>(curRoles));
                userRepository.save(curUser);
            }
        } else {
            Set<Role> roles = new HashSet<>();
            roles.add(roleRepository.getRoleByName(role));
            user.setRoles(roles);
            userRepository.save(user);
        }
    }

    @Transactional
    @Override
    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }

    @Transactional
    @Override
    public void updateUser(User user) {
        User curUser = userRepository.getOne(user.getId());
        curUser.setLogin(user.getLogin());
        curUser.setPassword(user.getPassword());
        curUser.setAge(user.getAge());
        userRepository.save(curUser);
    }

    @Transactional(readOnly = true)
    @Override
    public User getUserById(Long id) {
        Optional<User> users = userRepository.findById(id);
        User user = null;
        if (users.isPresent()) {
            user = users.get();
        }
        return user;
    }

    @Transactional(readOnly = true)
    @Override
    public User getUserByLogin(String login) {
        return userRepository.findByLogin(login);
    }

    @Transactional(readOnly = true)
    @Override
    public List<Role> getAllRoles() {
        return roleRepository.findAll();
    }

    @Transactional(readOnly = true)
    @Override
    public List<Role> getRolesByUserId(long id) {
        return roleRepository.getRolesByUserId(id);
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
    public boolean existUserByLogin(String login) {
        List<User> users = getAllUsers();
        for (User u : users) {
            if (u.getLogin().equals(login)) {
                return true;
            }
        }
        return false;
    }
}
