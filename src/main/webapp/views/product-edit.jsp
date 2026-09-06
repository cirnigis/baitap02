<%@ page language="java"
    contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="jakarta.tags.core" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Sửa sản phẩm</title>
</head>

<body>

<h2>Sửa sản phẩm</h2>

<c:if test="${not empty error}">
    <p style="color: red;">${error}</p>
</c:if>

<form action="${pageContext.request.contextPath}/product/edit"
      method="post">

    <!-- ID sản phẩm -->
    <input type="hidden"
           name="id"
           value="${product.id}">

    <!-- Tên sản phẩm -->
    <div>
        <label>Tên sản phẩm:</label>
        <input type="text"
               name="name"
               value="${product.name}"
               required>
    </div>

    <br>

    <!-- Mô tả -->
    <div>
        <label>Mô tả:</label>
        <textarea name="description"
                  rows="5"
                  cols="40">${product.description}</textarea>
    </div>

    <br>

    <!-- Giá -->
    <div>
        <label>Giá:</label>
        <input type="number"
               name="price"
               value="${product.price}"
               step="0.01"
               min="0"
               required>
    </div>

    <br>

    <!-- Ảnh -->
    <div>
        <label>Ảnh:</label>
        <input type="text"
               name="image"
               value="${product.image}">
    </div>

    <br>

    <!-- Số lượng -->
    <div>
        <label>Số lượng:</label>
        <input type="number"
               name="quantity"
               value="${product.quantity}"
               min="0"
               required>
    </div>

    <br>

    <!-- Danh mục -->
    <div>
        <label>Danh mục:</label>

        <select name="categoryId" required>

            <option value="">-- Chọn danh mục --</option>

            <c:forEach var="category" items="${categories}">

                <option value="${category.cateId}"
                    <c:if test="${category.cateId == product.category.cateId}">
                        selected
                    </c:if>
                >
                    ${category.cateName}
                </option>

            </c:forEach>

        </select>
    </div>

    <br>

    <!-- Nút cập nhật -->
    <button type="submit">
        Cập nhật sản phẩm
    </button>

</form>

<br>

<a href="${pageContext.request.contextPath}/product">
    Quay lại danh sách
</a>

</body>
</html>