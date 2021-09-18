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
        entityManager.createQuery("DELETE FROM User WHERE id = :id")
                .setParameter("id", id)
                .executeUpdate();
    }

    @Override
    public void updateUserById(long id, User updateUser) {
        User user = entityManager.find(User.class, id);
        user.setEmail(updateUser.getEmail());
        user.setName(updateUser.getName());
        user.setSurname(updateUser.getSurname());
    }

    @Override
    public List<User> getAllUsers() {
        TypedQuery<User> query = entityManager.createQuery("select u from User u", User.class);
        System.out.println("Запрос на вывод");
        return query.getResultList();
    }

    @Override
    public User getUserById(long id) {
        User user = entityManager.find(User.class, id);
        System.out.println("найден пользователь: " + user);
        return user;
    }
}
