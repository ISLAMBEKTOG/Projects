package jm.task.core.jdbc;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.service.UserService;
import jm.task.core.jdbc.service.UserServiceImpl;

import java.util.ArrayList;
import java.util.List;

public class Main {
    private static UserService userService = new UserServiceImpl();

    public static void main(String[] args) {
        System.out.println("Create table");
        userService.createUsersTable();

        List<User> users = new ArrayList<>();
        users.add(new User("name1", "lastName1", (byte) 18));
        users.add(new User("name2", "lastName2", (byte) 19));
        users.add(new User("name3", "lastName3", (byte) 20));
        users.add(new User("name4", "lastName4", (byte) 21));

        for (User user: users){
            userService.saveUser(user.getName(), user.getLastName(), user.getAge());
            System.out.println("User с иминем - " + user.getName() + " добавлен в базу данных");
        }

        for (User user: userService.getAllUsers()){
            System.out.println(user);
        }

        System.out.println("Clean table");
        userService.cleanUsersTable();

        System.out.println("Drop table");
        userService.dropUsersTable();
    }
}
