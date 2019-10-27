package fr.univ_artois;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JPanel;

/**
 * La classe PanneauFin gère les éléments graphiques relatifs à l'écran de fin.
 * @author quentin sauvage
 *
 */
public class PanneauFin extends JPanel {
	
	/**
	 * L'ID du panneau.
	 */
	private static final long serialVersionUID = 1L;
	
	/**
	 * Les informations relatives au jeu.
	 * @see Jeu
	 */
	private Jeu jeu;
	
	/**
	 * Un JPanel contenant les boutons proposant de recommencer ou non.
	 * @see PanneauFin#PanneauFin(Jeu, int, int, boolean)
	 */
	private JPanel posBtns;
	
	/**
	 * Le message affiché selon le résultat du joueur
	 * @see PanneauFin#PanneauFin(Jeu, int, int, boolean)
	 * @see PanneauFin#paintComponent(Graphics)
	 */
	private String txt;
	
	/**
	 * Construit le panneau grâce au jeu, à une longueur et à une hauteur.
	 * @param j Le jeu permettant d'accéder au mot et au joueur.
	 * @param w La longueur servant à initialiser celle des boutons.
	 * @param h La hauteur servant à initialiser celle des boutons.
	 * @param gagne Le resultat du joueur.
	 * @see PanneauFin#posBtns
	 * @see PanneauFin#jeu
	 * @see BoutonFin
	 */
	public PanneauFin(Jeu j, int w, int h, boolean gagne){
		jeu = j;
		posBtns = new JPanel();
		this.setLayout(new BorderLayout());
		posBtns.setLayout(new BorderLayout());
		posBtns.add(new BoutonFin(jeu, "Oui !", w, h), BorderLayout.WEST);
		posBtns.add(new BoutonFin(jeu, "Non...", w, h), BorderLayout.EAST);
		this.add(posBtns, BorderLayout.SOUTH);
		if(gagne)
			txt = "Félicitations, vous avez deviné le mot !";
		else
			txt = "Dommage, vous avez perdu ! Le mot était " + jeu.getMot().getMot();
	}
	
	/**
	 * Dessine les éléments graphiques de PanneauFin.
	 * @see PanneauFin#txt
	 * @see Jeu#getJoueur()
	 * @see Joueur#getVictoires()
	 */
	public void paintComponent(Graphics g){
		Graphics2D g2d = (Graphics2D)g;
		g2d.setFont(new Font(g2d.getFont().getFontName(), Font.PLAIN, 15));
		GradientPaint gp;
		gp = new GradientPaint(0, 0, Color.BLACK, 50, 50, Color.DARK_GRAY, true);
		g2d.setPaint(gp);
		g2d.fillRect(0, 0, this.getWidth(), this.getHeight());
		g2d.setColor(Color.WHITE);
		g2d.drawString(txt, 10, 50);
		g2d.setFont(new Font(g2d.getFont().getFontName(), Font.PLAIN, 12));
		g2d.drawString("Série de victoires : " + jeu.getJoueur().getVictoires(), 50, 75);
		g2d.drawString("Voulez-vous recommencer ?", 50, 100);
	}
}
