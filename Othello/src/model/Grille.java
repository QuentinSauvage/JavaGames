package model;

import java.util.ArrayList;

import view.Observer;

/**
 * Contient les informations relatives au jeu.
 * @author Quentin Sauvage
 *
 */
public class Grille extends AbstractModel {

	/**
	 * Constructeur de grille.
	 * @param ia Booléen qui indique si l'IA est activée.
	 */
	public Grille(boolean ia) {
		super(ia);
	}
	
	/**
	 * Vérifie si le joueur peut jouer.
	 * @return Un booléen qui indique si le joueur peut jouer.
	 */
	@Override
	public boolean verifierAction() {
		ArrayList<int[]> al = new ArrayList<int[]>();
		for(int i = 0; i < 8; i++) {
			for(int j = 0; j < 8; j++) {
				if(grille[i][j] == 0)
					al = verifierCapture(i, j);
				if(al.size() > 0)
					return true;
			}
		}
		return false;
	}

	/**
	 * Vérifie les pions pouvant être capturés en jouant à cet endroit.
	 * @param x La colonne sélectionnée.
	 * @param y La ligne sélectionnée.
	 * @return Une ArrayList contenant les coordonnées des pions à retourner.
	 */
	@Override
	public ArrayList<int[]> verifierCapture(int x, int y) {
		ArrayList<int[]> al = new ArrayList<int[]>();
		al.addAll(verifierHaut(x, y));
		al.addAll(verifierBas(x, y));
		al.addAll(verifierGauche(x, y));
		al.addAll(verifierDroite(x, y));
		al.addAll(verifierHautGauche(x, y));
		al.addAll(verifierHautDroite(x, y));
		al.addAll(verifierBasGauche(x, y));
		al.addAll(verifierBasDroite(x, y));
		if(al.size() > 0) {
			int[] tab = new int[2];
			tab[0] = x;
			tab[1] = y;
			al.add(tab);
		}
		return al;
	}

	/**
	 * Vérifie les pions pouvant être capturés vers le haut en jouant à cet endroit.
	 * @param x La colonne sélectionnée.
	 * @param y La ligne sélectionnée.
	 * @return Une ArrayList contenant les coordonnées des pions à retourner.
	 */
	@Override
	public ArrayList<int[]> verifierHaut(int x, int y) {
		ArrayList<int[]> al = new ArrayList<int[]>();
		int oppose = 1;
		boolean retourne = false;
		if(tour == 0)
			oppose = 2;
		if(y > 0) {
			int i = 1;
			while(y - i >= 0) {
				if((grille[x][y - i] == 1 && tour == 0) || (grille[x][y - i] == 2 && tour == 1)) {
					retourne = true;
					break;
				} if(grille[x][y - i] == oppose) {
					int[] tab = new int[2];
					tab[0] = x;
					tab[1] = y - i;
					al.add(tab);
				} else
					break;
				i++;
			}
			if(!retourne)
				al.clear();
		}
		return al;
	}

	/**
	 * Vérifie les pions pouvant être capturés vers le bas en jouant à cet endroit.
	 * @param x La colonne sélectionnée.
	 * @param y La ligne sélectionnée.
	 * @return Une ArrayList contenant les coordonnées des pions à retourner.
	 */
	@Override
	public ArrayList<int[]> verifierBas(int x, int y) {
		ArrayList<int[]> al = new ArrayList<int[]>();
		int oppose = 1;
		boolean retourne = false;
		if(tour == 0)
			oppose = 2;
		if(y < 7) {
			int i = 1;
			while(y + i <= 7) {
				if((grille[x][y + i] == 1 && tour == 0) || (grille[x][y + i] == 2 && tour == 1)) {
					retourne = true;
					break;
				} if(grille[x][y + i] == oppose) {
					int[] tab = new int[2];
					tab[0] = x;
					tab[1] = y + i;
					al.add(tab);
				} else
					break;
				i++;
			}
			if(!retourne)
				al.clear();
		}
		return al;
	}

