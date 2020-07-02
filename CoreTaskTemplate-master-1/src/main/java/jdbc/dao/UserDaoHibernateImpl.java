package jdbc.dao;

import jdbc.model.User;
import jdbc.util.Util;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import java.util.List;

public class UserDaoHibernateImpl implements UserDao {
    private SessionFactory sessionFactory;

    public UserDaoHibernateImpl() {
        this.sessionFactory = Util.getSessionFactory();
    }

    @Override
    public void createUsersTable() {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();

        Query query = session.createSQLQuery("create table if not exists users (id bigint auto_increment, name varchar(255), last_name varchar(255), age tinyint, primary key (id))");
        query.executeUpdate();

        transaction.commit();
    }

    @Override
    public void dropUsersTable() {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();

        Query query = session.createSQLQuery("drop table if exists users");
        query.executeUpdate();

        transaction.commit();
    }

    @Override
    public void saveUser(String name, String lastName, byte age) {
        User user = new User(name, lastName, age);
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        session.save(user);
        transaction.commit();
    }

    @Override
    public void removeUserById(long id) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        session.delete(getUserById(id));
        transaction.commit();
    }

    @Override
    public List<User> getAllUsers() {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        List users = session.createQuery("FROM User").list();
        transaction.commit();
        return users;
    }

    @Override
    public void cleanUsersTable() {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();

        Query query = session.createQuery("delete from User");
        query.executeUpdate();

        transaction.commit();
    }

    public User getUserById(Long id) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();

        Query query = session.createQuery("FROM User u where u.id= :userId");
        query.setParameter("userId", id);
        List list = query.list();
        User user = (User) list.get(0);
        transaction.commit();

        return user;
    }
}
