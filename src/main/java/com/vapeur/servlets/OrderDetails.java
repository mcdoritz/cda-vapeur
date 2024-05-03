package com.vapeur.servlets;

import static com.vapeur.config.Debug.prln;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.vapeur.beans.Game;
import com.vapeur.beans.Order;
import com.vapeur.beans.User;
import com.vapeur.config.Database;
import com.vapeur.dao.DAOException;
import com.vapeur.dao.GameDAO;
import com.vapeur.dao.OrderDAO;
import com.vapeur.dao.UserDAO;

/**
 * Servlet implementation class OrderDetails
 */
@WebServlet("/order")
public class OrderDetails extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public OrderDetails() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession(false);

		if (session != null) {
			if (session.getAttribute("user") != null) {
				User userVerif = new User();
				userVerif = (User) session.getAttribute("user");

				if (request.getParameter("user_id") != null && Integer.valueOf(request.getParameter("user_id")) > 0
						&& request.getParameter("order_id") != null
						&& Integer.valueOf(request.getParameter("order_id")) > 0) {

					int user_id = Integer.valueOf(request.getParameter("user_id"));
					int order_id = Integer.valueOf(request.getParameter("order_id"));

					// Vérif que l'user a le droit de voir cette commande
					Database.connect();
					UserDAO userdao = new UserDAO();
					
					User user = userdao.getById(userVerif.getId());

					if (user != null) {
						OrderDAO orderdao = new OrderDAO();
						Order order = orderdao.getById(order_id);

						request.setAttribute("user", user);
						try {
							request.setAttribute("detailsList", orderdao.readAllByOrderId(order_id));
							request.setAttribute("pageTitle", "Commande n° " + order.getId() + ", " + order.getDate());
						} catch (DAOException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
							request.setAttribute("errorMSg", "Erreur dans la récupération des détails de la commande");
						}
					}else {
						request.setAttribute("errorMSg", "Erreur, vous n'êtes pas autorisé à voir cette commande.");
					}


				} else {
					request.setAttribute("errorMSg", "Erreur dans la récupération de la commande");
					doGet(request, response);
				}

				request.getRequestDispatcher("WEB-INF/app/orderDetails.jsp").forward(request, response);

			}

		}
	}
}