	/**
	 * Vérifie les pions pouvant être capturés vers la gauche en jouant à cet endroit.
	 * @param x La colonne sélectionnée.
	 * @param y La ligne sélectionnée.
	 * @return Une ArrayList contenant les coordonnées des pions à retourner.
	 */
	@Override
	public ArrayList<int[]> verifierGauche(int x, int y) {
		ArrayList<int[]> al = new ArrayList<int[]>();
		int oppose = 1;
		boolean retourne = false;
		if(tour == 0)
			oppose = 2;
		if(x > 0) {
			int i = 1;
			while(x - i >= 0) {
				if((grille[x - i][y] == 1 && tour == 0) || (grille[x - i][y] == 2 && tour == 1)) {
					retourne = true;
					break;
				} if(grille[x - i][y] == oppose) {
					int[] tab = new int[2];
					tab[0] = x - i;
					tab[1] = y;
					al.add(tab);
				} else
					break;
				i++;
			}
			if(!retourne)
				al.clear();
		}
		return al;
	}

	/**
	 * Vérifie les pions pouvant être capturés vers la droite en jouant à cet endroit.
	 * @param x La colonne sélectionnée.
	 * @param y La ligne sélectionnée.
	 * @return Une ArrayList contenant les coordonnées des pions à retourner.
	 */
	@Override
	public ArrayList<int[]> verifierDroite(int x, int y) {
		ArrayList<int[]> al = new ArrayList<int[]>();
		int oppose = 1;
		boolean retourne = false;
		if(tour == 0)
			oppose = 2;
		if(x < 7) {
			int i = 1;
			while(x + i <= 7) {
				if((grille[x + i][y] == 1 && tour == 0) || (grille[x + i][y] == 2 && tour == 1)) {
					retourne = true;
					break;
				} if(grille[x + i][y] == oppose) {
					int[] tab = new int[2];
					tab[0] = x + i;
					tab[1] = y;
					al.add(tab);
				} else
					break;
				i++;
			}
			if(!retourne)
				al.clear();
		}
		return al;
	}

	/**
	 * Vérifie les pions pouvant être capturés vers la diagonale haut/gauche en jouant à cet endroit.
	 * @param x La colonne sélectionnée.
	 * @param y La ligne sélectionnée.
	 * @return Une ArrayList contenant les coordonnées des pions à retourner.
	 */
	@Override
	public ArrayList<int[]> verifierHautGauche(int x, int y) {
		ArrayList<int[]> al = new ArrayList<int[]>();
		int oppose = 1;
		boolean retourne = false;
		if(tour == 0)
			oppose = 2;
		if(x > 0 && y > 0) {
			int i = 1;
			while(x - i >= 0 && y - i >= 0) {
				if((grille[x - i][y - i] == 1 && tour == 0) || (grille[x - i][y - i] == 2 && tour == 1)) {
					retourne = true;
					break;
				} if(grille[x - i][y - i] == oppose) {
					int[] tab = new int[2];
					tab[0] = x - i;
					tab[1] = y - i;
					al.add(tab);
				} else
					break;
				i++;
			}
			if(!retourne)
				al.clear();
		}
		return al;
	}

	/**
	 * Vérifie les pions pouvant être capturés vers la diagonale haut/droite en jouant à cet endroit.
	 * @param x La colonne sélectionnée.
	 * @param y La ligne sélectionnée.
	 * @return Une ArrayList contenant les coordonnées des pions à retourner.
	 */
	@Override
	public ArrayList<int[]> verifierHautDroite(int x, int y) {
		ArrayList<int[]> al = new ArrayList<int[]>();
		int oppose = 1;
		boolean retourne = false;
		if(tour == 0)
			oppose = 2;
		if(x < 7 && y > 0) {
			int i = 1;
			while(x + i <= 7 && y - i >= 0) {
				if((grille[x + i][y - i] == 1 && tour == 0) || (grille[x + i][y - i] == 2 && tour == 1)) {
					retourne = true;
					break;
				} if(grille[x + i][y - i] == oppose) {
					int[] tab = new int[2];
					tab[0] = x + i;
					tab[1] = y - i;
					al.add(tab);
				} else
					break;
				i++;
			}
			if(!retourne)
				al.clear();
		}
		return al;
	}

