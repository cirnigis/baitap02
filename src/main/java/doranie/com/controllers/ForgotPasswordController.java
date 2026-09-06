package doranie.com.controllers;

import java.io.IOException;
import java.util.Random;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import doranie.com.service.UserService;
import doranie.com.service.impl.UserServiceImpl;
import doranie.com.utils.EmailUtil;

@WebServlet("/forgot-password")
public class ForgotPasswordController extends HttpServlet {

    private static final long serialVersionUID = 1L;

    UserService service = new UserServiceImpl();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        req.getRequestDispatcher("/views/forgot-password.jsp")
           .forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        req.setCharacterEncoding("UTF-8");
        resp.setCharacterEncoding("UTF-8");

        String email = req.getParameter("email");

        if (email == null || email.trim().isEmpty()) {
            req.setAttribute("alert", "Vui lòng nhập email");
            req.getRequestDispatcher("/views/forgot-password.jsp")
               .forward(req, resp);
            return;
        }

        if (!service.checkExistEmail(email)) {
            req.setAttribute("alert", "Email không tồn tại");
            req.getRequestDispatcher("/views/forgot-password.jsp")
               .forward(req, resp);
            return;
        }

        String otp = String.format("%06d",
                new Random().nextInt(1000000));

        HttpSession session = req.getSession();

        session.setAttribute("forgotPasswordEmail", email);
        session.setAttribute("forgotPasswordOTP", otp);

        EmailUtil.sendOTP(email, otp);

        resp.sendRedirect(
                req.getContextPath() + "/verify-forgot-password");
    }
}