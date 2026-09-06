<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="jakarta.tags.core"%>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Xác nhận OTP</title>
</head>
<body>

    <h2>Xác nhận mã OTP</h2>

    <c:if test="${alert != null}">
        <h3>${alert}</h3>
    </c:if>

    <form action="${pageContext.request.contextPath}/verify-forgot-password"
          method="post">

        <input type="text"
               name="otp"
               placeholder="Nhập mã OTP"
               maxlength="6">

        <button type="submit">Xác nhận OTP</button>

    </form>

</body>
</html>