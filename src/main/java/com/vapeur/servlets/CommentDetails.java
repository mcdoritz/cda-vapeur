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

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession(false);
		
		if(request.getParameter("score") != null && request.getParameter("content") != null && request.getParameter("id") != null){
			prln("Suite");
			if(session != null) {
				prln("Suite");
				if(session.getAttribute("user") != null) {
					prln("Suite");
					User user = (User) session.getAttribute("user");
					
					
					try {
						prln("Suite");
						int score = Integer.valueOf(request.getParameter("score"));
						int game_id = Integer.valueOf(request.getParameter("id"));
						String content = request.getParameter("content");
						CommentDAO commentdao = new CommentDAO();
						Timestamp timestamp = new Timestamp(System.currentTimeMillis());

						Comment comment = new Comment(content, timestamp, score, user.getId(), user.getNickname(), game_id);
						Database.connect();
						
						commentdao.save(comment);
						prln("Suite");
						
					} catch (BeanException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
						request.setAttribute("errorMsg", e.getMessage());
					}
					
				}else {
					request.setAttribute("errorMsg", "Erreur, veuillez vous reconnecter.");
				}
			}else {
				request.setAttribute("errorMsg", "Erreur, veuillez vous reconnecter.");
			}
		}else {
			request.setAttribute("errorMsg", "L'évaluation n'est pas complète.");
		}
		request.getRequestDispatcher("WEB-INF/app/library.jsp").forward(request, response);
	}
	
}
