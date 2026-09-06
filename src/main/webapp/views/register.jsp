<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="jakarta.tags.core"%>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Tạo tài khoản</title>
</head>

<body>

    <form action="${pageContext.request.contextPath}/register" method="post">

        <h2>Tạo tài khoản mới</h2>

        <c:if test="${alert != null}">
            <h3 class="alert alert-danger">${alert}</h3>
        </c:if>

		<section>
			<label>
				<div>
					<input type="text" placeholder="Tài khoản" name="username"
						class="form-control">
				</div>
			</label>
		</section>

		<section>
			<label>
				<div>
					<input type="password" placeholder="Mật khẩu" name="password"
						class="form-control">
				</div>
			</label>
		</section>

		<section>
			<label>
				<div>
					<input type="text" placeholder="Họ và tên" name="fullname"
						class="form-control">
				</div>
			</label>
		</section>

		<section>
			<label>
				<div>
					<input type="email" placeholder="Email" name="email"
						class="form-control">
				</div>
			</label>
		</section>

		<section>
			<label>
				<div>
					<input type="text" placeholder="Số điện thoại" name="phone"
						class="form-control">
				</div>
			</label>
		</section>

		<button type="submit">Đăng ký</button>

	</form>

</body>
</html>
