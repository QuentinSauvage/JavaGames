package model;

import java.util.Random;

/**
 * Définit le comportement de l'IA.
 * @author Quentin Sauvage
 *
 */
public class IA extends Joueur {
	/**
	 * Matrice indiquant à l'IA les endroits où elle a joué.
	 */
	private CaseJeu[][] coupsJoues;
	
	/**
	 * Constructeur vide de l'IA.
	 */
	public IA() {
		super();
		coupsJoues = new CaseJeu[10][10];
		for(int i = 0; i < 10; i++)
			for(int j = 0; j < 10; j++)
				coupsJoues[i][j] = new CaseJeu();
	}
	

	/**
	 * Getter de coupsJoues.
	 * @return La grille représentant les endroit où a joué l'IA.
	 */
	public CaseJeu[][] getCoupsJoues() {
		return coupsJoues;
	}

	/**
	 * Setter de coupsJoues.
	 * @param coupsJoues La nouvelle grilles de coups joués par l'IA.
	 */
	public void setCoupsJoues(CaseJeu[][] coupsJoues) {
		this.coupsJoues = coupsJoues;
	}
	
	/**
	 * Calcule l'endroit où doit jouer l'IA à partir d'un bateau touché.
	 * @param i La colonne où a été touché le bateau.
	 * @param j La ligne où a été touché le bateau.
	 * @return Un tableau indiquant si une case est libre autour du bateau touché.
	 */
	public int[] verifierDirections(int i, int j) {
		int[] tab = new int[2];
		tab[0] = tab[1] = -1;
		if(verifierHaut(i, j)) {
			tab[0] = i - 1;
			tab[1] = j;
		} else if(verifierBas(i, j)) {
			tab[0] = i + 1;
			tab[1] = j;
		} else if(verifierGauche(i, j)) {
			tab[0] = i;
			tab[1] = j - 1;
		} else if(verifierDroite(i, j)) {
			tab[0] = i;
			tab[1] = j + 1;
		}
		return tab;
	}
	
	/**
	 * Vérifie si la case à gauche est libre.
	 * @param i La colonne où a été touché le bateau.
	 * @param j La ligne où a été touché le bateau.
	 * @return Booléen qui indique si la case à gauche n'a pas été jouée.
	 */
	public boolean verifierGauche(int i, int j) {
		if(i == 0)
			return false;
		return coupsJoues[i - 1][j].getValue() == 0;
	}
	
	/**
	 * Vérifie si la case à droite est libre.
	 * @param i La colonne où a été touché le bateau.
	 * @param j La ligne où a été touché le bateau.
	 * @return Booléen qui indique si la case à droite n'a pas été jouée.
	 */
	public boolean verifierDroite(int i, int j) {
		if(i == 9)
			return false;
		return coupsJoues[i + 1][j].getValue() == 0;
	}
	
	/**
	 * Vérifie si la case en haut est libre.
	 * @param i La colonne où a été touché le bateau.
	 * @param j La ligne où a été touché le bateau.
	 * @return Booléen qui indique si la case en haut n'a pas été jouée.
	 */
	public boolean verifierHaut(int i, int j) {
		if(j == 0)
			return false;
		return coupsJoues[i][j - 1].getValue() == 0;
	}

	/**
	 * Vérifie si la case en bas est libre.
	 * @param i La colonne où a été touché le bateau.
	 * @param j La ligne où a été touché le bateau.
	 * @return Booléen qui indique si la case en bas n'a pas été jouée.
	 */
	public boolean verifierBas(int i, int j) {
		if(j == 9)
			return false;
		return coupsJoues[i][j + 1].getValue() == 0;
	}
	
	/**
	 * Calcule l'endroit où l'IA doit jouer.
	 * @return Un couple de valeur représentant les coordonnées du prochain coup.
	 */
	public int[] jouer() {
		int[] tab = new int[2];
		for(int i = 0; i < 10; i++) {
			for(int j = 0; j < 10; j++) {
				if(coupsJoues[i][j].getValue() == 1) {
					tab = verifierDirections(i, j);
					if(tab[0] != -1 && tab[1] != -1) {
						return tab;
					}
				}
			}
		}
		Random rd = new Random();
		boolean ok = false;
		while(!ok) {
			tab[0] = rd.nextInt(10);
			tab[1] = rd.nextInt(10);
			if(coupsJoues[tab[0]][tab[1]].getValue() == 0)
				ok = true;
		}
		return tab;
	}
}
