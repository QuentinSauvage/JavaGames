package fr.univ_artois;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 * La classe PanneauPendu gère les éléments graphiques relatifs à l'interface du pendu.
 * @author quentin sauvage
 *
 */
public class PanneauPendu extends JPanel {
	
	/**
	 * L'ID du panneau.
	 */
	private static final long serialVersionUID = 1L;
	
	/**
	 * Les informations relatives au jeu.
	 * @see Jeu
	 * @see PanneauPendu#PanneauPendu(Jeu)
	 */
	private Jeu jeu;
	
	/**
	 * Un JLabel représentant le mot à trouver.
	 * @see PanneauPendu#PanneauPendu(Jeu)
	 * @see PanneauPendu#actualiser(String)
	 */
	private JLabel mot;
	
	/**
	 * Un JLabel représentant le nombre de vies restantes.
	 * @see PanneauPendu#PanneauPendu(Jeu)
	 * @see PanneauPendu#actualiser(String)
	 */
	private JLabel vies;
	
	/**
	 * Un JPanel contenant les JLabel à afficher.
	 * @see LabelPendu
	 * @see PanneauPendu#PanneauPendu(Jeu)
	 */
	private LabelPendu ctnLabel;
	
	/**
	 * Construit le panneau en lui donnant accès au jeu.
	 * @param j Le jeu permettant d'accéder au mot et au joueur.
	 * @see PanneauPendu#ctnLabel
	 * @see PanneauPendu#jeu
	 * @see PanneauPendu#mot
	 * @see PanneauPendu#vies
	 * @see Jeu#getMot()
	 * @see Mot#motADeviner()
	 * @see Jeu#getJoueur()
	 * @see Joueur#getVies()
	 */
	public PanneauPendu(Jeu j){
		jeu = j;
		ctnLabel = new LabelPendu();
		ctnLabel.setLayout(new BorderLayout());
		mot = new JLabel();
		vies = new JLabel();
		mot.setText("Mot à trouver : " + jeu.getMot().motADeviner());
		vies.setText("Nombre de vies restantes : " + jeu.getJoueur().getVies());
		mot.setForeground(Color.YELLOW);
		vies.setForeground(Color.YELLOW);
		ctnLabel.add(mot, BorderLayout.WEST);
		ctnLabel.add(vies, BorderLayout.EAST);
		this.add(ctnLabel);
	}
	
	/**
	 * Dessine les éléments graphiques de PanneauPendu.
	 */
	public void paintComponent(Graphics g){
		Graphics2D g2d = (Graphics2D)g;
		g2d.setFont(new Font(g2d.getFont().getFontName(), Font.PLAIN, 15));
		GradientPaint gp;
		gp = new GradientPaint(0, 0, Color.BLACK, 50, 50, Color.DARK_GRAY, true);
		g2d.setPaint(gp);
		g2d.fillRect(0, 0, this.getWidth(), this.getHeight());
		g2d.setColor(Color.YELLOW);
		dessinerPendu(g);
		actualiser();
	}
	
	/**
	 * Actualise le mot et le nombres de vies du joueur en fonction des lettres qu'il a sélectionné.
	 * @see PanneauPendu#mot
	 * @see PanneauPendu#vies
	 * @see Jeu#getMot()
	 * @see Mot#motADeviner()
	 * @see Jeu#getJoueur()
	 * @see Joueur#getVies()
	 */
	public void actualiser() {
		mot.setText("Mot à trouver : " + jeu.getMot().motADeviner());
		vies.setText("Nombre de vies restantes : " + jeu.getJoueur().getVies());
	}
	
