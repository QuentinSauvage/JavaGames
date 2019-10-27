import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.KeyEvent;
import java.util.Random;

import javax.swing.JPanel;

/**
 * Jeu gère tous les élements composant le jeu et son déroulement.
 * @author Quentin Sauvage
 *
 */
@SuppressWarnings("serial")
public class Jeu extends JPanel {
	/**
	 * Regroupe la balle de départ et les quatre balles bonus pouvant apparaître au cours de la partie.
	 */
	private Balle listeBalles[];
	
	/**
	 * Le nombre de balles actuellement présentes sur le plateau permet de savoir s'il faut encore générer des balles bonus ou non.
	 */
	private int nbBalles;
	
	/**
	 * La barre en haut du plateau, empêchant les balles de sortir du côté supérieur.
	 */
	private Objet barreHaut;
	
	/**
	 * La barre en bas du plateau, empêchant les balles de sortir du côté inférieur.
	 */
	private Objet barreBas;
	
	/**
	 * La barre qui représente le joueur 1.
	 */
	public Objet joueur1;
	
	/**
	 * La barre qui représente le joueur 2.
	 */
	public Objet joueur2;
	
	/**
	 * Le score du joueur 1.
	 */
	private int score1;
	
	/**
	 * Le score du joueur 2.
	 */
	private int score2;
	
	/**
	 * Entier représentant le joueur qui vient de perdre la manche, utile pour initialiser une manche.
	 */
	private int joueurPerdant;
	
	/**
	 * Le chrono générant des balles.
	 */
	private ChronoBonus bonus;
	
	/**
	 * Booléen qui permet de savoir si la partie est finie ou non.
	 */
	private boolean finDePartie;
	
	/**
	 * Booléen indiquant si une manche est en cours (la balle de départ se déplace).
	 */
	private boolean enCours;
	
	/**
	 * Booléen qui informe sur la présence de l'IA.
	 */
	private boolean ia;
	
	/**
	 * Le constructeur du jeu instancie les balles et autres objets du jeu.
	 * @param ia La présence ou non de l'IA, selon le choix du joueur.
	 * @see Objet
	 * @see Balle
	 * @see Clavier
	 * @see ChronoBonus
	 * @see Chrono
	 */
	public Jeu(boolean ia) {
		super();
		this.ia = ia;
		if(ia)
			new IA();
		this.repaint();
		this.setSize(new Dimension(700, 360));
		this.setBackground(Color.BLACK);
		barreHaut = new Objet(5, 5, this.getWidth() - 10, 5);
		barreBas = new Objet(5, this.getHeight() - 10, this.getWidth() - 10, 5);
		joueur1 = new Objet(5, (this.getHeight() / 2) - 30, 5, 60);
		joueur2 = new Objet(this.getWidth() - 10, (this.getHeight() / 2) - 30, 5, 60);
		listeBalles = new Balle[5];
		nbBalles = 1;
		Random rd = new Random();
		double y = rd.nextFloat();
		int pos = rd.nextInt(2);
		if(pos == 0) {
			y = -y;
		}
		listeBalles[0] = new Balle(joueur1.getX() + joueur1.getLargeur() + 4, this.getHeight() / 2, 10, 10, 1, y, false, true);
		for(int i = 1; i < 5; i++) {
			listeBalles[i] = new Balle(this.getWidth() / 2, this.getHeight() / 2, 20, 20, 0, 0, true, false);
		}
		score1 = 0;
		score2 = 0;
		joueurPerdant = 1;
		enCours = false;
		finDePartie = false;
		this.setFocusable(true);  
		this.requestFocusInWindow();
		this.addKeyListener(new Clavier());
		bonus = new ChronoBonus();
		new Chrono();
	}
	
	/**
	 * Permet de savoir si la partie est finie.
	 * @return Booléen indiquant si un joueur a gagné.
	 */
	public boolean isFinDePartie() {
		return finDePartie;
	}

	/**
	 * Met à jour l'état de la partie.
	 * @param finDePartie Le nouvel état "finDePartie" du jeu.
	 */
	public void setFinDePartie(boolean finDePartie) {
		this.finDePartie = finDePartie;
	}
	
	/**
	 * Permet de savoir si la manche est en cours ou n'a pas encore commencé.
	 * @return Booléen indiquant si le joueur perdant a commencé à se déplacer.
	 */
	public boolean getEnCours() {
		return enCours;
	}
	
	/**
	 * Met à jour l'état de la manche.
	 * @param b Le nouvel état "enCours" de la manche..
	 */
	public void setEnCours(boolean b) {
		enCours = b;
	}
	
	/**
	 * Indique combien de balles sont visibles sur le plateau.
	 * @return le nombre de balles bonus ou en déplacement, présentes dans le jeu.
	 */
	public int getNbBalles() {
		return nbBalles;
	}
	
