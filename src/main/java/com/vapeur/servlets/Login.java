package com.vapeur.servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.vapeur.beans.User;
import com.vapeur.config.Database;
import com.vapeur.dao.DAOException;
import com.vapeur.dao.UserDAO;

import static com.vapeur.config.Debug.*;

@WebServlet("/login")
public class Login extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public Login() {
		super();
		// TODO Auto-generated constructor stub
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession(false);
		request.setAttribute("url", backOfficeUrl());

		if (session == null) {
			session = request.getSession();
		}else {
			if (session.getAttribute("user") == null) {
				prln("login : pas d'user loggué");
				

			} else {
				prln("login : user loggué");

				response.sendRedirect("library");
			}
		}
		request.setAttribute("pageTitle", "Connexion");
		request.getRequestDispatcher("WEB-INF/app/login.jsp").forward(request, response);
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		request.setAttribute("pageTitle", "Connexion");

		// Vérification que les champs sont pas null AJOUTER VERIFICATION VALIDATION
		if (request.getParameter("email") != null && request.getParameter("password") != null) {
			try {
				Database.connect();
				UserDAO userdao = new UserDAO();
				User authorizedUser;
				try {
					authorizedUser = userdao.login(request.getParameter("email"), request.getParameter("password"));
					if (authorizedUser != null) {
						// Si tout est OK création de la session
						HttpSession session = request.getSession();
						session.setAttribute("user", authorizedUser);
						response.sendRedirect("profile");
					} else {
						doGet(request, response);
					}
				} catch (DAOException e) {
					request.setAttribute("errorMsg", e.getMessage());
					e.printStackTrace();

					request.getRequestDispatcher("WEB-INF/app/login.jsp").forward(request, response);
				}
			}catch (Exception e) {
				request.setAttribute("errorMsg", "La base de donnée est indisponible. Merci de revenir plus tard." );
				request.getRequestDispatcher("WEB-INF/app/login.jsp").forward(request, response);
			}
			

		} else {
			request.getRequestDispatcher("WEB-INF/app/login.jsp").forward(request, response);
		}
	}
}
