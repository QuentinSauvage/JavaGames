import java.util.Random;

/**
 * Gère les évènements et les actions liés aux joueurs et à la grille.
 * @author Quentin Sauvage
 *
 */
public class Jeu {
	/**
	 * La fenêtre contenant tous les éléments graphiques.
	 * @see Fenetre
	 */
	private Fenetre f;
	/**
	 * Le tableau de traitsHorizontaux.
	 */
	private Trait[][] traitsHorizontaux;
	
	/**
	 * Le tableau de traitsVerticaux.
	 */
	private Trait[][] traitsVerticaux;
	
	/**
	 * Un tableau contenant le nombre de points de chaque joueur.
	 */
	private int[] pointsJoueurs;
	
	/**
	 * Le tour actuel.
	 */
	private int tour;
	
	/**
	 * Le nombre de carrés remplis au total dans la grille.
	 */
	private int nbCarresRemplis;
	
	/**
	 * Indique si la partie est finie.
	 */
	private boolean fini;
	
	/**
	 * Initialise les attributs du jeu.
	 */
	public Jeu(){
		tour = 0;
		fini = false;
		nbCarresRemplis = 0;
	}
	
	/**
	 * Lance une nouvelle partie en créant la fenêtre associée au jeu.
	 */
	public void lancerJeu() {
		f = new Fenetre(this);
	}
	
	/**
	 * Récupère le tour actuel.
	 * @return le tour actuel.
	 */
	public int getTour() {
		return tour;
	}
	
	/**
	 * Indique si la partie est finie ou non.
	 * @return booléen indiquant si la partie est finie.
	 */
	public boolean getEtat() {
		return fini;
	}
	
	/**
	 * Retourne le nombre de points du joueur n°indice.
	 * @param indice l'indice du joueur dont on veut récupérr le nombre de points.
	 * @return Le nombre de points du joueur indice.
	 */
	public int getPoints(int indice) {
		return pointsJoueurs[indice];
	}
	
	/**
	 * Récupère le nombre de points maximum marqué parmi les joueurs.
	 * @return Le nombre de points maximum marqué.
	 */
	public int getMaxPoints() {
		int max = 0;
		for(int i = 0; i < pointsJoueurs.length; i++)
			if(pointsJoueurs[i] > max)
				max = i;
		return max;
	}
	
	/**
	 * Incrémente le tour de jeu.
	 */
	public void increaseTour() {
		tour = (tour + 1) % Constantes.nbJoueurs;
	}
	
	/**
	 * Augmente les points du joueur actuel, et vérifie si la partie est finie.
	 * @see Fenetre#afficherFin(boolean)
	 */
	public void increasePoints() {
		pointsJoueurs[tour]++;
		nbCarresRemplis++;
		if(pointsJoueurs[tour] > (((Constantes.nbColonnes * Constantes.nbLignes) / 2) + 1)) {
			fini = true;
			f.afficherFin(false);
		}
		else if(nbCarresRemplis == Constantes.nbColonnes * Constantes.nbLignes) {
			fini = true;
			f.afficherFin(true);
		}
	}
	
	/**
	 * Initialise les tableaux de traits et les ajoute dans la fenêtre.
	 * @see Fenetre#commencer()
	 * @see Fenetre#ajouter(Trait[][], Trait[][])
	 */
	public void commencer() {
		pointsJoueurs = new int[Constantes.nbJoueurs];
		f.commencer();
		traitsHorizontaux = new Trait[Constantes.nbLignes + 1][Constantes.nbLignes];
		traitsVerticaux = new Trait[Constantes.nbColonnes + 1][Constantes.nbColonnes];
		for(int i = 0; i < Constantes.nbLignes + 1; i++) {
			for(int j = 0; j < Constantes.nbLignes; j++) {
				traitsHorizontaux[i][j] = new Trait(i, j, 'h', this);
				traitsVerticaux[i][j] = new Trait(i, j, 'v', this);
			}
		}
		f.ajouter(traitsHorizontaux, traitsVerticaux);
	}
	
