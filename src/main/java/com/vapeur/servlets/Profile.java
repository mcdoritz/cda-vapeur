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
import com.vapeur.dao.OrderDAO;
import com.vapeur.dao.OrderDetailDAO;

@WebServlet("/profile")
public class Profile extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public Profile() {
        super();
        // TODO Auto-generated constructor stub
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession(false);
		
		if (session != null) {
			if (session.getAttribute("user") != null) {
				User user = new User();
				user = (User) session.getAttribute("user");
				if (user.getId() > 0) {
					Database.connect();
					OrderDAO orderdao = new OrderDAO();
					try {
						request.setAttribute("ordersList", orderdao.readAllByUserId(user.getId()));
					} catch (DAOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
						request.setAttribute("errorMsg", e.getMessage());
					}
				}else {
					request.setAttribute("errorMsg", "Erreur, merci de vous reconnecter.");
				}
			}
		}
		
		
		
		request.setAttribute("pageTitle", "Profil");
		request.getRequestDispatcher("WEB-INF/app/profile.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
