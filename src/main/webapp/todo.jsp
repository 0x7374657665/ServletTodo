<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@ page import="com.manifestcorp.models.Todo" %>
<%@ page import="java.util.List" %>


<!DOCTYPE html>
<html>
<head>
    <title>Things To Do</title>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/main.css"/>
</head>
<body>
<div id="content">
    <h1>TODO:</h1>
    <fieldset>
        <legend>You have <em>${todos.size()}</em> things to do.</legend>
        <ul>
            <c:forEach items="${todos}" var="todo">
                <li>
                    <input type="checkbox" ${todo.done ? 'checked' : ''}>
                    <span class="${todo.done ? "done" : ""}">${todo.text}</span>
                </li>
            </c:forEach>
            <hr>
            <li>
                <input type="text" placeholder="next thing todo...">
            </li>
        </ul>
    </fieldset>
</div>
</body>
</html>