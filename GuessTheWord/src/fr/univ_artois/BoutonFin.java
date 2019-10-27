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
 * La classe BoutonFin gère le graphisme des boutons proposant au joueur de recommencer ou non.
 * @author quentin sauvage
 *
 */
public class BoutonFin extends JButton implements ActionListener{
	
	/**
	 * L'ID du bouton.
	 */
	private static final long serialVersionUID = 1L;
	
	/**
	 * Les informations relatives au jeu.
	 * @see Jeu
	 * @see BoutonFin#BoutonFin(Jeu, String, int, int)
	 * @see BoutonFin#actionPerformed(ActionEvent)
	 */
	private Jeu jeu;
	
	/**
	 * La couleur de fond du bouton.
	 * @see BoutonFin#BoutonFin(Jeu, String, int, int)
	 * @see BoutonFin#paintComponent(Graphics)
	 */
	private Color couleurFond;
	
	/**
	 * La couleur d'écriture du bouton.
	 * @see BoutonFin#BoutonFin(Jeu, String, int, int)
	 * @see BoutonFin#paintComponent(Graphics)
	 */
	private Color couleurTxt;
	
	/**
	 * Construit le bouton en lui donnant un ActionListener.
	 * @param j Le jeu permettant d'accéder au mot et au joueur.
	 * @param nom Le nom du bouton.
	 * @param w La longueur du bouton.
	 * @param h La hauteur du bouton.
	 * @see Jeu
	 * @see BoutonFin#jeu
	 * @see BoutonFin#couleurFond
	 * @see BoutonFin#couleurTxt
	 */
	public BoutonFin(Jeu j, String nom, int w, int h){
		super(nom);
		jeu = j;
		if(nom == "Oui !"){
			couleurFond = Color.GREEN;
			couleurTxt = Color.WHITE;
		} else {
			couleurFond = Color.RED;
			couleurTxt = Color.BLACK;
		}
		this.setPreferredSize(new Dimension(w/2, h/5));
		this.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent arg0) {
				BoutonFin.this.actionPerformed(arg0);
			}  
		});
	}

	/**
	 * Informe le jeu du choix du joueur.
	 * @param arg0 L'évènement produit.
	 * @see Jeu
	 * @see Jeu#choixRecommencer(String)
	 */
	@Override
	public void actionPerformed(ActionEvent arg0) {
		jeu.choixRecommencer(this.getText());
	}
	
	/**
	 * Dessine les éléments graphiques de BoutonFin.
	 * @param g Le contexte graphique de BoutonFin.
	 */
	public void paintComponent(Graphics g){
		Graphics2D g2d = (Graphics2D)g;
		GradientPaint gp;
		gp = new GradientPaint(0, 0, couleurFond, 50, 50, couleurFond.darker(), true);
		g2d.setPaint(gp);
		g2d.fillRect(0, 0, this.getWidth(), this.getHeight());
		g2d.setColor(couleurTxt);
		g2d.drawString(this.getText(), this.getWidth() / 3, (this.getHeight() / 2));
	}
}
