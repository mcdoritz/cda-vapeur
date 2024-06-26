package com.vapeur.servlets;

import static com.vapeur.config.Debug.prln;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.vapeur.config.Database;
import com.vapeur.dao.UserDAO;

@WebServlet("/test")
public class Test extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public Test() {
		super();
		// TODO Auto-generated constructor stub
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		// ----------------------
		Database.connect();

		request.getRequestDispatcher("WEB-INF/app/test.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		prln("doPost Test : ");

		doGet(request, response);
	}

}
