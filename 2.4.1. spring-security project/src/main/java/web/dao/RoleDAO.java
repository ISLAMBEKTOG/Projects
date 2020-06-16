package web.dao;

import web.model.Role;

import java.util.List;

public interface RoleDAO {
    List<Role> getAllRoles();

    Role getRoleByName(String name);

    List<Role> getRolesById(long id);
}
