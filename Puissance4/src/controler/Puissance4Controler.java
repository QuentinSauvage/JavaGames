package controler;

import initialisation.Proprietes;
import model.AbstractModel;
import model.IA;
import model.Puissance4;

/**
 * Contrôle les actions de l'utilisateur.
 * @author Quentin Sauvage
 *
 */
public class Puissance4Controler extends AbstractControler {


	/**
	 * Construit le controler à partir d'un modèle à contrôler.
	 * @param m Le modèle à contrôler.
	 */
	public Puissance4Controler(AbstractModel m) {
		super(m);
	}

	/**
	 * Demande au modèle de se mettre à jour en fonction du clic, et l'informe du changement d'état.
	 * @param x La colonne sur laquelle le joueur a cliqué.
	 * @see Puissance4
	 * @see IA
	 * @see Proprietes
	 */
	@Override
	void control(int x) {
		String str = "ok";
		if(modele instanceof IA && modele.getTour() == 2) {
			x = ((IA) modele).jouer();
		}
		if(modele.getColonnes()[x] <  Proprietes.nbLignes) {
			modele.addPion(x, modele.getColonnes()[x]);
			modele.setColonne(x);
			if(modele.getPoses() == 0)
				str = "Le plateau est rempli.";
			if(modele.alignLignes() || modele.alignColonnes() || modele.alignDiagonales()) {
				str = "Le joueur " + modele.getTour();
				if(Proprietes.mode)
					str += " a perdu !";
				else
					str += " a gagné !";
			}
			modele.notifyObserver(x, str);
			modele.setTour(1);
			modele.setPoses(modele.getPoses() - 1);
		}
	}
}
