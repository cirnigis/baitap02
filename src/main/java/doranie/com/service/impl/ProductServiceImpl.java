package doranie.com.service.impl;

import java.util.List;

import doranie.com.dao.ProductDao;
import doranie.com.dao.impl.ProductDaoJPA;
import doranie.com.models.Product;
import doranie.com.service.ProductService;

public class ProductServiceImpl implements ProductService {

	private final ProductDao productDao = new ProductDaoJPA();

	@Override
	public void insert(Product product) {
		productDao.insert(product);
	}

	@Override
	public void edit(Product product) {
		productDao.edit(product);
	}

	@Override
	public void delete(int id) {
		productDao.delete(id);
	}

	@Override
	public Product get(int id) {
		return productDao.get(id);
	}

	@Override
	public List<Product> getAll() {
		return productDao.getAll();
	}

	@Override
	public List<Product> search(String keyword) {
		return productDao.search(keyword);
	}

	@Override
	public List<Product> getLatest(int limit) {
		return productDao.getLatest(limit);
	}

	@Override
	public List<Product> getPage(int page, int pageSize) {
		return productDao.getPage(page, pageSize);
	}

	@Override
	public int count() {
		return productDao.count();
	}
}