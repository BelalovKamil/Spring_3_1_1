package web.dao;

import org.springframework.stereotype.Repository;
import web.model.User;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;

@Repository
public class UserDaoImpl implements UserDao {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public void saveUser(User user) {
        entityManager.persist(user);
    }

    @Override
    public void removeUserById(long id) {
        entityManager.createQuery("delete from User where id = :id")
                .setParameter("id", id)
                .executeUpdate();
    }

    @Override
    public void updateUserById(long id, User updateUser) {
        entityManager.createQuery("update User set email = :email" +
                ", name = :name, surname = :surname where id = :id")
                .setParameter("id", id)
                .setParameter("name", updateUser.getName())
                .setParameter("surname", updateUser.getSurname())
                .setParameter("email", updateUser.getEmail())
                .executeUpdate();
    }

    @Override
    public List<User> getAllUsers() {
        return entityManager.createQuery("select u from User u", User.class).getResultList();
    }

    @Override
    public User getUserById(long id) {
        return entityManager.find(User.class, id);
    }
}
