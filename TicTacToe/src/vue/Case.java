package vue;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;

import javax.swing.JPanel;

@SuppressWarnings("serial")
public class Case extends JPanel {
	private int valeur;
	
	public Case() {
		this.setPreferredSize(new Dimension(100, 100));
		valeur = -1;
	}
	
	public int getValeur() {
		return valeur;
	}
	
	public void setValeur(int valeur) {
		this.valeur = valeur;
	}
	
	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.drawLine(this.getWidth() - 1, 0, this.getWidth() - 1, this.getHeight() - 1);
		g.drawLine(0, this.getHeight() - 1, this.getWidth() - 1, this.getHeight() - 1);
		String s = "";
		g.setFont(new Font("Arial", Font.BOLD, 40));
		if(valeur == 0) {
			s = "O";
			g.setColor(Color.CYAN.darker());
		}
		else if(valeur == 1) {
			s = "X";
			g.setColor(Color.MAGENTA.brighter());
		}
		g.drawString(s, (this.getWidth() / 2) - 15, (this.getHeight() / 2) + 10);
	}
}