	/**
	 * Met à jour le nombre de balles visibles.
	 * @param x Le nouveau nombre de balles visibles.
	 */
	public void setNbBalles(int x) {
		nbBalles = x;
	}
	
	/**
	 * Rend visible une balle sur le jeu, sous forme de bonus.
	 * @param x L'abscisse de la nouvelle balle.
	 * @param y L'ordonnée de la nouvelle balle.
	 * @param dy La direction verticale de la nouvelle balle.
	 * @see Objet#setX(double)
	 * @see Objet#setY(double)
	 * @see Balle#setDy(double)
	 * @see Balle#setVisible(boolean)
	 */
	public void addBalle(int x, int y, double dy) {
		listeBalles[nbBalles].setX(x);
		listeBalles[nbBalles].setY(y);
		listeBalles[nbBalles].setDy(dy);
		listeBalles[nbBalles].setVisible(true);
		nbBalles++;
	}
	
	/**
	 * Indique l'état de l'IA.
	 * @return l'état "IA" du jeu, vrai si elle est activée, faux sinon.
	 */
	public boolean getIA() {
		return ia;
	}
	
	/**
	 * Met à jour l'état "IA" du jeu.
	 * @param ia Le nouvel état "IA" du jeu.
	 */
	public void setIA(boolean ia) {
		this.ia = ia;
	}
	
	/**
	 * Trouve la balle horizontalement la plus proche du joueur n°2.
	 * @return La balle la plus proche du joueur 2, sans tenir compte de la vitesse.
	 * @see Balle#getDx()
	 * @see Balle#getX()
	 */
	public Balle getBalleProche() {
		Balle b = null;
		double xMax = 0;
		for(int i = 0; i < 5; i++) {
			if(listeBalles[i].getDx() == 1 && listeBalles[i].getX() > xMax) {
				xMax = listeBalles[i].getX();
				b = listeBalles[i];
			}
		}
		return b;
	}
	
	/**
	 * Modifie la position verticale des joueurs, selon la touche entrée.
	 * @param j L'indice du joueur.
	 * @param d La direction du joueur.
	 * @see Objet#getY()
	 * @see Objet#getHauteur()
	 * @see Objet#setY(double)
	 */
	public void bouger(int j, int d) {
		if(j == 0) {
			if((d == -1 && joueur1.getY() > barreHaut.getY() + barreHaut.getHauteur()) || (d == 1 && joueur1.getY() + joueur1.getHauteur() < barreBas.getY()))
				joueur1.setY(joueur1.getY() + d);
		} else {
			if((d == -1 && joueur2.getY() > barreHaut.getY() + barreHaut.getHauteur()) || (d == 1 && joueur2.getY() + joueur2.getHauteur() < barreBas.getY()))
				joueur2.setY(joueur2.getY() + d);
		}
	}
	
	/**
	 * Détermine si la manche vient de commencer.
	 * @param eKeyCode Le code correspondant à la touche entrée.
	 */
	public void lancerManche(int eKeyCode) {
		if((joueurPerdant == 2 && (eKeyCode == KeyEvent.VK_UP || eKeyCode == KeyEvent.VK_DOWN)) || (joueurPerdant == 1 && (eKeyCode == KeyEvent.VK_Z || eKeyCode == KeyEvent.VK_S)))
			setEnCours(true);
	}
	
	/**
	 * Demande à la fenêtre d'afficher le panneau de fin de jeu.
	 * @param joueur L'indice du joueur gagnant.
	 * @see Fenetre#afficherFin(int)
	 */
	public void afficherFin(int joueur) {
		this.repaint();
		finDePartie = true;
		Main.fenetre.afficherFin(joueur);
	}
	
	/**
	 * Simule le trajet restant de la balle jusqu'au joueur 2.
	 * @param b La balle devant être simulée.
	 * @return La position Y de la balle lorsque celle-ci sera horizontalement égale à celle de la joueur 2.
	 * @see Objet#getX()
	 * @see Objet#getY()
	 */
	public double simulerTrajet(Balle b) {
		double x = b.getX();
		double y = b.getY();
		double dy = b.getDy();
		while(x + 10 < this.getWidth()) {
			if(y >= barreBas.getY())
				dy = -1;
			if(y <= barreHaut.getY())
				dy = 1;
			x++;
			y += dy;
		}
		return y;
	}
	
