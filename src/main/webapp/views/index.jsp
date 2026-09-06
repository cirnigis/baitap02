<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="jakarta.tags.core"%>

<!DOCTYPE html>
<html>

<head>
<meta charset="UTF-8">
<title>Trang chủ</title>
</head>

<body>

	<%@ include file="topbar.jsp"%>

	<h1>Trang chủ</h1>

	<p>Chào mừng bạn đến với hệ thống.</p>

	<p>
		<a href="${pageContext.request.contextPath}/product"> Xem tất cả
			sản phẩm </a>
	</p>

	<h2>Sản phẩm mới nhất</h2>

	<table border="1" cellpadding="8" cellspacing="0">

		<tr>
			<th>ID</th>
			<th>Tên sản phẩm</th>
			<th>Giá</th>
			<th>Số lượng</th>
			<th>Danh mục</th>
			<th>Ngày tạo</th>
		</tr>

		<c:forEach var="product" items="${products}">

			<tr>

				<td>${product.id}</td>

				<td><a
					href="${pageContext.request.contextPath}/product/detail?id=${product.id}">
						${product.name} </a></td>

				<td>${product.price}</td>

				<td>${product.quantity}</td>

				<td>${product.category.cateName}</td>

				<td>${product.createdDate}</td>

			</tr>

		</c:forEach>

	</table>

</body>

</html>