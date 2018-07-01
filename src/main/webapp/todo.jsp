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
<h1>TODO:</h1>
<form id="todoForm" action="add" method="post">
    <ul>
        <c:forEach items="${todos}" var="todo">
            <li>
                <input type="checkbox" ${todo.done ? 'checked' : ''}>
                <input name="done-${todo.id}" type="hidden" value="${todo.done}">
                <span class="${todo.done ? "done" : ""}">${todo.text}</span>
                <input type="hidden" name="text-${todo.id}" value="${todo.text}">
            </li>
        </c:forEach>
        <li>
            <input type="checkbox" style="visibility: hidden">
            <input type="text" placeholder="next thing to do..." name="newTodo">
        </li>
    </ul>
</form>
<hr>
<p id="controls">[ <a href="clear">clear completed</a> | <a href="javascript:todoForm.submit()">save updates</a> ]</p>
<script type="text/javascript">
    const todoForm = document.getElementById('todoForm')
    todoForm.addEventListener('change', event => {
        const checkbox = event.target
        const hiddenCheckboxInput = checkbox.nextElementSibling
        const span = hiddenCheckboxInput.nextElementSibling

        checkbox.nextElementSibling.value = checkbox.checked
        span.className = checkbox.checked ? 'done' : ''
        span.className += ' changed'
    })

    document.getElementsByName("newTodo")[0].focus()
</script>
</body>
</html>