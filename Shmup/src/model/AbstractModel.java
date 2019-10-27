package model;

import java.util.ArrayList;

import view.Observer;

public abstract class AbstractModel implements Observable {
	/**
	 * Contient la liste de tous les observers à mettre à jour graphiquement (ici il n'y a que la vue).
	 * @see Observer
	 */
	protected ArrayList<Observer> listObserver = new ArrayList<Observer>();

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
