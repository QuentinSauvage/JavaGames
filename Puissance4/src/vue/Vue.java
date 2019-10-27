package vue;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridLayout;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import controler.AbstractControler;
import initialisation.Debut;
import initialisation.Proprietes;
import model.AbstractModel;
import model.Puissance4;
import observer.Observer;


/**
 * Gère les éléments graphiques du plateau de jeu.
 * @author Quentin Sauvage
 *
 */
@SuppressWarnings("serial")
public class Vue extends JFrame implements Observer {
	
	/**
	 * Contient tous les éléments graphiques du jeu.
	 */
	private JPanel container = new JPanel();
	
	/**
	 * Contient la liste des pions à dessiner.
	 * @see Pion
	 */
	private Pion[][] pions;
	
	/**
	 * Le controler permettant de gérer les actions du joueur.
	 * @see AbstractControler
	 */
	private AbstractControler controler;
	
	/**
	 * JPanel qui dessine la grille du jeu, faisant X colonnes et Y lignes.
	 * @see Grille
	 */
	protected Grille grille;
	
	/**
	 * Construit la Vue à partir d'un controler gérant les actions et d'un modele contenant les infos du jeu.
	 * @param c Le controler qui gère les évènements.
	 * @param modele Le modele qui possede les attributs du jeu.
	 * @see AbstractControler
	 * @see AbstractModel
	 */
	public Vue(AbstractControler c, AbstractModel modele) {
		this.setSize(new Dimension(200 + 100 * Proprietes.nbColonnes, 100 * (Proprietes.nbLignes + 3)));
		this.setTitle("Puissance 4");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setLocationRelativeTo(null);
		this.setResizable(false);
		container.setBackground(Color.WHITE);
		pions = new Pion[Proprietes.nbLignes][Proprietes.nbColonnes];
		grille = new Grille();
		for(int i = 0; i < Proprietes.nbLignes; i++) {
			for(int j = 0; j < Proprietes.nbColonnes; j++) {
				pions[i][j] = new Pion();
				grille.add(pions[i][j]);
			}
		}
		controler = c;
		Top top = new Top(modele);
		container.add(top);
		container.add(grille);
		this.setContentPane(container);
		this.setVisible(true);
	}
	
	/**
	 * Permet d'indiquer en haut de la fenêtre que le joueur X doit jouer. 
	 * @author Quentin Sauvage
	 */
	class Top extends JPanel {
		/**
		 * JLabel indiquant qui doit jouer.
		 */
		private JLabel joueur = new JLabel();
		
		/**
		 * Le modele permet d'acccéder au tour actuel.
		 * @see Puissance4
		 */
		private Puissance4 modele;
		
		/**
		 * Construit le Top à partir du modele permettant de connaitre le tour actuel.
		 * @param modele Le jeu possédant toutes les infos de ce dernier.
		 * @see AbstractModel
		 */
		public Top(AbstractModel modele) {
			super();
			this.setPreferredSize(new Dimension(100 * Proprietes.nbColonnes, 100));
			joueur.setFont(new Font("Impact", Font.BOLD, 30));
			this.add(joueur);
			this.modele = (Puissance4) modele;
		}
		
		protected void paintComponent(Graphics g) {
			joueur.setText("Joueur " + modele.getTour() + " : ");
		}
	}
	
	/**
	 * Dessine la grille de jeu ainsi que tous les pions compris dedans.
	 * @author Quentin Sauvage
	 */
	class Grille extends JPanel {
		private int couleurJoueur;
		
		/**
		 * Construit la grille du jeu selon les paramètres rentrés auparavant.
		 * @see Proprietes
		 */
		public Grille() {
			super();
			this.setPreferredSize(new Dimension(100 * Proprietes.nbColonnes, 100 * (Proprietes.nbLignes + 1)));
			this.setLayout(new GridLayout(Proprietes.nbLignes + 1, Proprietes.nbColonnes));
			this.addMouseListener(new JeuListener());
			this.addMouseMotionListener(new DeplacementSouris(this));
			couleurJoueur = 1;
			for(int i = 0; i < Proprietes.nbColonnes; i++)
				this.add(new Pion());
		}
		
