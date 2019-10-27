import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.util.Random;

/**
 * Gère les évènements et les actions liés au joueur et au plateau.
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
	 * Le score obtenu par le joueur.
	 */
	private int score;
	
	/**
	 * Booléen indiquant si le joueur a perdu ou non.
	 */
	private boolean perdu;
	
	/**
	 * Booléen indiquant si le joueur a atteint la tuile 2048.
	 */
	private boolean jeuFini;
	
	/**
	 * Booléen indiquant si la description est actuellement affichée.
	 */
	private boolean description;
	
	/**
	 * Une matrice de Tuile qui contient toutes les tuiles du plateau.
	 * @see Tuile
	 */
	private Tuile[][] plateau;
	
	/**
	 * Booléen indiquant si le jeu est en pause.
	 */
	private boolean pause;
	
	/**
	 * Booléen indiquant si l'Intelligence Artificielle est activée
	 */
	private boolean ia;
	
	/**
	 * Constructeur de Jeu, qui permet de lancer la partie.
	 * @see Jeu#initialiser()
	 */
	public Jeu() {
		initialiser();
	}
	
	/**
	 * Initialise les attributs du jeu.
	 */
	public void initialiser() {
		perdu = false;
		pause = false;
		jeuFini = false;
		description = false;
		ia = false;
		f = new Fenetre(this);
		plateau = new Tuile[Constantes.cote][Constantes.cote];
		score = 0;
	}
	
	/**
	 * Informe les classes sur l'état de la partie.
	 * @return booléen indiquant l'état de la partie (perdu ou non).
	 */
	public boolean getEtat() {
		return perdu;
	}
	
	/**
	 * Renvoie un booléen informant sur la potentielle du joueur.
	 * @return booléen indiquant si le joueur a atteint 2048.
	 */
	public boolean getFini() {
		return jeuFini;
	}
	
	/**
	 * Indique si le jeu est en pause ou non.
	 * @return booléen indiquant si la pause est activée.
	 */
	public boolean getPause() {
		return pause;
	}
	
	/**
	 * Informe sur le score actuel du joueur.
	 * @return int représentant le score du joueur.
	 */
	public int getScore() {
		return score;
	}
	/**
	 * Indique si l'IA est actuellement activée.
	 * @return booléen informant de l'état de l'IA.
	 */
	public boolean getIA() {
		return ia;
	}
	
	/**
	 * Permet de mettre le jeu en pause.
	 */
	public void setPause() {
		pause = !pause;
	}
	
	/**
	 * Génère les tuiles vides et deux tuiles remplies sur le plateau.
	 * @see Jeu#genererTuile()
	 */
	public void creerTuiles() {
		for(int i = 0; i < Constantes.cote; i++)
			for(int j = 0; j < Constantes.cote; j++)
				plateau[i][j] = new Tuile(i, j);
		genererTuile();
		genererTuile();
	}
	
	/**
	 * Indique si le joueur peut encore se déplacer.
	 * @see Tuile#getValeur()
	 * @see Tuile#equals(Object)
	 * @return booléen indiquant si le joueur est mort.
	 */
	public boolean testerMort() {
		for(int i = 0; i < Constantes.cote; i++) {
			for(int j = 0; j < Constantes.cote; j++) {
				if(plateau[i][j].getValeur() == 0)
					return true;
				if(i > 0)
					if(plateau[i][j].equals(plateau[i - 1][j]) || plateau[i - 1][j].getValeur() == 0)
						return true;
				if(i < Constantes.cote - 1)
					if(plateau[i][j].equals(plateau[i + 1][j]) || plateau[i + 1][j].getValeur() == 0)
						return true;
				if(j > 0)
					if(plateau[i][j].equals(plateau[i][j - 1]) || plateau[i][j - 1].getValeur() == 0)
						return true;
				if(j < Constantes.cote - 1)
					if(plateau[i][j].equals(plateau[i][j + 1]) || plateau[i][j + 1].getValeur() == 0)
						return true;
			}
		}
		return false;
	}
	
	/**
	 * Génère une tuile à une case aléatoire du plateau.
	 * @see Tuile#upgrade(int)
	 */
	public void genererTuile() {
		Random rd = new Random();
		int index = rd.nextInt(plateau.length * plateau[0].length);
		if(plateau[index % Constantes.cote][index / Constantes.cote].getValeur() == 0) {
			plateau[index % Constantes.cote][index / Constantes.cote].upgrade(2);
		}
		else
			genererTuile();
	}
	/**
	 * Indique si la tuile sélectionnée peut être déplacée dans la direction indiquée.
	 * @see Tuile#getValeur()
	 * @see Tuile#equals(Object)
	 * @see Tuile#upgrade(int)
	 * @see Tuile#getFusion()
	 * @see Tuile#setFusion(boolean)
	 * @see Fenetre#recommencer()
	 * @see Fenetre#updateScore()
	 * @param x La coordonnée horizontale de la tuile actuelle.
	 * @param y La coordonnée verticale de la tuile actuelle.
	 * @param nextX La coordonnée horizontale de la prochaine tuile selon la direction donnée.
	 * @param nextY La coordonnée verticale de la prochaine tuile selon la direction donnée.
	 * @param t La tuile permettant de stocker la valeur fusionnée.
	 * @return Un booléen indiquant si cette tuile peut se déplacer.
	 */
	public boolean testerDeplacement(int x, int y, int nextX, int nextY, Tuile t) {
		int tValeur = t.getValeur();
		int actuelleValeur = plateau[x][y].getValeur();
		if(plateau[x][y].estAuBord(nextX, nextY)) {
			if(plateau[x][y].equals(t) && !plateau[x][y].getFusion()) {
				plateau[x][y].upgrade(tValeur);
				plateau[x][y].setFusion(true);
				score += tValeur * 2;
				if(tValeur * 2 == 2048 && !jeuFini) {
					jeuFini = true;
					f.recommencer();
				}
				f.updateScore();
				return true;
			} else if (actuelleValeur == 0) {
				plateau[x][y].upgrade(tValeur);
				return true;
			}
			return false;
		}	
		else if(actuelleValeur == 0) {
			if (!testerDeplacement(x + nextX, y + nextY, nextX, nextY, t)) {
				plateau[x][y].upgrade(tValeur);
			}
			return true;
		} else {
			if(plateau[x][y].equals(t) && !plateau[x][y].getFusion()) {
				plateau[x][y].upgrade(tValeur);
				plateau[x][y].setFusion(true);
				score += tValeur * 2;
				if(tValeur * 2 == 2048 && !jeuFini) {
					jeuFini = true;
					f.recommencer();
				}
				f.updateScore();
				return true;
			} else
				return false;
		}
	}
	
	/**
	 * Déplace les tuiles du plateau selon la direction donnée en paramètre.
	 * @see Tuile#getValeur()
	 * @see Tuile#upgrade(int)
	 * @see Tuile#setFusion(boolean)
	 * @see Jeu#genererTuile()
	 * @param x La direction horizontale empruntée par le joueur.
	 * @param y La direction verticale empruntée par le joueur.
	 */
	public void deplacer(int x, int y) {
		boolean deplacement = false;
		if(x > 0 || y > 0) {
			for(int i = Constantes.cote - 1; i >= 0; i--)
				if(i + x >= 0 && i + x < Constantes.cote)
					for(int j = Constantes.cote - 1; j >= 0; j--)
						if(j + y >= 0 && j + y < Constantes.cote)
							if(plateau[i][j].getValeur() > 0)
								if(testerDeplacement(i + x, j + y, x, y, plateau[i][j])) {
									deplacement = true;
									plateau[i][j].upgrade(0);
								}
		} else {
			for(int i = 0; i < Constantes.cote; i++)
				if(i + x >= 0 && i + x < Constantes.cote)
					for(int j = 0; j < Constantes.cote; j++)
						if(j + y >= 0 && j + y < Constantes.cote)
							if(plateau[i][j].getValeur() > 0)
								if(testerDeplacement(i + x, j + y, x, y, plateau[i][j])) {
									deplacement = true;
									plateau[i][j].upgrade(0);
								}
		}
		for(int i = 0; i < Constantes.cote; i++)
			for(int j = 0; j < Constantes.cote; j++)
				plateau[i][j].setFusion(false);
		if(deplacement)
			genererTuile();
	}
	
	/*public int testerDeplacementIA(int x, int y, int nextX, int nextY, Tuile t) {
		int tValeur = t.getValeur();
		int actuelleValeur = plateau[x][y].getValeur();
		if(plateau[x][y].estAuBord(nextX, nextY)) {
			if(plateau[x][y].equals(t) && !plateau[x][y].getFusion()) {
				plateau[x][y].upgrade(tValeur);
				plateau[x][y].setFusion(true);
				return tValeur;
			} else if (actuelleValeur == 0) {
				plateau[x][y].upgrade(tValeur);
				return tValeur;
			}
			return 0;
		}	
		else if(actuelleValeur == 0) {
			if (testerDeplacementIA(x + nextX, y + nextY, nextX, nextY, t) == 0) {
				plateau[x][y].upgrade(tValeur);
			}
			return tValeur;
		} else {
			if(plateau[x][y].equals(t) && !plateau[x][y].getFusion()) {
				plateau[x][y].upgrade(tValeur);
				plateau[x][y].setFusion(true);
				return tValeur;
			} else
				return 0;
		}
	}
	
	public int deplacerIA(int x, int y) {
		int max = 0, tmp;
		if(x > 0 || y > 0) {
			for(int i = Constantes.cote - 1; i >= 0; i--)
				if(i + x >= 0 && i + x < Constantes.cote)
					for(int j = Constantes.cote - 1; j >= 0; j--)
						if(j + y >= 0 && j + y < Constantes.cote) {
							if(plateau[i][j].getValeur() > 0) {
								tmp = testerDeplacementIA(i + x, j + y, x, y, plateau[i][j]);
								if(tmp > max) {
									max = tmp;
								}
							}
							if(max != 0) {
									plateau[i][j].upgrade(0);
								}
						}
		} else {
			for(int i = 0; i < Constantes.cote; i++)
				if(i + x >= 0 && i + x < Constantes.cote)
					for(int j = 0; j < Constantes.cote; j++)
						if(j + y >= 0 && j + y < Constantes.cote)
							if(plateau[i][j].getValeur() > 0) {
								tmp = testerDeplacementIA(i + x, j + y, x, y, plateau[i][j]);
								if(tmp > max) {
									max = tmp;
								}
								if(max != 0) {
									plateau[i][j].upgrade(0);
								}
							}
		}
		for(int i = 0; i < Constantes.cote; i++)
			for(int j = 0; j < Constantes.cote; j++)
				plateau[i][j].setFusion(false);
		return max;
	}*/
	
	/**
	 * Demande à l'IA de choisir une direction.
	 * @see Jeu#testerMort()
	 * @see Jeu#gameOver()
	 * @see Jeu#deplacer(int, int)
	 */
	public void jouerIA() {
		if(!testerMort()) {
			ia = false;
			gameOver();
		}
		int i = -1, j = 0;//, max, direction;
		/*int[] directions = new int[4];
		Tuile[][] p = new Tuile[Constantes.cote][Constantes.cote];
		for(int x = 0; x < Constantes.cote; x++) {
			for(int y = 0; y < Constantes.cote; y++) {
				p[x][y] = new Tuile(plateau[x][y].getY(), plateau[x][y].getX(), plateau[x][y].getValeur());
			}
		}
		directions[0] = deplacerIA(-1, 0);
		directions[1] = deplacerIA(1, 0);
		directions[2] = deplacerIA(0, -1);
		directions[3] = deplacerIA(0, 1);
		for(int x = 0; x < Constantes.cote; x++) {
			for(int y = 0; y < Constantes.cote; y++) {
				plateau[x][y] = p[x][y];
			}
		}
		max = directions[0];
		direction = 0;
		if(directions[1] > max) {
			max = directions[1];
			direction = 1;
		}
		if(directions[2] > max) {
			max = directions[2];
			direction = 2;
		}
		if(directions[3] > max) {
			max = directions[3];
			direction = 3;
		}*/
		Random rd = new Random();
		int rand;
		rand = rd.nextInt(4);
		if(rand == 0) {
			i = -1;
			j = 0;
		}
		if(rand == 1) {
			i = 1;
			j = 0;
		}
		if(rand == 2) {
			i = 0;
			j = -1;
		}
		if(rand == 3) {
			i = 0;
			j = 1;
		}
		deplacer(i, j);
	}
	
	/**
	 * Récupère l'évènement Clavier entré par le joueur.
	 * @see Jeu#Controle(KeyEvent)
	 * @param e La touche enfoncée par le joueur.
	 */
	public void gestion(KeyEvent e) {
		Controle(e);
	}
	
	/**
	 * Informe la fenêtre que le joueur a perdu.
	 * @see Fenetre#recommencer()
	 */
	public void gameOver() {
		perdu = true;
		f.recommencer();
	}
	
	/**
	 * Recommence une partie ou quitte le jeu, en fonction du choix du joueur.
	 * @see Jeu#initialiser()
	 * @see Fenetre#removeFin()
	 * @see Jeu#setPause()
	 * @param str Le choix du joueur.
	 */
	public void recommencer(String str) {
		if(str == "OUI") {
			f.dispose();
			initialiser();
		} else if(perdu)
			f.dispose();
		else {
			f.removeFin();
			this.setPause();
		}
	}
	
	/**
	 * Gère la touche entrée par le joueur.
	 * @param event L'évènement clavier entré par le joueur.
	 * @see Jeu#gameOver()
	 * @see Jeu#deplacer(int, int)
	 * @see Fenetre#afficherDescription(boolean)
	 * @see Jeu#initialiser()
	 */
	public void Controle(KeyEvent event) {
		int direction = event.getKeyCode();
		if(!testerMort()) {
			gameOver();
		}
		if(!perdu && !pause) {
			if(direction == KeyEvent.VK_UP)
				deplacer(-1, 0);
			else if(direction == KeyEvent.VK_DOWN)
				deplacer(1, 0);
			else if(direction == KeyEvent.VK_LEFT)
				deplacer(0, -1);
			else if(direction == KeyEvent.VK_RIGHT)
				deplacer(0, 1);
			else if(direction == KeyEvent.VK_H) {
				f.afficherDescription(description);
				description = !description;
			} else if(direction == KeyEvent.VK_J) {
				f.dispose();
				initialiser();
			} else if(direction == KeyEvent.VK_SPACE) {
				ia = !ia;
			}
			else if(direction == KeyEvent.VK_ESCAPE)
				f.dispose();
		}
	}
	
	/**
	 * Redessine chaque tuiles.
	 * @see Tuile#affichage(Graphics)
	 * @param g Le contexte graphique du plateau.
	 */
	public void affichage(Graphics g) {
		for(int i = 0; i < Constantes.cote; i++)
			for(int j = 0; j < Constantes.cote; j++)
				plateau[i][j].affichage(g);
	}
}
