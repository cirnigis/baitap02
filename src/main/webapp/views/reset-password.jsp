<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Đặt lại mật khẩu</title>
</head>
<body>

    <h2>Đặt lại mật khẩu</h2>

    <c:if test="${alert != null}">
        <h3>${alert}</h3>
    </c:if>

    <form action="${pageContext.request.contextPath}/reset-password"
          method="post">

        <section>
            <input type="password"
                   name="password"
                   placeholder="Mật khẩu mới">
        </section>

        <section>
            <input type="password"
                   name="confirmPassword"
                   placeholder="Nhập lại mật khẩu mới">
        </section>

        <button type="submit">Đổi mật khẩu</button>

    </form>

</body>
</html>