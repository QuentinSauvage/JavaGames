package model;

import java.util.ArrayList;

import view.Observer;

/**
 * Définit les méthodes et attributs d'un Model.
 * @author Quentin Sauvage
 *
 */
public abstract class AbstractModel implements Observable {
	/**
	 * Contient la liste de tous les observers à mettre à jour graphiquement (ici il n'y a que la vue).
	 */
	protected ArrayList<Observer> listObserver = new ArrayList<Observer>();
	/**
	 * Représente le joueur 1.
	 */
	protected Joueur joueur1;
	/**
	 * Représente le joueur 2.
	 */
	protected Joueur joueur2;
	/**
	 * Indique qui doit jouer.
	 */
	protected int tour;
	
	/**
	 * Getter de tour.
	 * @return le tour actuel.
	 */
	public int getTour() {
		return tour;
	}
	
	/**
	 * Setter de tour.
	 * @param t Incrémente le tour.
	 */
	public void setTour(int t) {
		tour = (tour + t) % 2;
	}
	
	/**
	 * Getter de joueur1;
	 * @return Le joueur 1;
	 */
	public Joueur getJoueur1() {
		return joueur1;
	}
	
	/**
	 * Setter de joueur1;
	 * @param j Le nouveau joueur1;
	 */
	public void setJoueur1(Joueur j) {
		joueur1 = j;
	}
	
	/**
	 * Getter de joueur2;
	 * @return Le joueur 2;
	 */
	public Joueur getJoueur2() {
		return joueur2;
	}
	
	/**
	 * Setter de joueur2;
	 * @param j Le nouveau joueur2;
	 */
	public void setJoueur2(Joueur j) {
		joueur2 = j;
	}
	
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
	public abstract void notifyObserver(int x, int y, boolean gauche, String str);
	
	/**
	 * Supprime les observateurs.
	 * @see Observer
	 */
	public void removeObserver() {
		listObserver = new ArrayList<Observer>();
	}
}
