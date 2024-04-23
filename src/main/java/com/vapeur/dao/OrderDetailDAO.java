package com.vapeur.dao;

import static com.vapeur.config.Debug.bddSays;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.vapeur.beans.OrderDetail;
import com.vapeur.config.Database;

public class OrderDetailDAO {

    public void save(OrderDetail object) {
        try {
            String query = "INSERT INTO order_details (game_id, order_id, quantity, unit_price) VALUES (?, ?, ?, ?)";
            
            try (PreparedStatement ps = Database.connexion.prepareStatement(query)) {
                ps.setInt(1, object.getGameId());
                ps.setInt(2, object.getOrderId());
                ps.setInt(3, object.getQuantity());
                ps.setFloat(4, object.getUnitPrice());

                ps.executeUpdate();
                
                String objectInfos = "Order Detail for Order ID: " + object.getOrderId();
                bddSays("create", true, 0, objectInfos);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
    
    public void saveList(ArrayList<OrderDetail> ordersList) throws DAOException {
        try {
            
            try (PreparedStatement ps = Database.connexion.prepareStatement("INSERT INTO order_details (game_id, order_id, quantity, unit_price) VALUES (?, ?, ?, ?)")) {
            	
            	for(OrderDetail od:ordersList) {
            		ps.setInt(1, od.getGameId());
                    ps.setInt(2, od.getOrderId());
                    ps.setInt(3, od.getQuantity());
                    ps.setFloat(4, od.getUnitPrice());
                    
                    ps.executeUpdate();
                    String objectInfos = "Order Detail inséré : " + od.getOrderId();
            	}
                
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new DAOException("Erreur avec l'insertion dans la BDD des détails de la commande");
        }
    }

    public ArrayList<OrderDetail> getByOrderId(int order_id) {
        ArrayList<OrderDetail> orderDetailsList = new ArrayList<>();
        try {
            PreparedStatement ps = Database.connexion.prepareStatement("SELECT * FROM order_details WHERE order_id = ?");
            ps.setInt(1, order_id);

            ResultSet resultat = ps.executeQuery();

            while (resultat.next()) {
                OrderDetail object = new OrderDetail();

                object.setGameId(resultat.getInt("game_id"));
                object.setOrderId(resultat.getInt("order_id"));
                object.setQuantity(resultat.getInt("quantity"));
                object.setUnitPrice(resultat.getFloat("unit_price"));

                orderDetailsList.add(object);
            }
            String objectInfos = "Order Detail for Order ID: " + order_id;
            bddSays("read", true, orderDetailsList.size(), objectInfos);
            return orderDetailsList;
        } catch (Exception ex) {
            ex.printStackTrace();
            bddSays("read", false, order_id, null);
            return null;
        }
    }

    public void delete(int order_id) {
        try {
            PreparedStatement ps = Database.connexion.prepareStatement("DELETE FROM order_details WHERE order_id = ?");
            ps.setInt(1, order_id);

            ps.executeUpdate();

            bddSays("delete", true, order_id, null);

        } catch (Exception ex) {
            bddSays("delete", false, order_id, null);
            ex.printStackTrace();
        }
    }
}
