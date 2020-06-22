package web.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import web.model.Role;
import web.model.User;
import web.repositories.RoleRepository;
import web.repositories.UserRepository;

import java.util.Collection;
import java.util.HashSet;
import java.util.List;
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
        if (existUserByEmail(user.getEmail())) {
            /*Check for validation users */
            if (validateUser(user)) {
                User curUser = getUserByEmail(user.getEmail());
                List<Role> curRoles = getRolesById(curUser.getId());

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
    public void deleteUser(long id) {
        userRepository.deleteById(id);
    }

    @Transactional
    @Override
    public void updateUser(User user, String role) {
        User curUser = userRepository.getOne(user.getId());

        curUser.setFirstName(user.getFirstName());
        curUser.setLastName(user.getLastName());
        curUser.setAge(user.getAge());
        curUser.setEmail(user.getEmail());
        curUser.setPassword(user.getPassword());

        Set<Role> roles = new HashSet<>();
        roles.add(roleRepository.getRoleByName(role));
        curUser.setRoles(roles);

        userRepository.save(curUser);
    }

    @Transactional(readOnly = true)
    @Override
    public User getUserById(long id) {
        return userRepository.getUserById(id);
    }

    @Transactional(readOnly = true)
    @Override
    public User getUserByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    @Transactional(readOnly = true)
    @Override
    public List<Role> getAllRoles() {
        return roleRepository.findAll();
    }

    @Override
    public String curUserEmail() {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return ((UserDetails) principal).getUsername();
    }

    @Override
    public String curUserRoles() {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Collection<? extends GrantedAuthority> authorities = ((UserDetails) principal).getAuthorities();
        StringBuilder string_roles = new StringBuilder();
        for (GrantedAuthority authority : authorities) {
            string_roles.append(authority.getAuthority()).append(" ");
        }
        return string_roles.toString();
    }

    @Transactional(readOnly = true)
    @Override
    public List<Role> getRolesById(long id) {
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
    public boolean existUserByEmail(String email) {
        List<User> users = getAllUsers();
        for (User u : users) {
            if (u.getEmail().equals(email)) {
                return true;
            }
        }
        return false;
    }
}
