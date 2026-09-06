<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="jakarta.tags.core"%>

<!DOCTYPE html>
<html>

<head>
<meta charset="UTF-8">

<title>Danh sách sản phẩm</title>
</head>

<body>

	<h2>Danh sách sản phẩm</h2>

	<br>

	<a href="${pageContext.request.contextPath}/product/add"> Thêm sản
		phẩm </a>

	<br>
	<br>

	<table border="1" cellpadding="8" cellspacing="0">

		<tr>
			<th>ID</th>
			<th>Tên sản phẩm</th>
			<th>Mô tả</th>
			<th>Giá</th>
			<th>Số lượng</th>
			<th>Danh mục</th>
			<th>Ngày tạo</th>
			<th>Thao tác</th>
		</tr>

		<c:forEach var="product" items="${products}">

			<tr>

				<td>${product.id}</td>

				<td>
    				<a href="${pageContext.request.contextPath}/product/detail?id=${product.id}"> ${product.name} </a>
				</td>

				<td>${product.description}</td>

				<td>${product.price}</td>

				<td>${product.quantity}</td>

				<td>${product.category.cateName}</td>

				<td>${product.createdDate}</td>

				<td><a
					href="${pageContext.request.contextPath}/product/edit?id=${product.id}">
						Sửa </a> | <a
					href="${pageContext.request.contextPath}/product/delete?id=${product.id}"
					onclick="return confirm('Bạn có chắc muốn xóa sản phẩm này không?');">
						Xóa </a></td>

			</tr>

		</c:forEach>

	</table>
	
	<br>

<div>
    <c:if test="${currentPage > 1}">
        <a href="${pageContext.request.contextPath}/product?page=${currentPage - 1}">
            Trang trước
        </a>
    </c:if>

    <c:forEach var="i" begin="1" end="${totalPages}">
        <c:choose>
            <c:when test="${i == currentPage}">
                <strong>[${i}]</strong>
            </c:when>
            <c:otherwise>
                <a href="${pageContext.request.contextPath}/product?page=${i}">
                    ${i}
                </a>
            </c:otherwise>
        </c:choose>
    </c:forEach>

    <c:if test="${currentPage < totalPages}">
        <a href="${pageContext.request.contextPath}/product?page=${currentPage + 1}">
            Trang sau
        </a>
    </c:if>
</div>

</body>

</html>