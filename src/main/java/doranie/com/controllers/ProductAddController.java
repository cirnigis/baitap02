package doranie.com.controllers;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.List;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import doranie.com.models.Category;
import doranie.com.models.Product;
import doranie.com.service.CategoryService;
import doranie.com.service.ProductService;
import doranie.com.service.impl.CategoryServiceImpl;
import doranie.com.service.impl.ProductServiceImpl;

@WebServlet(urlPatterns = { "/product/add" })
public class ProductAddController extends HttpServlet {

	private static final long serialVersionUID = 1L;

	private final ProductService productService = new ProductServiceImpl();
	private final CategoryService categoryService = new CategoryServiceImpl();

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		List<Category> categories = categoryService.getAll();

		req.setAttribute("categories", categories);

		req.getRequestDispatcher("/views/product-add.jsp").forward(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		req.setCharacterEncoding("UTF-8");

		String name = req.getParameter("name");
		String description = req.getParameter("description");
		String priceStr = req.getParameter("price");
		String image = req.getParameter("image");
		String quantityStr = req.getParameter("quantity");
		String categoryIdStr = req.getParameter("categoryId");

		try {
			BigDecimal price = new BigDecimal(priceStr);
			int quantity = Integer.parseInt(quantityStr);
			int categoryId = Integer.parseInt(categoryIdStr);

			Category category = categoryService.get(categoryId);

			Product product = new Product(name, description, price, image, quantity, category, new java.util.Date());

			productService.insert(product);

			resp.sendRedirect(req.getContextPath() + "/product");

		} catch (Exception e) {
			e.printStackTrace();

			req.setAttribute("error", "Không thể thêm sản phẩm.");

			List<Category> categories = categoryService.getAll();
			req.setAttribute("categories", categories);

			req.getRequestDispatcher("/views/product-add.jsp").forward(req, resp);
		}
	}
}