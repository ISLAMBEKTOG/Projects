package web.dao;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import web.model.Role;

import javax.persistence.TypedQuery;
import java.util.List;

@Repository
public class RoleDaoImp implements RoleDAO {

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public List<Role> getAllRoles() {
        TypedQuery<Role> query = (TypedQuery<Role>) sessionFactory.getCurrentSession().createQuery("select r from Role r", Role.class);
        return query.getResultList();
    }

    @Override
    public Role getRoleByName(String name) {
        TypedQuery<Role> query = (TypedQuery<Role>) sessionFactory.getCurrentSession().createQuery("select r from Role r where r.name = :roleName", Role.class);
        query.setParameter("roleName", name);
        return query.getResultList().get(0);
    }

    @Override
    public List<Role> getRolesById(long id) {
        TypedQuery<Role> query = (TypedQuery<Role>) sessionFactory.getCurrentSession().createQuery("select r from User u join u.roles r where u.id = :userId", Role.class);
        query.setParameter("userId", id);
        return query.getResultList();
    }
}
