package com.vapeur.servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/")
public class Landing extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public Landing() {
        super();
        // TODO Auto-generated constructor stub
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		//Vérif 404 -------------
		String requestURI = request.getRequestURI();
	    if (!requestURI.equals("/")) {
	        response.sendRedirect("404");
	        return;
	    }
	    // ----------------------
		request.getRequestDispatcher("WEB-INF/app/landing.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
