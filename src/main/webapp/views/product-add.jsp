<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="jakarta.tags.core"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Thêm sản phẩm</title>
</head>
<body>

	<h2>Thêm sản phẩm</h2>

	<c:if test="${not empty error}">
		<p style="color: red;">${error}</p>
	</c:if>

	<form action="${pageContext.request.contextPath}/product/add"
		method="post">

		<div>
			<label>Tên sản phẩm:</label> <input type="text" name="name" required>
		</div>

		<br>

		<div>
			<label>Mô tả:</label>
			<textarea name="description"></textarea>
		</div>

		<br>

		<div>
			<label>Giá:</label> <input type="number" name="price" step="0.01"
				min="0" required>
		</div>

		<br>

		<div>
			<label>Ảnh:</label> <input type="text" name="image">
		</div>

		<br>

		<div>
			<label>Số lượng:</label> <input type="number" name="quantity" min="0"
				required>
		</div>

		<br>

		<div>
			<label>Danh mục:</label> <select name="categoryId" required>

				<option value="">-- Chọn danh mục --</option>

				<c:forEach var="category" items="${categories}">
					<option value="${category.cateId}">${category.cateName}</option>
				</c:forEach>

			</select>
		</div>

		<br>

		<button type="submit">Thêm sản phẩm</button>

	</form>

	<br>

	<a href="${pageContext.request.contextPath}/product"> Quay lại danh
		sách </a>

</body>
</html>