package com.manifestcorp.servlets;

import com.manifestcorp.models.Todo;
import com.manifestcorp.repositories.TodoRepository;

import javax.annotation.Resource;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;
import java.io.IOException;
import java.util.List;

@WebServlet("/clear")
public class ClearTodosServlet extends HttpServlet {

    @Resource(name="jdbc/todoDS")
    private DataSource ds;

    @Override
    public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {


        TodoRepository todoRepo = new TodoRepository(ds);
        List<Todo> doneTodos = todoRepo.getDoneTodos();

        req.setAttribute("doneTodos",doneTodos);

        RequestDispatcher requestDispatcher = getServletContext().getRequestDispatcher("/clearConfirm.jsp");
        requestDispatcher.forward(req, res);
    }

    @Override
    public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

        TodoRepository todoRepo = new TodoRepository(ds);
        todoRepo.clearDone();

        res.sendRedirect(req.getContextPath());
    }
}
