<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<html>
<head>
    <title>List groups</title>
</head>
<body>
<tiles:insertDefinition name="template">
    <tiles:putAttribute name="body">
        <h1>Group list</h1>
        <form method="get" action="/group/listGroup">
            <table border="1">
                <tr>
                    <td colspan="2">
                        <input type="text" name="q">
                    </td>
                    <td>
                        <input type="submit" value="Submit">
                    </td>
                </tr>
                <tr>
                    <td>Id</td>
                    <td>Name</td>
                    <td>#</td>
                    <td></td>
                    <td></td>
                </tr>
                <c:forEach items="${groups}" var="g" varStatus="loop">
                    <tr>
                        <td>${g.id}</td>
                        <td>${g.name}</td>
                        <td><a href="delete/${g.id}">Delete</a></td>
                        <td><a href="update?id=${g.id}">Update</a></td>
                        <td><a href="/user/listUser/${g.id}">${g.name}</a>
                            <ul>
                                <c:forEach items="${g.users}" var="user" varStatus="loop">
                                    <li>${user.username} - ${user.age}</li>
                                </c:forEach>
                            </ul>
                        </td>
                    </tr>
                </c:forEach>
            </table>
        </form>
        <a href="/group/add">Add</a>
        <a href="/user/listUser">List user</a>
    </tiles:putAttribute>
</tiles:insertDefinition>
</body>
</html>
