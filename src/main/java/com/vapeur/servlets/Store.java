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

    public Store() {
        super();
        // TODO Auto-generated constructor stub
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession(false);
		Database.connect();
		
		List<Game> gamesList = new ArrayList<>();
		
		
		int totalGames = gamedao.countAll();
		prln(totalGames + " games dans la bdd");
		
		int page = 0;
		
		try {
			
			if(request.getParameter("page") != null) {
				page = Integer.parseInt(request.getParameter("page"));
				if(page > 0) {
					gamesList = gamedao.readAll(page, genres, modes, languages, platforms, developers);
					
				}else {
					gamesList = gamedao.readAll(1, genres, modes, languages, platforms, developers);
					
				}
			}else {
				gamesList = gamedao.readAll(1, genres, modes, languages, platforms, developers);
			}
			
			// Préparation des options pour les filtres
			
			
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
				response.sendRedirect("store");
			}else {
				request.getRequestDispatcher("WEB-INF/app/store.jsp").forward(request, response);
			}

		}catch (NumberFormatException e){
			response.sendRedirect("404");
		}

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		prln("doPost Store");
		
		String[] genresSelected = request.getParameterValues("genres");
		String[] modesSelected = request.getParameterValues("modes");
		String[] languagesSelected = request.getParameterValues("languages");
		String[] platformsSelected = request.getParameterValues("platforms");
		String[] developersSelected = request.getParameterValues("developers");
		
		// -- Faire ça plutôt côté DAO ?  :
		
		ArrayList<Integer> genresFilter = new ArrayList<>();
		ArrayList<Integer> modesFilter = new ArrayList<>();
		ArrayList<Integer> languagesFilter = new ArrayList<>();
		ArrayList<Integer> platformsFilter = new ArrayList<>();
		ArrayList<Integer> developersFilter = new ArrayList<>();
		
		if(genresSelected != null) {
			for(String str:genresSelected) {
				genresFilter.add(Integer.parseInt(str));
			}
		}
		
		if(modesSelected != null) {
			for(String str:modesSelected) {
				modesFilter.add(Integer.parseInt(str));
			}
		}
		
		
		if(languagesSelected != null) {
			for(String str:languagesSelected) {
				languagesFilter.add(Integer.parseInt(str));
			}
		}
		
		
		
		if(platformsSelected != null) {
			for(String str:platformsSelected) {
				platformsFilter.add(Integer.parseInt(str));
			}
		}
		
		
		
		if(developersSelected != null) {
			for(String str:developersSelected) {
				developersFilter.add(Integer.parseInt(str));
			}
		}
        for(int g:genresFilter) {
    		prln("Servlet valeur de  g : " + g);
    	}
		
        List<Game> gamesList = new ArrayList<>();
		gamesList = gamedao.readAll(1, genresFilter, modesFilter, languagesFilter, platformsFilter, developersFilter);
		
		request.setAttribute("gamesInPage", gamesList.size());
		request.setCharacterEncoding("UTF-8");
		request.setAttribute("gamesList", gamesList);
		
		prln("Taille liste filtrée : " + gamesList.size());
		
		request.setAttribute("genresChecked", genresFilter);
		request.setAttribute("modesChecked", modesFilter);
		request.setAttribute("languagesChecked", languagesFilter);
		request.setAttribute("platformsChecked", platformsFilter);
		request.setAttribute("developersChecked", developersFilter);
		request.setAttribute("pageTitle", "Magasin");
		request.getRequestDispatcher("WEB-INF/app/store.jsp").forward(request, response);
	}

}
