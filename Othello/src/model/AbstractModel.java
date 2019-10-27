package model;

import java.util.ArrayList;

import view.Observer;

/**
 * Définit l'état et le comportement du Model.
 * @author Quentin Sauvage
 *
 */
public abstract class AbstractModel implements Observable {
	/**
	 * Contient la liste de tous les observers à mettre à jour graphiquement (ici il n'y a que la vue).
	 */
	protected ArrayList<Observer> listObserver = new ArrayList<Observer>();
	/**
	 * La grille du jeu.
	 */
	protected int[][] grille;
	/**
	 * Le tour actuel.
	 */
	protected int tour;
	/**
	 * Le score du joueur 1.
	 */
	protected int score1;
	/**
	 * Le score du joueur 2.
	 */
	protected int score2;
	/**
	 * Indique si l'ia est activée.
	 */
	protected boolean ia;
	
	/**
	 * Constructeur du Model.
	 * @param ia booléen indiquant si l'ia est activée.
	 */
	public AbstractModel(boolean ia) {
		grille = new int[8][8];
		grille[3][4] = 1;
		grille[4][4] = 2;
		grille[3][3] = 2;
		grille[4][3] = 1;
		tour = 0;
		score1 = 2;
		score2 = 2;
		this.ia = ia;
	}
	
	/**
	 * Getter de grille.
	 * @return La grille du jeu.
	 */
	public int[][] getGrille() {
		return grille;
	}
	
	/**
	 * Setter de grille.
	 * @param grille La nouvelle grille du jeu.
	 */
	public void setGrille(int[][] grille) {
		this.grille = grille;
	}
	
	/**
	 * Getter de tour.
	 * @return Le tour actuel.
	 */
	public int getTour() {
		return tour;
	}
	
	/**
	 * Setter de tour.
	 * @param tour Le nouveau tour du jeu.
	 */
	public void setTour(int tour) {
		this.tour = tour;
	}
	
	/**
	 * Getter de score1.
	 * @return Le score du joueur 1.
	 */
	public int getScore1() {
		return score1;
	}
	
	/**
	 * Setter de score1.
	 * @param score1 Le nouveau score du joueur 1.
	 */
	public void setScore1(int score1) {
		this.score1 = score1;
	}
	
	/**
	 * Getter de score2.
	 * @return Le score du joueur 2.
	 */
	public int getScore2() {
		return score2;
	}
	
	/**
	 * Setter de score2.
	 * @param score2 Le nouveau score du joueur 2.
	 */
	public void setScore2(int score2) {
		this.score2 = score2;
	}
	
	/**
	 * Getter de ia.
	 * @return Un booléen qui indique si l'ia est activée.
	 */
	public boolean getIa() {
		return ia;
	}
	
	/**
	 * Setter de ia.
	 * @param ia La nouvelle valeur d'ia.
	 */
	public void setIa(boolean ia) {
		this.ia = ia;
	}
	
	public void addObserver(Observer obs) {
		this.listObserver.add(obs);
	}
	
	public abstract void notifyObserver(int x, int y, int tour, ArrayList<int[]> al, int s1, int s2, String str);
	public abstract void notifyObserver(String str);
	public abstract ArrayList<int[]> verifierCapture(int x, int y);
	public abstract boolean verifierAction();
	public abstract ArrayList<int[]> verifierHaut(int x, int y);
	public abstract ArrayList<int[]> verifierBas(int x, int y);
	public abstract ArrayList<int[]> verifierGauche(int x, int y);
	public abstract ArrayList<int[]> verifierDroite(int x, int y);
	public abstract ArrayList<int[]> verifierHautGauche(int x, int y);
	public abstract ArrayList<int[]> verifierHautDroite(int x, int y);
	public abstract ArrayList<int[]> verifierBasGauche(int x, int y);
	public abstract ArrayList<int[]> verifierBasDroite(int x, int y);
	public abstract int[] calculerMax();
	
	public void removeObserver() {
		listObserver = new ArrayList<Observer>();
	}
}
