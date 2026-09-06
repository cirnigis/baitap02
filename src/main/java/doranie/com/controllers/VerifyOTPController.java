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

@WebServlet("/verify-otp")
public class VerifyOTPController extends HttpServlet {

    private static final long serialVersionUID = 1L;

    UserService service = new UserServiceImpl();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        req.getRequestDispatcher("/views/verify-otp.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        req.setCharacterEncoding("UTF-8");
        resp.setCharacterEncoding("UTF-8");

        String otpNhap = req.getParameter("otp");

        HttpSession session = req.getSession();

        String otpDung = (String) session.getAttribute("registerOTP");

        if (otpNhap == null || otpNhap.isEmpty()) {
            req.setAttribute("alert", "Vui lòng nhập mã OTP");
            req.getRequestDispatcher("/views/verify-otp.jsp").forward(req, resp);
            return;
        }

        if (otpDung == null) {
            req.setAttribute("alert", "OTP không tồn tại hoặc đã hết phiên");
            req.getRequestDispatcher("/views/verify-otp.jsp").forward(req, resp);
            return;
        }

        if (!otpDung.equals(otpNhap)) {
            req.setAttribute("alert", "Mã OTP không chính xác");
            req.getRequestDispatcher("/views/verify-otp.jsp").forward(req, resp);
            return;
        }

        // OTP đúng → lấy thông tin đăng ký từ Session
        String username = (String) session.getAttribute("registerUsername");
        String password = (String) session.getAttribute("registerPassword");
        String email = (String) session.getAttribute("registerEmail");
        String fullname = (String) session.getAttribute("registerFullname");
        String phone = (String) session.getAttribute("registerPhone");

        // Tạo tài khoản
        boolean result = service.register(
                email,
                password,
                username,
                fullname,
                phone
        );

        if (result) {

            // Xóa dữ liệu đăng ký tạm thời khỏi Session
            session.removeAttribute("registerUsername");
            session.removeAttribute("registerPassword");
            session.removeAttribute("registerEmail");
            session.removeAttribute("registerFullname");
            session.removeAttribute("registerPhone");
            session.removeAttribute("registerOTP");

            // Đăng ký thành công → chuyển sang Login
            resp.sendRedirect(req.getContextPath() + "/login");

        } else {

            req.setAttribute("alert", "Đăng ký thất bại");
            req.getRequestDispatcher("/views/verify-otp.jsp").forward(req, resp);
        }
    }
}