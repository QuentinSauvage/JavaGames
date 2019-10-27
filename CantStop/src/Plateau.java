import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.util.ArrayList;

import javax.swing.JPanel;

@SuppressWarnings("serial")
public class Plateau extends JPanel {
	private Modele modele;
	
	public Plateau(Modele m) {
		super();
		modele = m;
		this.setPreferredSize(new Dimension(800, 800));
		this.setBackground(new Color(255, 255,204));
	}
	
	public void paintComponent(Graphics g) {
 		super.paintComponent(g);
 		((Graphics2D) g).setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
 		ArrayList<Voie> l = modele.getListeVoies();
 		Joueur joueur = null;
 		if(Main.fenetre != null) {
 			joueur = Main.fenetre.modele.getListeJoueurs()[Main.fenetre.modele.getControle()];
 			
 		}
 		for(int i = 0; i < 11; i++) {
 			for(int j = 0; j < l.get(i).getTaille(); j++) {
 				g.setColor(Color.GRAY.brighter());
 				//boolean present = false;
 
					g.fillOval(10 + ((l.get(i).getId() - 2) * 74), this.getHeight() - ((j * 55) + 60), 40, 40);
					g.setColor(Color.BLACK);
					g.drawString(Integer.toString(i + 2), 25 + (74 * i), this.getHeight() - 8);
					//if(present) {
					//	g.setColor(Color.WHITE);
					//	g.drawString("G", ((l.get(i).getId() - 2) * 74) + 25, this.getHeight() - ((j) * 55 + 35));
					//}
 				//}
 				if(j + 1 < l.get(i).getTaille()) {
 					g.setColor(Color.BLACK);
 					g.fillRect(25 + ((l.get(i).getId() - 2) * 74), this.getHeight() - ((j * 55) + 75), 10, 15);
 				}
 			}
 		}
 		if(joueur != null) {
 			for(Grimpeur gr : joueur.getListeGrimpeurs()) {
 				if(gr.getPosition() > 0) {
 					g.setColor(joueur.getColor());
 					g.fillOval(10 + ((gr.getVoie().getId() - 2) * 74), this.getHeight() - (((gr.getPosition() - 1) * 55) + 60), 40, 40);
 					g.setColor(Color.WHITE);
 					g.drawString("G", ((gr.getVoie().getId() - 2) * 74) + 25, this.getHeight() - ((gr.getPosition() - 1) * 55 + 35));
 				}
 			}
 			g.setColor(joueur.getColor());
 			for(Pion p : joueur.getListePions()) {
 				if(p.getPosition() > 0) {
 					g.fillOval(10 + ((p.getVoie().getId() - 2) * 74), this.getHeight() - (((p.getPosition() - 1) * 55) + 60), 40, 40);
 				}
 			}
 		}
 		g.setColor(Color.BLACK);
 		g.fillRect(this.getWidth() - 1, 1, 1, this.getHeight() - 1);
	}
}
