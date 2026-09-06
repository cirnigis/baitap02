package doranie.com.dao.impl;

import java.util.List;

import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;

import doranie.com.config.JPAConfig;
import doranie.com.dao.CategoryDao;
import doranie.com.models.Category;

public class CategoryDaoJPA implements CategoryDao {

    @Override
    public void insert(Category category) {

        EntityManager em = JPAConfig.getCategoryEntityManager();

        try {
            em.getTransaction().begin();

            em.persist(category);

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

    @Override
    public void edit(Category category) {

        EntityManager em = JPAConfig.getCategoryEntityManager();

        try {
            em.getTransaction().begin();

            em.merge(category);

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

    @Override
    public void delete(int id) {

        EntityManager em = JPAConfig.getCategoryEntityManager();

        try {
            em.getTransaction().begin();

            Category category = em.find(Category.class, id);

            if (category != null) {
                em.remove(category);
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

    @Override
    public Category get(int id) {

        EntityManager em = JPAConfig.getCategoryEntityManager();

        try {
            return em.find(Category.class, id);

        } finally {

            em.close();
        }
    }

    @Override
    public Category get(String name) {

        EntityManager em = JPAConfig.getUserEntityManager();

        try {
            String jpql =
                    "SELECT c FROM Category c WHERE c.cateName = :name";

            TypedQuery<Category> query =
                    em.createQuery(jpql, Category.class);

            query.setParameter("name", name);

            List<Category> result = query.getResultList();

            if (result.isEmpty()) {
                return null;
            }

            return result.get(0);

        } finally {

            em.close();
        }
    }

    @Override
    public List<Category> getAll() {

        EntityManager em = JPAConfig.getCategoryEntityManager();

        try {
            String jpql = "SELECT c FROM Category c";

            TypedQuery<Category> query =
                    em.createQuery(jpql, Category.class);

            return query.getResultList();

        } finally {

            em.close();
        }
    }

    @Override
    public List<Category> search(String keyword) {

        EntityManager em = JPAConfig.getCategoryEntityManager();

        try {
            String jpql =
                    "SELECT c FROM Category c "
                    + "WHERE c.cateName LIKE :keyword";

            TypedQuery<Category> query =
                    em.createQuery(jpql, Category.class);

            query.setParameter("keyword", "%" + keyword + "%");

            return query.getResultList();

        } finally {

            em.close();
        }
    }
}