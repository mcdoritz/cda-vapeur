package com.vapeur.servlets;

import static com.vapeur.config.Debug.prln;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.vapeur.beans.Game;
import com.vapeur.beans.Order;
import com.vapeur.beans.OrderDetail;
import com.vapeur.beans.User;
import com.vapeur.config.Database;
import com.vapeur.dao.DAOException;
import com.vapeur.dao.GameDAO;
import com.vapeur.dao.OrderDAO;
import com.vapeur.dao.OrderDetailDAO;

/**
 * Servlet implementation class Checkout
 */
@WebServlet("/checkout")
public class Checkout extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public Checkout() {
		super();
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession(false);
		prln("doPost Checkout");
		// Vérifier qu'on vient bien du formulaire cart, et de toute façon on a besoin
		// des infos :
		if (request.getParameterValues("game-id") != null && request.getParameterValues("quantity") != null
				&& request.getParameterValues("uPrice") != null) {
			if (session != null) {

				if (session.getAttribute("cart") != null) {

					String[] gamesIds = request.getParameterValues("game-id");
					String[] quantites = request.getParameterValues("quantity");
					String[] unitPrices = request.getParameterValues("uPrice");

					if (request.getParameter("submit").equals("proceed")) {
						prln("proceed");
						if (session.getAttribute("user") != null) {
							if (gamesIds != null && quantites != null) {
								
								try {
									Database.connect();

									Order orderToSave = new Order();

									User user = new User();
									user = (User) session.getAttribute("user");
									Date date = new Date();
									orderToSave.setDate(date);
									orderToSave.setUserId(user.getId());

									OrderDAO orderdao = new OrderDAO();
									OrderDetailDAO orderdetaildao = new OrderDetailDAO();

									try {
										Order orderSaved = new Order();
										orderSaved = orderdao.getById(orderdao.save(orderToSave));
										int order_id = orderSaved.getId();

										ArrayList<OrderDetail> ordersList = new ArrayList<>(); // récupère tous les orders
																								// et on
																								// put tout d'un coup dans
																								// la
																								// bdd, plus safe en cas
																								// d'exception

										for (int i = 0; i < gamesIds.length && i < quantites.length; i++) {
											OrderDetail orderdetail = new OrderDetail();

											orderdetail.setGameId(Integer.valueOf(gamesIds[i]));
											orderdetail.setQuantity(Integer.valueOf(quantites[i]));
											orderdetail.setOrderId(order_id);
											orderdetail.setUnitPrice(Float.valueOf(unitPrices[i]));

											ordersList.add(orderdetail);

										}

										// Enregistrement final + màj du stock
										orderdetaildao.saveList(ordersList);

										// Vide le panier
										session.setAttribute("cart", null);
										request.setAttribute("infoMsg",
												"La commande a bien été passée ! Retrouvez la dans votre profil, et retrouvez vos jeux dans la biliothèque !.");
										request.getRequestDispatcher("WEB-INF/app/checkout.jsp").forward(request, response);

									} catch (DAOException e) {
										e.printStackTrace();
										request.setAttribute("errorMsg", e.getMessage());
										request.getRequestDispatcher("WEB-INF/app/checkout.jsp").forward(request, response);

									}
								}catch (Exception e){
									e.printStackTrace();;
								}
								

							}

						} else {
							request.setAttribute("errorMsg", "Merci de vous connecter pour valider le panier");
							request.getRequestDispatcher("WEB-INF/app/login.jsp").forward(request, response);
						}
					}else {
						prln("maj");
						try {
							Database.connect();
							HashMap<Game, Integer> gamesAndQuantityInCart = new HashMap<Game, Integer>();

							GameDAO gamedao = new GameDAO();
							int index = 0;
							for (String id : gamesIds) {
								gamesAndQuantityInCart.put(gamedao.getById(Integer.valueOf(id)),
										Integer.valueOf(quantites[index]));

								index++;
							}

							session.setAttribute("cart", gamesAndQuantityInCart);
							response.sendRedirect("cart");
						} catch (Exception e) {
							e.printStackTrace();
						}

					}
				}
			}
		} else {
			response.sendRedirect("cart");
		}

	}

}
