package model;

import initialisation.Proprietes;
import observer.*;

/**
 * Contient les données du jeu.
 * @author Quentin Sauvage
 *
 */
public class Puissance4 extends AbstractModel {
	
	/**
	 * Constructeur de Puissance4 à partir des attributs de Proprietes.
	 * @see Proprietes
	 */
	public Puissance4() {
		tour = 1;
		plateau = new int[Proprietes.nbColonnes][Proprietes.nbLignes];
		colonnes = new int[Proprietes.nbColonnes];
		poses = Proprietes.nbLignes * Proprietes.nbColonnes - 1;
	}
	
	/**
	 * Permet d'accéder au tour actuel (1 ou 2).
	 * @return Le tour actuel.
	 */
	public int getTour() {
		return tour;
	}
	
	/**
	 * Permet de passer au tour suivant.
	 * @param t Le nouveau tour.
	 */
	public void setTour(int t) {
		tour = (tour % 2) + t;
	}
	
	/**
	 * Permet d'accéder au nombre de pions posés dans la grille.
	 * @return Le nombre de pions posés pour le moment dans la grille.
	 */
	public int getPoses() {
		return poses;
	}

	/**
	 * Modifie le nombre de pions posés dans la grille.
	 * @param poses Le nouveau nombre de pions posés.
	 */
	public void setPoses(int poses) {
		this.poses = poses;
	}
	
	/**
	 * Permet d'accéder au plateau (c'est-à-dire la matrice d'entiers représentant la grille).
	 * @return La matrice de pions.
	 */
	public int[][] getPlateau() {
		return plateau;
	}
	
	/**
	 * Modifie le plateau actuel avec un nouveau plateau.
	 * @param p Le nouveau plateau.
	 */
	public void setPlateau(int[][] p) {
		plateau = p;
	}
	
	/**
	 * Permet d'accéder à la liste des colonnes du jeu.
	 * @return Les colonnes contenant le nombre de pions posés.
	 */
	public int[] getColonnes() {
		return colonnes;
	}
	
	/**
	 * Incrémente le nombre de pions dans la colonne indice.
	 * @param indice La colonne à modifier.
	 */
	public void setColonne(int indice) {
		colonnes[indice]++;
	}
	
	/**
	 * Ajoute un pion dans la grille aux coordonnées indiquées.
	 * @param x L'abscisse du pion à insérer.
	 * @param c L'ordonnée du pion à insérer.
	 */
	public void addPion(int x, int c) {
		plateau[x][c] = tour;
	}
	
	/**
	 * Vérifie si X pions sont horizontalement alignés., X étant le nombre de pions à aligner.
	 * @return Vrai si X pions sont horizontalement alignés, faux sinon.
	 * @see Proprietes
	 */
	public boolean alignLignes() {
		int sum, cpt;
		for(int i = 0; i < Proprietes.nbColonnes; i++) {
			for(int j = 0; j < Proprietes.nbLignes; j++) {
				if(plateau[i][j] != 0) {
					sum = 0;
					cpt = 0;
					while(cpt < Proprietes.alignes) {
						if(i + cpt < Proprietes.nbColonnes) {
							if(plateau[i + cpt][j] == plateau[i][j])
								sum += plateau[i + cpt][j];
						}
						cpt++;
					}
					if(sum == Proprietes.alignes * plateau[i][j]) {
						return true;
					}
				}
			}
		}
		return false;
	}
	
	/**
	 * Vérifie si X pions sont verticalement alignés., X étant le nombre de pions à aligner.
	 * @return Vrai si X pions sont verticalement alignés, faux sinon.
	 * @see Proprietes
	 */
	public boolean alignColonnes() {
		int sum, cpt;
		for(int i = 0; i < Proprietes.nbColonnes; i++) {
			for(int j = 0; j < Proprietes.nbLignes; j++) {
				if(plateau[i][j] != 0) {
					sum = 0;
					cpt = 0;
					while(cpt < Proprietes.alignes) {
						if(j + cpt < Proprietes.nbLignes) {
							if(plateau[i][j + cpt] == plateau[i][j])
								sum += plateau[i][j + cpt];
						}
						cpt++;
					}
					if(sum == Proprietes.alignes * plateau[i][j])
						return true;
				}
			}
		}
		return false;
	}
	
	/**
	 * Vérifie si X pions sont horizontalement alignés., X étant le nombre de pions à aligner.
	 * @return Vrai si X pions sont horizontalement alignés, faux sinon.
	 * @see Proprietes
	 */
	public boolean alignDiagonales() {
		int sum1, sum2, cpt;
		for(int i = 0; i < Proprietes.nbColonnes; i++) {
			for(int j = 0; j < Proprietes.nbLignes; j++) {
				if(plateau[i][j] != 0) {
					sum1 = 0;
					sum2 = 0;
					cpt = 0;
					while(cpt < Proprietes.alignes) {
						if(i - cpt >= 0 && j + cpt < Proprietes.nbLignes) {
							if(plateau[i - cpt][j + cpt] == plateau[i][j])
								sum1 += plateau[i - cpt][j + cpt];
						}
						if(i + cpt < Proprietes.nbColonnes && j + cpt < Proprietes.nbLignes) {
							if(plateau[i + cpt][j + cpt] == plateau[i][j])
								sum2 += plateau[i + cpt][j + cpt];
						}
						cpt++;
					}
					if(sum1 == Proprietes.alignes * plateau[i][j] || sum2 == Proprietes.alignes * plateau[i][j])
						return true;
				}
			}
		}
		return false;
	}

	/**
	 * Demande à l'observer de se mettre à jour.
	 * @param x La colonne sélectionnée.
	 * @param str L'état du jeu.
	 * @see Observer
	 */
	public void notifyObserver(int x, String str) {
		for(Observer obs : listObserver)
			obs.update(x, colonnes[x], tour, str);
	}
}
