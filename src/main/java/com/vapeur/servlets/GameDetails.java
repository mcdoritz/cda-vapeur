package com.vapeur.servlets;

import static com.vapeur.config.Debug.prln;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.google.gson.Gson;
import com.vapeur.beans.Game;
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
	 * @throws ServletException 
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
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
						
						String directoryPath = "/assets/images/games/" + game.getId(); // Remplacez ceci par le chemin absolu de votre dossier d'images
						File directory = new File(directoryPath);
						ArrayList<String> images = new ArrayList<>();
						if (directory.isDirectory()) {
						    File[] files = directory.listFiles();
						    for (File file : files) {
						    	images.add(file.getName());

						    }
						} else {
						    System.out.println("Le chemin spécifié n'est pas un répertoire.");
						}
						
						
						
						//Récupérer les images

				        try {
				        	// *********************************************************************************************
				        	// *********************************************************************************************
				        	// *********************************************************************************************
				        	// *********************************************************************************************

				        	//Note pour ZAK : le code ci-dessous est certainement bien dégueulasse. Je te remercie de me faire un retour dessus.
				        	// Le code permet de récupérer des images au nom inconnu
				        	
				        	// *********************************************************************************************
				        	// *********************************************************************************************
				        	// *********************************************************************************************
				        	// *********************************************************************************************

				            //Vérifier que le serveur est accessible ainsi que le dossier du jeu
				        	
			            	// URL distant de backoffice
				        	String distantURL = "http://localhost:8081/VapeurBackOffice/assets/images/games/"+game.getId()+"/";
				            URL urlTest = new URL(distantURL);
				            
				            HttpURLConnection connection = (HttpURLConnection) urlTest.openConnection();
	
				            connection.setRequestMethod("HEAD");
				            
				            int responseCode = connection.getResponseCode();
				            
				            if(responseCode >= 200 && responseCode < 300) {
				            	prln("CODE 200");
				            	
				                // Récupérer le contenu HTML de la page
				                Document doc = Jsoup.connect(distantURL).get();

				                // Sélectionner tous les liens dans la page
				                Elements links = doc.select("a[href]");

				                List<String> imageUrls = new ArrayList<>();

				                // Parcourir les liens pour récupérer les URLs des images
				                for (Element link : links) {
				                    String href = link.attr("href");
				                    // Vérifier si le lien pointe vers une image (pas forcément utile mais bon)
				                    if (href.endsWith(".jpg") || href.endsWith(".jpeg") || href.endsWith(".png") || href.endsWith(".gif")) {
				                        // Construire l'URL complète de l'image
				                        String imageUrl = distantURL + href.substring(href.lastIndexOf("/"));
				                        // Ajouter l'URL à la liste
				                        imageUrls.add(imageUrl);
				                    }
				                }
				                
				                for(String str:imageUrls) {
				                	prln(str);
				                }

				                request.setAttribute("images", imageUrls);

				            }else {
				            	request.setAttribute("errorMsg", "Attention, pas d'image trouvée pour ce jeu");
				            	prln("PAS CODE 200");
				            }
				            
				            
				         // URL distant de backoffice LOGO DU JEU
				        	String distantURLlogo = "http://localhost:8081/VapeurBackOffice/assets/images/games/"+game.getId()+"/logo";
				            URL urlTestlogo = new URL(distantURLlogo);
				            
				            HttpURLConnection connection2 = (HttpURLConnection) urlTestlogo.openConnection();
	
				            connection2.setRequestMethod("HEAD");
				            
				            int responseCode2 = connection2.getResponseCode();
				            
				            if(responseCode2 >= 200 && responseCode2 < 300) {
				            	prln("CODE 200");
				            	
				                // Récupérer le contenu HTML de la page
				                Document doc = Jsoup.connect(distantURLlogo).get();

				                // Sélectionner tous les liens dans la page
				                Elements links = doc.select("a[href]");

				               String logoUrl = "";

				                // Parcourir les liens pour récupérer les URLs des images
				                for (Element link : links) {
				                    String href = link.attr("href");
				                    // Vérifier si le lien pointe vers une image (pas forcément utile mais bon)
				                    if (href.endsWith(".jpg") || href.endsWith(".jpeg") || href.endsWith(".png") || href.endsWith(".gif") || href.endsWith(".webp")) {
				                        // Construire l'URL complète de l'image
				                        String imageUrl = distantURLlogo + href.substring(href.lastIndexOf("/"));
				                        prln(distantURLlogo);
				                        // Ajouter l'URL à la liste
				                        logoUrl = imageUrl;
				                    }
				                }
				                
				                prln(logoUrl);

				                request.setAttribute("logo", logoUrl);

				            }else {
				            	request.setAttribute("errorMsg", "Attention, pas d'image trouvée pour ce jeu");
				            	prln("PAS CODE 200");
				            }
				            
				        } catch (Exception e) {
				            e.printStackTrace();
				        }


						
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
