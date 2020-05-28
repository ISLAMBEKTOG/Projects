package dao;

import model.User;

import java.sql.SQLException;
import java.util.List;

public interface UserDAO {
    List<User> getAllUsers() throws SQLException;

    void addUser(String name, int age, String gender) throws SQLException;

    void deleteUser(Long id) throws SQLException;

    void updateUser(Long id, String name, int age, String gender) throws SQLException;

    User getUserById(Long id) throws SQLException;
}
