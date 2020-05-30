package dao;

import connection.DBHelper;
import model.User;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;

import java.sql.SQLException;
import java.util.List;

public class UserHibernateDAO implements UserDAO {
    private SessionFactory sessionFactory;

    public UserHibernateDAO() {
        this.sessionFactory = createSessionFactory();
    }

    private static SessionFactory createSessionFactory() {
        Configuration configuration = DBHelper.getInstance().getConfiguration();
        StandardServiceRegistryBuilder builder = new StandardServiceRegistryBuilder();
        builder.applySettings(configuration.getProperties());
        ServiceRegistry serviceRegistry = builder.build();
        return configuration.buildSessionFactory(serviceRegistry);
    }

    @Override
    public List<User> getAllUsers() {
        Session session = null;
        Transaction transaction = null;
        List users = null;

        try {
            session = sessionFactory.openSession();
            transaction = session.beginTransaction();
            users = session.createQuery("FROM User").list();
            transaction.commit();
        } catch (Exception e) {
            e.printStackTrace();

            if (transaction != null) transaction.rollback();
        } finally {
            try {
                if (session != null) session.close();
            } catch (Exception ignored) {
            }
        }
        return users;
    }

    @Override
    public void addUser(String login, String password, int age, String gender, String role) {


        Session session = null;
        Transaction transaction = null;

        try {
            if (!existUser(login)) {
                User user = new User(login, password, age, gender, role);

                session = sessionFactory.openSession();
                transaction = session.beginTransaction();
                session.save(user);
                transaction.commit();
            }
        } catch (Exception e) {
            e.printStackTrace();

            if (transaction != null) transaction.rollback();
        } finally {
            try {
                if (session != null) session.close();
            } catch (Exception ignored) {
            }
        }
    }

    @Override
    public void deleteUser(Long id) {
        Session session = null;
        Transaction transaction = null;

        try {
            session = sessionFactory.openSession();
            transaction = session.beginTransaction();
            session.delete(getUserById(id));
            transaction.commit();
        } catch (Exception e) {
            e.printStackTrace();

            if (transaction != null) transaction.rollback();
        } finally {
            try {
                if (session != null) session.close();
            } catch (Exception ignored) {
            }
        }
    }

    @Override
    public void updateUser(Long id, String login, String password, int age, String gender, String role) {
        Session session = null;
        Transaction transaction = null;

        try {
            session = sessionFactory.openSession();
            transaction = session.beginTransaction();
            User user = new User(id, login, password, age, gender, role);
            session.update(user);

            transaction.commit();
        } catch (Exception e) {
            e.printStackTrace();

            if (transaction != null) transaction.rollback();
        } finally {
            try {
                if (session != null) session.close();
            } catch (Exception ignored) {
            }
        }
    }

    @Override
    public User getUserById(Long id) {
        Session session = null;
        Transaction transaction = null;
        User user = null;
        try {
            session = sessionFactory.openSession();
            transaction = session.beginTransaction();

            Query query = session.createQuery("FROM User u where u.id= :userId");
            query.setParameter("userId", id);
            List list = query.list();
            user = (User) list.get(0);

            transaction.commit();
        } catch (Exception e) {
            e.printStackTrace();

            if (transaction != null) transaction.rollback();
        } finally {
            try {
                if (session != null) session.close();
            } catch (Exception ignored) {
            }
        }
        return user;
    }

    @Override
    public User getUserByLogin(String login) throws SQLException {
        Session session = null;
        Transaction transaction = null;
        User user = null;
        try {
            session = sessionFactory.openSession();
            transaction = session.beginTransaction();

            Query query = session.createQuery("FROM User u where u.login= :userLogin");
            query.setParameter("userLogin", login);
            List list = query.list();
            user = (User) list.get(0);

            transaction.commit();
        } catch (Exception e) {
            e.printStackTrace();

            if (transaction != null) transaction.rollback();
        } finally {
            try {
                if (session != null) session.close();
            } catch (Exception ignored) {
            }
        }
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
