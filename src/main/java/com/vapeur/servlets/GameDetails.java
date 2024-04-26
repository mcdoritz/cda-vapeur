package com.vapeur.servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import static com.vapeur.config.Debug.*;

import com.vapeur.beans.Game;
import com.vapeur.beans.User;
import com.vapeur.config.Database;
import com.vapeur.dao.CommentDAO;
import com.vapeur.dao.GameDAO;

/**
 * Servlet implementation class Game
 */
@WebServlet("/game")
public class GameDetails extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public GameDetails() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		@SuppressWarnings("unused")
		HttpSession session = request.getSession(false);
		
		if (request.getParameter("id") != null) {
			try {
				Database.connect();
				int id = Integer.parseInt(request.getParameter("id"));
				if(id > 0) {
					GameDAO gamedao = new GameDAO();
					Game game = gamedao.getById(id);

					if(game != null) {
						prln("Servlet Game : game " + game.getTitle() + " trouvé");
						request.setAttribute("game", game);
						CommentDAO commentdao = new CommentDAO();
						int totalNotes = commentdao.countCommentsById(id);
						
						//Vérifier si le joueur est connecté et s'il possède le jeu, si oui, s'il l'a noté.
						if(session.getAttribute("user") != null){
							User user = (User) session.getAttribute("user");
							int user_id = user.getId();
							if(gamedao.isGameInUserLibrary(user_id)) {
								request.setAttribute("gameInUserLibrary", true);
								if(commentdao.getById(id, user_id) != null){
									request.setAttribute("userHasCommented", true);
								};
							}
							
						}
						
						request.setAttribute("totalNotes", totalNotes);
						request.setAttribute("euro", "€");
						request.setAttribute("pageTitle", game.getTitle());
						request.getRequestDispatcher("WEB-INF/app/game.jsp").forward(request, response);
					}else {
						prln("Erreur servlet Game : pas de game trouvé");
						response.sendRedirect("store");
					}

				}else {
					prln("Erreur servlet Game : l'id dans l'URL n'est pas un nombre");
					response.sendRedirect("store");
				}
			}catch (Exception e) {
				request.setAttribute("errorMsg", "La base de donnée est indisponible. Merci de revenir plus tard." );
			}
			
			
		}else {
			prln("Erreur servlet Game : pas d'id dans l'URL");
			response.sendRedirect("store");
		}
		
	}

}
