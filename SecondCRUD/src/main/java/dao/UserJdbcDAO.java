package dao;

import connection.MySqlConnectionJdbc;
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
        this.connection = MySqlConnectionJdbc.getMysqlConnection();
    }

    public List<User> getAllUsers() throws SQLException {
        List<User> users = new ArrayList<>();

        String sql = "select * from users";
        PreparedStatement stmt = connection.prepareStatement(sql);
        stmt.execute();

        ResultSet result = stmt.getResultSet();
        while (result.next()) {
            users.add(new User(result.getLong("id"),
                    result.getString("name"),
                    result.getInt("age"),
                    result.getString("gender")));
        }

        result.close();
        stmt.close();
        return users;
    }

    public void addUser(String name, int age, String gender) throws SQLException {
        String sql = "insert into users (name, age, gender) values (?, ?, ?)";
        PreparedStatement stmt = connection.prepareStatement(sql);
        stmt.setString(1, name);
        stmt.setInt(2, age);
        stmt.setString(3, gender);
        stmt.execute();
        stmt.close();
    }

    public void deleteUser(Long id) throws SQLException {
        String sql = "delete from users where id=?";
        PreparedStatement stmt = connection.prepareStatement(sql);
        stmt.setLong(1, id);
        stmt.execute();
        stmt.close();
    }

    public void updateUser(Long id, String name, int age, String gender) throws SQLException {
        String sql = "update users set name = ?, age = ?, gender = ? where id = ?";
        PreparedStatement stmt = connection.prepareStatement(sql);
        stmt.setString(1, name);
        stmt.setInt(2, age);
        stmt.setString(3, gender);
        stmt.setLong(4, id);

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

        String name = resultSet.getString("name");
        int age = resultSet.getInt("age");
        String gender = resultSet.getString("gender");
        User user = new User(id, name, age, gender);

        resultSet.close();
        stmt.close();
        return user;
    }
}
