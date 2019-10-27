package model;

import view.Observer;

/**
 * Informe les Observers qu'ils doivent se mettre à jour.
 * @author Quentin Sauvage
 */
public interface Observable {
	
	/**
	 * Ajoute un observer.
	 * @param obs L'observateur à ajouter.
	 * @see Observer
	 */
	public void addObserver(Observer obs);
	
	/**
	 * Supprime les observers.
	 */
	public void removeObserver();
	
	/**
	 * Demande à l'observer de se mettre à jour.
	 * @param x La colonne sélectionnée.
	 * @param x La ligne sélectionnée.
	 * @param typeClic Le bouton de la souris utilisé.
	 * @param str L'état du jeu.
	 */
	public void notifyObserver(int x, int y, int typeClic, String str);
}
