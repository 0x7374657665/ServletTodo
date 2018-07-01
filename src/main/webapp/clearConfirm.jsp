<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Confirm Clear Todos</title>
</head>
<body>
<h1>Confirm:</h1>
<form action="clear" method="post" id="clearForm">
    <p>You are about to delete the items listed below. Continue?</p>
    <button type="submit">DELETE</button>&nbsp;<button type="button" onclick="window.location = '${pageContext.request.contextPath}'">CANCEL</button>
</form>
<hr>
<ul>
    <c:forEach items="${doneTodos}" var="todo">
        <li>${todo.text}</li>
    </c:forEach>
</ul>
</body>
</html>
