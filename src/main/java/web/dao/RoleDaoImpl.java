package web.dao;

import org.springframework.stereotype.Repository;
import web.model.Role;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Repository
public class RoleDaoImpl implements RoleDao{

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public Role getRoleByName(String name) {
        name = "ROLE_" + name.toUpperCase();
        return entityManager.createQuery("select u from Role u where u.role = :role", Role.class)
                .setParameter("role", name)
                .getSingleResult();
    }
}
