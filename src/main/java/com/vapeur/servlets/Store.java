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

    public Store() {
        super();
        // TODO Auto-generated constructor stub
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession(false);
		
		if(request.getParameter("reset") != null) {
			prln("reset");
		}
		
		for(int i:genres) {
			prln("valeur de genres_id : " + i);
		}
		genres.clear();
		modes.clear();
		languages.clear();
		platforms.clear();
		developers.clear();
		prln("*** cleared **");
		for(int i:genres) {
			prln("valeur de genres_id : " + i);
		}
		
		if(genres.size() == 0) {
			prln("genres vide");
		}
		
		Database.connect();
		
		List<Game> gamesList = new ArrayList<>();
		
		
		int totalGames = gamedao.countAll();
		prln(totalGames + " games dans la bdd");
		
		try {
			//Récupérer tous les paramètres de l'url // String => tableau str => tableau int

			String genresSelectedStr = request.getParameter("genres");
			String modesSelectedStr  = request.getParameter("modes");
			String languagesSelectedStr  = request.getParameter("languages");
			String platformsSelectedStr  = request.getParameter("platforms");
			String developersSelectedStr  = request.getParameter("developers");
			
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
					gamesList = gamedao.readAll(page, genres, modes, languages, platforms, developers);
					
				}else {
					prln("LA");
					gamesList = gamedao.readAll(1, genres, modes, languages, platforms, developers);
					
				}
			}else {
				prln("LA BAS");
				for(int i:genres) {
					prln("valeur de genres_id avant dao : " + i);
				}
				gamesList = gamedao.readAll(0, genres, modes, languages, platforms, developers);
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
			
			request.setAttribute("page", page);
			request.setAttribute("gamesInPage", gamesList.size());
			request.setCharacterEncoding("UTF-8");
			request.setAttribute("gamesList", gamesList);
			request.setAttribute("pageTitle", "Magasin");
			request.setAttribute("euro", "€");
			request.setAttribute("totalGames", totalGames);
			
			//Si pas de games dans la liste, alors retour à la première page.
			if(gamesList.size() == 0) {
				prln("po de jeu");
				response.sendRedirect("landing");
			}else {
				request.getRequestDispatcher("WEB-INF/app/store.jsp").forward(request, response);
			}

		}catch (NumberFormatException e){
			prln("Exception de nombre dans la SERVLET");
			e.printStackTrace();
			response.sendRedirect("404");
		}

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
