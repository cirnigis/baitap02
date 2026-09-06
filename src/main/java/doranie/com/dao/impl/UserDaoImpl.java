package doranie.com.dao.impl;

import java.util.List;

import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;

import doranie.com.config.JPAConfig;
import doranie.com.dao.UserDao;
import doranie.com.models.User;

public class UserDaoImpl implements UserDao {

    // LOGIN

    @Override
    public User get(String username) {

        EntityManager em = JPAConfig.getUserEntityManager();

        try {
            String jpql =
                    "SELECT u FROM User u WHERE u.userName = :username";

            TypedQuery<User> query =
                    em.createQuery(jpql, User.class);

            query.setParameter("username", username);

            List<User> result = query.getResultList();

            if (result.isEmpty()) {
                return null;
            }

            return result.get(0);

        } finally {
            em.close();
        }
    }

    // REGISTER

    @Override
    public void insert(User user) {

        EntityManager em = JPAConfig.getUserEntityManager();

        try {
            em.getTransaction().begin();

            em.persist(user);

            em.getTransaction().commit();

        } catch (Exception e) {

            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }

            e.printStackTrace();

        } finally {
            em.close();
        }
    }

    // CHECK EMAIL

    @Override
    public boolean checkExistEmail(String email) {

        EntityManager em = JPAConfig.getUserEntityManager();

        try {
            String jpql =
                    "SELECT u FROM User u WHERE u.email = :email";

            TypedQuery<User> query =
                    em.createQuery(jpql, User.class);

            query.setParameter("email", email);

            return !query.getResultList().isEmpty();

        } finally {
            em.close();
        }
    }

    // CHECK USERNAME

    @Override
    public boolean checkExistUsername(String username) {

        EntityManager em = JPAConfig.getUserEntityManager();

        try {
            String jpql =
                    "SELECT u FROM User u WHERE u.userName = :username";

            TypedQuery<User> query =
                    em.createQuery(jpql, User.class);

            query.setParameter("username", username);

            return !query.getResultList().isEmpty();

        } finally {
            em.close();
        }
    }


    // CHECK SĐT

    @Override
    public boolean checkExistPhone(String phone) {

        EntityManager em = JPAConfig.getUserEntityManager();

        try {
            String jpql =
                    "SELECT u FROM User u WHERE u.phone = :phone";

            TypedQuery<User> query =
                    em.createQuery(jpql, User.class);

            query.setParameter("phone", phone);

            return !query.getResultList().isEmpty();

        } finally {
            em.close();
        }
    }


    // UPDATE PASSWORD

    @Override
    public void updatePassword(String email, String password) {

        EntityManager em = JPAConfig.getUserEntityManager();

        try {
            em.getTransaction().begin();

            String jpql =
                    "SELECT u FROM User u WHERE u.email = :email";

            TypedQuery<User> query =
                    em.createQuery(jpql, User.class);

            query.setParameter("email", email);

            List<User> result = query.getResultList();

            if (!result.isEmpty()) {

                User user = result.get(0);

                user.setPassWord(password);

                em.merge(user);
            }

            em.getTransaction().commit();

        } catch (Exception e) {

            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }

            e.printStackTrace();

        } finally {
            em.close();
        }
    }
}