	/**
	 * Gère les éléments du jeu lorsqu'une manche se termine et détermine si la partie est finie.
	 * @param indice L'indice du joueur ayant remporté la manche.
	 * @see Objet#setY(double)
	 * @see Objet#getX()
	 * @see Objet#getLargeur()
	 * @see Jeu#afficherFin(int)
	 * @see Balle#setBonus(boolean)
	 * @see Balle#setVisible(boolean)
	 * @see Balle#setLargeur(int)
	 * @see Balle#setHauteur(int)
	 * @see Balle#setSleepTimer(int)
	 * @see Balle#setLimiteVitesse(int)
	 * @see Balle#setDx(double)
	 * @see Balle#setLimiteVitesse(int)
	 * @see ChronoBonus#setChrono(int)
	 */
	public void terminerManche(int indice) {
		joueur1.setY((this.getHeight() / 2) - 30);
		joueur2.setY((this.getHeight() / 2) - 30);
		int dx;
		double x;
		if(indice == 0) {
			score1++;
			if(score1 >= 7 && score1 - score2 > 1)
				afficherFin(1);
			joueurPerdant = 2;
			dx = -1;
			x = joueur2.getX() - 14;
		}
		else {
			score2++;
			if(score2 >= 7 && score2 - score1 > 1)
				afficherFin(2);
			joueurPerdant = 1;
			dx = 1;
			x = joueur1.getX() + joueur1.getLargeur() + 4;
		}
		setEnCours(false);
		for(int i = 1; i < 5; i++) {
			listeBalles[i].setBonus(true);
			listeBalles[i].setVisible(false);
			listeBalles[i].setLargeur(20);
			listeBalles[i].setHauteur(20);
			listeBalles[i].setSleepTimer(10);
			listeBalles[i].setLimiteVitesse(1000);
		}
		bonus.setChrono(25);
		listeBalles[0].setX(x);
		listeBalles[0].setY(this.getHeight() / 2);
		listeBalles[0].setDx(dx);
		listeBalles[0].setSleepTimer(10);
		listeBalles[0].setLimiteVitesse(1000);
		nbBalles = 1;
	}

	public void paintComponent(Graphics g) {
 		super.paintComponent(g);
 		Graphics2D g2 = (Graphics2D) g;
 		g2.setColor(Color.WHITE);
 		((Graphics2D) g2).setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
 		Font f = new Font("Symbola", Font.BOLD, 50);
        g2.setFont(f);
 		g2.drawString(Integer.toString(score1), 280, 50);
 		g2.drawString(Integer.toString(score2), 390, 50);
 		g.setColor(Color.WHITE.darker());
 		for(int i = 15; i < this.getHeight() - 10; i+=10)
 			g2.fillOval((this.getWidth() / 2) - 5, i, 5, 5);
 		for(int i = 0; i < 5; i++) {
 			if(listeBalles[i].isVisible() == true) {
 				for(int j = 0; j < 5; j++) {
 	 				if(i != j) {
 	 					if(listeBalles[i].proche(listeBalles[j]))
 	 						listeBalles[i].contact(listeBalles[j]);	
 	 				}
 	 			}
 				if(!listeBalles[i].isBonus())
 					g2.setColor(Color.YELLOW.brighter());
 				else {
 					g2.setColor(Color.RED.brighter());
 				}
		 		if(listeBalles[i].proche(joueur1))
		 			listeBalles[i].contact(joueur1);
		 		else if(listeBalles[i].proche(joueur2))
		 			listeBalles[i].contact(joueur2);
		 		if(listeBalles[i].proche(barreHaut))
		 			listeBalles[i].contact(barreHaut);
		 		else if(listeBalles[i].proche(barreBas))
		 			listeBalles[i].contact(barreBas);
		 		g2.fillOval((int)listeBalles[i].getX(), (int)listeBalles[i].getY(), listeBalles[i].getLargeur(), listeBalles[i].getHauteur());
 			}
 		}
 		GradientPaint gp;
 		gp = new GradientPaint(0, 0, Color.GREEN.brighter(), 20, 20, Color.CYAN.darker(), true);
		g2.setPaint(gp);
	 	g2.fillRect((int)barreHaut.getX(), (int)barreHaut.getY(), barreHaut.getLargeur(), barreHaut.getHauteur());
	 	g2.fillRect((int)barreBas.getX(), (int)barreBas.getY(), barreBas.getLargeur(), barreBas.getHauteur());
		gp = new GradientPaint(0, 0, Color.BLUE, 5, 5, Color.WHITE, true);
		g2.setPaint(gp);
 		g2.fillRect((int)joueur1.getX(), (int)joueur1.getY(), joueur1.getLargeur(), joueur1.getHauteur());
		gp = new GradientPaint(0, 0, Color.RED, 5, 5, Color.YELLOW, true);
		g2.setPaint(gp);
 		g2.fillRect((int)joueur2.getX(), (int)joueur2.getY(), joueur2.getLargeur(), joueur2.getHauteur());
	}
}
