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
import com.vapeur.config.Database;
import com.vapeur.dao.DAOException;
import com.vapeur.dao.GameDAO;

@WebServlet("/search")
public class Search extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public Search() {
        super();
        // TODO Auto-generated constructor stub
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession(false);
		
		String search = "";
		if(request.getParameter("search") != null) {
			search = request.getParameter("search");
			
			try {
				Database.connect();
				GameDAO gamedao = new GameDAO();
				List<Game> gamesList = new ArrayList<>();
				gamesList = gamedao.readSearched(0, search);
				for(Game g:gamesList) {
					prln(g.getTitle());
				}
			} catch (DAOException e) {
				prln(e.getMessage());
				e.printStackTrace();
			}
			
		}else {
			prln("Erreur, saerch vide");
		}
		
		
		
		request.getRequestDispatcher("WEB-INF/app/search.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
