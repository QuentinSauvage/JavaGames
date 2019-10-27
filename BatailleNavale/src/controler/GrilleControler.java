package controler;

import model.AbstractModel;
import model.IA;
import model.Joueur;

/**
 * Contrôle les clics des joueurs.
 * @author Quentin Sauvage
 *
 */
public class GrilleControler  extends AbstractControler {

	/**
	 * Constructeur de GrilleControler.
	 * @param m Le model à contrôler.
	 */
	public GrilleControler(AbstractModel m) {
		super(m);
	}

	/**
	 * Indique aux joueurs si un bateau a été touché, et si oui, lequel.
	 * @param gauche Booléen indiquant quelle grille est ciblée.
	 * @param joueur Le joueur jouant actuellement.
	 * @param x La colonne ciblée.
	 * @param y La ligne ciblée.
	 * @return Une string indiquant ce qui a été touché.
	 */
	public String verifierTour(boolean gauche, Joueur joueur, int x, int y) {
		String str;
		if((modele.getTour() == 0 && gauche) || (modele.getTour() == 1 && !gauche) || joueur.getGrille()[x][y].getValue() == 2) {
			str = "non";
		} else {
			modele.setTour(1);
			if(joueur.getGrille()[x][y].getValue() == 1) {
				String bateau = joueur.getGrille()[x][y].getBateau().toString().toLowerCase();
				int ind = joueur.getGrille()[x][y].getBateau().ordinal();
				joueur.getBateaux()[ind]--;
				if(joueur.getBateaux()[ind] == 0)
					str = "coulé : " + bateau;
				else
					str = "touché : " + bateau;
			} else
				str = "raté";
			joueur.getGrille()[x][y].setValue(2);
		}
		return str;
	}
	
	/**
	 * Demande au modèle de se mettre à jour en fonction du clic, et l'informe du changement d'état.
	 * @param x La colonne cliquée.
	 * @param y La ligne cliquée.
	 * @param gauche Booléen qui indique si la grille sélectionnée est celle de gauche.
	 */
	@Override
	void control(int x, int y, boolean gauche) {
		String str = "raté";
		Joueur joueur;
		if(modele.getJoueur2() instanceof IA && !gauche) {
			joueur = modele.getJoueur2();
			str = verifierTour(gauche, joueur, x, y);
		} else if(modele.getJoueur2() instanceof IA) {
			str = "non";
			joueur = modele.getJoueur1();
			IA ia = ((IA)modele.getJoueur2());
			int[] tab = ia.jouer();
			x = tab[0];
			y = tab[1];
			if(joueur.getGrille()[x][y].getValue() == 1)
				ia.getCoupsJoues()[x][y].setValue(1);
			else
				ia.getCoupsJoues()[x][y].setValue(2);
			str = verifierTour(gauche, joueur, x, y);
		} else {
			if(gauche)
				joueur = modele.getJoueur1();
			else
				joueur = modele.getJoueur2();
			str = verifierTour(gauche, joueur, x, y);
		}
		if(joueur.getSomme() == 0)
			str = "victoire";
		modele.notifyObserver(x, y, gauche, str);
	}

}
