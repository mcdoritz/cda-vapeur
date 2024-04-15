package com.vapeur.dao;

import java.util.List;

import com.vapeur.beans.User;

public interface UserDAO {
	
	// CRUDités ----------------------
	
	// CREATE / UPDATE
	void save(User objet);
	
	// READ
	User read(int user_id);
	List<User> readAll(String status);
	
	// DELETE
	void delete(int user_id);
	

}
