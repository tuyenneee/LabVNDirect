<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ page language="java" pageEncoding="UTF-8"%>
<tiles:insertDefinition name="template">
	<tiles:putAttribute name="body">
		<h1>Add User</h1>
		<form:form method="post" action="/user/save">
			<p>Tài khoản: <form:input type="text" path="username" name="username"/></p>
			<p>Mật khẩu: <form:input type="text" path="password" name="password"/></p>
			<p>Email: <form:input type="text" path="email" name="email"/></p>
			<p>Tuổi: <form:input type="text" path="age" name="age"/></p>
			<p>Nhóm: <form:select path="groupId" id="group" name="group">
				<form:options items="${groups}"/>
			</form:select></p>

			<p class="submit">
				<input type="submit" name="add" value="Add">
			</p>
		</form:form>
	</tiles:putAttribute>
</tiles:insertDefinition>