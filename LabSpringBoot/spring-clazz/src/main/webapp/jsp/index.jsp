<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>

<html>
<body>
	<tiles:insertDefinition name="template">
		<tiles:putAttribute name="body">
			<h2>${message}</h2>
		</tiles:putAttribute>
	</tiles:insertDefinition>

</body>
</html>
