package com.vapeur.servlets;

import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.security.crypto.bcrypt.BCrypt;

import com.vapeur.beans.User;
import com.vapeur.config.Database;
import com.vapeur.dao.UserDAO;

@WebServlet("/login")
public class Login extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public Login() {
		super();
		// TODO Auto-generated constructor stub
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");

		// Vérification que les champs sont pas null AJOUTER VERIFICATION VALIDATION
		if (request.getParameter("email") != null && request.getParameter("password") != null) {
			Database.connect();
			UserDAO userdao = new UserDAO();
			User authorizedUser = userdao.login(request.getParameter("email"), request.getParameter("password"));

			if (authorizedUser != null) {
				// Si tout est OK création de la session
				HttpSession session = request.getSession();
				session.setAttribute("user", authorizedUser);
				response.sendRedirect("/");
			} else {
				doGet(request, response);
			}

		}
	}
}
