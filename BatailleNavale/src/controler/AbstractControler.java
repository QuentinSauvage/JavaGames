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
	 * @param gauche Booléen qui indique si la grille sélectionnée est celle de gauche.
	 */
	public void setJeu(int x, int y, boolean gauche) {
			control(y, x, gauche);
	}
	
	/**
	 * Demande au modèle de se mettre à jour en fonction du clic, et l'informe du changement d'état.
	 * @param x La colonne cliquée.
	 * @param y La ligne cliquée.
	 * @param gauche Booléen qui indique si la grille sélectionnée est celle de gauche.
	 */
	abstract void control(int x, int y, boolean gauche);
	
}
