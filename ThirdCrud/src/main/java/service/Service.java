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
