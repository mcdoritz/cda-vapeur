package com.vapeur.servlets;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.vapeur.beans.Game;
import com.vapeur.config.Database;
import com.vapeur.dao.GameDAO;

import static com.vapeur.config.Debug.*;

@WebServlet("/store")
public class Store extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public Store() {
        super();
        // TODO Auto-generated constructor stub
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession(false);
		Database.connect();
		GameDAO gamedao = new GameDAO();
		List<Game> gamesList = new ArrayList<>();
		int totalGames = gamedao.countAll();
		prln(totalGames + " games dans la bdd");
		
		int page = 0;
		
		try {
			
			if(request.getParameter("page") != null) {
				page = Integer.parseInt(request.getParameter("page"));
				if(page > 0) {
					gamesList = gamedao.readAll(page);
					
				}else {
					gamesList = gamedao.readAll(1);
					
				}
			}else {
				gamesList = gamedao.readAll(1);
			}

			request.setAttribute("page", page);
			request.setAttribute("gamesInPage", gamesList.size());
			request.setCharacterEncoding("UTF-8");
			request.setAttribute("gamesList", gamesList);
			request.setAttribute("pageTitle", "Magasin");
			request.setAttribute("euro", "€");
			request.setAttribute("totalGames", totalGames);
			
			//Si pas de games dans la liste, alors retour à la première page.
			if(gamesList.size() == 0) {
				response.sendRedirect("store");
			}else {
				request.getRequestDispatcher("WEB-INF/app/store.jsp").forward(request, response);
			}

		}catch (NumberFormatException e){
			response.sendRedirect("404");
		}
		
		
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
