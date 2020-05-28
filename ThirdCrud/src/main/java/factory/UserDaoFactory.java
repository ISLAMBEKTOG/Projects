package factory;

import dao.UserDAO;
import dao.UserHibernateDAO;
import dao.UserJdbcDAO;

public class UserDaoFactory {
    public UserDAO getUserDAO(String daotype) {
        if (daotype.equalsIgnoreCase("jdbc")) {
            return new UserJdbcDAO();
        } else {
            return new UserHibernateDAO();
        }
    }
}