	/**
	 * Vérifie les pions pouvant être capturés vers la diagonale bas/gauche en jouant à cet endroit.
	 * @param x La colonne sélectionnée.
	 * @param y La ligne sélectionnée.
	 * @return Une ArrayList contenant les coordonnées des pions à retourner.
	 */
	@Override
	public ArrayList<int[]> verifierBasGauche(int x, int y) {
		ArrayList<int[]> al = new ArrayList<int[]>();
		int oppose = 1;
		boolean retourne = false;
		if(tour == 0)
			oppose = 2;
		if(x > 0 && y < 7) {
			int i = 1;
			while(x - i >= 0 && y + i <= 7) {
				if((grille[x - i][y + i] == 1 && tour == 0) || (grille[x - i][y + i] == 2 && tour == 1)) {
					retourne = true;
					break;
				} if(grille[x - i][y + i] == oppose) {
					int[] tab = new int[2];
					tab[0] = x - i;
					tab[1] = y + i;
					al.add(tab);
				} else
					break;
				i++;
			}
			if(!retourne)
				al.clear();
		}
		return al;
	}

	/**
	 * Vérifie les pions pouvant être capturés vers la diagonale bas/droite en jouant à cet endroit.
	 * @param x La colonne sélectionnée.
	 * @param y La ligne sélectionnée.
	 * @return Une ArrayList contenant les coordonnées des pions à retourner.
	 */
	@Override
	public ArrayList<int[]> verifierBasDroite(int x, int y) {
		ArrayList<int[]> al = new ArrayList<int[]>();
		int oppose = 1;
		boolean retourne = false;
		if(tour == 0)
			oppose = 2;
		if(x < 7 && y < 7) {
			int i = 1;
			while(x + i <= 7 && y + i <= 7) {
				if((grille[x + i][y + i] == 1 && tour == 0) || (grille[x + i][y + i] == 2 && tour == 1)) {
					retourne = true;
					break;
				} if(grille[x + i][y + i] == oppose) {
					int[] tab = new int[2];
					tab[0] = x + i;
					tab[1] = y + i;
					al.add(tab);
				} else
					break;
				i++;
			}
			if(!retourne)
				al.clear();
		}
		return al;
	}
	
	/**
	 * L'IA calcule le meilleur endroit pour jouer.
	 * @return Un couple de coordonnées correspondant à l'endroit où l'IA a décidé de jouer.
	 */
	public int[] calculerMax() {
		ArrayList<int[]> al = new ArrayList<int[]>();
		ArrayList<int[]> tmp = new ArrayList<int[]>();
		int[] tab = new int[2];
		tab[0] = -1;
		tab[1] = -1;
		for(int i = 0; i < 8; i++) {
			for(int j = 0; j < 8; j++) {
				if(grille[i][j] == 0)
					tmp = verifierCapture(i, j);
				if(tmp.size() > al.size()) {
					al.clear();
					al.addAll(tmp);
					tab[0] = i;
					tab[1] = j;
				}
			}
		}
		return tab;
	}
	
	@Override
	public void notifyObserver(String str) {
		for(Observer obs : listObserver)
			obs.update(str);
	}
	
	@Override
	public void notifyObserver(int x, int y, int tour, ArrayList<int[]> al, int s1, int s2, String str) {
		for(Observer obs : listObserver)
			obs.update(x, y, tour, al, s1, s2, str);
	}
}
