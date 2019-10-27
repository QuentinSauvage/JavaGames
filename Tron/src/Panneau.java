import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;

import javax.swing.JPanel;

public class Panneau extends JPanel {
	private static final long serialVersionUID = 1L;
	private Jeu jeu;
	
	public Panneau(Jeu j){
		jeu = j;
		int h = Constantes.nbColonnes * Constantes.nbPixels;
		int l = Constantes.nbLignes * Constantes.nbPixels;
		this.setBackground(Color.BLACK);
		this.setPreferredSize(new Dimension(l, h));
		this.setFocusable(true);
	}
	
	@Override
	public void paintComponent(Graphics g){
		g.setColor(Color.BLACK);
		g.fillRect(0,  0,  this.getWidth(), this.getHeight());
		g.setColor(Color.WHITE);
		for(int i = 0; i < Constantes.nbPixels; i++) {
			g.drawLine(i*Constantes.nbPixels, 0, i*Constantes.nbPixels, this.getHeight());
			g.drawLine(0,  i*Constantes.nbPixels,  this.getWidth(), i*Constantes.nbPixels);
		}
		jeu.affichage(g);
	}
}
