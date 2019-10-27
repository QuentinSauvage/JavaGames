package fr.univ_artois;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 * La classe Fenetre gère les différents panneaux à afficher.
 * @author quentin sauvage
 *
 */
public class Fenetre extends JFrame{
	
	/**
	 * L'ID de la fenetre.
	 */
	private static final long serialVersionUID = 1L;
	
	/**
	 * Les informations relatives au jeu.
	 * @see Jeu
	 * @see Fenetre#Fenetre(Jeu)
	 * @see Fenetre#lancerJeu(String)
	 * @see Fenetre#afficherFin(boolean)
	 */
	private Jeu jeu;
	
	/**
	 * Le panneau demandant de choisir une difficulté.
	 * @see PanneauDifficulte
	 * @see Fenetre#Fenetre(Jeu)
	 */
	private PanneauDifficulte choixDifficulte;
	
	/**
	 * Le panneau principal du jeu, qui affiche le pendu et le clavier.
	 * @see PanneauJeu
	 * @see Fenetre#lancerJeu(String)
	 */
	private PanneauJeu pendu;
	
	/**
	 * Le panneau demandant au joueur s'il veut recommencer.
	 * @see PanneauFin
	 * @see Fenetre#afficherFin(boolean)
	 */
	private PanneauFin fin;
	
	/**
	 * Le constructeur de Fenetre. PanneauDifficulte est instancié ici.
	 * @param j Le jeu permettant d'accéder au mot et au joueur.
	 * @see Jeu
	 * @see PanneauDifficulte
	 * @see Fenetre#choixDifficulte
	 */
	public Fenetre(Jeu j){
		jeu = j;
		this.setTitle("Pendu");
		this.setSize(new Dimension(400, 200));
		this.setResizable(false);
		
		choixDifficulte = new PanneauDifficulte(jeu, this.getWidth(), this.getHeight());
		this.setContentPane(choixDifficulte);
		
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.pack();
		this.setVisible(true);
		
	}
	
	/**
	 * Permet d'afficher le cadre principal du jeu.
	 * @see PanneauJeu
	 * @see Fenetre#pendu
	 */
	public void lancerJeu(){
		this.setSize(new Dimension(700, 700));
		pendu = new PanneauJeu(jeu);
		this.setContentPane(pendu);
		this.setLocationRelativeTo(null);
		this.repaint();
		this.revalidate();
	}
	
	/**
	 * Affiche l'écran de fin du jeu.
	 * @param gagne Un booléen indiquant si le joueur a trouvé le mot.
	 * @see PanneauFin
	 * @see Fenetre#fin
	 */
	public void afficherFin(boolean gagne){
		this.setSize(new Dimension(500, 200));
		fin = new PanneauFin(jeu, this.getWidth(), this.getHeight(), gagne);
		this.setLocationRelativeTo(null);
		this.setContentPane(fin);
		this.repaint();
		this.revalidate();
	}
	
	/**
	 * Affiche un message pour dire au revoir au joueur.
	 */
	public void quitter(){
		this.setSize(new Dimension(200, 100));
		
		/**
		 * MessageExit définit le panneau contenant le message d'au revoir.
		 * @author quentin
		 *
		 */
		class MessageExit extends JPanel {
			
			/**
			 * L'ID du panneau.
			 */
			private static final long serialVersionUID = 1L;
			
			/**
			 * Ce constructeur définit le modèle d'affichage du panneau.
			 */
			public MessageExit(){
				this.setLayout(new BorderLayout());
			}
			
			/**
			 * Peint les éléments du panneau dans la fenêtre.
			 */
			public void paintComponent(Graphics g){
				Graphics2D g2d = (Graphics2D)g;
				g2d.setFont(new Font(g2d.getFont().getFontName(), Font.PLAIN, 15));
				GradientPaint gp;
				gp = new GradientPaint(0, 0, Color.BLACK, 50, 50, Color.DARK_GRAY, true);
				g2d.setPaint(gp);
				g2d.fillRect(0, 0, Fenetre.this.getWidth(), Fenetre.this.getHeight());
				g2d.setColor(Color.WHITE);
				g2d.drawString("Au revoir !", 50, 50);
			}
		}
		MessageExit me = new MessageExit();
		this.setContentPane(me);
		this.setLocationRelativeTo(null);
		this.repaint();
		this.revalidate();
	}
}
