package com.vapeur.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.vapeur.beans.Genre;
import com.vapeur.beans.Mode;
import com.vapeur.config.Database;

import static com.vapeur.config.Debug.*;

public class GenreDAO {

    public void save(Genre object) {
        try {
            if (object.getId() != 0) {
                String query = "UPDATE genres SET name = ? WHERE id = ?";
                
                try (PreparedStatement ps = Database.connexion.prepareStatement(query)) {
                    ps.setString(1, object.getName());
                    ps.setInt(2, object.getId());

                    ps.executeUpdate();
                }
                String objectInfos = "Genre ID: " + object.getId();
                bddSays("update", true, object.getId(), objectInfos);
            } else {
                String query = "INSERT INTO genres (name) VALUES (?)";
                
                try (PreparedStatement ps = Database.connexion.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {
                    ps.setString(1, object.getName());

                    ps.executeUpdate();
                    
                    try (ResultSet generatedKeys = ps.getGeneratedKeys()) {
                        if (generatedKeys.next()) {
                            String objectInfos = "Genre ID: " + generatedKeys.getInt(1);
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

    public Genre getById(int genre_id) {
        try {
            PreparedStatement ps = Database.connexion.prepareStatement("SELECT * FROM genres WHERE id = ?");
            ps.setInt(1, genre_id);

            ResultSet resultat = ps.executeQuery();

            Genre object = new Genre();
            
            while (resultat.next()) {
                object.setId(resultat.getInt("id"));
                object.setName(resultat.getString("name"));
            }
            
            String objectInfos = "Genre ID: " + object.getId();
            bddSays("read", true, object.getId(), objectInfos);
            return object;

        } catch (Exception ex) {
            ex.printStackTrace();
            bddSays("read", false, genre_id, null);
            return null;
        }
    }
    
    public List<Genre> readAll() {
        ArrayList<Genre> genresList = new ArrayList<>();
        String query = "SELECT * FROM genres";
        try {
            PreparedStatement ps = Database.connexion.prepareStatement(query);
            ResultSet resultat = ps.executeQuery();

            while (resultat.next()) {
                Genre object = new Genre();

                object.setId(resultat.getInt("id"));
                object.setName(resultat.getString("name"));

                genresList.add(object);
            }
            bddSays("readAll", true, genresList.size(), null);
            return genresList;
        } catch (Exception ex) {
            bddSays("readAll", false, 0, null);
            ex.printStackTrace();
            return null;
        }
    }
    
    public List<Genre> readAllByGameId(int game_id) {
        ArrayList<Genre> genresList = new ArrayList<>();
        String query = "SELECT genres.id AS genre_id, genres.name AS genre_name FROM games_genres JOIN genres ON games_genres.genre_id = genres.id WHERE game_id = ?";
        try {
            PreparedStatement ps = Database.connexion.prepareStatement(query);
            ps.setInt(1, game_id);
            ResultSet resultat = ps.executeQuery();

            while (resultat.next()) {
                Genre object = new Genre();

                object.setId(resultat.getInt("genre_id"));
                object.setName(resultat.getString("genre_name"));

                genresList.add(object);
            }
            bddSays("readAll", true, genresList.size(), null);
            return genresList;
        } catch (Exception ex) {
            bddSays("readAll", false, 0, null);
            ex.printStackTrace();
            return null;
        }
    }

    public void delete(int genre_id) {
        try {
            PreparedStatement ps = Database.connexion.prepareStatement("DELETE FROM genres WHERE id = ?");
            ps.setInt(1, genre_id);

            ps.executeUpdate();

            bddSays("delete", true, genre_id, null);

        } catch (Exception ex) {
            bddSays("delete", false, genre_id, null);
            ex.printStackTrace();
        }
    }
}