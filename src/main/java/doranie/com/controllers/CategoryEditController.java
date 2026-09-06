package doranie.com.controllers;

import java.io.IOException;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.File;
import java.util.List;
import java.nio.charset.StandardCharsets;
import java.nio.file.Path;

import org.apache.commons.fileupload2.core.DiskFileItem;
import org.apache.commons.fileupload2.core.DiskFileItemFactory;
import org.apache.commons.fileupload2.jakarta.servlet6.JakartaServletFileUpload;

import doranie.com.utils.Constant;
import doranie.com.models.Category;
import doranie.com.service.CategoryService;
import doranie.com.service.impl.CategoryServiceImpl;

@WebServlet(urlPatterns = { "/admin/category/edit" })
public class CategoryEditController extends HttpServlet {

	private static final long serialVersionUID = 1L;

	CategoryService cateService = new CategoryServiceImpl();

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		String id = req.getParameter("id");

		Category category = cateService.get(Integer.parseInt(id));

		req.setAttribute("category", category);

		RequestDispatcher dispatcher = req.getRequestDispatcher("/views/admin/edit-category.jsp");

		dispatcher.forward(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		Category category = new Category();

		DiskFileItemFactory factory = DiskFileItemFactory.builder().get();

		JakartaServletFileUpload<DiskFileItem, DiskFileItemFactory> upload = new JakartaServletFileUpload<>(factory);

		upload.setHeaderCharset(StandardCharsets.UTF_8);

		try {
			List<DiskFileItem> items = upload.parseRequest(req);

			for (DiskFileItem item : items) {

				if (item.isFormField()) {

					if (item.getFieldName().equals("id")) {
						category.setCateId(Integer.parseInt(item.getString(StandardCharsets.UTF_8)));

					} else if (item.getFieldName().equals("name")) {
						category.setCateName(item.getString(StandardCharsets.UTF_8));
					}

				} else if (item.getFieldName().equals("icon")) {

					if (item.getSize() > 0) {

						String originalFileName = item.getName();

						int index = originalFileName.lastIndexOf(".");
						String ext = originalFileName.substring(index + 1);

						String fileName = System.currentTimeMillis() + "." + ext;

						File file = new File(Constant.DIR + "/category/" + fileName);

						Path path = file.toPath();

						item.write(path);

						category.setIcons("category/" + fileName);

					} else {
						category.setIcons(null);
					}
				}
			}

			cateService.edit(category);

			resp.sendRedirect(req.getContextPath() + "/admin/category/list");

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}