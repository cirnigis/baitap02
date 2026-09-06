package doranie.com.dao.impl;

import java.util.List;

import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;

import doranie.com.config.JPAConfig;
import doranie.com.dao.ProductDao;
import doranie.com.models.Product;

public class ProductDaoJPA implements ProductDao {

	@Override
	public void insert(Product product) {
		EntityManager em = JPAConfig.getCategoryEntityManager();

		try {
			em.getTransaction().begin();
			em.persist(product);
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
	public void edit(Product product) {
		EntityManager em = JPAConfig.getCategoryEntityManager();

		try {
			em.getTransaction().begin();
			em.merge(product);
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

			Product product = em.find(Product.class, id);

			if (product != null) {
				em.remove(product);
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
	public Product get(int id) {
		EntityManager em = JPAConfig.getCategoryEntityManager();

		try {
			return em.find(Product.class, id);
		} finally {
			em.close();
		}
	}

	@Override
	public List<Product> getAll() {
		EntityManager em = JPAConfig.getCategoryEntityManager();

		try {
			String jpql = "SELECT p FROM Product p";

			TypedQuery<Product> query = em.createQuery(jpql, Product.class);

			return query.getResultList();
		} finally {
			em.close();
		}
	}

	@Override
	public List<Product> search(String keyword) {
		EntityManager em = JPAConfig.getCategoryEntityManager();

		try {
			String jpql = "SELECT p FROM Product p WHERE p.name LIKE :keyword";

			TypedQuery<Product> query = em.createQuery(jpql, Product.class);

			query.setParameter("keyword", "%" + keyword + "%");

			return query.getResultList();
		} finally {
			em.close();
		}
	}

	@Override
	public List<Product> getLatest(int limit) {
		EntityManager em = JPAConfig.getCategoryEntityManager();

		try {
			String jpql = "SELECT p FROM Product p ORDER BY p.createdDate DESC";

			TypedQuery<Product> query = em.createQuery(jpql, Product.class);

			query.setMaxResults(limit);

			return query.getResultList();

		} finally {
			em.close();
		}
	}

	@Override
	public List<Product> getPage(int page, int pageSize) {
		EntityManager em = JPAConfig.getCategoryEntityManager();

		try {
			String jpql = "SELECT p FROM Product p ORDER BY p.id DESC";

			TypedQuery<Product> query = em.createQuery(jpql, Product.class);

			query.setFirstResult((page - 1) * pageSize);
			query.setMaxResults(pageSize);

			return query.getResultList();

		} finally {
			em.close();
		}
	}

	@Override
	public int count() {
		EntityManager em = JPAConfig.getCategoryEntityManager();

		try {
			String jpql = "SELECT COUNT(p) FROM Product p";

			TypedQuery<Long> query = em.createQuery(jpql, Long.class);

			return query.getSingleResult().intValue();

		} finally {
			em.close();
		}
	}
}