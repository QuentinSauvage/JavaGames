package controler;

import java.io.File;

import model.AbstractModel;
import model.CreationModel;

/**
 * Gère les actions du joueur pendant la création d'une grille.
 * @author Quentin Sauvage
 *
 */
public class CreationControler  extends AbstractControler {

	/**
	 * Constructeur de CreationControler.
	 * @param m Le modèle à contrôler.
	 */
	public CreationControler(AbstractModel m) {
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
		if(typeClic == newValue)
			typeClic = 0;
		modele.getGrilleJeu()[y][x] = typeClic;
		modele.notifyObserver(y, x, typeClic, "ok");
	}
	
	/**
	 * Contrôle l'enregistrement d'une grille.
	 * @param str Le nom du fichier devant être créé.
	 */
	@Override
	void control(String str) {
		String res;
		if(str.equals(""))
			res = "probleme";
		else {
			File f = new File("src/grilles/" + str + ".txt");
			if(!f.exists()) {
				((CreationModel)modele).enregistrer("src/grilles/" + str + ".txt");
				res = "enregistrer";
			} else
				res = "probleme";
		}
		modele.notifyObserver(res);
	}

}
