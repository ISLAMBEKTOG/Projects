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
    @SuppressWarnings("unchecked")
    public List<User> getAllUsers() {
        TypedQuery<User> query = (TypedQuery<User>) sessionFactory.getCurrentSession().createQuery("from User");
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
    public void updateUser(Long id, User user) {
        Query query = sessionFactory.getCurrentSession().createQuery("update User u set u.name = :userName, u.age = :userAge, u.gender = :userGender where u.id = :userId");
        query.setParameter("userId", id);
        query.setParameter("userName", user.getName());
        query.setParameter("userAge", user.getAge());
        query.setParameter("userGender", user.getGender());
        int result = query.executeUpdate();
    }

    @Override
    public User getUserById(Long id) {
        TypedQuery<User> query = (TypedQuery<User>) sessionFactory.getCurrentSession().createQuery("select u from User u where u.id = :userId", User.class);
        query.setParameter("userId", id);
        return query.getResultList().get(0);
    }
}
