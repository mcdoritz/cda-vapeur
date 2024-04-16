package com.vapeur.dao;

import static com.vapeur.config.Debug.*;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.vapeur.beans.Game;
import com.vapeur.beans.Platform;
import com.vapeur.config.Database;
import com.vapeur.dao.GameDAO;

public class GameDAO {

    
    public void save(Game object) {
        try {
            if (object.getId() != 0) {
                String query = "UPDATE games SET title = ?, description = ?, classification = ?, price = ?, release_date = ?, users_avg_score = ?, total_reviews = ?, controller_support = ?, requires_3rd_party_account = ?, stock = ?, tags = ?, developer_id = ? WHERE id = ?";
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
                    ps.setString(11, object.getTags());
                    ps.setInt(12, object.getDeveloperId());
                    ps.setInt(13, object.getId());
                    ps.executeUpdate();
                }
                String objectInfos = object.getTitle();
                bddSays("update", true, object.getId(), objectInfos);
            } else {
                String query = "INSERT INTO games (title, description, classification, price, release_date, users_avg_score, total_reviews, controller_support, requires_3rd_party_account, stock, tags, developer_id) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
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
                    ps.setString(110, object.getTags());
                    ps.setInt(12, object.getDeveloperId());
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
            PreparedStatement ps = Database.connexion.prepareStatement("SELECT * FROM games WHERE games.id = ?");
            ps.setInt(1, game_id);
            ResultSet resultat = ps.executeQuery();
            Game object = new Game();
            while (resultat.next()) {
                object.setId(resultat.getInt("id"));
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
                object.setTags(resultat.getString("tags"));
                
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
    public List<Game> readAll() {
        ArrayList<Game> gamesList = new ArrayList<>();
        String query = "SELECT games.id, title, price, release_date, users_avg_score, total_reviews, stock, platforms.id AS platform_id FROM games JOIN games_platforms ON games.id = games_platforms.game_id JOIN platforms ON games_platforms.platform_id = platforms.id ORDER BY games.id ASC";

        try {
            PreparedStatement ps = Database.connexion.prepareStatement(query);
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
                ArrayList<Platform> platforms = new ArrayList<>(); //Il y en aura forcément qu'une.
                platforms.add(platformdao.getById(resultat.getInt("platform_id"), true));
                object.setPlatforms(platforms);
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

