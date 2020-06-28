package web.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import web.model.User;

public interface UserRepo extends JpaRepository<User, Long> {
    User findByEmail(String email);
}
