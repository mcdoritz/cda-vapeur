package com.vapeur.servlets;

import java.io.File;
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
import com.vapeur.dao.OrderDAO;
import com.vapeur.dao.OrderDetailDAO;
import com.vapeur.dao.UserDAO;

import static com.vapeur.config.Debug.*;

@WebServlet("/profile")
public class Profile extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public Profile() {
		super();
		// TODO Auto-generated constructor stub
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession(false);
		request.setAttribute("url", backOfficeUrl());
		Boolean redirect = false;
		if (session != null) {
			if (session.getAttribute("user") != null) {
				User user = new User();
				user = (User) session.getAttribute("user");
				if (user.getId() > 0) {
					try {
						Database.connect();
						OrderDAO orderdao = new OrderDAO();
						try {
							request.setAttribute("ordersList", orderdao.readAllByUserId(user.getId()));
						} catch (DAOException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
							request.setAttribute("errorMsg", e.getMessage());
						}
					}catch (Exception e) {
						request.setAttribute("errorMsg", "La base de donnée est indisponible. Merci de revenir plus tard." );
					}
					
				} else {
					request.setAttribute("errorMsg", "Erreur, merci de vous reconnecter.");
				}
			}else {
				redirect = true;
			}
		}
		if(redirect) {
			response.sendRedirect("login");
		}else {
			request.setAttribute("pageTitle", "Profil");
			request.getRequestDispatcher("WEB-INF/app/profile.jsp").forward(request, response);
		}
		
	}

}