		/**
		 * Permet d'accéder à la couleur du joueur actuel.
		 * @return La couleur du joueur actuel.
		 */
		public int getCouleurJoueur() {
			return couleurJoueur;
		}
		
		/**
		 * Modifie la couleur du joueur actuel.
		 * @param i La nouvelle couleur à utiliser.
		 */
		public void setCouleurJoueur(int i) {
			couleurJoueur += i;
			if(couleurJoueur > 2)
				couleurJoueur = 1;
		}
		
		/**
		 * Fait apparaître le pion indicateur au-dessus de la nouvelle colonne survolée.
		 * @param x La colonne survolée par la souris.
		 * @see Proprietes
		 * @see Pion
		 */
		public void modifierIndicateur(int x) {
			Pion p;
			for(int i = 0; i < Proprietes.nbColonnes; i++) {
				p = (Pion) this.getComponentAt(i * 100, 0);
				if(i == x / 100)
					p.setValeur(couleurJoueur);
				else
					p.setValeur(0);
			}
		}
		
		protected void paintComponent(Graphics g) {
			Graphics2D g2d = (Graphics2D)g;
			GradientPaint gp;
			gp = new GradientPaint(0, 0, new Color(96, 96, 96), 50, 0, new Color(192, 192, 192), true);
			g2d.setPaint(gp);
			g2d.fillRect(0, 90, this.getWidth(), this.getHeight());
		}
	}
	
	/**
	 * Gère les déplacements de la souris
	 * @author Quentin Sauvage
	 */
	class DeplacementSouris implements MouseMotionListener {
		/**
		 * La grille permettant de gérer la position de la souris sur les colonnes.
		 * @see Grille
		 */
		private Grille grille;
		
		/**
		 * Construit DeplacementSouris à partir de la grille de la Vue.
		 * @param grille
		 * @see Grille
		 */
		public DeplacementSouris(Grille grille) {
			this.grille = grille;
		}
		
		@Override
		public void mouseDragged(MouseEvent me) {
			// TODO Auto-generated method stub	
		}

		/**
		 * Redessine l'indicateur au-dessus de la nouvelle colonne survolée par la souris.
		 */
		@Override
		public void mouseMoved(MouseEvent me) {
			// TODO Auto-generated method stub
			grille.modifierIndicateur(me.getX());
			repaint();
		}
		
	}
	
	/**
	 * Gère les clics de la souris.
	 * @author Quentin Sauvage
	 *
	 */
	class JeuListener implements MouseListener {
		/**
		 * Demande au controler de vérifier si l'action du joueur est correct, et de modifier le jeu si besoin.
		 */
		@Override
		public void mouseClicked(MouseEvent me) {
			controler.setJeu(me.getX() / 100);
		}

		@Override
		public void mouseEntered(MouseEvent me) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mouseExited(MouseEvent me) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mousePressed(MouseEvent me) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mouseReleased(MouseEvent me) {
			// TODO Auto-generated method stub
			
		}
	}
	
	/**
	 * Informe les joueurs que la partie est finie, et leur demande s'ils veulent recommencer.
	 * @param str La chaîne de caractères informant sur l'état du jeu.
	 */
	public void afficherFin(String str) {
		Object[] options = {"Oui !", "Non."};
		int entree = JOptionPane.showOptionDialog(null,str, "Partie finie", JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.INFORMATION_MESSAGE, null, options, options[0]);
		if(entree == 0) {
			this.dispose();
			Debut.lancerJeu();
		}
		else
			this.dispose();
	}
	
	/**
	 * Demande aux observers de se mettre à jour.
	 * @param x La colonne du nouveau pion.
	 * @param y La ligne du nouveau pion.
	 * @param tour Le tour actuel
	 * @param str La chaîne de caractères indiquant l'état du modèle aux observateurs.
	 */
	@Override
	public void update(int x, int y, int tour, String str) {
		pions[Proprietes.nbLignes - y][x].setValeur(tour);
		grille.setCouleurJoueur(1);
		grille.modifierIndicateur(x * 100);
		repaint();
		if(str != "ok")
			afficherFin(str + "\nVoulez-vous recommencer ?");
	}
}
