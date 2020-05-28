package service;

import dao.UserDAO;
import dao.UserHibernateDAO;
import model.User;

import java.sql.SQLException;
import java.util.List;

public class UserService {
    private UserDAO dao;
    private static UserService userService;

    public static UserService getInstance() {
        if (userService == null) {
            userService = new UserService();
        }
        return userService;
    }

    private UserService() {
        dao = getUserDAO();
    }

    public List<User> getAllUsers() throws SQLException {
        return dao.getAllUsers();
    }

    public void deleteUser(Long id) throws SQLException {
        dao.deleteUser(id);
    }

    public void addUser(String name, int age, String gender) throws SQLException {
        dao.addUser(name, age, gender);
    }

    public void updateUser(Long id, String name, int age, String gender) throws SQLException {
        dao.updateUser(id, name, age, gender);
    }

    public User getUserById(Long id) throws SQLException {
        return dao.getUserById(id);
    }

    private static UserDAO getUserDAO() {
//        return new UserJdbcDAO();
        return new UserHibernateDAO();
    }
}
