package web.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserDetailsServiceImp implements UserDetailsService {

    @Autowired
    private UserService userService;

    @Transactional(readOnly = true)
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userService.getUserByUsername(username);
//        User user = userService.getUserByLogin(login);
//        Set<GrantedAuthority> roles = new HashSet<>();
//
//        List<Role> userRoles = userService.getRolesById(user.getId());
//        for (Role r : userRoles) {
//            roles.add(new SimpleGrantedAuthority(r.getName()));
//        }
//
//        UserDetails userDetails =
//                new org.springframework.security.core.userdetails.User(user.getLogin(), user.getPassword(), roles);
//
//        return userDetails;
    }
}
