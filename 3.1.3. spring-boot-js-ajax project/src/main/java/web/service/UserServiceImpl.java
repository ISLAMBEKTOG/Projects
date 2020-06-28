package web.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import web.model.Role;
import web.model.User;
import web.model.UserForm;
import web.repositories.RoleRepo;
import web.repositories.UserRepo;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class UserServiceImpl implements UserService {
    private final UserRepo userRepo;

    private final RoleRepo roleRepo;

    @Autowired
    public UserServiceImpl(UserRepo userRepo, RoleRepo roleRepo) {
        this.userRepo = userRepo;
        this.roleRepo = roleRepo;
    }

    @Transactional
    @Override
    public void createUser(UserForm userForm) {
        User user = new User(userForm.getFirstName(), userForm.getLastName(),
                userForm.getAge(), userForm.getEmail(), userForm.getPassword());
        if (!validateUser(user)) {
            Set<Role> roles = new HashSet<>();
            roles.add(roleRepo.getRoleByName(userForm.getRole()));
            user.setRoles(roles);
            userRepo.save(user);
        }
    }

    @Transactional(readOnly = true)
    @Override
    public List<User> readAllUsers() {
        return userRepo.findAll();
    }

    @Transactional(readOnly = true)
    @Override
    public User readUser(long id) {
        return userRepo.findById(id).isPresent() ? userRepo.findById(id).get() : null;
    }

    @Transactional
    @Override
    public void updateUser(UserForm userForm) {
        User curUser = readUser(userForm.getId());
        curUser.setFirstName(userForm.getFirstName());
        curUser.setLastName(userForm.getLastName());
        curUser.setAge(userForm.getAge());
        curUser.setEmail(userForm.getEmail());
        curUser.setPassword(userForm.getPassword());

        Set<Role> roles = new HashSet<>();
        roles.add(roleRepo.getRoleByName(userForm.getRole()));
        curUser.setRoles(roles);

        userRepo.save(curUser);
    }

    @Transactional
    @Override
    public void deleteUser(long id) {
        userRepo.deleteById(id);
    }

    @Transactional
    @Override
    public List<Role> getRolesByUserId(long id) {
        return roleRepo.getRolesByUserId(id);
    }

    @Override
    public boolean validateUser(User user) {
        List<User> users = readAllUsers();
        for (User u : users) {
            if (u.equals(user)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public User getUserByEmail(String email) {
        return userRepo.findByEmail(email);
    }

    @Override
    public String curUserEmail() {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return ((UserDetails) principal).getUsername();
    }
}
