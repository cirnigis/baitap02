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

@WebServlet(urlPatterns = "/register")
public class RegisterController extends HttpServlet {

    private static final long serialVersionUID = 1L;

    UserService service = new UserServiceImpl();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        req.getRequestDispatcher("/views/register.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        req.setCharacterEncoding("UTF-8");
        resp.setCharacterEncoding("UTF-8");

        String username = req.getParameter("username");
        String password = req.getParameter("password");
        String email = req.getParameter("email");
        String fullname = req.getParameter("fullname");
        String phone = req.getParameter("phone");

        if (username == null || username.isEmpty()
                || password == null || password.isEmpty()
                || email == null || email.isEmpty()
                || fullname == null || fullname.isEmpty()
                || phone == null || phone.isEmpty()) {

            req.setAttribute("alert", "Vui lòng nhập đầy đủ thông tin");
            req.getRequestDispatcher("/views/register.jsp").forward(req, resp);
            return;
        }

        if (service.checkExistUsername(username)) {
            req.setAttribute("alert", "Tài khoản đã tồn tại");
            req.getRequestDispatcher("/views/register.jsp").forward(req, resp);
            return;
        }

        if (service.checkExistEmail(email)) {
            req.setAttribute("alert", "Email đã tồn tại");
            req.getRequestDispatcher("/views/register.jsp").forward(req, resp);
            return;
        }

        if (service.checkExistPhone(phone)) {
            req.setAttribute("alert", "Số điện thoại đã tồn tại");
            req.getRequestDispatcher("/views/register.jsp").forward(req, resp);
            return;
        }

        // Tạo OTP 6 chữ số
        String otp = String.format("%06d",
                new Random().nextInt(1000000));

        // Lưu thông tin đăng ký tạm thời vào Session
        HttpSession session = req.getSession();

        session.setAttribute("registerUsername", username);
        session.setAttribute("registerPassword", password);
        session.setAttribute("registerEmail", email);
        session.setAttribute("registerFullname", fullname);
        session.setAttribute("registerPhone", phone);

        // Lưu OTP vào Session
        session.setAttribute("registerOTP", otp);

        // Gửi OTP tới email
        EmailUtil.sendOTP(email, otp);

        // Chuyển sang trang nhập OTP
        resp.sendRedirect(req.getContextPath() + "/verify-otp");
    }
}