	/**
	 * Permet à l'IA de calculer le meilleur endroit où elle doit jouer.
	 * @param i L'abscisse du point où commence l'IA.
	 * @param j L'ordonnée du point où commence l'IA.
	 * @return un tableau contenant le nombre de carrés pouvant être remplis, l'abscisse et l'ordonnée du trait que l'IA doit choisir, l'orientation de ce trait.
	 * @see Trait#getColorie()
	 * @see Trait#setCouleur(Couleurs)
	 */
	public int[] calculIA(int i, int j) {
		int[] max1, max2, max3, max4;
		max1 = new int[5];
		max2 = new int[5];
		max3 = new int[5];
		max4 = new int[5];
		if(i > 0 && i <= Constantes.nbLignes && j < Constantes.nbLignes) {
				if(traitsVerticaux[j][i - 1].getColorie() && traitsVerticaux[j + 1][i - 1].getColorie() && traitsHorizontaux[i - 1][j].getColorie() && traitsHorizontaux[i][j].getColorie() == false) {
					traitsHorizontaux[i][j].setCouleur(Couleurs.BLUE);
					max1[0] = 1 + calculIA(i + 1, j)[0];
					max1[1] = i;
					max1[2] = j;
					max1[3] = 0;
					max1[4] = 0;
					traitsHorizontaux[i][j].setCouleur(null);
				}
			if(traitsVerticaux[i - 1][j].getColorie() && traitsHorizontaux[j][i -1].getColorie() && traitsHorizontaux[j + 1][i - 1].getColorie() && traitsVerticaux[i][j].getColorie() == false) {
				traitsVerticaux[i][j].setCouleur(Couleurs.BLUE);
				max3[0] = 1 + calculIA(i + 1, j)[0];
				max3[1] = i;
				max3[2] = j;
				max3[3] = 1;
				max3[4] = 0;
				traitsVerticaux[i][j].setCouleur(null);
			}
		} if(i >= 0 && i < Constantes.nbLignes && j < Constantes.nbLignes) {
			if(traitsVerticaux[j][i].getColorie() && traitsVerticaux[j + 1][i].getColorie() && traitsHorizontaux[i + 1][j].getColorie() && traitsHorizontaux[i][j].getColorie() == false) {
				traitsHorizontaux[i][j].setCouleur(Couleurs.BLUE);
				max2[0] = 1 + calculIA(i - 1, j)[0];
				max2[1] = i;
				max2[2] = j;
				max2[3] = 0;
				max2[4] = 1;
				traitsHorizontaux[i][j].setCouleur(null);
			}
			if(i > 0)
				if(traitsVerticaux[i + 1][j].getColorie() && traitsHorizontaux[j][i].getColorie() && traitsHorizontaux[j + 1][i].getColorie() && traitsVerticaux[i][j].getColorie() == false) {
					traitsVerticaux[i][j].setCouleur(Couleurs.BLUE);
					max4[0] = 1 + calculIA(i - 1, j)[0];
					max4[1] = i;
					max4[2] = j;
					max4[3] = 1;
					max4[4] = 1;
					traitsVerticaux[i][j].setCouleur(null);
				}
		}
		if(max1[0] < max2[0])
			max1 = max2;
		if(max1[0] < max3[0])
			max1 = max3;
		if(max1[0] < max4[0])
			max1 = max4;
		return max1;
	}
	
