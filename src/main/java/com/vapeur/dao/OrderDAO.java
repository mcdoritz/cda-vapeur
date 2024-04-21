package com.vapeur.dao;

import static com.vapeur.config.Debug.bddSays;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.vapeur.beans.Order;
import com.vapeur.config.Database;

public class OrderDAO {

    public int save(Order object) throws DAOException {
        try {
            if (object.getId() != 0) {
                String query = "UPDATE orders SET date = ?, user_id = ? WHERE id = ?";
                
                try (PreparedStatement ps = Database.connexion.prepareStatement(query)) {
                    ps.setDate(1, new java.sql.Date(object.getDate().getTime()));
                    ps.setInt(2, object.getUserId());
                    ps.setInt(3, object.getId());

                    ps.executeUpdate();
                }
                String objectInfos = "Order ID: " + object.getId();
                bddSays("update", true, object.getId(), objectInfos);
                return object.getId();
            } else {
                String query = "INSERT INTO orders (date, user_id) VALUES (?, ?)";
                
                try (PreparedStatement ps = Database.connexion.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {
                    ps.setDate(1, new java.sql.Date(object.getDate().getTime()));
                    ps.setInt(2, object.getUserId());

                    ps.executeUpdate();
                    
                    try (ResultSet generatedKeys = ps.getGeneratedKeys()) {
                        if (generatedKeys.next()) {
                        	int id = generatedKeys.getInt(1);
                            String objectInfos = "Order ID: " + id;
                            bddSays("create", true, id, objectInfos);
                            return id;
                        } else {
                            bddSays("create", false, object.getId(), null);
                            throw new DAOException("Erreur avec la BDD, contactez le service client.");
                        }
                    }
                }
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new DAOException("Erreur avec la bdd");
        }
		
    }

    public Order getById(int order_id) {
        try {
            PreparedStatement ps = Database.connexion.prepareStatement("SELECT * FROM orders WHERE id = ?");
            ps.setInt(1, order_id);

            ResultSet resultat = ps.executeQuery();

            Order object = new Order();
            
            while (resultat.next()) {
                object.setId(resultat.getInt("id"));
                object.setDate(resultat.getDate("date"));
                object.setUserId(resultat.getInt("user_id"));
            }
            
            String objectInfos = "Order ID: " + object.getId();
            bddSays("read", true, object.getId(), objectInfos);
            return object;

        } catch (Exception ex) {
            ex.printStackTrace();
            bddSays("read", false, order_id, null);
            return null;
        }
    }
    
    public List<Order> readAll() {
        ArrayList<Order> ordersList = new ArrayList<>();
        String query = "SELECT * FROM orders";
        try {
            PreparedStatement ps = Database.connexion.prepareStatement(query);
            ResultSet resultat = ps.executeQuery();

            while (resultat.next()) {
                Order object = new Order();

                object.setId(resultat.getInt("id"));
                object.setDate(resultat.getDate("date"));
                object.setUserId(resultat.getInt("user_id"));

                ordersList.add(object);
            }
            bddSays("readAll", true, ordersList.size(), null);
            return ordersList;
        } catch (Exception ex) {
            bddSays("readAll", false, 0, null);
            ex.printStackTrace();
            return null;
        }
    }

    public void delete(int order_id) {
        try {
            PreparedStatement ps = Database.connexion.prepareStatement("DELETE FROM orders WHERE id = ?");
            ps.setInt(1, order_id);

            ps.executeUpdate();

            bddSays("delete", true, order_id, null);

        } catch (Exception ex) {
            bddSays("delete", false, order_id, null);
            ex.printStackTrace();
        }
    }
}
