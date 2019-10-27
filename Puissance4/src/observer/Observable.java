package observer;

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
	 * @param str L'état du jeu.
	 */
	public void notifyObserver(int x, String str);
}
