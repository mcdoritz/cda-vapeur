package com.vapeur.servlets;

import static com.vapeur.config.Debug.prln;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.vapeur.beans.Developer;
import com.vapeur.beans.Game;
import com.vapeur.beans.GameResults;
import com.vapeur.beans.Genre;
import com.vapeur.beans.Language;
import com.vapeur.beans.Mode;
import com.vapeur.beans.Platform;
import com.vapeur.config.Database;
import com.vapeur.dao.DeveloperDAO;
import com.vapeur.dao.GameDAO;
import com.vapeur.dao.GenreDAO;
import com.vapeur.dao.LanguageDAO;
import com.vapeur.dao.ModeDAO;
import com.vapeur.dao.PlatformDAO;

@WebServlet("/store")
public class Store extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private ArrayList<Integer> genres = new ArrayList<>();
	private ArrayList<Integer> modes = new ArrayList<>();
	private ArrayList<Integer> languages = new ArrayList<>();
	private ArrayList<Integer> platforms = new ArrayList<>();
	private ArrayList<Integer> developers = new ArrayList<>();
	
	private GameDAO gamedao = new GameDAO();
	private GenreDAO genredao = new GenreDAO();
	private ModeDAO modedao = new ModeDAO();
	private LanguageDAO languagedao = new LanguageDAO();
	private PlatformDAO platformdao = new PlatformDAO();
	private DeveloperDAO developerdao = new DeveloperDAO();
	
	private int page = 0;
	private int limitPerPage = 12;

    public Store() {
        super();
        // TODO Auto-generated constructor stub
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession(false);
		
		genres.clear();
		modes.clear();
		languages.clear();
		platforms.clear();
		developers.clear();
		
		try {
			Database.connect();
			
			List<Game> gamesList = new ArrayList<>();
			GameResults gameResults = new GameResults();
			
			try {
				//Récupérer tous les paramètres de l'url // String => tableau str => tableau int

				String genresSelectedStr = request.getParameter("genres");
				String modesSelectedStr  = request.getParameter("modes");
				String languagesSelectedStr  = request.getParameter("languages");
				String platformsSelectedStr  = request.getParameter("platforms");
				String developersSelectedStr  = request.getParameter("developers");
				
				if(request.getParameter("reset") != null) {
					genresSelectedStr = null;
					modesSelectedStr  = null;
					languagesSelectedStr  = null;
					platformsSelectedStr  = null;
					developersSelectedStr  = null;
				}
				
				String[] genresSelectedTbl;
				String[] modesSelectedTbl;
				String[] languagesSelectedTbl;
				String[] platformsSelectedTbl;
				String[] developersSelectedTbl;
				
				if(genresSelectedStr != null) {
					genresSelectedTbl = genresSelectedStr.split(",");
					for(String str:genresSelectedTbl) {
						prln("genre id str : " + str);
						genres.add(Integer.parseInt(str));
					}
				}
				
				if(modesSelectedStr != null) {
					modesSelectedTbl = modesSelectedStr.split(",");
					for(String str:modesSelectedTbl) {
						modes.add(Integer.parseInt(str));
					}
				}
				
				if(languagesSelectedStr != null) {
					languagesSelectedTbl = languagesSelectedStr.split(",");
					for(String str:languagesSelectedTbl) {
						languages.add(Integer.parseInt(str));
					}
				}
				
				if(platformsSelectedStr != null) {
					platformsSelectedTbl = platformsSelectedStr.split(",");
					for(String str:platformsSelectedTbl) {
						platforms.add(Integer.parseInt(str));
					}
				}
				
				if(developersSelectedStr != null) {
					developersSelectedTbl = developersSelectedStr.split(",");
					for(String str:developersSelectedTbl) {
						developers.add(Integer.parseInt(str));
					}
				}

				if(request.getParameter("page") != null) {
					page = Integer.parseInt(request.getParameter("page"));
					if(page > 0) {
						prln("ICI");
						gameResults = gamedao.readAll(page, genres, modes, languages, platforms, developers, false);
						
					}else {
						prln("LA");
						gameResults = gamedao.readAll(1, genres, modes, languages, platforms, developers, false);
						
					}
				}else {
					prln("LA BAS");
					for(int i:genres) {
						prln("valeur de genres_id avant dao : " + i);
					}
					gameResults = gamedao.readAll(0, genres, modes, languages, platforms, developers, false);
				}
				
				// Préparation des options pour l'affichage des filtres
				ArrayList<Genre> genresFilter = new ArrayList<>(genredao.readAll());
				ArrayList<Mode> modesFilter = new ArrayList<>(modedao.readAll());
				ArrayList<Language> languagesFilter = new ArrayList<>(languagedao.readAll());
				ArrayList<Platform> platformsFilter = new ArrayList<>(platformdao.readAll());
				ArrayList<Developer> developersFilter = new ArrayList<>(developerdao.readAll());
				
				// ----------------------------------------
				
				request.setAttribute("genres", genresFilter);
				request.setAttribute("modes", modesFilter);
				request.setAttribute("languages", languagesFilter);
				request.setAttribute("platforms", platformsFilter);
				request.setAttribute("developers", developersFilter);
				
				request.setAttribute("genresSelected", genres);
				request.setAttribute("modesSelected", modes);
				request.setAttribute("languagesSelected", languages);
				request.setAttribute("platformsSelected", platforms);
				request.setAttribute("developersSelected", developers);
				
				int gamesInPage = 0;
				int totalResults = 0;
				
				if(gameResults != null) {
					gamesInPage = gameResults.getTotalResults() > limitPerPage ? limitPerPage : gameResults.getTotalResults();
					totalResults = gameResults.getTotalResults();
					request.setAttribute("gamesInPage", gamesInPage);
					request.setAttribute("gamesList", gameResults.getGames());
					request.setAttribute("totalGames", totalResults);
				}

				request.setAttribute("page", page);
				
				request.setCharacterEncoding("UTF-8");
				request.setAttribute("pageTitle", "Magasin");
				request.setAttribute("euro", "€");
				
				//Si pas de games dans la liste, alors retour à la première page.
				if( totalResults == 0) {
					prln("po de jeu");
					request.setAttribute("infoMsg", "Les filtres ont tout filtré ! Mettez moins de filtres");
				}
				

			}catch (NumberFormatException e){
				prln("Exception de nombre dans la SERVLET");
				e.printStackTrace();
				response.sendRedirect("404");
			}
		}catch (Exception e) {
			request.setAttribute("errorMsg", "La base de donnée est indisponible. Merci de revenir plus tard." );
		}
		request.getRequestDispatcher("WEB-INF/app/store.jsp").forward(request, response);

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		prln("doPost Store");
		
		//Prendre les values du form et les puter dans l'url puis retour à doGet.
		String[] genresSelected = request.getParameterValues("genresForm");
		String[] modesSelected = request.getParameterValues("modesForm");
		String[] languagesSelected = request.getParameterValues("languagesForm");
		String[] platformsSelected = request.getParameterValues("platformsForm");
		String[] developersSelected = request.getParameterValues("developersForm");
		
		String filters [] = {"genres", "modes", "languages", "platforms", "developers"};
		
		ArrayList<String[]> filtersSelected = new ArrayList<>();
		filtersSelected.add(genresSelected);
		filtersSelected.add(modesSelected);
		filtersSelected.add(languagesSelected);
		filtersSelected.add(platformsSelected);
		filtersSelected.add(developersSelected);
		
		String parameters = "";
		
		Boolean alreadyOneParam = false;
		int index = 0;
		
		for(String[] params:filtersSelected) {
			if(params != null) {
				parameters += !alreadyOneParam ? "?" + filters[index] + "=" : "&" + filters[index] + "=";
				for(String str:params) {
					parameters += str + ",";
					alreadyOneParam = true;
				}
				parameters = parameters.substring(0, parameters.length() - 1); //Enlever la dernière virgule
			}	
			index++;
		}
		
		String url = "store" + parameters;
		prln("servlet store dopost " + url);
		response.sendRedirect(url);
	}

}
