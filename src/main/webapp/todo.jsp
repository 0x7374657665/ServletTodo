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
    <form name="todoForm" action="add" method="post">
        <ul>
            <c:forEach items="${todos}" var="todo">
                <li>
                    <input type="checkbox" ${todo.done ? 'checked' : ''}>
                    <input name="done-${todo.id}" type="hidden" value="${todo.done}">
                    <span class="${todo.done ? "done" : ""}">${todo.text}</span>
                    <input type="hidden" name="text-${todo.id}" value="${todo.text}">
                </li>
            </c:forEach>
            <hr>
            <li>
                <input type="text" placeholder="next thing to do..." name="newTodo">
            </li>
        </ul>
    </form>
    <p id="controls">[ <a href="javascript:void(0)">clear completed</a> | <a href="javascript:void(0)">save updates</a>
        ]</p>
</div>
<script type="text/javascript">
    let todoForm = document.getElementById('content')
    todoForm.addEventListener('change', event => {
        const checkbox = event.target
        const hiddenCheckboxInput = checkbox.nextElementSibling
        const span = hiddenCheckboxInput.nextElementSibling

        checkbox.nextElementSibling.value = checkbox.checked
        span.className = checkbox.checked ? 'done' : ''
    })

    document.getElementsByName("newTodo")[0].focus()
</script>
</body>
</html>