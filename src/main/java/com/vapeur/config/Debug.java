package com.vapeur.config;

public class Debug {
	
	public static void prln(String message) {
		System.out.println(message);
	}
	
	public static void pr(String message) {
		System.out.print(message);
	}
	
	public static void bddSays(String crud, Boolean status, int objectId, String objectInfos) {
		switch (crud){
			case "create":
				if(status) {
					prln("Objet " + objectId + " " + objectInfos + " a été créé dans la BDD");
				}else {
					prln("Objet n'a PAS été créé dans la BDD");
				}
			break;
			
			case "read":
				if(status) {
					prln("Objet " + objectId + " " + objectInfos + " a été trouvé dans la BDD");
				}else {
					prln("Objet " + objectId + " n'a PAS été trouvé dans la BDD");
				}
			break;
			
			case "readAll":
				if(status) {
					prln("Une liste de " + objectId + " objets a été trouvé dans la BDD");
				}else {
					prln("Aucun objet trouvé dans la BDD");
				}
			break;
			
			case "update":
				if(status) {
					prln("Objet " + objectId + " " + objectInfos + " a été mis à jour dans la BDD");
				}else {
					prln("Objet " + objectId + " n'a PAS été mis à jour dans la BDD");
				}
			break;
			
			case "delete":
				if(status) {
					prln("Objet " + objectId + " a été supprimé de la BDD");
				}else {
					prln("Objet " + objectId + " n'a PAS été supprimé de la BDD");
				}
			break;
			
		}
		
		
		
	}

}
