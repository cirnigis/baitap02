package doranie.com.controllers;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import doranie.com.service.UserService;
import doranie.com.service.impl.UserServiceImpl;

@WebServlet("/reset-password")
public class ResetPasswordController extends HttpServlet {

    private static final long serialVersionUID = 1L;

    UserService service = new UserServiceImpl();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        HttpSession session = req.getSession();

        Boolean verified =
                (Boolean) session.getAttribute("forgotPasswordVerified");

        if (verified == null || !verified) {
            resp.sendRedirect(req.getContextPath() + "/forgot-password");
            return;
        }

        req.getRequestDispatcher("/views/reset-password.jsp")
           .forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        req.setCharacterEncoding("UTF-8");
        resp.setCharacterEncoding("UTF-8");

        HttpSession session = req.getSession();

        Boolean verified =
                (Boolean) session.getAttribute("forgotPasswordVerified");

        if (verified == null || !verified) {
            resp.sendRedirect(req.getContextPath() + "/forgot-password");
            return;
        }

        String password = req.getParameter("password");
        String confirmPassword = req.getParameter("confirmPassword");

        // Kiểm tra mật khẩu rỗng
        if (password == null || password.trim().isEmpty()) {
            req.setAttribute("alert", "Vui lòng nhập mật khẩu mới");

            req.getRequestDispatcher("/views/reset-password.jsp")
               .forward(req, resp);
            return;
        }

        // Kiểm tra nhập lại mật khẩu
        if (confirmPassword == null || confirmPassword.trim().isEmpty()) {
            req.setAttribute("alert", "Vui lòng nhập lại mật khẩu");

            req.getRequestDispatcher("/views/reset-password.jsp")
               .forward(req, resp);
            return;
        }

        // Kiểm tra hai mật khẩu giống nhau
        if (!password.equals(confirmPassword)) {
            req.setAttribute("alert", "Mật khẩu nhập lại không khớp");

            req.getRequestDispatcher("/views/reset-password.jsp")
               .forward(req, resp);
            return;
        }

        String email =
                (String) session.getAttribute("forgotPasswordEmail");

        if (email == null || email.trim().isEmpty()) {
            req.setAttribute("alert", "Phiên đặt lại mật khẩu không hợp lệ");

            req.getRequestDispatcher("/views/reset-password.jsp")
               .forward(req, resp);
            return;
        }

        // Cập nhật mật khẩu bằng JPA
        service.updatePassword(email, password);

        // Xóa thông tin phiên quên mật khẩu
        session.removeAttribute("forgotPasswordEmail");
        session.removeAttribute("forgotPasswordOTP");
        session.removeAttribute("forgotPasswordVerified");

        // Quay về trang đăng nhập
        resp.sendRedirect(req.getContextPath() + "/login");
    }
}