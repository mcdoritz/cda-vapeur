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

import static com.vapeur.config.Debug.*;

import com.vapeur.beans.Game;
import com.vapeur.beans.GameResults;
import com.vapeur.config.Database;
import com.vapeur.dao.DAOException;
import com.vapeur.dao.GameDAO;

@WebServlet("/search")
public class Search extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private int page = 0;

    public Search() {
        super();
        // TODO Auto-generated constructor stub
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession(false);
		request.setAttribute("url", backOfficeUrl());
		
		String search = "";
		
		if(request.getParameter("search") != null) {
			search = request.getParameter("search");
			
			try {
				Database.connect();
				GameDAO gamedao = new GameDAO();
				GameResults gameresults = new GameResults();
				
				if(request.getParameter("page") != null) {
					page = Integer.parseInt(request.getParameter("page"));
					if(page > 0) {
						gameresults = gamedao.readSearched(page, search);
						
					}else {
						gameresults = gamedao.readSearched(1, search);
						
					}
				}else {
					gameresults = gamedao.readSearched(0, search);
				}
				
				request.setAttribute("search", search);
				request.setAttribute("totalGames", gameresults.getTotalResults());
				request.setAttribute("gamesList", gameresults.getGames());
				
			} catch (Exception e) {
				prln(e.getMessage());
				request.setAttribute("errorMsg", e.getMessage());
			}
			request.setAttribute("pageTitle", "Résultats de la recherche");
			request.getRequestDispatcher("WEB-INF/app/store.jsp").forward(request, response);
		}else {
			prln("Search vide");
			request.setAttribute("infoMsg", "Entrez votre recherche");
			request.setAttribute("pageTitle", "Rechercher un jeu");
			request.getRequestDispatcher("WEB-INF/app/store.jsp").forward(request, response);
		}
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
