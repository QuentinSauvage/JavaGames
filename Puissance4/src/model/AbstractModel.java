package model;

import java.util.ArrayList;

import observer.*;

public abstract class AbstractModel implements Observable {
	/**
	 * Le tour actuel (1 ou 2) permettant de savoir quel joueur doit jouer.
	 */
	protected int tour;
	
	/**
	 * Une matrice qui contient toutes les cases de la grille.
	 */
	protected int[][] plateau;
	
	/**
	 * Un tableau de colonnes indiquant combien de pions sont placés dans chaque colonne.
	 */
	protected int[] colonnes;
	
	/**
	 * Indique le nombre de pions posés dans la grille.
	 */
	protected int poses;
	
	/**
	 * Contient la liste de tous les observers à mettre à jour graphiquement (ici il n'y a que la vue).
	 * @see Observer
	 */
	protected ArrayList<Observer> listObserver = new ArrayList<Observer>();
	
	/**
	 * Permet d'accéder au tour actuel (1 ou 2).
	 * @return Le tour actuel.
	 */
	public abstract int getTour();
	
	/**
	 * Permet de passer au tour suivant.
	 * @param t Le nouveau tour.
	 */
	public abstract void setTour(int t);
	
	/**
	 * Permet d'accéder au nombre de pions posés dans la grille.
	 * @return Le nombre de pions posés pour le moment dans la grille.
	 */
	public abstract int getPoses();
	
	/**
	 * Modifie le nombre de pions posés dans la grille.
	 * @param poses Le nouveau nombre de pions posés.
	 */
	public abstract void setPoses(int poses);
	
	/**
	 * Permet d'accéder au plateau (c'est-à-dire la matrice d'entiers représentant la grille).
	 * @return La matrice de pions.
	 */
	public abstract int[][] getPlateau();
	
	/**
	 * Modifie le plateau actuel avec un nouveau plateau.
	 * @param p Le nouveau plateau.
	 */
	public abstract void setPlateau(int[][] p);
	
	/**
	 * Permet d'accéder à la liste des colonnes du jeu.
	 * @return Les colonnes contenant le nombre de pions posés.
	 */
	public abstract int[] getColonnes();
	
	/**
	 * Incrémente le nombre de pions dans la colonne indiquée.
	 * @param indice La colonne à modifier.
	 */
	public abstract void setColonne(int indice);
	
	/**
	 * Ajoute un pion dans la grille aux coordonnées indiquées.
	 * @param x L'abscisse du pion à insérer.
	 * @param c L'ordonnée du pion à insérer.
	 */
	public abstract void addPion(int x, int c);
	
	/**
	 * Vérifie si X pions sont horizontalement alignés., X étant le nombre de pions à aligner.
	 * @return Vrai si X pions sont horizontalement alignés, faux sinon.
	 */
	public abstract boolean alignLignes();
	
	/**
	 * Vérifie si X pions sont verticalement alignés., X étant le nombre de pions à aligner.
	 * @return Vrai si X pions sont verticalement alignés, faux sinon.
	 */
	public abstract boolean alignColonnes();
	
	/**
	 * Vérifie si X pions sont horizontalement alignés., X étant le nombre de pions à aligner.
	 * @return Vrai si X pions sont horizontalement alignés, faux sinon.
	 */
	public abstract boolean alignDiagonales();
	
	/**
	 * Ajoute un observer.
	 * @param obs L'observateur à ajouter.
	 * @see Observer
	 */
	public void addObserver(Observer obs) {
		this.listObserver.add(obs);
	}
	
	/**
	 * Demande à l'observer de se mettre à jour.
	 * @param x La colonne sélectionnée.
	 * @param str L'état du jeu.
	 */
	public abstract void notifyObserver(int x, String str);
	
	/**
	 * Supprime les observateurs.
	 * @see Observer
	 */
	public void removeObserver() {
		listObserver = new ArrayList<Observer>();
	}
}
