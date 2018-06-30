<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<%@ page import="com.manifestcorp.models.Todo" %>
<%@ page import="java.util.List" %>
<html>
<head>
    <title>Things To Do</title>
</head>
<body>
<h1>TODO:</h1>
<p>You have <em>${todos.size()}</em> things to do.</p>
<ul>
    <c:forEach items="${todos}" var="todo" >
        <li>${todo.text}</li>
    </c:forEach>
</ul>
</body>
</html>