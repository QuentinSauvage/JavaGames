package controler;

import java.util.ArrayList;

import model.AbstractModel;

/**
 * Contrôle les actions des joueurs.
 * @author Quentin Sauvage
 *
 */
public class GrilleControler  extends AbstractControler {

	/**
	 * Constructeur de GrilleControler.
	 * @param m Le modèle à contrôler.
	 */
	public GrilleControler(AbstractModel m) {
		super(m);
	}

	/**
	 * Demande au modèle de se mettre à jour en fonction des coordonnées données en indiquant les pions à retourner.
	 * @param x La colonne sur laquelle le joueur a cliqué.
	 * @param y La ligne sur laquelle le joueur a cliqué.
	 */
	@Override
	void control(int x, int y) {
		int tour = modele.getTour() + 1;
		String str = "non";
		int[][] grille = modele.getGrille();
		ArrayList<int[]> al = new ArrayList<int[]>();
		if(modele.getIa() && modele.getTour() == 1) {
			int[] tab = new int[2];
			tab = modele.calculerMax();
			if(tab[0] != -1 && tab[1] != -1) {
				str = "oui";
				al = modele.verifierCapture(tab[0], tab[1]);
				modele.setTour(tour % 2);
				for(int[] tmp : al) {
					grille[tmp[0]][tmp[1]] = tour;
				}
			} else {
				modele.setTour(tour % 2);
			}
		} else {
			if(grille[x][y] == 0) {
				al = modele.verifierCapture(x, y);
				if(al.size() > 0) {
					str = "oui";
					modele.setTour(tour % 2);
					for(int[] tab : al) {
						grille[tab[0]][tab[1]] = tour;
					}
				} else {
					str = "non";
				}
			}
		}
		if(al.size() > 0) {
			if(tour == 1) {
				modele.setScore1(modele.getScore1() + al.size());
				modele.setScore2(modele.getScore2() - (al.size() - 1));
			} else {
				modele.setScore1(modele.getScore1() - (al.size() - 1));
				modele.setScore2(modele.getScore2() + al.size());
			}
		}
		if(modele.getScore1() + modele.getScore2() == 64) {
			str = "fini";
		}
		modele.notifyObserver(y,  x, tour, al, modele.getScore1(), modele.getScore2(), str);
	}

	/**
	 * Vérifie que le joueur a le droit de passer son tour.
	 */
	@Override
	void control() {
		String str = "non";
		if(!modele.verifierAction()) {
			modele.setTour((modele.getTour() + 1) % 2);
			str = "oui";
		}
		modele.notifyObserver(str);
	}

}
