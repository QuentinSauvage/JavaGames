package fr.univ_artois;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JPanel;

/**
 * La classe PanneauDifficulte gère les éléments graphiques relatifs au choix de la difficulté.
 * @author quentin sauvage
 *
 */
public class PanneauDifficulte extends JPanel {
	
	/**
	 * L'ID du panneau.
	 */
	private static final long serialVersionUID = 1L;
	
	/**
	 * Les informations relatives au jeu.
	 * @see Jeu
	 * @see PanneauDifficulte#PanneauDifficulte(Jeu, int, int)
	 */
	private Jeu jeu;
	
	/**
	 * Un JPanel contenant les boutons proposant une difficulté.
	 * @see PanneauDifficulte#PanneauDifficulte(Jeu, int, int)
	 */
	private JPanel btnDiff;
	
	/**
	 * Construit le panneau grâce au jeu, à une longueur et à une hauteur.
	 * @param j Le jeu permettant d'accéder au mot et au joueur.
	 * @param w La longueur servant à initialiser celle des boutons.
	 * @param h La hauteur servant à initialiser celle des boutons.
	 * @see PanneauDifficulte#btnDiff
	 * @see PanneauDifficulte#jeu
	 * @see BoutonDifficulte
	 */
	public PanneauDifficulte(Jeu j, int w, int h){
		jeu = j;
		btnDiff = new JPanel();
		this.setLayout(new BorderLayout());
		btnDiff.setLayout(new BorderLayout());
		this.setPreferredSize(new Dimension(400, 200));
		btnDiff.add(new BoutonDifficulte(jeu, "FACILE", Color.GREEN, w, h), BorderLayout.WEST);
		btnDiff.add(new BoutonDifficulte(jeu, "MOYEN", Color.YELLOW, w, h), BorderLayout.CENTER);
		btnDiff.add(new BoutonDifficulte(jeu, "DIFFICILE", Color.RED, w, h), BorderLayout.EAST);
		this.add(btnDiff, BorderLayout.SOUTH);
	}
	
	/**
	 * Dessine les éléments graphiques de PanneauDifficulte.
	 */
	public void paintComponent(Graphics g){
		Graphics2D g2d = (Graphics2D)g;
		g2d.setFont(new Font(g2d.getFont().getFontName(), Font.PLAIN, 15));
		GradientPaint gp;
		gp = new GradientPaint(0, 0, Color.BLACK, 50, 50, Color.DARK_GRAY, true);
		g2d.setPaint(gp);
		g2d.fillRect(0, 0, this.getWidth(), this.getHeight());
		g2d.setColor(Color.WHITE);
		g2d.drawString("Bienvenue dans le jeu du pendu !", 50, 50);
		g2d.setFont(new Font(g2d.getFont().getFontName(), Font.PLAIN, 12));
		g2d.drawString("Veuillez choisir une difficulté ci-dessous :", 50, 100);
	}
}
