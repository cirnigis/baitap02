package doranie.com.controllers;

import java.io.IOException;
import java.util.List;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import doranie.com.models.Product;
import doranie.com.service.ProductService;
import doranie.com.service.impl.ProductServiceImpl;

@WebServlet(urlPatterns = { "/product" })
public class ProductController extends HttpServlet {

	private static final long serialVersionUID = 1L;

	private final ProductService productService = new ProductServiceImpl();

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		req.setCharacterEncoding("UTF-8");
		resp.setCharacterEncoding("UTF-8");
		resp.setContentType("text/html; charset=UTF-8");

		int page = 1;
		int pageSize = 6;

		String pageStr = req.getParameter("page");

		if (pageStr != null) {
			try {
				page = Integer.parseInt(pageStr);
			} catch (NumberFormatException e) {
				page = 1;
			}
		}

		if (page < 1) {
			page = 1;
		}

		int totalProducts = productService.count();
		int totalPages = (int) Math.ceil((double) totalProducts / pageSize);

		if (totalPages > 0 && page > totalPages) {
			page = totalPages;
		}

		List<Product> products = productService.getPage(page, pageSize);

		req.setAttribute("products", products);
		req.setAttribute("currentPage", page);
		req.setAttribute("totalPages", totalPages);

		req.getRequestDispatcher("/views/product-list.jsp").forward(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		doGet(req, resp);
	}
}