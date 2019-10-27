package fr.univ_artois;

import java.awt.Color;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

/**
 * La classe ToucheClavier gère le graphisme des touches du clavier.
 * @author quentin sauvage
 *
 */
public class ToucheClavier extends JButton implements ActionListener{
	
	/**
	 * L'ID du bouton.
	 */
	private static final long serialVersionUID = 1L;
	
	/**
	 * Les informations relatives au jeu.
	 * @see Jeu
	 * @see ToucheClavier#ToucheClavier(Jeu, String)
	 * @see ToucheClavier#actionPerformed(ActionEvent)
	 */
	private Jeu jeu;
	
	/**
	 * Le nom de la touche.
	 * @see ToucheClavier#ToucheClavier(Jeu, String)
	 * @see ToucheClavier#actionPerformed(ActionEvent)
	 * @see ToucheClavier#paintComponent(Graphics)
	 */
	private String nom;

	/**
	 * Construit la touche en lui donnant le jeu et son nom.
	 * @param j Le jeu permettant d'accéder au mot et au joueur.
	 * @param s Le nom de la touche.
	 * @see Jeu
	 * @see ToucheClavier#jeu
	 * @see ToucheClavier#nom
	 */
	public ToucheClavier(Jeu j, String s){
		jeu = j;
		nom = s;
		this.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent arg0) {
				ToucheClavier.this.actionPerformed(arg0);
			}  
		});
	}
	
	/**
	 * Vérifie si la lettre donnée se trouve dans le mot.
	 * Si la touche est celle de l'abandon, cette méthode demande au jeu de s'arrêter.
	 * @param arg0 L'évènement produit.
	 * @see ToucheClavier#jeu
	 * @see ToucheClavier#nom
	 */
	@Override
	public void actionPerformed(ActionEvent arg0) {
		if(!this.isSelected()){
			this.setSelected(true);
			if(nom != ""){
				if(nom == "ABANDON")
					jeu.demanderRecommencer(false);
				else
					jeu.verifierLettre(nom);
			}
		}
	}
	
	/**
	 * Dessine les éléments graphiques de la touche.
	 * @param g Le contexte graphique de la touche.
	 */
	public void paintComponent(Graphics g){
		Graphics2D g2d = (Graphics2D)g;
		GradientPaint gp;
		if(this.isSelected())
			gp = new GradientPaint(0, 0, Color.RED, 50, 50, Color.CYAN.darker(), true);
		else
			gp = new GradientPaint(0, 0, Color.BLACK, 50, 50, Color.CYAN.darker(), true);
		g2d.setPaint(gp);
		g2d.fillRect(0, 0, this.getWidth(), this.getHeight());
		g2d.setColor(Color.YELLOW);
		g2d.drawString(nom, this.getWidth() / 2 / 4, (this.getHeight() / 2 + 5));
	}

}
