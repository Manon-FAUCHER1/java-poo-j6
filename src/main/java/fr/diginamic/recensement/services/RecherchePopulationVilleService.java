package fr.diginamic.recensement.services;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.Scanner;

import com.mysql.cj.jdbc.Driver;

import fr.diginamic.recensement.IntegrationRecensement;
import fr.diginamic.recensement.entites.Recensement;
import fr.diginamic.recensement.entites.Ville;
import fr.diginamic.recensement.exceptions.ExceptionMessage;
import fr.diginamic.recensement.props.DbMgr;


/**
 * Recherche et affichage de la population d'une ville
 * 
 * @author DIGINAMIC
 *
 */
public class RecherchePopulationVilleService extends MenuService {
	
	@Override
	public void traiter(Scanner scanner) throws ExceptionMessage {
		
		Connection connection = DbMgr.connection();
		
		try {
			System.out.println("Quel est le nom de la ville recherchée ? ");
			String choix = scanner.nextLine();
			
			Statement statement = connection.createStatement();
			
			ResultSet curseur = statement.executeQuery("SELECT code_ville, nom_ville, population_ville FROM ville WHERE nom_ville = " + "'" + choix + "'");
			
			Ville villeSelect = null;
			
			while (curseur.next()) {
				int codeVlle = curseur.getInt("code_ville");
				String nomVille = curseur.getString("nom_ville");
				int pop = curseur.getInt("population_ville");
				
				villeSelect = new Ville(codeVlle, nomVille, pop);
			}
			
			System.out.println(villeSelect);
			
			
		} catch (SQLException e) {
			throw new ExceptionMessage("Une erreur c'est produite lors de la recherche de la ville \n" + e.getMessage());
		}
		
		

	
	}

}
