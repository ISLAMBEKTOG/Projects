package hiber;

import hiber.config.AppConfig;
import hiber.model.Car;
import hiber.model.User;
import hiber.service.UserService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.sql.SQLException;
import java.util.List;

public class MainApp {
    public static void main(String[] args) throws SQLException {
        AnnotationConfigApplicationContext context =
                new AnnotationConfigApplicationContext(AppConfig.class);

        UserService userService = context.getBean(UserService.class);

        User user1 = new User("User1", "Lastname1", "user1@mail.ru");
        User user2 = new User("User2", "Lastname2", "user2@mail.ru");
        User user3 = new User("User3", "Lastname3", "user3@mail.ru");
        User user4 = new User("User4", "Lastname4", "user4@mail.ru");

        user1.setCar(new Car("mers", 1111));
        user2.setCar(new Car("bmw", 4444));
        user3.setCar(new Car("tayota", 2222));
        user4.setCar(new Car("nissan", 7777));

        userService.add(user1);
        userService.add(user2);
        userService.add(user3);
        userService.add(user4);


        List<User> users = userService.listUsers();
        for (User user : users) {
            System.out.println("Id = " + user.getId());
            System.out.println("First Name = " + user.getFirstName());
            System.out.println("Last Name = " + user.getLastName());
            System.out.println("Email = " + user.getEmail());
            System.out.println("Car name = " + user.getCar().getName());
            System.out.println("Car series = " + user.getCar().getSeries());
            System.out.println();
        }

        System.out.println("======================================");

        User userBySeries = userService.getUserBySeries(7777);
        System.out.println("Id = " + userBySeries.getId());
        System.out.println("First Name = " + userBySeries.getFirstName());
        System.out.println("Last Name = " + userBySeries.getLastName());
        System.out.println("Email = " + userBySeries.getEmail());
        System.out.println("Car name = " + userBySeries.getCar().getName());
        System.out.println("Car series = " + userBySeries.getCar().getSeries());
        System.out.println();

        context.close();
    }
}
