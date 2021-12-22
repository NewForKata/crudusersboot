package web.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import web.dao.RoleDAO;
import web.model.Role;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class RoleServiceImpl implements RoleService {


    private final RoleDAO roleDAO;

    public RoleServiceImpl(RoleDAO roleDAO) {
        this.roleDAO = roleDAO;
    }

    @Override
    public void save(Role role) {
        roleDAO.save(role);
    }

    @Override
    public void delete(Role role) {
        roleDAO.delete(role);
    }

    @Override
    public Role getById(Long id) {
        return getById(id);
    }

    @Override
    public Role getRoleByName(String rolename) {
        return getRoleByName(rolename);
    }

    @Override
    public Role createRoleIfNotFound(String name, long id) {
        return createRoleIfNotFound(name, id);
    }

    @Override
    public Set<Role> getRolesForPage() {
        HashSet<Role> setroles = new HashSet<>();
        Role role_admin = roleDAO.createRoleIfNotFound("ADMIN", 1L);
        Role role_user = roleDAO.createRoleIfNotFound("USER", 2L);
        setroles.add(role_admin);
        setroles.add(role_user);
        return setroles;
    }

    @Override
    public Set<Role> getByIds(List<String> roles) {
        return roleDAO.getByIds(roles);
        }

}
