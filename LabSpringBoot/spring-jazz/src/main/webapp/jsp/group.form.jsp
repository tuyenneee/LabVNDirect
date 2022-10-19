<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page language="java" pageEncoding="UTF-8" %>
<tiles:insertDefinition name="template">
    <tiles:putAttribute name="body">
        <h1>Add Group</h1>
        <form:form method="post" action="/group/save">
            <form:input type = "hidden" path="id" name = "id"/>
            <p>
                <input type="text" path="name" name="name">
            </p>
            <p class="submit">
                <input type="submit" name="add" value="Add">
            </p>
        </form:form>
        <a href="listGroup">List groups</a>
    </tiles:putAttribute>
</tiles:insertDefinition>