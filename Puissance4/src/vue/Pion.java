 package vue;

import java.awt.Color;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JPanel;

import initialisation.Proprietes;

/**
 * JPanel représentant un pion de la partie, de la couleur du joueur associé.
 * @author Quentin Sauvage
 */
@SuppressWarnings("serial")
public class Pion extends JPanel {
	/**
	 * Permet de définir à quel joueur appartient le pion, et donc ses couleurs.
	 */
	private int valeur;
	
	/**
	 * Permet d'accéder à la valeur du Pion.
	 * @return La valeur du pion.
	 */
	public int getValeur() {
		return valeur;
	}

	/**
	 * Modifie la valeur du pion.
	 * @param valeur La nouvelle valeur du pion.
	 */
	public void setValeur(int valeur) {
		this.valeur = valeur;
	}
	
	@Override
	protected void paintComponent(Graphics g) {
		Graphics2D g2d = (Graphics2D)g;
		GradientPaint gp;
		boolean pion = false;
		if(valeur == 1) {
			gp = new GradientPaint(0, 0, Proprietes.joueur12, 50, 50, Proprietes.joueur11, true);
			g2d.setPaint(gp);
			pion = true;
		} else if(valeur == 2) {
			gp = new GradientPaint(0, 0, Proprietes.joueur22, 50, 50, Proprietes.joueur21, true);
			g2d.setPaint(gp);
			pion = true;
		} else {
			g.setColor(Color.WHITE);
		}
		g.fillOval(10, 10, 70, 70);
		if(pion) {
			if((valeur == 1 && Proprietes.joueur11 == Color.BLACK) || (valeur == 2 && Proprietes.joueur21 == Color.GRAY))
				g.setColor(Color.WHITE);
			else
				g.setColor(Color.BLACK);
			g.drawOval(10, 10, 70, 70);
			g.drawOval(15, 15, 60, 60);
		}
	}
}
