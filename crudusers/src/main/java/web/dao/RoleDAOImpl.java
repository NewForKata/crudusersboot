package web.dao;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import web.model.Role;
import web.model.User;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Component
public class RoleDAOImpl implements RoleDAO{
    @PersistenceContext
    private EntityManager entityManager;

    public RoleDAOImpl() {
    }
    
    @Override
    public void save(Role role) {
        Role managed = entityManager.merge(role);
        entityManager.persist(managed);
    }
    
    @Override
    public void delete(Role role) {
        Role managed = entityManager.merge(role);
        entityManager.remove(managed);
    }

    @Override
    public Role getById(Long id) {
        return entityManager.find(Role.class, id );
    }

    @Override
    public Role getRoleByName(String rolename) {
        try{
            Role role = entityManager.createQuery("SELECT r FROM Role r where r.name = :name", Role.class)
                    .setParameter("name", rolename)
                    .getSingleResult();
            return role;
        } catch (NoResultException ex){
            return null;
        }
    }

    
    public Role createRoleIfNotFound(String name, long id) {
        Role role = getRoleByName(name);
        if (role == null) {
            role = new Role(id, name);
            save(role);
        }
        return role;
    }

    @Override
    public Set<Role> getByIds(List<String> roles) {
        List<Role> listRoles = entityManager.createQuery("SELECT r FROM Role r where r.name IN :roles")
        .setParameter("roles", roles)
                .getResultList();
        return new HashSet(listRoles);
    }
}
