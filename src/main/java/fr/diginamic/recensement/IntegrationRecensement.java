/**
 * 
 */
package fr.diginamic.recensement;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.List;
import java.util.ResourceBundle;

import com.mysql.cj.jdbc.Driver;

import fr.diginamic.recensement.props.DbMgr;




/**
 * @author manon
 *
 */
public class IntegrationRecensement {


	/**
	 * @param args
	 * @throws IOException
	 */
	public static void main(String[] args) throws IOException {

		try {
			Connection connection = DbMgr.connection();
			Statement statement = connection.createStatement();

			// Recupération des donner dans le fichier
			Path filePath = Paths.get("C:\\Users\\manon\\Documents\\2 - PROFESSIONNEL\\1 - FORMATIONS\\1 - DIGINAMIC\\2 - COURS & TP\\Workspace java\\java-poo-j6\\src\\main\\resources\\recensement.csv");
			List<String> lignesFichier = Files.readAllLines(filePath, StandardCharsets.UTF_8);

			if (lignesFichier == null) {
				System.out.println("L'application doit s'arrétée en raison d'une erreur d'exécution.");
				System.exit(-1);
			}

			for (String ligne : lignesFichier) {
				String[] colonne = ligne.split(";");

				if (colonne[0].matches("\\d+")) {

					int codeRegion 			= Integer.parseInt(colonne[0]);
					String nomRegion 		= colonne[1];
					
					String codeDepartement  = colonne[2];
					
					int codeVille 			= Integer.parseInt(colonne[5]);
					String nomVille  		= colonne[6].replaceAll("'", "`");
					
					int population 			= Integer.parseInt(colonne[9].replace(" ", ""));
					
					PreparedStatement stmtInsertVille = connection.prepareStatement(
							"INSERT INTO ville( code_ville, nom_ville, population_ville) "
							+ "VALUES(?,?,?)");

					stmtInsertVille.setInt(1, codeVille);
					stmtInsertVille.setString(2, nomVille);
					stmtInsertVille.setInt(3, population);
					
					stmtInsertVille.executeUpdate();					
				
				}
			}
			
			statement.close();
			connection.close();

		} catch (Exception e) {
			System.out.println("Impossible de se connecter a la base de données \n" + e.getMessage());
		}

	}
	

}
