package model;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

import view.Observer;

/**
 * Contient tous les attributs et méthodes permettant de manipuler la création d'une grille.
 * @author Quentin Sauvage
 *
 */
public class CreationModel extends AbstractModel {

	/**
	 * Constructeur de CreationModel.
	 * @param h La hauteur de la grille à créer.
	 * @param l La largeur de la grille à créer.
	 */
	public CreationModel(int h, int l) {
		hauteur = h;
		largeur = l;
		grilleJeu = new int[hauteur][largeur];
	}
	
	/**
	 * Enregistre la grille dans un nouveau fichier.
	 * @param str Le préfixe du fichier à créer.
	 */
	public void enregistrer(String str) {
		File f = new File(str);
		FileWriter fw;
		try {
			fw = new FileWriter(f);
			PrintWriter pw = new PrintWriter(fw);
			pw.println(largeur);
			pw.println(hauteur);
			String s;
			for(int i = 0; i < hauteur; i++) {
				s = "";
				for(int j = 0; j < largeur; j++) {
					s += grilleJeu[i][j];
				}
				pw.println(s);
			}
			pw.close();
			fw.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Override
	public void notifyObserver(int x, int y, int typeClic, String str) {
		for(Observer obs : listObserver)
			obs.update(x, y, typeClic, str);
	}

	@Override
	public void notifyObserver(String str) {
		for(Observer obs : listObserver)
			obs.update(str);
	}

}
