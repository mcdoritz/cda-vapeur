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
import com.vapeur.config.Database;
import com.vapeur.dao.GameDAO;
import com.vapeur.dao.OrderDAO;

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
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession(false);
		
		if (request.getParameter("id") != null) {
			Database.connect();
			int id = Integer.parseInt(request.getParameter("id"));
			if(id > 0) {
				OrderDAO orderdao = new OrderDAO();
				Order order = orderdao.getById(id);
				
				if(order != null) {
					prln("Servlet Order : Order " + order.getId() + " trouvé");
					request.setAttribute("order", order);
					
					/*for(String t:game.getTags()) {
						prln(t);
					}*/
					
					
					request.setAttribute("euro", "€");
					//request.setAttribute("pageTitle", order.getTitle());
					request.getRequestDispatcher("WEB-INF/app/order.jsp").forward(request, response);
				}else {
					prln("Erreur servlet Order : pas de order trouvé");
					response.sendRedirect("profile");
				}
				
				
				
			}else {
				prln("Erreur servlet Order : l'id dans l'URL n'est pas un nombre");
				response.sendRedirect("profile");
			}
			
		}else {
			prln("Erreur servlet Order : pas d'id dans l'URL");
			response.sendRedirect("profile");
		}
		
	}

}
