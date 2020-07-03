package web.dao;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import web.model.User;

import javax.persistence.Query;
import javax.persistence.TypedQuery;
import java.util.List;

@Repository
public class UserDaoImp implements UserDAO {
    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public List<User> getAllUsers() {
        TypedQuery<User> query = (TypedQuery<User>) sessionFactory.getCurrentSession().createQuery("select u from User u", User.class);
        return query.getResultList();
    }

    @Override
    public void addUser(User user) {
        sessionFactory.getCurrentSession().save(user);
    }

    @Override
    public void deleteUser(Long id) {
        User user = getUserById(id);
        sessionFactory.getCurrentSession().delete(user);
    }

    @Override
    public void updateUser(User user) {
        Query query = sessionFactory.getCurrentSession().createQuery("update User u set u.username = :userUsername, u.password = :userPass, u.age = :userAge where u.id = :userId");
        query.setParameter("userId", user.getId());
        query.setParameter("userUsername", user.getUsername());
        query.setParameter("userPass", user.getPassword());
        query.setParameter("userAge", user.getAge());
        int result = query.executeUpdate();
    }

    @Override
    public User getUserById(Long id) {
        TypedQuery<User> query = (TypedQuery<User>) sessionFactory.getCurrentSession().createQuery("select u from User u where u.id = :userId", User.class);
        query.setParameter("userId", id);
        return query.getResultList().get(0);
    }

    @Override
    public User getUserByUsername(String username) {
        TypedQuery<User> query = (TypedQuery<User>) sessionFactory.getCurrentSession().createQuery("select u from User u where u.username = :userUsername", User.class);
        query.setParameter("userUsername", username);
        return query.getResultList().get(0);
    }
}
