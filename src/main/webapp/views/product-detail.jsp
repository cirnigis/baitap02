<%@ page language="java"
    contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Chi tiết sản phẩm</title>
</head>
<body>

<h2>Chi tiết sản phẩm</h2>

<table border="1" cellpadding="8" cellspacing="0">

    <tr>
        <th>ID</th>
        <td>${product.id}</td>
    </tr>

    <tr>
        <th>Tên sản phẩm</th>
        <td>${product.name}</td>
    </tr>

    <tr>
        <th>Mô tả</th>
        <td>${product.description}</td>
    </tr>

    <tr>
        <th>Giá</th>
        <td>${product.price}</td>
    </tr>

    <tr>
        <th>Số lượng</th>
        <td>${product.quantity}</td>
    </tr>

    <tr>
        <th>Danh mục</th>
        <td>${product.category.cateName}</td>
    </tr>

    <tr>
        <th>Ảnh</th>
        <td>${product.image}</td>
    </tr>

    <tr>
        <th>Ngày tạo</th>
        <td>${product.createdDate}</td>
    </tr>

</table>

<br>

<a href="${pageContext.request.contextPath}/product">
    Quay lại danh sách
</a>

</body>
</html>