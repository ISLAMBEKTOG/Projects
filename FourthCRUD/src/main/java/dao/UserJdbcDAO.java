package dao;

import connection.DBHelper;
import model.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserJdbcDAO implements UserDAO {
    private Connection connection;

    public UserJdbcDAO() {
        this.connection = DBHelper.getInstance().getConnection();
    }

    public List<User> getAllUsers() {
        List<User> users = new ArrayList<>();
        try {
            String sql = "select * from users";
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.execute();

            ResultSet result = stmt.getResultSet();
            while (result.next()) {
                users.add(new User(result.getLong("id"),
                        result.getString("login"),
                        result.getString("password"),
                        result.getInt("age"),
                        result.getString("gender"),
                        result.getString("role")));
            }

            result.close();
            stmt.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return users;
    }

    public void addUser(String login, String password, int age, String gender, String role) throws SQLException {
        if (!existUser(login)) {
            String sql = "insert into users (login, password, age, gender, role) values (?, ?, ?, ?, ?)";
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setString(1, login);
            stmt.setString(2, password);
            stmt.setInt(3, age);
            stmt.setString(4, gender);
            stmt.setString(5, role);
            stmt.execute();
            stmt.close();
        }
    }

    public void deleteUser(Long id) throws SQLException {
        String sql = "delete from users where id=?";
        PreparedStatement stmt = connection.prepareStatement(sql);
        stmt.setLong(1, id);
        stmt.execute();
        stmt.close();
    }

    public void updateUser(Long id, String login, String password, int age, String gender, String role) throws SQLException {
        String sql = "update users set login = ?, password = ?, age = ?, gender = ?, role = ? where id = ?";
        PreparedStatement stmt = connection.prepareStatement(sql);
        stmt.setString(1, login);
        stmt.setString(2, password);
        stmt.setInt(3, age);
        stmt.setString(4, gender);
        stmt.setString(5, role);
        stmt.setLong(6, id);

        stmt.executeUpdate();
        stmt.close();
    }

    public User getUserById(Long id) throws SQLException {
        String sql = "select * from users where id=?";
        PreparedStatement stmt = connection.prepareStatement(sql);
        stmt.setLong(1, id);
        stmt.execute();

        ResultSet resultSet = stmt.getResultSet();
        resultSet.next();

        String login = resultSet.getString("login");
        String password = resultSet.getString("password");
        int age = resultSet.getInt("age");
        String gender = resultSet.getString("gender");
        String role = resultSet.getString("role");
        User user = new User(id, login, password, age, gender, role);

        resultSet.close();
        stmt.close();
        return user;
    }

    @Override
    public User getUserByLogin(String login) throws SQLException {
        String sql = "select * from users where login=?";
        PreparedStatement stmt = connection.prepareStatement(sql);
        stmt.setString(1, login);
        stmt.execute();

        ResultSet resultSet = stmt.getResultSet();
        resultSet.next();

        Long id = resultSet.getLong("id");
        String password = resultSet.getString("password");
        int age = resultSet.getInt("age");
        String gender = resultSet.getString("gender");
        String role = resultSet.getString("role");
        User user = new User(id, login, password, age, gender, role);

        resultSet.close();
        stmt.close();
        return user;
    }

    @Override
    public boolean existUser(String login) {
        List<User> users = getAllUsers();
        for (User user : users) {
            if (user.getLogin().equalsIgnoreCase(login)) {
                return true;
            }
        }
        return false;
    }
}
