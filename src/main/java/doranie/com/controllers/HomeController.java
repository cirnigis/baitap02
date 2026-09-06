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

@WebServlet(urlPatterns = "/")
public class HomeController extends HttpServlet {

    private static final long serialVersionUID = 1L;

    private final ProductService productService =
            new ProductServiceImpl();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        List<Product> products = productService.getLatest(10);

        req.setAttribute("products", products);

        req.getRequestDispatcher("/views/index.jsp")
           .forward(req, resp);
    }
}