package com.vapeur.servlets;

import static com.vapeur.config.Debug.prln;

import java.io.File;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.vapeur.beans.BeanException;
import com.vapeur.beans.User;
import com.vapeur.config.Database;
import com.vapeur.dao.DAOException;
import com.vapeur.dao.OrderDAO;
import com.vapeur.dao.UserDAO;

/**
 * Servlet implementation class Editprofile
 */
@WebServlet("/editProfile")
public class Editprofile extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Editprofile() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	HttpSession session = request.getSession(false);
		
		if (session != null) {
			if (session.getAttribute("user") != null) {
				User user = new User();
				user = (User) session.getAttribute("user");
				if (user.getId() == 0) {
					response.sendRedirect("login");
				}
			}
		}
		
		request.setAttribute("pageTitle", "Editer mon profil");
		request.getRequestDispatcher("WEB-INF/app/editProfile.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession(false);
		
		prln("servlet editProfil doPost !");
		prln(request.getParameter("firstname"));
		prln(request.getParameter("lastname"));
		prln(request.getParameter("email"));
		
		if (session != null) {
			prln("servlet editProfil letsgo.....");
			if (session.getAttribute("user") != null) {
				prln("servlet editProfil letsgo...");
				if(request.getParameter("avatar") != null) {
					// Upload image
					if (request.getParameter("avatar") != null) {
						/* Vérifier si l'image de l'avatar existe
						String destination = "public" + File.separator + "avatars" + File.separator
								+ user.getAvatar(); // String du chemin
						String upload = getServletContext().getRealPath(destination); // Conversion du string vers
																						// un vrai chemin
						File avatar = new File(upload); // Création de l'image
						modif = true;*/
					}else {
						//userModif.setAvatar(user.getAvatar());
					}
					
				}else if (request.getParameter("currentPassword") != "") {
					prln("servlet editProfil letsGo");
					Boolean modif = false;

					// Vérifier mdp
					UserDAO userdao = new UserDAO();
					User user = new User();
					User authorizedUser = new User();
					User userModif = new User();
					user = (User) session.getAttribute("user");
					Database.connect();
					try {
						authorizedUser = userdao.login(user.getEmail(), request.getParameter("currentPassword"));
					} catch (DAOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
						request.setAttribute("errorMsg", e.getMessage());
					}

					if (authorizedUser == null) {
						request.setAttribute("errorMsg", "Mot de passe actuel incorrect.");
					} else {
						

						// Pseudo
						try {
							userModif.setNickname(user.getNickname());
							
						} catch (BeanException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
							request.setAttribute("errorMsg", e.getMessage());
						}
						
						//Avatar
						userModif.setAvatar(user.getAvatar());

						// Mail
						if (request.getParameter("email") != "") {
							prln("request email pas nul");
							try {
								userModif.setEmail(request.getParameter("email"));
								modif = true;
							} catch (BeanException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
								request.setAttribute("errorMsg", e.getMessage());
							}
						} else {
							try {
								userModif.setEmail(user.getEmail());
							} catch (BeanException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
								request.setAttribute("errorMsg", e.getMessage());
							}
						}

						// Firstname
						if (request.getParameter("firstname") != "") {
							userModif.setFirstname(request.getParameter("firstname"));
							modif = true;
						} else {
							userModif.setFirstname(user.getFirstname());
						}

						// Lastname
						if (request.getParameter("lastname") != "") {
							userModif.setLastname(request.getParameter("lastname"));
							modif = true;
						} else {
							userModif.setLastname(user.getLastname());
						}

						// Password
						if (request.getParameter("new-password") != "") {
							if (request.getParameter("confirm-new-password") != "") {
								if (request.getParameter("new-password")
										.equals(request.getParameter("confirm-new-password"))) {
									userModif.setPassword(request.getParameter("new-password"));
									modif = true;
								} else {
									request.setAttribute("errorMsg",
											"Le nouveau mot de passe et sa confirmation sont différents.");
								}

							} else {
								request.setAttribute("errorMsg", "La confirmation du mot de passe est vide.");
							}
						} else {
							userModif.setPassword(user.getPassword());
						}

						// Adresse
						if (request.getParameter("ShippingAddress") != "") {
							userModif.setShippingAddress(user.getShippingAddress());
							modif = true;
						} else {
							userModif.setShippingAddress(user.getShippingAddress());
						}
					}
					
					if(modif) {
						try {
							userModif.setId(user.getId());
							userModif.setActive(true);
							if(userdao.save(userModif)) {
								request.setAttribute("infoMsg", "Détails changés !");
								session.setAttribute("user", userdao.getById(user.getId()));
							}
						} catch (DAOException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
							request.setAttribute("errorMsg", e.getMessage());
						}
					}

				}else {
					prln("password VIDE");
					request.setAttribute("errorMsg", "Le mot de passe actuel n'a pas été entré.");
				}

			} else {
				request.setAttribute("errorMsg", "Erreur, veuillez vous reconnecter.");
			}
		}
		request.setAttribute("pageTitle", "Editer mon profil");
		request.getRequestDispatcher("WEB-INF/app/editProfile.jsp").forward(request, response);
	}

}
