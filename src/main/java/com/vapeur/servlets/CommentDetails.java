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
import com.vapeur.beans.User;
import com.vapeur.config.Database;
import com.vapeur.dao.CommentDAO;

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

		if (request.getParameter("game_id") != null && request.getParameter("user_id") != null) {
			int game_id = Integer.valueOf(request.getParameter("game_id"));
			int user_id = Integer.valueOf(request.getParameter("user_id"));
			if(game_id != 0 && user_id != 0) {
				if (session != null) {
					if (session.getAttribute("user") != null) {
						User user = (User) session.getAttribute("user");
						
						//Vérif que c'est le bon user
						if(user.getId() == user_id) {
							try {
								Database.connect();
								
								CommentDAO commentdao = new CommentDAO();
								
								Comment comment = commentdao.getById(user_id, game_id);
								
								request.setAttribute("comment", comment);
								
							} catch (Exception e) {
								request.setAttribute("errorMsg", e.getMessage());
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

		if (request.getParameter("score") != null && request.getParameter("content") != null
				&& request.getParameter("game_id") != null) {
			if (session != null) {
				if (session.getAttribute("user") != null) {
					User user = (User) session.getAttribute("user");

					try {
						int score = Integer.valueOf(request.getParameter("score"));
						int game_id = Integer.valueOf(request.getParameter("game_id"));
						String content = request.getParameter("content");
						CommentDAO commentdao = new CommentDAO();
						Timestamp timestamp = new Timestamp(System.currentTimeMillis());

						Comment comment = new Comment(content, timestamp, score, user.getId(), user.getNickname(),
								game_id);
						Database.connect();

						commentdao.save(comment);
						
						request.setAttribute("infoMsg", "Commentaire mis à jour !");

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
