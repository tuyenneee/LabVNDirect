<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>

<html>
    <head>
        <title>List students</title>
        <script>
            function view(id) {
                var xmlHttp = new XMLHttpRequest();
                xmlHttp.open("GET", "json/" + id, true)
                xmlHttp.onload = function () {
                    if (this.status != 200)
                        return;
                    var student = JSON.parse(this.responseText);
                    document.getElementById('content').innerHTML = 'Name: ' + student.name
                                        + '<img style="width: 200px; height: 200px;" src="/student/avatar/'+student.id+'"/>';
                    var dialog = document.getElementById('viewStudent');
                    dialog.show();
                    console.log(this.responseText);
                };
                xmlHttp.send();
            }
        </script>
    </head>
    <body>
        <tiles:insertDefinition name="studentTemplate">
            <tiles:putAttribute name="body">
                <dialog id="viewStudent" style="width: 50%; border: 1px dotted black;">
                    <div id="content"></div>
                    <button id="hide">Close</button>
                </dialog>
                <form method = "GET" action ="list">
                    <table border = "1">
                        <tr>
                            <td colspan = "4"><input type = "text" name = "q" size = "30"></td>
                            <td><input type = "submit" value = "Submit"></td>
                        </tr>
                        <tr>
                            <td>Id</td>
                            <td>Name</td>
                            <td>Age</td>
                            <td>Delete</td>
                            <td>Edit</td>
                            <td></td>
                        </tr>
                        <c:forEach items="${students}" var="s">
                            <tr>
                                <td>${s.id}</td>
                                <td>${s.name}</td>
                                <td>${s.age}</td>
                                <td><a href = "delete/${s.id}">Delete</a></td>
                                <td><a href = "edit/${s.id}">Edit</a></td>
                                <td><a href = "javascript:view(${s.id})">${s.name}</a></td>
                            </tr>
                        </c:forEach>
                    </table>
                    <a href="add">Add</a>
                </form>
            </tiles:putAttribute>
        </tiles:insertDefinition>
        <script>
            (function () {
                var dialog = document.getElementById('viewStudent');
                document.getElementById('hide').onclick = function () {
                    dialog.close();
                };
            })();
        </script>
    </body>
</html>
