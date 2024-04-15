package beans;

import java.io.Serializable;
import java.sql.Date;

public class Order implements Serializable {
    private static final long serialVersionUID = 1L;
    
    private int id;
    private Date date;
    private int userId;

    // Constructeurs
    public Order() {
    }

    public Order(int id, Date date, int userId) {
        setId(id);
        setDate(date);
        setUserId(userId);
    }

    // Getters et setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    // Méthode toString pour l'affichage
    @Override
    public String toString() {
        return "Order{" +
                "id=" + id +
                ", date=" + date +
                ", userId=" + userId +
                '}';
    }
}
