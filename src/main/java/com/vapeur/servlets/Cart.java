package com.vapeur.servlets;

import static com.vapeur.config.Debug.prln;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.vapeur.beans.Game;
import com.vapeur.dao.GameDAO;

@WebServlet("/cart")
public class Cart extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public Cart() {
		super();
		// TODO Auto-generated constructor stub
	}

	@SuppressWarnings("unchecked")
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		HttpSession session = request.getSession(false);
		prln("Servlet Cart");
		Boolean redirect = false;
		Map<Game, Integer> gamesAndQuantityInCart = new HashMap<>();

		if (session != null) {
			if (session.getAttribute("cart") == null) {
				prln("cart/del : Panier non créé");
				session.setAttribute("cart", gamesAndQuantityInCart);
			} else {
				gamesAndQuantityInCart = (Map<Game, Integer>) session.getAttribute("cart");

				Iterator<Map.Entry<Game, Integer>> iterator = gamesAndQuantityInCart.entrySet().iterator();
				while (iterator.hasNext()) {
					Map.Entry<Game, Integer> entry = iterator.next();
					int game_id = entry.getKey().getId();
					int quantity = entry.getValue();

				}

				if (gamesAndQuantityInCart.size() > 0) {
					prln("Servlet cart/add : le panier contient " + gamesAndQuantityInCart.size() + " games.");
				} else {
					prln("Servlet cart/add : le panier est vide");
				}

			}

		} else {
			prln("Servlet cart : Erreur de session");
		}
		// ajouter au cart :
		// Si add différent de null, alors c'est qu'on ajoute un jeu au panier
		if (request.getParameter("add") != null) {
			int id = Integer.parseInt(request.getParameter("add"));
			if (id > 0) {

				// Vérifier que le jeu n'est pas déjà présent dans le panier
				Boolean gameInCart = false;
				Iterator<Map.Entry<Game, Integer>> iterator = gamesAndQuantityInCart.entrySet().iterator();
				while (iterator.hasNext()) {
					Map.Entry<Game, Integer> entry = iterator.next();
					int game_id = entry.getKey().getId();
					int quantity = entry.getValue();
					// System.out.println("Game: " + game_id + ", quantity: " + quantity);
					if (game_id == id) {
						// Jeu présent ! Donc ajouter 1 à la quantity
						gameInCart = true;
						prln("Servlet Cart/add : game déjà dans panier; + 1 quantity");
						entry.setValue(quantity + 1);
						break;
					}
				}

				// Si jeu pas présent dans le panier, on va le chercher dans la bdd s'il est
				// toujours là bas.
				if (!gameInCart) {

					GameDAO gamedao = new GameDAO();
					Game gameToAdd = gamedao.getById(id);
					if (gameToAdd.getTitle() != null) {
						gamesAndQuantityInCart.put(gameToAdd, 1);
						session.setAttribute("cart", gamesAndQuantityInCart);

						prln("Servlet Cart/add : ajout game");
					} else {
						prln("Servlet Cart/add : erreur dans la récupération du game");
					}
				}

			}
			redirect = true;
			// Si del différent de null, alors c'est qu'on supprime un jeu au panier
		} else if (request.getParameter("del") != null) {
			{
				int id = Integer.parseInt(request.getParameter("del"));
				if (id > 0) {

					gamesAndQuantityInCart = (Map<Game, Integer>) session.getAttribute("cart");
					int i = 0;

					// Parcourir le tableau et checker tous les id;
					Iterator<Map.Entry<Game, Integer>> iterator = gamesAndQuantityInCart.entrySet().iterator();
					while (iterator.hasNext()) {
						Map.Entry<Game, Integer> entry = iterator.next();
						int game_id = entry.getKey().getId();
						int quantity = entry.getValue();
						// System.out.println("Game: " + game_id + ", quantity: " + quantity);
						if (game_id == id) {
							// Jeu présent ! Donc on suppr
							gamesAndQuantityInCart.remove(entry.getKey());
							prln("Servlet Cart/del : game dans panier; supprimé");
							prln("Reste " + gamesAndQuantityInCart.size() + " game(s) dans le panier");
							break;
						}
					}
				}
			}
			redirect = true;
		}
		prln("Il y a : " + gamesAndQuantityInCart.size() + " games dans le panier");
		request.setAttribute("NbGamesInCart", Integer.toString(gamesAndQuantityInCart.size()));
		request.setAttribute("pageTitle", "Panier");
		request.setAttribute("euro", "€");

		if (redirect) {
			response.sendRedirect("cart");
		} else {
			request.getRequestDispatcher("WEB-INF/app/cart.jsp").forward(request, response);
		}

	}

}
