package doranie.com.dao;

import java.util.List;
import doranie.com.models.Product;

public interface ProductDao {
	void insert(Product product);

	void edit(Product product);

	void delete(int id);

	Product get(int id);

	List<Product> getAll();

	List<Product> search(String keyword);

	List<Product> getLatest(int limit);

	List<Product> getPage(int page, int pageSize);

	int count();
}