package com.vapeur.servlets;

import java.io.IOException;
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
		
		List<Game> gamesList = gamedao.readAll("all");
		
		prln("nb : " + gamesList.size());
		request.setCharacterEncoding("UTF-8");
		request.setAttribute("gamesList", gamesList);
		request.setAttribute("pageTitle", "Magasin");
		request.setAttribute("euro", "€");
		request.getRequestDispatcher("WEB-INF/app/store.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
