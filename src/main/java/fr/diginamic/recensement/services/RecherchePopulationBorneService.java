package fr.diginamic.recensement.services;

import java.util.List;
import java.util.Scanner;

import org.apache.commons.lang3.math.NumberUtils;

import fr.diginamic.recensement.entites.Recensement;
import fr.diginamic.recensement.entites.Ville;
import fr.diginamic.recensement.exceptions.ExceptionMessage;

/**
 * Recherche et affichage de toutes les villes d'un département dont la
 * population est comprise entre une valeur min et une valeur max renseignées
 * par l'utilisateur.
 * 
 * @author DIGINAMIC
 *
 */
public class RecherchePopulationBorneService extends MenuService {

	@Override
	public void traiter(Recensement rec, Scanner scanner) throws ExceptionMessage {

		System.out.println("Quel est le code du département recherché ? ");
		String choix = scanner.nextLine();
		

		System.out.println("Choississez une population minimum (en milliers d'habitants): ");
		String saisieMin = scanner.nextLine();
		if (!NumberUtils.isDigits(saisieMin) || saisieMin.contains(".")) {
			 throw new ExceptionMessage("Il faut saisir un nombre entier (positif) !");
		}
		
		System.out.println("Choississez une population maximum (en milliers d'habitants): ");
		String saisieMax = scanner.nextLine();
		if (!NumberUtils.isCreatable(saisieMax) || saisieMax.contains(".")) {
			 throw new ExceptionMessage("Il faut saisir un nombre entier !");
		} 

		int min = Integer.parseInt(saisieMin) * 1000;
		int max = Integer.parseInt(saisieMax) * 1000;
		
		if (min < 0 || max < 0 || min > max) {
			throw new ExceptionMessage("Il faut saisir un nombre entier et positif !");
		}
		
		Boolean trouver = false;
		
		List<Ville> villes = rec.getVilles();
		for (Ville ville : villes) {
			if (ville.getCodeDepartement().equalsIgnoreCase(choix)) {
				trouver = true;
				if (ville.getPopulation() >= min && ville.getPopulation() <= max) {
					System.out.println(ville);
				}
			}
		}
		
		if (!trouver) {
			throw new ExceptionMessage("Le code département saisie n'est pas correcte.");
		}
		
	}

}
