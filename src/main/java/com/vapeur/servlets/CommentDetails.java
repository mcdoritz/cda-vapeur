package com.vapeur.servlets;

import java.io.IOException;
import java.sql.Timestamp;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.vapeur.beans.BeanException;
import com.vapeur.beans.Comment;
import com.vapeur.beans.Game;
import com.vapeur.beans.User;
import com.vapeur.config.Database;
import com.vapeur.dao.CommentDAO;
import com.vapeur.dao.DAOException;
import com.vapeur.dao.GameDAO;

import static com.vapeur.config.Debug.*;

/**
 * Servlet implementation class Comment
 */
@WebServlet("/comment")
public class CommentDetails extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public CommentDetails() {
		super();
		// TODO Auto-generated constructor stub
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession(false);
		request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");

		if (request.getParameter("game_id") != null) {
			int game_id = Integer.valueOf(request.getParameter("game_id"));
			
			if(game_id != 0) {
				if (session != null) {
					if (session.getAttribute("user") != null) {
						User user = (User) session.getAttribute("user");
						int user_id = user.getId();
						//Vérif que c'est le bon user et qu'il possède le jeu
						if(user_id != 0) {
							
							try {
								Database.connect();
								
								GameDAO gamedao = new GameDAO();
								//Vérification que l'user a ce jeu
								if(gamedao.isGameInUserLibrary(game_id, user_id)){
									CommentDAO commentdao = new CommentDAO();
									Comment comment = new Comment();
									Game game = gamedao.getStockAndTitle(game_id);
									game.setId(game_id);
									//Vérification s'il a commenté.
									if(commentdao.getById(user_id, game_id) != null){
										prln("User a commenté le jeu.");
										comment = commentdao.getById(user_id, game_id);
									}else {
										prln("User N'A PAS commenté le jeu.");
									}
									
									request.setAttribute("game", game);
									request.setAttribute("comment", comment);
								}else {
									String e = "Erreur, vous ne possédez pas ce jeu";
									request.setAttribute("errorMsg", e);
									
								}
								
							} catch (Exception e) {
								e.printStackTrace();
								request.setAttribute("errorMsg", "Erreur avec la base de données");
							}
							
						}else {
							request.setAttribute("errorMsg", "Erreur, vous essayez d'accéder à un commentaire qui ne vous appartient pas.");
						}
						
						
						
					}else {
						request.setAttribute("errorMsg", "Erreur, veuillez vous reconnecter.");
						}
				}else {
					request.setAttribute("errorMsg", "Erreur, veuillez vous reconnecter.");
				}
			}else {
				request.setAttribute("errorMsg", "Erreur");
			}
			
		} else {
			request.setAttribute("errorMsg", "Erreur avec l'URL");
		}
		
		request.setAttribute("pageTitle", "Evaluer");
		request.getRequestDispatcher("WEB-INF/app/comment.jsp").forward(request, response);
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession(false);
		request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");

		if (request.getParameter("score") != null && request.getParameter("content") != null
				&& request.getParameter("game_id") != null) {
			if (session != null) {
				if (session.getAttribute("user") != null) {
					User user = (User) session.getAttribute("user");

					try {
						//je dois faire comme ça car ça bugguait au premier commentaire si on laissait à 0
						int score = 0;
						if(request.getParameter("score").equals("0.0")) {
							score = 0;
						}else {
							score = Integer.valueOf(request.getParameter("score").substring(0, request.getParameter("score").indexOf(".")));
						}
						
						int game_id = Integer.valueOf(request.getParameter("game_id"));
						if(game_id != 0) {
							String content = request.getParameter("content");
							CommentDAO commentdao = new CommentDAO();
							Timestamp timestamp = new Timestamp(System.currentTimeMillis());

							Comment comment = new Comment(content, timestamp, score, user.getId(), user.getNickname(),
									game_id, false);
							Database.connect();

							commentdao.save(comment);
							
							request.setAttribute("infoMsg", "Commentaire mis à jour !");
						}else {
							request.setAttribute("errorMsg", "Erreur de récupération du jeu.");
						}
						

					} catch (BeanException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
						request.setAttribute("errorMsg", e.getMessage());
					}

				} else {
					request.setAttribute("errorMsg", "Erreur, veuillez vous reconnecter.");
				}
			} else {
				request.setAttribute("errorMsg", "Erreur, veuillez vous reconnecter.");
			}
		} else {
			request.setAttribute("errorMsg", "L'évaluation n'est pas complète.");
		}
		request.getRequestDispatcher("WEB-INF/app/comment.jsp").forward(request, response);
	}

}
