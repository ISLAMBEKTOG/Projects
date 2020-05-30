package service;

import dao.UserDAO;
import factory.UserDaoFactory;
import model.User;

import java.io.IOException;
import java.io.InputStream;
import java.sql.SQLException;
import java.util.List;
import java.util.Properties;

public class Service {
    private UserDAO dao;
    private static Service service;

    public static Service getInstance() {
        if (service == null) {
            service = new Service();
        }
        return service;
    }

    private Service() {
        dao = getUserDAO();
    }

    public List<User> getAllUsers() {
        return dao.getAllUsers();
    }

    public void deleteUser(Long id) throws SQLException {
        dao.deleteUser(id);
    }

    public void addUser(String login, String password, int age, String gender, String role) throws SQLException {
        dao.addUser(login, password, age, gender, role);
    }

    public void updateUser(Long id, String login, String password, int age, String gender, String role) throws SQLException {
        dao.updateUser(id, login, password, age, gender, role);
    }

    public User getUserById(Long id) throws SQLException {
        return dao.getUserById(id);
    }

    public User getUserByLogin(String login) throws SQLException {
        return dao.getUserByLogin(login);
    }

    public String getRoleByLogin(String login) throws SQLException {
        User user = getUserByLogin(login);
        return user.getRole();
    }

    public boolean existUser(String login) {
        return dao.existUser(login);
    }

    public boolean validateUser(String login, String password) {
        if(existUser(login)){
            List<User> users = getAllUsers();
            for (User user : users) {
                if (user.getLogin().equalsIgnoreCase(login) && user.getPassword().equalsIgnoreCase(password)) {
                    return true;
                }
            }
        }
        return false;
    }

    private static UserDAO getUserDAO() {
        InputStream inputStream;
        Properties property = new Properties();
        UserDAO userDAO = null;
        try {
            inputStream = Service.class.getClassLoader().getResourceAsStream("db.properties");
            property.load(inputStream);
            String daotype = property.getProperty("daotype");

            userDAO = new UserDaoFactory().getUserDAO(daotype);

        } catch (IOException ignored) {
        }

        return userDAO;
    }
}
