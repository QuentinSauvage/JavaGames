package model;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import view.Observer;

/**
 * Contient tous les attributs et méthodes permettant de manipuler la découverte d'une grille.
 * @author Quentin Sauvage
 *
 */
public class GrilleModel extends AbstractModel {

	/**
	 * Constructeur de GrilleModel
	 * @param filename Le nom du fichier contenant la grille à découvrir.
	 */
	public GrilleModel(String filename) {
		recupererGrille(filename);
	}
	
	/**
	 * Cherche dans l'architecture le fichier contenant la grille à découvrir, et en extrait les valeurs.
	 * @param filename Le nom du fichier contenant la grille.
	 */
	public void recupererGrille(String filename) {
		File f = new File(filename);
		String chaine;
		int i, j = 0;
		if(f.exists()) {
			try {
				FileReader fr = new FileReader(f);
				BufferedReader br = new BufferedReader(fr);
				largeur = Integer.parseInt(br.readLine());
				hauteur = Integer.parseInt(br.readLine());
				grilleObjectif = new int[hauteur][largeur];
				grilleJeu = new int[hauteur][largeur];
				chaine = br.readLine();
				while(chaine != null) {
					for(i = 0; i < chaine.length(); i++) {
						grilleObjectif[j][i] = Character.getNumericValue(chaine.charAt(i));
					}
					chaine = br.readLine();
					j++;
				}
				br.close();
				fr.close();
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		genererLignes();
		genererColonnes();
	}
	
	/**
	 * Génère les String définissant les cases noires de chaque ligne à partir de la grille obtenue grâce au fichier.
	 */
	public void genererLignes() {
		int cpt;
		String str;
		for(int i = 0; i < hauteur; i++) {
			cpt = 0;
			str = "";
			for(int j = 0; j < largeur; j++) {
				if(grilleObjectif[i][j] == 1)
					cpt++;
				else {
					if(cpt != 0) {
						str += cpt + "-";
						cpt = 0;
					}
				}
			}
			if(cpt != 0) {
				str += cpt;
				lignes.add(str);
			}
			else {
				if(str.length() > 1) {
					lignes.add(str.substring(0, str.length() - 1));
				}
			}
		}
	}
	
	/**
	 * Génère les String définissant les cases noires de chaque colonne à partir de la grille obtenue grâce au fichier.
	 */
	public void genererColonnes() {
		int cpt;
		String str;
		for(int i = 0; i < largeur; i++) {
			cpt = 0;
			str = "<html>";
			for(int j = 0; j < hauteur; j++) {
				if(grilleObjectif[j][i] == 1)
					cpt++;
				else {
					if(cpt != 0) {
						str += cpt + "<br>";
						cpt = 0;
					}
				}
			}
			str += cpt + "</html>";
			colonnes.add(str);
		}
	}
	
	/**
	 * Indique si la grille dessinée par le joueur est la même que celle contenue dans le fichier.
	 * @return booléen indiquant si le joueur a gagné.
	 */
	public boolean gagner() {
		for(int i = 0; i < hauteur; i++) {
			for(int j = 0; j < largeur; j++) {
				if(grilleObjectif[i][j] != grilleJeu[i][j])
					return false;
			}
		}
		return true;
	}
	
	@Override
	public void notifyObserver(int x, int y, int typeClic, String str) {
		for(Observer obs : listObserver)
			obs.update(x, y, typeClic, str);
	}

	@Override
	public void notifyObserver(String str) {
		// TODO Auto-generated method stub
		
	}

}
