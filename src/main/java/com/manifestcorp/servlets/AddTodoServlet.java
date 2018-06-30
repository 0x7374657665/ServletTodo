package com.manifestcorp.servlets;

import com.manifestcorp.models.Todo;
import com.manifestcorp.repositories.TodoRepository;

import javax.annotation.Resource;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;
import java.io.IOException;
import java.util.*;

@WebServlet("/add")
public class AddTodoServlet extends HttpServlet {

    @Resource(name = "jdbc/todoDS")
    DataSource ds;

    @Override
    public void doPost(HttpServletRequest req, HttpServletResponse res) throws IOException {
        List<Todo> todos = new ArrayList<>();

        //sift through request parameters to dig out form data and populate todos list
        Enumeration<String> parameterNames = req.getParameterNames();
        while(parameterNames.hasMoreElements()) {
            String key = parameterNames.nextElement();
            if(key.startsWith("done-")) {
                int id = Integer.parseInt(key.substring(5));
                boolean done = Boolean.parseBoolean(req.getParameter(key));
                String text = req.getParameter("text-" + id);
                todos.add(new Todo(id, text, done));
            }
        }

        TodoRepository todoRepo = new TodoRepository(ds);
        todoRepo.mergeTodos(todos);
        todoRepo.addTodo(req.getParameter("newTodo"));

        res.sendRedirect(req.getContextPath());
    }
}