	/**
	 * L'IA calcule pour chaque trait le nombre de carrés qu'elle peut remplir, puis sélectionne le maximum.
	 * @see Jeu#calculIA(int, int)
	 * @see Jeu#increaseTour()
	 * @see Trait#clicAction()
	 * @see Jeu#increasePoints()
	 * @see Trait#getColorie()
	 * @see Fenetre#remplirCarre(Trait)
	 */
	public void jouerIA() {
		int[][] maxPossible = new int[(Constantes.nbLignes + 1) * Constantes.nbLignes][5];
		int indiceMax = 0;
		int k = 0;
		for(int i = 0; i < Constantes.nbLignes + 1; i++) {
			for(int j = 0; j < Constantes.nbLignes; j++) {
				maxPossible[k] = calculIA(i, j);
				k++;
			}
		}
		for(int i = 0; i < (Constantes.nbLignes + 1) * Constantes.nbLignes; i++) {
			if(maxPossible[i][0] > maxPossible[indiceMax][0])
				indiceMax = i;
		}
		int i = maxPossible[indiceMax][1];
		int j = maxPossible[indiceMax][2];
		if(maxPossible[indiceMax][0] == 0) {
			Random rd = new Random();
			if(rd.nextInt(2) == 0) {
				while(traitsHorizontaux[i][j].getColorie() == true) {
					i = rd.nextInt(Constantes.nbLignes + 1);
					j = rd.nextInt(Constantes.nbLignes);
				}
				traitsHorizontaux[i][j].clicAction();
			} else {
				while(traitsVerticaux[i][j].getColorie() == true) {
					i = rd.nextInt(Constantes.nbColonnes + 1);
					j = rd.nextInt(Constantes.nbColonnes);
				}
				traitsVerticaux[i][j].clicAction();
			}
			increaseTour();
		}
		else {
			increasePoints();
			if(maxPossible[indiceMax][3] == 0) { //Si c'est un trait horizontal
				if(maxPossible[indiceMax][4] == 0) //Si le carré à remplir est au-dessus
					f.remplirCarre(traitsHorizontaux[i - 1][j]);
				else {
					if(j < Constantes.nbLignes && i < Constantes.nbLignes)
						if(traitsVerticaux[j][i].getColorie() && traitsVerticaux[j + 1][i].getColorie() && traitsHorizontaux[i + 1][j].getColorie())
							f.remplirCarre(traitsHorizontaux[i][j]);
				}
				traitsHorizontaux[i][j].clicAction();
			} else {
				if(maxPossible[indiceMax][4] == 0) //Si le carré à remplir est à gauche
					f.remplirCarre(traitsVerticaux[i - 1][j]);
				else {
					if(i < Constantes.nbColonnes && j < Constantes.nbColonnes)
						if(traitsVerticaux[i + 1][j].getColorie() && traitsHorizontaux[j][i].getColorie() && traitsHorizontaux[j + 1][i].getColorie())
							f.remplirCarre(traitsVerticaux[i][j]);
				}
				traitsVerticaux[i][j].clicAction();
			}
			jouerIA();
		}
	}
	
	/**
	 * Vérifie si le joueur a rempli un carré en cliquant sur le trait.
	 * @see Trait#getCoordonnesX()
	 * @see Trait#getCoordonnesY()
	 * @see Trait#getAlignement()
	 * @see Jeu#increasePoints()
	 * @see Fenetre#remplirCarre(Trait)
	 * @see Trait#getColorie()
	 * @see Jeu#increaseTour()
	 * @param t Le trait cliqué par le joueur.
	 */
	public void calcul(Trait t) {
		int x = t.getCoordonnesX();
		int y = t.getCoordonnesY();
		boolean carreRempli = false;
		
		if(t.getAlignement() == 'h') {
			if(x > 0)
				if(traitsVerticaux[y][x - 1].getColorie() && traitsVerticaux[y + 1][x - 1].getColorie() && traitsHorizontaux[x - 1][y].getColorie()) {
					carreRempli = true;
					increasePoints();
					f.remplirCarre(traitsHorizontaux[x -1][y]);
				}
			if(x < Constantes.nbLignes)
				if(traitsVerticaux[y][x].getColorie() && traitsVerticaux[y + 1][x].getColorie() && traitsHorizontaux[x + 1][y].getColorie()) {
					carreRempli = true;
					increasePoints();
					f.remplirCarre(t);
				}
		} else {
			if(x > 0)
				if(traitsVerticaux[x - 1][y].getColorie() && traitsHorizontaux[y][x -1].getColorie() && traitsHorizontaux[y + 1][x - 1].getColorie()) {
					carreRempli = true;
					increasePoints();
					f.remplirCarre(traitsVerticaux[x - 1][y]);
				}
			if(x < Constantes.nbColonnes)
				if(traitsVerticaux[x + 1][y].getColorie() && traitsHorizontaux[y][x].getColorie() && traitsHorizontaux[y + 1][x].getColorie()) {
					carreRempli = true;
					increasePoints();
					f.remplirCarre(t);
				}
		}
		if(!carreRempli)
			increaseTour();
	}
}