package web.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import web.model.User;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Repository
public class UserDAOImpl implements UserDAO {

    @Autowired
    private EntityManagerFactory entityManagerFactory;

    @Override
    public void add(User user) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();
        entityManager.persist(user);
        entityManager.getTransaction().commit();
        entityManager.close();
    }

    @Override
    public List<User> listUsers() {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        TypedQuery<User> query = entityManager.createQuery("SELECT user FROM User user ", User.class);
        List<User> users = new ArrayList<>();
        try {
            users = query.getResultList();
        } catch (Exception e) {
            System.err.println(Arrays.toString(e.getStackTrace()));
        } finally {
            entityManager.close();
        }
        return users;

    }

    @Override
    public User getUserById(int id) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        User user = new User();
        try {
            user = entityManager.find(User.class, id);
        } catch (Exception e) {
            System.err.println(Arrays.toString(e.getStackTrace()));
        } finally {
            entityManager.close();
        }
        return user;
    }

    @Override
    public void dropUserById(int id) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();
        try {
            entityManager.remove(entityManager.find(User.class, id));
            entityManager.getTransaction().commit();
        } catch (Exception e) {
            System.err.println(Arrays.toString(e.getStackTrace()));
        } finally {
            entityManager.close();
        }
    }

    @Override
    public void updateUserNameById(int id, User updatedUser) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();
        User userForUpgrade = entityManager.find(User.class, id);
        try {
            userForUpgrade.setName(updatedUser.getName());
            userForUpgrade.setSurname(updatedUser.getSurname());
            userForUpgrade.setNickname(updatedUser.getNickname());
            userForUpgrade.setEmail(updatedUser.getEmail());
            entityManager.getTransaction().commit();
        } catch (Exception e) {
            System.err.println(Arrays.toString(e.getStackTrace()));
        } finally {
            entityManager.close();
        }
    }
}
