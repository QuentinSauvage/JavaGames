package controler;

import model.AbstractModel;

/**
 * Définit les méthodes à utiliser pour contrôler le modèle.
 * @author Quentin Sauvage
 *
 */
public abstract class AbstractControler {
	/**
	 * Le modèle à contrôler
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
	 * @param x La colonne sur laquelle le joueur a cliqué.
	 * @param y La ligne sur laquelle le joueur a cliqué.
	 * @param newValue la valeur que la case sélectionnée doit prendre.
	 * @param typeClic Le clic de la souris utilisé.
	 */
	public void setJeu(int x, int y, int newValue, int typeClic) {
		if(typeClic == 3)
			typeClic--;
		control(x, y, newValue, typeClic);
	}
	
	/**
	 * Appelle le contrôle de l'enregistrement de la grille.
	 * @param str Le nom de la grille créée.
	 */
	public void setJeu(String str) {
		control(str);
	}
	
	abstract void control(int x, int y, int newValue, int typeClic);
	abstract void control(String str);
}
