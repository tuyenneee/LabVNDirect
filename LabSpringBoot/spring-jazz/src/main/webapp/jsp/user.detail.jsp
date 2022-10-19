<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>User detail</title>
</head>
<body>
<tiles:insertDefinition name="template">
    <tiles:putAttribute name="body">
        <h1>User</h1>
        <p>User: ${users.username}</p>
        <p>Password: ${users.password}</p>
        <p>Email: ${users.email}</p>
        <p>Age: ${users.age}</p>
        <p>Group: ${users.group.name}</p>
    </tiles:putAttribute>
</tiles:insertDefinition>
</body>
</html>
