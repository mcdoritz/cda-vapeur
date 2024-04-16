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

import static com.vapeur.config.Debug.*;

@WebServlet(urlPatterns = { "/", "/landing" })
public class Landing extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public Landing() {
		super();
		// TODO Auto-generated constructor stub
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession(false);
		// Vérif 404 -------------
		String requestURI = request.getRequestURI();
		prln(requestURI);
		if (!requestURI.equals("/Vapeur/") && !requestURI.equals("/Vapeur/landing")) {
			response.sendRedirect("404");
			return;
		}
		
		if (session != null) {
			if (session.getAttribute("cart") == null) {
				prln("Panier non créé");
				/*List<Game> gamesInCart = new ArrayList<>();
				session.setAttribute("cart", gamesInCart);*/
			}else {
				List<Game> gamesInCart = (ArrayList<Game>)session.getAttribute("cart");
				if(gamesInCart.size() > 0) {
					prln("Le panier n'est po vide");
					for(Game g:gamesInCart) {
						prln(g.getTitle());
					}
				}else {
					prln("Le panier est vide");
				}
				
			}

		} else {
			prln("Erreur de session");
		}

		// ----------------------
		request.getRequestDispatcher("WEB-INF/app/landing.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
