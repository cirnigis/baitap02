<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="jakarta.tags.core"%>

<!DOCTYPE html>
<html>

<head>
<meta charset="UTF-8">
<title>Danh sách danh mục</title>
</head>

<body>

	<h2>Danh sách danh mục</h2>

	<table border="1">
		<thead>
			<tr>
				<th>STT</th>
				<th>Ảnh</th>
				<th>Tên danh mục</th>
				<th>Thao tác</th>
			</tr>
		</thead>

		<tbody>

			<c:forEach items="${cateList}" var="cate" varStatus="STT">

				<tr class="odd gradeX">

					<td>${STT.index + 1}</td>

					<c:url value="/image?fname=${cate.icons}" var="imgUrl"></c:url>

					<td><img height="150" width="200" src="${imgUrl}"
						alt="${cate.cateName}"></td>

					<td>${cate.cateName}</td>

					<td><a
						href="<c:url value='/admin/category/edit?id=${cate.cateId}'/>"
						class="center"> Sửa </a> | <a
						href="<c:url value='/admin/category/delete?id=${cate.cateId}'/>"
						class="center"> Xóa </a></td>

				</tr>

			</c:forEach>

		</tbody>
	</table>

</body>

</html>