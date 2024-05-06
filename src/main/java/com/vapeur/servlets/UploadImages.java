package com.vapeur.servlets;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;
import static com.vapeur.config.Debug.*;

/**
 * Servlet implementation class UploadImages : permet de créer un dossier pour les images du jeu quand le back office fait des uploads et enregistrer les images
 * Cette servlet est donc une API 
 * 
 * 
 * Je la laisse la. Mais j'ai galéré et après une demi-journée entière passée dessus alors que tout le reste est terminé, j'ai préféré laisser tomber et garder
 * le système peu efficace d'avoir obligatoirement besoin des 2 serveurs lancés en même temps, car les images utilisées par le front restent sur le serveur back.
 * Avec la servlet je voulais que le back upload les images sur le front, mais pas trouvé et pas le temps. Cet ECF m'a pris beaucoup trop de temps il faut que
 * j'avance sur mes dossiers.
 * 
 * 
 * 
 *  /createFolder est un endpoint
 */
@WebServlet("/uploadImages")
public class UploadImages extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UploadImages() {
        super();
        // TODO Auto-generated constructor stub
    }


	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Récupérer les données de la demande
        BufferedReader reader = request.getReader();
        StringBuilder requestData = new StringBuilder();
        String line;
        while ((line = reader.readLine()) != null) {
            requestData.append(line);
        }
        JSONObject jsonObject = new JSONObject(requestData.toString());
        
        // Récupérer le nom du dossier à créer à partir des données de la demande
        String folderName = jsonObject.getString("folderName");
        prln("FrontOffice : " + folderName);
        
        // Chemin où le dossier sera créé
        String absolutePath = getServletContext().getRealPath(folderName);
        File folder = new File(absolutePath);
        
        // Vérifier si le dossier existe déjà
        if (!folder.exists()) {
            // Créer le dossier
            if (folder.mkdirs()) {
                response.setStatus(HttpServletResponse.SC_OK);
                prln("Frontoffice : Dossier créé avec succès : " + folder.getAbsolutePath());
            } else {
                response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
                prln("Frontoffice : Impossible de créer le dossier : " + folder.getAbsolutePath());
            }
        } else {
            response.setStatus(HttpServletResponse.SC_CONFLICT);
            prln("Frontoffice : Le dossier existe déjà : " + folder.getAbsolutePath());
        }
    }

}
