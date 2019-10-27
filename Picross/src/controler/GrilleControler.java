package controler;

import model.AbstractModel;
import model.GrilleModel;

/**
 * Gère les actions du joueur pendant que celui-ci doit découvrir une grille.
 * @author Quentin Sauvage
 *
 */
public class GrilleControler  extends AbstractControler {

	/**
	 * Constructure de GrilleControler.
	 * @param m Le modèle à contrôler.
	 */
	public GrilleControler(AbstractModel m) {
		super(m);
	}

	/**
	 * Demande au modèle de se mettre à jour en fonction du clic, et l'informe du changement d'état.
	 * @param x La colonne sur laquelle le joueur a cliqué.
	 * @param y La ligne sur laquelle le joueur a cliqué.
	 * @param typeClic Le clic de la souris utilisé.
	 */
	@Override
	void control(int x, int y, int newValue, int typeClic) {
		int[][] grilleObjectif = modele.getGrilleObjectif();
		String str = "continue";
		if(grilleObjectif[y][x] == 0 && typeClic == 1)
			str = "perdu";
		if(typeClic == newValue)
			typeClic = 0;
		modele.getGrilleJeu()[y][x] = typeClic;
		if(((GrilleModel)modele).gagner())
				str = "gagner";
		modele.notifyObserver(y, x, typeClic, str);
	}

	@Override
	void control(String str) {
		// TODO Auto-generated method stub
		
	}

}
