package fr.univ_artois;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.BorderFactory;
import javax.swing.JPanel;

/**
 * La classe LabelPendu gère les JLabel à afficher dans la classe PanneauPendu.
 * @author quentin sauvage
 *
 */
public class LabelPendu extends JPanel{
	
	/**
	 * L'ID du panneau.
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Fixe une taille et une marge au LabelPendu.
	 */
	public LabelPendu() {
		this.setPreferredSize(new Dimension(650, 30));
		this.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
	}
	
	/**
	 * Dessine les éléments graphiques de PanneauPendu.
	 */
	public void paintComponent(Graphics g){
		Graphics2D g2d = (Graphics2D)g;
		g2d.setFont(new Font(g2d.getFont().getFontName(), Font.PLAIN, 15));
		GradientPaint gp;
		gp = new GradientPaint(0, 0, Color.DARK_GRAY, 50, 50, Color.GRAY, true);
		g2d.setPaint(gp);
		g2d.fillRoundRect(0, 0, this.getWidth(), this.getHeight(), 10, 50);
	}
}
