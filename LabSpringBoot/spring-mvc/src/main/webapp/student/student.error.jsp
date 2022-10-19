<%@page isErrorPage="true" import="java.io.*"%>
<html>
    <head>
        <title>Student error</title>
    </head>
    <body>
        <%
            Exception exp = (Exception) request.getAttribute("javax.servlet.error.exception");
            exp.printStackTrace(new java.io.PrintWriter(out));
        %>
    </body>
</html>