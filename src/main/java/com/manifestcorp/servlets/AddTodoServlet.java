package com.manifestcorp.servlets;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Arrays;
import java.util.Enumeration;
import java.util.Map;

@WebServlet("/add")
public class AddTodoServlet extends HttpServlet {

    @Override
    public void doPost(HttpServletRequest req, HttpServletResponse res) throws IOException {
        Enumeration<String> parameterNames = req.getParameterNames();
        while(parameterNames.hasMoreElements()) {
            String key = parameterNames.nextElement();
            if(key.startsWith("done-")) {
                int id = Integer.parseInt(key.substring(5));
                boolean done = Boolean.parseBoolean(req.getParameter(key));
                String text = req.getParameter("text-" + id);
                System.out.println("[" + (done ? "X" : " ") + "] " + text);
            }
        }

        res.sendRedirect(req.getContextPath());
    }
}
