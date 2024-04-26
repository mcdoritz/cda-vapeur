package com.vapeur.servlets;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import static com.vapeur.config.Debug.*;

import com.vapeur.beans.Game;
import com.vapeur.beans.GameResults;
import com.vapeur.beans.Genre;
import com.vapeur.beans.Mode;
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
		int totalNotes = 0;
		String gameTitle = "";
		
		if (request.getParameter("id") != null) {
			try {
				Database.connect();
				int game_id = Integer.parseInt(request.getParameter("id"));
				if(game_id > 0) {
					GameDAO gamedao = new GameDAO();
					Game game = gamedao.getById(game_id);
					gameTitle = game.getTitle();

					if(game.getTitle() != null) {
						prln("Servlet Game : game " + game.getTitle() + " trouvé");
						request.setAttribute("game", game);
						CommentDAO commentdao = new CommentDAO();
						totalNotes = commentdao.countCommentsById(game_id);
						
						//Suggestion de jeux du même genre et mode :
						ArrayList<Integer> genres_id = new ArrayList<>();
						ArrayList<Integer> modes_id = new ArrayList<>();
						ArrayList<Integer> gameNotToShow = new ArrayList<>();
						
						gameNotToShow.add(game.getId());
						
						for(Genre g:game.getGenres()) {
							genres_id.add(g.getId());
						}
						
						for(Mode m:game.getModes()) {
							modes_id.add(m.getId());
						}
						
						request.setAttribute("suggestions", gamedao.readSuggestions(gameNotToShow, genres_id, modes_id));
						
						//Vérifier si le joueur est connecté et s'il possède le jeu, si oui, s'il l'a noté.
						if(session != null) {
							if(session.getAttribute("user") != null){
								User user = (User) session.getAttribute("user");
								int user_id = user.getId();
								if(gamedao.isGameInUserLibrary(game_id, user_id)) {
									request.setAttribute("gameInUserLibrary", true);
									if(commentdao.getById(user_id, game_id) != null){
										request.setAttribute("userHasCommented", true);
										prln("servlet Game : le joueur possède le jeu ET a commenté");
									}else {
										prln("servlet Game : le joueur possède le jeu mais n'a pas commenté");
									}
								}else {
									prln("Erreur servlet Game : le joueur ne possède pas le jeu");
								}
								
							}else {
								prln("servlet Game : pas de session user");
							}
						}else {
							prln("servlet gamedetails : pas de session");
						}
						
						
					}else {
						prln("Erreur servlet Game : pas de game trouvé");
						request.setAttribute("errorMsg", "La base de donnée est indisponible. Merci de revenir plus tard." );
					}

				}else {
					prln("Erreur servlet Game : l'id dans l'URL n'est pas un nombre");
					request.setAttribute("errorMsg", "Erreur avec l'URL" );
				}
			}catch (Exception e) {
				prln("Erreur servlet Game : exception");
				prln(e.getMessage());
				e.printStackTrace();
				request.setAttribute("errorMsg", "La base de donnée est indisponible. Merci de revenir plus tard." );
			}

		}else {
			prln("Erreur servlet Game : pas d'id dans l'URL");
			request.setAttribute("errorMsg", "Erreur avec l'URL" );
		}
		
		request.setAttribute("totalNotes", totalNotes);
		request.setAttribute("euro", "€");
		request.setAttribute("pageTitle", gameTitle);
		request.getRequestDispatcher("WEB-INF/app/game.jsp").forward(request, response);
		
	}

}
