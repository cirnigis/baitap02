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

@WebServlet(urlPatterns = { "/product/edit" })
public class ProductEditController extends HttpServlet {

	private static final long serialVersionUID = 1L;

	private final ProductService productService = new ProductServiceImpl();
	private final CategoryService categoryService = new CategoryServiceImpl();

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		String idStr = req.getParameter("id");

		try {
			int id = Integer.parseInt(idStr);

			Product product = productService.get(id);

			if (product == null) {
				resp.sendRedirect(req.getContextPath() + "/product");
				return;
			}

			List<Category> categories = categoryService.getAll();

			req.setAttribute("product", product);
			req.setAttribute("categories", categories);

			req.getRequestDispatcher("/views/product-edit.jsp").forward(req, resp);

		} catch (Exception e) {
			e.printStackTrace();
			resp.sendRedirect(req.getContextPath() + "/product");
		}
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		req.setCharacterEncoding("UTF-8");

		try {
			int id = Integer.parseInt(req.getParameter("id"));

			String name = req.getParameter("name");
			String description = req.getParameter("description");
			BigDecimal price = new BigDecimal(req.getParameter("price"));
			String image = req.getParameter("image");
			int quantity = Integer.parseInt(req.getParameter("quantity"));
			int categoryId = Integer.parseInt(req.getParameter("categoryId"));

			Product product = productService.get(id);

			if (product == null) {
				resp.sendRedirect(req.getContextPath() + "/product");
				return;
			}

			Category category = categoryService.get(categoryId);

			product.setName(name);
			product.setDescription(description);
			product.setPrice(price);
			product.setImage(image);
			product.setQuantity(quantity);
			product.setCategory(category);

			productService.edit(product);

			resp.sendRedirect(req.getContextPath() + "/product");

		} catch (Exception e) {
			e.printStackTrace();

			req.setAttribute("error", "Không thể cập nhật sản phẩm.");

			try {
				int id = Integer.parseInt(req.getParameter("id"));
				Product product = productService.get(id);
				List<Category> categories = categoryService.getAll();

				req.setAttribute("product", product);
				req.setAttribute("categories", categories);
			} catch (Exception ex) {
				ex.printStackTrace();
			}

			req.getRequestDispatcher("/views/product-edit.jsp").forward(req, resp);
		}
	}
}