package initialisation;

import java.awt.Color;

/**
 * Stocke les différents paramètres de la partie.
 * @author Quentin Sauvage
 *
 */
public class Proprietes {
	public static int nbLignes;
	public static int nbColonnes;
	public static int alignes;
	public static boolean ia;
	public static boolean mode;
	public static Color joueur11;
	public static Color joueur12;
	public static Color joueur21;
	public static Color joueur22;
	
	/**
	 * Met à jour les variables du jeu.
	 * @param l Le nombre de lignes du jeu.
	 * @param c Le nombre de colonnes du jeu.
	 * @param a Le nombre de pionts à aligner.
	 * @param ordi Booléen indiquant si l'IA est activée.
	 * @param m Booléen indiquant si le mode "suicide" est activé.
	 * @param j11 La première couleur du joueur 1.
	 * @param j12 La deuxième couleur du joueur 1.
	 * @param j21 La première couleur du joueur 2.
	 * @param j22 La deuxième couleur du joueur 2.
	 */
	public static void setValeurs(int l, int c, int a, boolean ordi, boolean m, Color j11, Color j12, Color j21, Color j22) {
		nbLignes = l;
		nbColonnes = c;
		alignes = a;
		ia = ordi;
		mode = m;
		joueur11 = j11;
		joueur12 = j12;
		joueur21 = j21;
		joueur22 = j22;
	}
}
