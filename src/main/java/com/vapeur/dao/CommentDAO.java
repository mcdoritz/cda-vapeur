package com.vapeur.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import com.vapeur.beans.Comment;
import com.vapeur.config.Database;
import static com.vapeur.config.Debug.*;

public class CommentDAO {

    public void save(Comment object) {
        try {
            if (object.getUserId() != 0 && object.getGameId() != 0) {
                String query = "UPDATE comments SET content = ?, uploaded = ?, score = ? WHERE user_id = ? AND game_id = ?";
                
                try (PreparedStatement ps = Database.connexion.prepareStatement(query)) {
                    ps.setString(1, object.getContent());
                    ps.setTimestamp(2, object.getUploaded());
                    ps.setFloat(3, object.getScore());
                    ps.setInt(4, object.getUserId());
                    ps.setInt(5, object.getGameId());

                    ps.executeUpdate();
                }
                String objectInfos = "Comment ID: " + object.getUserId() + "-" + object.getGameId();

            } else {
                String query = "INSERT INTO comments (content, uploaded, score, user_id, game_id) VALUES (?, ?, ?, ?, ?)";
                
                try (PreparedStatement ps = Database.connexion.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {
                    ps.setString(1, object.getContent());
                    ps.setTimestamp(2, object.getUploaded());
                    ps.setFloat(3, object.getScore());
                    ps.setInt(4, object.getUserId());
                    ps.setInt(5, object.getGameId());

                    ps.executeUpdate();
                    
                    try (ResultSet generatedKeys = ps.getGeneratedKeys()) {
                        if (generatedKeys.next()) {
                            String objectInfos = "Comment ID: " + generatedKeys.getInt(1);
                            bddSays("create", true, generatedKeys.getInt(1), objectInfos);
                        } else {
                            throw new DAOException("L'insertion a échoué, aucun ID généré n'a été récupéré.");
                        }
                    }
                }
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public Comment getById(int user_id, int game_id) {
        try {
            PreparedStatement ps = Database.connexion.prepareStatement("SELECT * FROM comments WHERE user_id = ? AND game_id = ?");
            ps.setInt(1, user_id);
            ps.setInt(2, game_id);

            ResultSet resultat = ps.executeQuery();

            Comment object = new Comment();
            
            while (resultat.next()) {

                object.setContent(resultat.getString("content"));
                object.setUploaded(resultat.getTimestamp("uploaded"));
                object.setScore(resultat.getFloat("score"));
                object.setUserId(user_id);
                object.setGameId(game_id);
            }
            
            return object;

        } catch (Exception ex) {
            ex.printStackTrace();

            return null;
        }
    }
    
    public Comment getScore(int user_id, int game_id) {
        try {
            PreparedStatement ps = Database.connexion.prepareStatement("SELECT score FROM comments WHERE user_id = ? AND game_id = ?");
            ps.setInt(1, user_id);
            ps.setInt(2, game_id);

            ResultSet resultat = ps.executeQuery();

            Comment object = new Comment();
            
            while (resultat.next()) {
                object.setScore(resultat.getInt("score"));
                object.setGameId(game_id);
                object.setUserId(user_id);
            }
            
            return object;

        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }
    }
    
    public ArrayList<Comment> getByUserId(int user_id) {
        try {
            PreparedStatement ps = Database.connexion.prepareStatement("SELECT * FROM comments WHERE user_id = ?");
            ps.setInt(1, user_id);

            ResultSet resultat = ps.executeQuery();

            Comment object = new Comment();
            ArrayList<Comment> listComments = new ArrayList<>();
            
            while (resultat.next()) {
                object.setContent(resultat.getString("content"));
                object.setUploaded(resultat.getTimestamp("uploaded"));
                object.setScore(resultat.getFloat("score"));
                object.setUserId(user_id);
                object.setGameId(resultat.getInt("game_id"));
                
                listComments.add(object);
            }
            
            return listComments;

        } catch (Exception ex) {
            ex.printStackTrace();
            bddSays("read", false, user_id, null);
            return null;
        }
    }
    
    public ArrayList<Comment> getByGameId(int game_id) {
        try {
            PreparedStatement ps = Database.connexion.prepareStatement("SELECT comments.content AS content, comments.uploaded AS uploaded, comments.score AS score, comments.user_id AS user_id, users.nickname AS user_nickname FROM comments JOIN users ON comments.user_id = users.id  WHERE game_id = ? ORDER BY uploaded DESC");
            ps.setInt(1, game_id);

            ResultSet resultat = ps.executeQuery();

            
            ArrayList<Comment> listComments = new ArrayList<>();
            
            while (resultat.next()) {
            	Comment object = new Comment();
                object.setContent(resultat.getString("content"));
                object.setUploaded(resultat.getTimestamp("uploaded"));
                object.setScore(resultat.getFloat("score"));
                object.setUserId(resultat.getInt("user_id"));
                object.setGameId(game_id);
                object.setUserNickname(resultat.getString("user_nickname"));
                listComments.add(object);
            }
            
            return listComments;

        } catch (Exception ex) {
            ex.printStackTrace();
            bddSays("read", false, game_id, null);
            return null;
        }
    }
    
    public List<Comment> readAll() {
        ArrayList<Comment> commentsList = new ArrayList<>();
        String query = "SELECT * FROM comments";
        try {
            PreparedStatement ps = Database.connexion.prepareStatement(query);
            ResultSet resultat = ps.executeQuery();

            while (resultat.next()) {
                Comment object = new Comment();

                object.setContent(resultat.getString("content"));
                object.setUploaded(resultat.getTimestamp("uploaded"));
                object.setScore(resultat.getFloat("score"));
                object.setUserId(resultat.getInt("user_id"));
                object.setGameId(resultat.getInt("game_id"));

                commentsList.add(object);
            }
            bddSays("readAll", true, commentsList.size(), null);
            return commentsList;
        } catch (Exception ex) {
            bddSays("readAll", false, 0, null);
            ex.printStackTrace();
            return null;
        }
    }

    public void delete(int comment_id) {
        try {
            PreparedStatement ps = Database.connexion.prepareStatement("DELETE FROM comments WHERE id = ?");
            ps.setInt(1, comment_id);

            ps.executeUpdate();

            bddSays("delete", true, comment_id, null);

        } catch (Exception ex) {
            bddSays("delete", false, comment_id, null);
            ex.printStackTrace();
        }
    }
}
