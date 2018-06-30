package com.manifestcorp.servlets;

import java.io.IOException;

import javax.naming.NamingException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

import com.manifestcorp.models.Steve;

@WebServlet("/steve")
public class SteveServlet extends HttpServlet {

	@Override
	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		Steve.printTodos();
		RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/steve.jsp");
		dispatcher.forward(req, res);
	}

}
