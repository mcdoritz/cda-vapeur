package com.vapeur.servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.vapeur.beans.GameResults;
import com.vapeur.beans.User;
import com.vapeur.config.Database;
import com.vapeur.dao.GameDAO;
import static com.vapeur.config.Debug.*;

@WebServlet("/library")
public class Library extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public Library() {
        super();
        // TODO Auto-generated constructor stub
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession(false);
		
		
		if(session != null) {
			if(session.getAttribute("user") != null) {
				User user = (User) session.getAttribute("user");
				if(user.getId() != 0) {
					prln("user.getId est pas null");
					try {
						Database.connect();
						GameDAO gamedao = new GameDAO();
						GameResults gameresults = new GameResults();
						
						gameresults = gamedao.library(user.getId());
						
						if(gameresults != null) {
							request.setAttribute("gamesList", gameresults.getGames());
							request.setAttribute("pageTitle", "Bibliothèque");
						}else {
							request.setAttribute("infoMsg", "La biliothèque est vide !");
						}
					}catch (Exception e) {
						request.setAttribute("errorMsg", "La base de donnée est indisponible. Merci de revenir plus tard." );
					}
					
					
				}else {
					request.setAttribute("errorMsg", "Erreur avec la liste des jeux.");
				}
			}else {
				request.setAttribute("errorMsg", "Erreur avec la connexion utilisateur");
			}
		}
		
		request.setAttribute("pageTitle", "Bibliothèque");
		request.getRequestDispatcher("WEB-INF/app/library.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
