package com.vapeur.dao;

import static com.vapeur.config.Debug.*;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.vapeur.beans.Game;
import com.vapeur.beans.Genre;
import com.vapeur.beans.Language;
import com.vapeur.beans.Mode;
import com.vapeur.config.Database;

public class GameDAO {

    
    public void save(Game object) {
        try {
        	//Préparer les tags array->string
            String tags = "";
            ArrayList<String> arrayTags = new ArrayList<>(object.getTags());
            int i = 0;
            for(String t:arrayTags) {
            	if(i < arrayTags.size()) {
            		tags += t.toUpperCase() + " ";
            	}else {
            		tags += t.toUpperCase();
            	}
            	
            	i++;
            }
            
            if (object.getId() != 0) {
                String query = "UPDATE games SET title = ?, description = ?, classification = ?, price = ?, release_date = ?, users_avg_score = ?, total_reviews = ?, controller_support = ?, requires_3rd_party_account = ?, stock = ?, tags = ?, developer_id = ?, platform_id = ? WHERE id = ?";
                
                try (PreparedStatement ps = Database.connexion.prepareStatement(query)) {
                    ps.setString(1, object.getTitle());
                    ps.setString(2, object.getDescription());
                    ps.setInt(3, object.getClassification());
                    ps.setFloat(4, object.getPrice());
                    ps.setDate(5, new java.sql.Date(object.getReleaseDate().getTime()));
                    ps.setFloat(6, object.getUsersAvgScore());
                    ps.setInt(7, object.getTotalReviews());
                    ps.setBoolean(8, object.isControllerSupport());
                    ps.setBoolean(9, object.isRequires3rdPartyAccount());
                    ps.setInt(10, object.getStock());
                    ps.setString(11, tags);
                    ps.setInt(12, object.getDeveloperId());
                    ps.setInt(13, object.getPlatformId());
                    ps.setInt(14, object.getId());
                    ps.executeUpdate();
                }
                String objectInfos = object.getTitle();
                bddSays("update", true, object.getId(), objectInfos);
            } else {
                String query = "INSERT INTO games (title, description, classification, price, release_date, users_avg_score, total_reviews, controller_support, requires_3rd_party_account, stock, tags, developer_id, platform_id) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
                try (PreparedStatement ps = Database.connexion.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {
                    ps.setString(1, object.getTitle());
                    ps.setString(2, object.getDescription());
                    ps.setInt(3, object.getClassification());
                    ps.setFloat(4, object.getPrice());
                    ps.setDate(5, new java.sql.Date(object.getReleaseDate().getTime()));
                    ps.setFloat(6, object.getUsersAvgScore());
                    ps.setInt(7, object.getTotalReviews());
                    ps.setBoolean(8, object.isControllerSupport());
                    ps.setBoolean(9, object.isRequires3rdPartyAccount());
                    ps.setInt(10, object.getStock());
                    ps.setString(11, tags);
                    ps.setInt(12, object.getDeveloperId());
                    ps.setInt(12, object.getPlatformId());
                    ps.executeUpdate();
                    try (ResultSet generatedKeys = ps.getGeneratedKeys()) {
                        if (generatedKeys.next()) {
                            String objectInfos = object.getTitle();
                            bddSays("create", true, generatedKeys.getInt(1), objectInfos);
                        } else {
                            bddSays("create", false, object.getId(), null);
                            throw new SQLException("L'insertion a échoué, aucun ID généré n'a été récupéré.");
                        }
                    }
                }
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
    
    //Tout prendre
    public Game getById(int game_id) {
        try {
        	
            PreparedStatement ps = Database.connexion.prepareStatement("SELECT games.id, title, description, classification, price, release_date, users_avg_score, controller_support, requires_3rd_party_account, total_reviews, stock, tags, developer_id, platforms.id AS platform_id, platforms.name AS platform_name, platforms.acronym AS platform_acronym FROM games JOIN platforms ON games.platform_id = platforms.id  JOIN developers ON games.developer_id = developers.id WHERE games.id = ?;");
            ps.setInt(1, game_id);
            ResultSet resultat = ps.executeQuery();
            Game object = new Game();
            PlatformDAO platformdao = new PlatformDAO();
            ModeDAO modedao = new ModeDAO();
            GenreDAO genredao = new GenreDAO();
            LanguageDAO languagedao = new LanguageDAO();
            DeveloperDAO developerdao = new DeveloperDAO();
            
            while (resultat.next()) {
            	
            	object.setId(game_id);
                object.setTitle(resultat.getString("title"));
                object.setDescription(resultat.getString("description"));
                object.setClassification(resultat.getInt("classification"));
                object.setPrice(resultat.getFloat("price"));
                object.setReleaseDate(resultat.getDate("release_date"));
                object.setUsersAvgScore(resultat.getFloat("users_avg_score"));
                object.setTotalReviews(resultat.getInt("total_reviews"));
                object.setControllerSupport(resultat.getBoolean("controller_support"));
                object.setRequires3rdPartyAccount(resultat.getBoolean("requires_3rd_party_account"));
                object.setStock(resultat.getInt("stock"));
                
                String tags = resultat.getString("tags");
                String[] arrayTags = tags.split(" ");
                ArrayList<String> arrayListTags = new ArrayList<>();
                
                for(String t:arrayTags) {
                	arrayListTags.add(t.toUpperCase());
                }
                
                object.setTags(arrayListTags);
                
                object.setPlatform(platformdao.getById(resultat.getInt("platform_id")));
                
            	ArrayList<Mode> modesList = new ArrayList<>(modedao.readAllByGameId(game_id));
            	ArrayList<Genre> genresList = new ArrayList<>(genredao.readAllByGameId(game_id));
            	ArrayList<Language> languagesList = new ArrayList<>(languagedao.readAllByGameId(game_id));
            	
                object.setModes(modesList);
                object.setGenres(genresList);
                object.setLanguages(languagesList);
                object.setDeveloper(developerdao.getById(resultat.getInt("developer_id")));
                
            }
            String objectInfos = object.getTitle();
            bddSays("read", true, object.getId(), objectInfos);
            return object;
        } catch (Exception ex) {
            ex.printStackTrace();
            bddSays("read", false, game_id, null);
            return null;
        }
    }
    
    //Sers à lister les jeux, inutile de tout prendre donc.
    public List<Game> readAll(int page) {
        ArrayList<Game> gamesList = new ArrayList<>();
        int min;
        
        if(page < 2) {
        	min = page;
        	
        }else {
        	min = (page-1) * 12 + 1;
        }

        try {
            PreparedStatement ps = Database.connexion.prepareStatement("SELECT games.id, title, price, release_date, users_avg_score, total_reviews, stock, platforms.id AS platform_id, platforms.name AS platform_name, platforms.acronym AS platform_acronym FROM games JOIN platforms ON games.platform_id = platforms.id WHERE stock > 0 ORDER BY games.title ASC LIMIT ?,12 ");
            ps.setInt(1, min);
            ResultSet resultat = ps.executeQuery();
            
            PlatformDAO platformdao = new PlatformDAO();
            
            while (resultat.next()) {
                Game object = new Game();
                object.setId(resultat.getInt("id"));
                object.setTitle(resultat.getString("title"));
                object.setPrice(resultat.getFloat("price"));
                object.setReleaseDate(resultat.getDate("release_date"));
                object.setUsersAvgScore(resultat.getFloat("users_avg_score"));
                object.setTotalReviews(resultat.getInt("total_reviews"));
                object.setStock(resultat.getInt("stock"));
                object.setPlatform(platformdao.getById(resultat.getInt("platform_id")));
                gamesList.add(object);
            }
            
            bddSays("readAll", true, gamesList.size(), null);
            return gamesList;
        } catch (Exception ex) {
            bddSays("readAll", false, 0, null);
            ex.printStackTrace();
            return null;
        }
    }
    
    public int countAll() {
    	try {
            PreparedStatement ps = Database.connexion.prepareStatement("SELECT COUNT(*) AS total FROM games WHERE stock > 0");
            ResultSet resultat = ps.executeQuery();
            
            int total = 0;
            
            while (resultat.next()) {
                total = resultat.getInt("total");
            }
            
            return total;
        } catch (Exception ex) {
            ex.printStackTrace();
            return 0;
        }
    }

    
    public void delete(int game_id) {
        try {
            PreparedStatement ps = Database.connexion.prepareStatement("DELETE FROM games WHERE id = ?");
            ps.setInt(1, game_id);
            ps.executeUpdate();
            bddSays("delete", true, game_id, null);
        } catch (Exception ex) {
            bddSays("delete", false, game_id, null);
            ex.printStackTrace();
        }
    }
}

