<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

	<c:url value="/admin/category/edit" var="edit"></c:url>

	<form role="form" action="${edit}" method="post"
		enctype="multipart/form-data">

		<input name="id" value="${category.cateId}" hidden="">

		<div class="form-group">
			<label>Tên danh mục:</label> <input type="text" class="form-control"
				value="${category.cateName}" name="name" />
		</div>

		<div class="form-group">

			<c:url value="/image?fname=${category.icons}" var="imgUrl"></c:url>

			<img class="img-responsive" width="100px" src="${imgUrl}" alt="">

			<label>Ảnh đại diện</label> <input type="file" name="icon" />
		</div>

		<button type="submit" class="btn btn-default">Sửa</button>

		<button type="reset" class="btn btn-primary">Hủy</button>

	</form>

</body>
</html>