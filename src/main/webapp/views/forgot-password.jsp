<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="jakarta.tags.core"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Quên mật khẩu</title>
</head>
<body>

	<h2>Quên mật khẩu</h2>

	<c:if test="${alert != null}">
		<h3>${alert}</h3>
	</c:if>

	<form action="${pageContext.request.contextPath}/forgot-password"
		method="post">

		<input type="email" name="email" placeholder="Nhập email">

		<button type="submit">Gửi mã OTP</button>

	</form>

	<br>

	<a href="${pageContext.request.contextPath}/login"> Quay lại đăng nhập </a>

</body>
</html>