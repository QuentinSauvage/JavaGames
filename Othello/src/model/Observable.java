package model;

import java.util.ArrayList;

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
	
	public void notifyObserver(int x, int y, int tour, ArrayList<int[]> al, int s1, int s2, String str);
	public void notifyObserver(String str);
}
