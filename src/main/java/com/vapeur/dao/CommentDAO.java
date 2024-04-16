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
            if (object.getId() != 0) {
                String query = "UPDATE comments SET content = ?, uploaded = ?, score = ?, user_id = ? WHERE id = ?";
                
                try (PreparedStatement ps = Database.connexion.prepareStatement(query)) {
                    ps.setString(1, object.getContent());
                    ps.setTimestamp(2, object.getUploaded());
                    ps.setFloat(3, object.getScore());
                    ps.setInt(4, object.getUserId());
                    ps.setInt(5, object.getId());

                    ps.executeUpdate();
                }
                String objectInfos = "Comment ID: " + object.getId();
                bddSays("update", true, object.getId(), objectInfos);
            } else {
                String query = "INSERT INTO comments (content, uploaded, score, user_id) VALUES (?, ?, ?, ?)";
                
                try (PreparedStatement ps = Database.connexion.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {
                    ps.setString(1, object.getContent());
                    ps.setTimestamp(2, object.getUploaded());
                    ps.setFloat(3, object.getScore());
                    ps.setInt(4, object.getUserId());

                    ps.executeUpdate();
                    
                    try (ResultSet generatedKeys = ps.getGeneratedKeys()) {
                        if (generatedKeys.next()) {
                            String objectInfos = "Comment ID: " + generatedKeys.getInt(1);
                            bddSays("create", true, generatedKeys.getInt(1), objectInfos);
                        } else {
                            bddSays("create", false, object.getId(), null);
                            throw new DAOException("L'insertion a échoué, aucun ID généré n'a été récupéré.");
                        }
                    }
                }
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public Comment getById(int comment_id) {
        try {
            PreparedStatement ps = Database.connexion.prepareStatement("SELECT * FROM comments WHERE id = ?");
            ps.setInt(1, comment_id);

            ResultSet resultat = ps.executeQuery();

            Comment object = new Comment();
            
            while (resultat.next()) {
                object.setId(resultat.getInt("id"));
                object.setContent(resultat.getString("content"));
                object.setUploaded(resultat.getTimestamp("uploaded"));
                object.setScore(resultat.getFloat("score"));
                object.setUserId(resultat.getInt("user_id"));
            }
            
            String objectInfos = "Comment ID: " + object.getId();
            bddSays("read", true, object.getId(), objectInfos);
            return object;

        } catch (Exception ex) {
            ex.printStackTrace();
            bddSays("read", false, comment_id, null);
            return null;
        }
    }
    
    public Comment getByUserId(int user_id) {
        try {
            PreparedStatement ps = Database.connexion.prepareStatement("SELECT * FROM comments WHERE user_id = ?");
            ps.setInt(1, user_id);

            ResultSet resultat = ps.executeQuery();

            Comment object = new Comment();
            
            while (resultat.next()) {
                object.setId(resultat.getInt("id"));
                object.setContent(resultat.getString("content"));
                object.setUploaded(resultat.getTimestamp("uploaded"));
                object.setScore(resultat.getFloat("score"));
                object.setUserId(user_id);
            }
            
            String objectInfos = "Comment de l'user" + user_id + " ID: " + object.getId();
            bddSays("read", true, object.getId(), objectInfos);
            return object;

        } catch (Exception ex) {
            ex.printStackTrace();
            bddSays("read", false, user_id, null);
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

                object.setId(resultat.getInt("id"));
                object.setContent(resultat.getString("content"));
                object.setUploaded(resultat.getTimestamp("uploaded"));
                object.setScore(resultat.getFloat("score"));
                object.setUserId(resultat.getInt("user_id"));

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
