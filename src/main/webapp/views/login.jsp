<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="jakarta.tags.core"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

	<jsp:include page="topbar.jsp" />
	<form action="login" method="post">

		<h2>Đăng nhập</h2>

		<c:if test="${alert != null}">
			<h3 class="alert alert-danger">${alert}</h3>
		</c:if>

		<section>
			<label class="input login-input">
				<div class="input-group">
					<span class="input-group-addon"> <i class="fa fa-user"></i>
					</span> <input type="text" placeholder="Tài khoản" name="username"
						class="form-control">
				</div>
			</label>
		</section>

		<section>
			<label class="input login-input">
				<div class="input-group">
					<span class="input-group-addon"> <i class="fa fa-lock"></i>
					</span> <input type="password" placeholder="Mật khẩu" name="password"
						class="form-control">
				</div>
			</label>
		</section>

		<section>
			<label> <input type="checkbox" name="remember"> Ghi
				nhớ đăng nhập
			</label>
		</section>

		<section>
			<button type="submit">Đăng nhập</button>
			<br> <a
				href="${pageContext.request.contextPath}/forgot-password"> Quên mật khẩu? </a>
		</section>

	</form>

</body>
</html>