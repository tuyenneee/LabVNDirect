<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>
<%@ page language="java" pageEncoding="UTF-8" %>
<%@taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<!DOCTYPE html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Spring Jazz Application</title>
</head>
<body>
<h1>Welcome to Spring Jazz </h1>
<p><a href="/">Trang Chủ</a>
    <sec:authorize access="!hasAnyRole('ROLE_USER')">
        <a href="/dang-nhap" style="margin-left: 30px">Đăng nhập</a>
    </sec:authorize>
    <sec:authorize access="hasAnyRole('ROLE_USER')">
        <a href="/nguoi-dung" style="margin-left: 30px">Người dùng</a>
        <a href="javascript:document.getElementById('logout').submit()" style="margin-left: 30px">Đăng xuất</a>
    </sec:authorize>
</p>
<form action="/logout" id="logout" method="post">
    <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}">
</form>
<tiles:insertAttribute name="body"/>
</body>
</html>