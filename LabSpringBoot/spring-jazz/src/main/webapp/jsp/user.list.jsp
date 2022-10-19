<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<html>
<head>
    <title>List users</title>
</head>
<body>
<tiles:insertDefinition name="template">
    <tiles:putAttribute name="body">
        <h1>User list</h1>
        <table border="1">
            <tr>
                <td>Name</td>
                <td>Age</td>
                <td>#</td>
            </tr>
            <c:forEach items="${users}" var="u" varStatus="loop">
                <tr>
                    <td><a href="/user/detail/${u.username}">${u.username}</a></td>
                    <td>${u.age}</td>
                    <td><a href="/user/delete/${u.username}">Delete</a></td>
                </tr>
            </c:forEach>
        </table>
        <p>Average of age is: ${average}</p>
        <a href="/group/listGroup">List group</a>
    </tiles:putAttribute>
</tiles:insertDefinition>

</body>
</html>
