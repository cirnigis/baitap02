package doranie.com.controllers;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/verify-forgot-password")
public class VerifyForgotPasswordController extends HttpServlet {

    private static final long serialVersionUID = 1L;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        req.getRequestDispatcher("/views/verify-forgot-password.jsp")
           .forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        req.setCharacterEncoding("UTF-8");
        resp.setCharacterEncoding("UTF-8");

        String otpNhap = req.getParameter("otp");

        HttpSession session = req.getSession();

        String otpDung = (String) session.getAttribute("forgotPasswordOTP");

        if (otpNhap == null || otpNhap.trim().isEmpty()) {

            req.setAttribute("alert", "Vui lòng nhập mã OTP");

            req.getRequestDispatcher("/views/verify-forgot-password.jsp")
               .forward(req, resp);

            return;
        }

        if (otpDung == null) {

            req.setAttribute("alert", "OTP không tồn tại hoặc đã hết phiên");

            req.getRequestDispatcher("/views/verify-forgot-password.jsp")
               .forward(req, resp);

            return;
        }

        if (!otpDung.equals(otpNhap)) {

            req.setAttribute("alert", "Mã OTP không chính xác");

            req.getRequestDispatcher("/views/verify-forgot-password.jsp")
               .forward(req, resp);

            return;
        }

        // OTP đúng
        session.setAttribute("forgotPasswordVerified", true);

        resp.sendRedirect(
                req.getContextPath() + "/reset-password");
    }
}