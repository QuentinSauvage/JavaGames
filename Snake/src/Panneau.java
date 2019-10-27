import java.awt.Dimension;
import java.awt.Graphics;

import javax.swing.JPanel;

public class Panneau extends JPanel implements Constantes{
	private static final long serialVersionUID = 1L;
	private Jeu jeu;
	
	public Panneau(Jeu j){
		jeu = j;
		int h = nbColonnes * nbPixels;
		int l = nbLignes * nbPixels;
		this.setPreferredSize(new Dimension(l, h));
	}
	
	@Override
	public void paintComponent(Graphics g){
		jeu.affichage(g);
	}
}
