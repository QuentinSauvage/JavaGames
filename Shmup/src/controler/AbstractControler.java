package controler;

import model.AbstractModel;

/**
 * Définit les méthodes à utiliser pour contrôler le modèle.
 * @author Quentin Sauvage
 *
 */
public abstract class AbstractControler {
	protected AbstractModel modele;
	
	/**
	 * Construit le controler à partir d'un modèle à contrôler.
	 * @param m Le modèle à contrôler.
	 * @see AbstractModel
	 */
	public AbstractControler(AbstractModel m) {
		modele = m;
	}
	
	/**
	 * Appelle le contrôle du jeu.
	 * @param x La colonne sur laquelle le joueur a cliqué.
	 */
	public void setJeu(int x) {
			control(x);
	}
	
	/**
	 * Demande au modèle de se mettre à jour en fonction du clic, et l'informe du changement d'état.
	 * @param x La colonne sur laquelle le joueur a cliqué.
	 */
	abstract void control(int x);
	
}
