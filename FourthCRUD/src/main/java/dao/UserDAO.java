package dao;

import model.User;

import java.sql.SQLException;
import java.util.List;

public interface UserDAO {
    List<User> getAllUsers();

    void addUser(String login, String password, int age, String gender, String role) throws SQLException;

    void deleteUser(Long id) throws SQLException;

    void updateUser(Long id, String login, String password, int age, String gender, String role) throws SQLException;

    User getUserById(Long id) throws SQLException;

    User getUserByLogin(String login) throws SQLException;

    boolean existUser(String login);
}
