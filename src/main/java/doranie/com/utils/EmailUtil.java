package doranie.com.utils;

import java.util.Properties;

import jakarta.mail.Authenticator;
import jakarta.mail.Message;
import jakarta.mail.PasswordAuthentication;
import jakarta.mail.Session;
import jakarta.mail.Transport;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeMessage;

public class EmailUtil {

    // Email Gmail dùng để gửi OTP
    private static final String FROM_EMAIL = "cirn.igis@gmail.com";

    // App Password của Gmail
    private static final String APP_PASSWORD = "pggx vtog wkjw zxhz";

    public static void sendOTP(String toEmail, String otp) {

        Properties props = new Properties();

        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "587");

        Session session = Session.getInstance(props, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(
                        FROM_EMAIL,
                        APP_PASSWORD
                );
            }
        });

        try {
            Message message = new MimeMessage(session);

            message.setFrom(new InternetAddress(FROM_EMAIL));
            message.setRecipients(
                    Message.RecipientType.TO,
                    InternetAddress.parse(toEmail)
            );

            message.setSubject("Mã OTP xác nhận đăng ký");

            message.setText(
                    "Mã OTP của bạn là: " + otp
                    + "\n\nMã OTP có hiệu lực trong thời gian ngắn."
            );

            Transport.send(message);

            System.out.println("Đã gửi OTP tới: " + toEmail);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}