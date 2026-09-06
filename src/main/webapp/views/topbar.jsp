<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="jakarta.tags.core"%>

<c:choose>

    <c:when test="${sessionScope.account == null}">

        <div>
            <ul>
                <li>
                    <a href="${pageContext.request.contextPath}/login">Đăng nhập</a>
                    |<a href="${pageContext.request.contextPath}/register">Đăng ký</a>
                </li>
            </ul>
        </div>

    </c:when>

    <c:otherwise>

        <div>
            <ul>
                <li>
                    <a href="${pageContext.request.contextPath}/member/myaccount">${sessionScope.account.fullName}</a>
                    | <a href="${pageContext.request.contextPath}/logout">Đăng Xuất</a>
                </li>
            </ul>
        </div>

    </c:otherwise>

</c:choose>

