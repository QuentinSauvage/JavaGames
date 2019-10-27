package fr.univ_artois;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

/**
 * La classe BoutonDifficulte gère le graphisme des boutons proposant une difficulté.
 * @author quentin sauvage
 *
 */
public class BoutonDifficulte extends JButton implements ActionListener{
	
	/**
	 * L'ID du bouton.
	 */
	private static final long serialVersionUID = 1L;
	
	/**
	 * Les informations relatives au jeu.
	 * @see Jeu
	 * @see BoutonDifficulte#BoutonDifficulte(Jeu, String, Color, int, int)
	 * @see BoutonDifficulte#actionPerformed(ActionEvent)
	 */
	private Jeu jeu;
	
	/**
	 * La couleur de fond du bouton.
	 * @see BoutonDifficulte#BoutonDifficulte(Jeu, String, Color, int, int)
	 * @see BoutonDifficulte#paintComponent(Graphics)
	 */
	private Color color;
	
	/**
	 * Construit le bouton en lui donnant un ActionListener.
	 * @param j Le jeu permettant d'accéder au mot et au joueur.
	 * @param nom Le nom du bouton.
	 * @param c La couleur de fond du bouton.
	 * @param w La longueur du bouton.
	 * @param h La hauteur du bouton.
	 * @see Jeu
	 * @see BoutonDifficulte#jeu
	 * @see BoutonDifficulte#color
	 */
	public BoutonDifficulte(Jeu j, String nom, Color c, int w, int h){
		super(nom);
		jeu = j;
		color = c;
		this.setPreferredSize(new Dimension(w/3, h/5));
		this.addActionListener(new ActionListener(){
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				BoutonDifficulte.this.actionPerformed(arg0);
			}  
		});
	}

	/**
	 * Informe le jeu de la difficulté choisie.
	 * @param arg0 L'évènement produit.
	 * @see Jeu
	 * @see Jeu#creerJeu(String)
	 */
	@Override
	public void actionPerformed(ActionEvent arg0) {
		jeu.creerJeu(this.getText());
	}
	
	/**
	 * Dessine les éléments graphiques de BoutonDifficulte.
	 * @param g Le contexte graphique de BoutonDifficulte.
	 */
	public void paintComponent(Graphics g){
		Graphics2D g2d = (Graphics2D)g;
		GradientPaint gp;
		gp = new GradientPaint(0, 0, Color.DARK_GRAY, 50, 50, color.darker(), true);
		g2d.setPaint(gp);
		g2d.fillRect(0, 0, this.getWidth(), this.getHeight());
		g2d.setColor(color);
		g2d.drawString(this.getText(), this.getWidth() / 2 / 4, (this.getHeight() / 2 + 5));
	}
}
