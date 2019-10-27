package model;

import java.util.ArrayList;

import view.Observer;

/**
 * Contient tous les attributs et méthodes permettant de manipuler les informations du jeu.
 * @author Quentin Sauvage
 *
 */
public abstract class AbstractModel implements Observable {
	/**
	 * Contient la liste de tous les observers à mettre à jour graphiquement.
	 */
	protected ArrayList<Observer> listObserver = new ArrayList<Observer>();
	/**
	 * La largeur de la grille actuelle.
	 */
	protected int largeur;
	/**
	 * La hauteur de la grille actuelle.
	 */
	protected int hauteur;
	/**
	 * Représente la grille comme elle est stockée dans le fichier.
	 */
	protected int[][] grilleObjectif;
	/**
	 * Représente la grille actuellement dessinée par le joueur.
	 */
	protected int[][] grilleJeu;
	/**
	 * Une Arraylist de String où chaque String est une suite de nombres définissant les successions de cases noires d'une ligne.
	 */
	protected ArrayList<String> lignes = new ArrayList<String>();
	/**
	 * Une Arraylist de String où chaque String est une suite de nombres définissant les successions de cases noires d'une colonne.
	 */
	protected ArrayList<String> colonnes = new ArrayList<String>();

	/**
	 * Getter de largeur.
	 * @return la largeur de la grille actuelle.
	 */
	public int getLargeur() {
		return largeur;
	}
	
	/**
	 * Setter de largeur.
	 * @param largeur La nouvelle largeur de la grille.
	 */
	public void setLargeur(int largeur) {
		this.largeur = largeur;
	}
	
	/**
	 * Getter de hauteur.
	 * @return la hauteur de la grille actuelle.
	 */
	public int getHauteur() {
		return hauteur;
	}
	
	/**
	 * Setter de hauteur.
	 * @param largeur La nouvelle hauteur de la grille.
	 */
	public void setHauteur(int hauteur) {
		this.hauteur = hauteur;
	}
	
	/**
	 * Getter de grilleObjectif.
	 * @return la grille devant être obtenue.
	 */
	public int[][] getGrilleObjectif() {
		return grilleObjectif;
	}
	
	/**
	 * Setter de grilleObjectif.
	 * @param largeur La nouvelle grille à obtenir.
	 */
	public void setGrilleObjectif(int[][] grille) {
		this.grilleObjectif = grille;
	}
	
	/**
	 * Getter de grilleJeu
	 * @return la grille actuellement dessinnée par le joueur.
	 */
	public int[][] getGrilleJeu() {
		return grilleJeu;
	}
	
	/**
	 * Setter de grilleJeu.
	 * @param largeur La nouvelle grille actuellement dessinéee.
	 */
	public void setGrilleJeu(int[][] grille) {
		this.grilleJeu = grille;
	}
	
	/**
	 * Getter de lignes.
	 * @return les String définissant les cases noires de chaque ligne.
	 */
	public ArrayList<String> getLignes() {
		return lignes;
	}

	/**
	 * Setter de lignes.
	 * @param largeur les nouvelles String définissant les cases noires de chaque ligne.
	 */
	public void setLignes(ArrayList<String> lignes) {
		this.lignes = lignes;
	}
	
	/**
	 * Getter de colonnes.
	 * @return les String définissant les cases noires de chaque colonne.
	 */
	public ArrayList<String> getColonnes() {
		return colonnes;
	}
	
	/**
	 * Setter de colonnes.
	 * @param largeur les nouvelles String définissant les cases noires de chaque colonne.
	 */
	public void setColonnes(ArrayList<String> colonnes) {
		this.colonnes = colonnes;
	}
	
	/**
	 * Ajoute un observer.
	 * @param obs L'observateur à ajouter.
	 */
	public void addObserver(Observer obs) {
		this.listObserver.add(obs);
	}
	
	/**
	 * Demande à l'observer de se mettre à jour.
	 * @param x La colonne sélectionnée.
	 * @param y La ligne sélectionnée.
	 * @param typeClic Le bouton de la souris utilisé.
	 * @param str L'état du jeu.
	 */
	public abstract void notifyObserver(int x, int y, int typeClic, String str);
	public abstract void notifyObserver(String str);
	
	/**
	 * Supprime les observateurs.
	 */
	public void removeObserver() {
		listObserver = new ArrayList<Observer>();
	}
}
