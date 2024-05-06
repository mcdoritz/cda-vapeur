package com.vapeur.servlets;

import static com.vapeur.config.Debug.deploy;
import static com.vapeur.config.Debug.prln;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.vapeur.beans.BeanException;
import com.vapeur.beans.User;
import com.vapeur.config.Database;
import com.vapeur.dao.DAOException;
import com.vapeur.dao.UserDAO;

@WebServlet("/signup")
public class Signup extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public Signup() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		HttpSession session = request.getSession(false);
		if(deploy) {
			request.setAttribute("deploy", deploy);
		}
		if (session.getAttribute("user") == null) {
			prln("signup : pas d'user loggué");
			request.setAttribute("pageTitle", "Inscription");
			request.getRequestDispatcher("WEB-INF/app/signup.jsp").forward(request, response);

		} else {
			prln("signup : user loggué");

			response.sendRedirect("library");
		}

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		prln("servlet doPost signup");
		try {
			// Vérification que les champs sont pas null AJOUTER VERIFICATION VALIDATION
			if (request.getParameter("nickname") != null && request.getParameter("email") != null
					&& request.getParameter("password") != null && request.getParameter("password-confirm") != null) {
				prln("pseudo " + request.getParameter("nickname") + " email " + request.getParameter("email") + " password : " + request.getParameter("password")
				+ " password 2 : " + request.getParameter("password-confirm"));

				if(!request.getParameter("password").equals(request.getParameter("password-confirm"))) {
					throw new ServletException("Les mots de passe entrés ne correspondent pas.");
				}else {
					
					try {

						Database.connect();
						UserDAO userdao = new UserDAO();
						User newUser = new User(request.getParameter("email"),request.getParameter("nickname"), request.getParameter("password"));
						if(userdao.save(newUser, false)) {
							request.setAttribute("signupDone", true);
						};

					} catch (DAOException e) {
						prln(e.getMessage());
						request.setAttribute("errorMsg", e.getMessage());
						request.getRequestDispatcher("WEB-INF/app/login.jsp").forward(request, response);
						
					} catch (BeanException e) {
						request.setAttribute("errorMsg", e.getMessage());
						e.printStackTrace();
						request.getRequestDispatcher("WEB-INF/app/login.jsp").forward(request, response);
					} catch (Exception e) {
						request.setAttribute("errorMsg", "La base de donnée est indisponible. Merci de revenir plus tard." );
						request.getRequestDispatcher("WEB-INF/app/login.jsp").forward(request, response);
					}
					
					request.getRequestDispatcher("WEB-INF/app/signup.jsp").forward(request, response);
					
				}

				

			}
		}catch (ServletException e){
			request.setAttribute("errorMsg", e.getMessage());
			request.getRequestDispatcher("WEB-INF/app/signup.jsp").forward(request, response);
		}
		

	}

}
