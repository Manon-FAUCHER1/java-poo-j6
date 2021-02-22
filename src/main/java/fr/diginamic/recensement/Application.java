package fr.diginamic.recensement;

import java.sql.DriverManager;
import java.util.Scanner;

import com.mysql.cj.jdbc.Driver;

import fr.diginamic.recensement.entites.Recensement;
import fr.diginamic.recensement.exceptions.ExceptionMessage;
import fr.diginamic.recensement.services.RechercheDepartementsPlusPeuplees;
import fr.diginamic.recensement.services.RecherchePopulationBorneService;
import fr.diginamic.recensement.services.RecherchePopulationDepartementService;
import fr.diginamic.recensement.services.RecherchePopulationRegionService;
import fr.diginamic.recensement.services.RecherchePopulationVilleService;
import fr.diginamic.recensement.services.RechercheRegionsPlusPeuplees;
import fr.diginamic.recensement.services.RechercheVillesPlusPeupleesDepartement;
import fr.diginamic.recensement.services.RechercheVillesPlusPeupleesFrance;
import fr.diginamic.recensement.services.RechercheVillesPlusPeupleesRegion;
import fr.diginamic.recensement.utils.RecensementUtils;

/**
 * Application de traitement des données de recensement de population
 * 
 * @param args
 */
public class Application {
	
	/**
	 * Point d'entrée
	 * 
	 * @param args arguments (non utilisés ici)
	 */
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		
		// Menu
		int choix = 0;
		do {

			// Affichage du menu
			afficherMenu();

			// Poser une question à l'utilisateur
			String choixMenu = scanner.nextLine();

			// Conversion du choix utilisateur en int
			choix = Integer.parseInt(choixMenu);

			// On exécute l'option correspondant au choix de l'utilisateur
			try {

				switch (choix) {
				case 1:
					RecherchePopulationVilleService rechercheVille = new RecherchePopulationVilleService();
					rechercheVille.traiter(scanner);
					break;
//				case 2:
//					RecherchePopulationDepartementService rechercheDept = new RecherchePopulationDepartementService();
//					rechercheDept.traiter(scanner);
//					break;
//				case 3:
//					RecherchePopulationRegionService rechercheRegion = new RecherchePopulationRegionService();
//					rechercheRegion.traiter(scanner);
//					break;
//				case 4:
//					RecherchePopulationBorneService recherchePopBorne = new RecherchePopulationBorneService();
//					recherchePopBorne.traiter(scanner);
//					break;
//				case 5:
//					RechercheVillesPlusPeupleesDepartement rechercheVillesPlusPeupleesDepartement = new RechercheVillesPlusPeupleesDepartement();
//					rechercheVillesPlusPeupleesDepartement.traiter(scanner);
//					break;
//				case 6:
//					RechercheVillesPlusPeupleesRegion rechercheVillesPlusPeupleesRegion = new RechercheVillesPlusPeupleesRegion();
//					rechercheVillesPlusPeupleesRegion.traiter(scanner);
//					break;
//				case 7:
//					RechercheDepartementsPlusPeuplees rechercherDepartementsPlusPeuplees = new RechercheDepartementsPlusPeuplees();
//					rechercherDepartementsPlusPeuplees.traiter(scanner);
//					break;
//				case 8:
//					RechercheRegionsPlusPeuplees rechercheRegionsPlusPeuplees = new RechercheRegionsPlusPeuplees();
//					rechercheRegionsPlusPeuplees.traiter(scanner);
//					break;
//				case 9:
//					RechercheVillesPlusPeupleesFrance rechercheVillesPlusPeupleesFrance = new RechercheVillesPlusPeupleesFrance();
//					rechercheVillesPlusPeupleesFrance.traiter(scanner);
//					break;
				}

			} catch (ExceptionMessage e) {
				System.err.println(e.getMessage());
			}
		} while (choix != 99);

		scanner.close();

	}

	/**
	 * Affichage du menu
	 */
	private static void afficherMenu() {
		System.out.println("***** Recensement population *****");
		System.out.println("1. Rechercher la population d'une ville");
		System.out.println("2. Rechercher la population d'un département");
		System.out.println("3. Rechercher la population d'une région");
		System.out.println("4. Rechercher la population des villes d'un département entre un min et un max");
		System.out.println("5. Rechercher les N plus grandes villes d'un département.");
		System.out.println("6. Rechercher les N plus grandes villes d'une région.");
		System.out.println("7. Rechercher les N plus grands départements de France.");
		System.out.println("8. Rechercher les N plus grandes régions de France.");
		System.out.println("9. Rechercher les N plus grandes villes de France.");
		System.out.println("99. Sortir");
	}
}