	/**
	 * Dessine les membres du pendu en fonction du nombre de vies du joueur.
	 * @param g Le contexte graphique de PanneauPendu
	 * @see Jeu#getJoueur()
	 * @see Joueur#getVies()
	 */
	public void dessinerPendu(Graphics g){
		Graphics2D g2d = (Graphics2D) g;
		int w = this.getWidth();
		int h = this.getHeight();
		
		
		int xBras1[] = {w - 190, w - 180, w - 215,  w - 210, w - 220, w - 230};
		int yBras[] = {(h/2) - 40, (h/2) - 30, (h/2), (h/2) + 40, (h/2) + 40, (h/2) - 10};
			
		int xBras2[] = {w - 160, w - 170, w - 135,  w - 140, w - 130, w - 120};
		
		
		int xJambe1[] = {w - 200, w - 180, w - 200,  w - 185, w - 205, w - 225};
		int yJambes[] = {(h/2) + 20, (h/2) + 30, (h/2) + 75, (h/2) + 120, (h/2) + 120, (h/2) + 70};
		
		int xJambe2[] = {w - 155, w - 175, w - 155,  w - 170, w - 150, w - 130};
		
		
		int xPotence[] = {w - 350, w - 300, w - 300, w - 350};
		int yPotence[] = {100, 50, 60, 110};
		boolean couleurInitialisee = false;
		GradientPaint gp;
		switch(jeu.getJoueur().getVies()){
			case 0 :
				gp = new GradientPaint(0, 0, Color.DARK_GRAY, 50, 50, Color.DARK_GRAY.darker(), true);
				g2d.setPaint(gp);
				couleurInitialisee = true;
				g.fillPolygon(xBras2, yBras, 6);
			case 1 :
				if(!couleurInitialisee){
					g2d.drawString("DERNIÈRE VIE !", this.getWidth()/10, this.getHeight()/2);
					gp = new GradientPaint(0, 0, Color.RED.darker(), 50, 50, Color.BLACK, true);
					g2d.setPaint(gp);
					couleurInitialisee = true;
				}
				g.fillPolygon(xBras1, yBras, 6);
			case 2 :
				if(!couleurInitialisee){
					gp = new GradientPaint(0, 0, Color.RED, 50, 50, Color.RED.darker(), true);
					g2d.setPaint(gp);
					couleurInitialisee = true;
				}
				g.fillPolygon(xJambe1, yJambes, 6);
				g.fillPolygon(xJambe2, yJambes, 6);
			case 3 :
				if(!couleurInitialisee){
					gp = new GradientPaint(0, 0, Color.ORANGE.darker(), 50, 50, Color.RED, true);
					g2d.setPaint(gp);
					couleurInitialisee = true;
				}
				g.fillRoundRect(w - 200, (h/2) - 50, 50, 90, 100, 50);
			case 4 :
				if(!couleurInitialisee){
					gp = new GradientPaint(0, 0, Color.YELLOW.darker(), 50, 50, Color.ORANGE.darker(), true);
					g2d.setPaint(gp);
					couleurInitialisee = true;
				}
				g.fillOval(w - 200, (h/2) - 100, 50, 50);
			case 5 :
				if(!couleurInitialisee){
					gp = new GradientPaint(0, 0, Color.ORANGE, 50, 50, Color.YELLOW.darker(), true);
					g2d.setPaint(gp);
					couleurInitialisee = true;
				}
				g.fillPolygon(xPotence, yPotence, 4);
			case 6 :
				if(!couleurInitialisee){
					gp = new GradientPaint(0, 0, Color.YELLOW, 50, 50, Color.ORANGE, true);
					g2d.setPaint(gp);
					couleurInitialisee = true;
				}
				g.fillRect(w - 180, 50, 10,(h/2) - 145);
			case 7 :
				if(!couleurInitialisee){
					gp = new GradientPaint(0, 0, Color.GREEN, 50, 50, Color.YELLOW, true);
					g2d.setPaint(gp);
					couleurInitialisee = true;
				}
				g.fillRect(w - 350, 50, 300,10);
			case 8 :
				if(!couleurInitialisee){
					gp = new GradientPaint(0, 0, Color.PINK, 50, 50, Color.GREEN, true);
					g2d.setPaint(gp);
					couleurInitialisee = true;
				}
				g.fillRect(w - 350, 50, 10, 350);
			default:
				if(!couleurInitialisee){
					gp = new GradientPaint(0, 0, Color.WHITE, 50, 50, Color.PINK, true);
					g2d.setPaint(gp);
					couleurInitialisee = true;
				}
				g.fillRect(w - 400, h - 100, 350,10);
				break;
			
		}
	}
}
