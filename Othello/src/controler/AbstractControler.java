package controler;

import model.AbstractModel;

/**
 * Définit les méthodes à utiliser pour contrôler le modèle.
 * @author Quentin Sauvage
 *
 */
public abstract class AbstractControler {
	/**
	 * Le modèle à contrôler.
	 */
	protected AbstractModel modele;
	
	/**
	 * Construit le controler à partir d'un modèle à contrôler.
	 * @param m Le modèle à contrôler.
	 */
	public AbstractControler(AbstractModel m) {
		modele = m;
	}
	
	/**
	 * Appelle le contrôle du jeu.
	 * @param x La colonne cliquée.
	 * @param y La ligne cliquée.
	 */
	public void setJeu(int x, int y) {
			control(x, y);
	}
	
	/**
	 * Vérifie si le joueur peut passer son tour.
	 */
	public void setJeu() {
		control();
	}
	
	abstract void control(int x, int y);
	abstract void control();
	
}
