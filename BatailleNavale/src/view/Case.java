 package view;

import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JPanel;

/**
 * Une case du plateau.
 * @author Quentin Sauvage
 *
 */
@SuppressWarnings("serial")
public class Case extends JPanel {

	/**
	 * La valeur de la case.
	 */
	private int valeur;

	/**
	 * Constructeur vide de Case.
	 */
	public Case() {
		valeur = 0;
	}
	
	/**
	 * Construit la case à partir d'une valeur.
	 * @param valeur La valeur de la case.
	 */
	public Case(int valeur) {
		this.valeur = valeur;
	}
	
	/**
	 * Getter de valeur.
	 * @return la valeur de la case.
	 */
	public int getValeur() {
		return valeur;
	}

	/**
	 * Setter de valeur.
	 * @param valeur La nouvelle valeur de la case.
	 */
	public void setValeur(int valeur) {
		this.valeur = valeur;
	}
	
	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.setColor(Color.CYAN);
		if(valeur == 2) {
			g.setColor(Color.BLACK);
		}
		g.fillRect(0, 0, this.getWidth(), this.getHeight());
		g.setColor(Color.BLACK);
		g.drawLine(0, 0, this.getWidth() - 1, 0);
		g.drawLine(0, 0, 0, this.getHeight() - 1);
		g.drawLine(this.getWidth() - 1, 0, this.getWidth() - 1, this.getHeight() - 1);
		g.drawLine(0, this.getHeight() - 1, this.getWidth() - 1, this.getHeight() - 1);
		if(valeur == 3) {
			g.drawLine(0,  0, this.getWidth() - 1, this.getHeight() - 1);
			g.drawLine(this.getWidth() - 1,  0, 0, this.getHeight() - 1);
		}
	}
}
