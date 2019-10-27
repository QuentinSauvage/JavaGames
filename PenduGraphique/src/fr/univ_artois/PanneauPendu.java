package fr.univ_artois;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JPanel;

public class PanneauPendu extends JPanel{
	private Mot mot;
	private int nbVies;
	
	public PanneauPendu(Mot m, int v){
		mot = m;
		nbVies = v;
		this.setPreferredSize(new Dimension(700, 500));
	}
	
	public Mot getMot(){
		return mot;
	}
	
	public int getNbVies(){
		return nbVies;
	}
	
	public void setNbVies(int v){
		nbVies = v;
	}
	
	public void decrease(){
		nbVies--;
	}
	
	public void paintComponent(Graphics g){
		Graphics2D g2d = (Graphics2D)g;
		g2d.setBackground(Color.BLACK);
		g2d.fillRect(0, 0, this.getWidth(), this.getHeight());
		g2d.setColor(Color.YELLOW);
		g2d.drawString("Mot à trouver : " + this.mot.motADeviner(), this.getWidth()/10, this.getHeight()/10);
		dessinerPendu(g);
	}
	
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
		switch(nbVies){
			case 0 :
				g2d.setColor(Color.DARK_GRAY);
				couleurInitialisee = true;
				g.fillPolygon(xBras2, yBras, 6);
			case 1 :
				if(!couleurInitialisee){
					g2d.drawString("DERNIÈRE VIE !", this.getWidth()/10, this.getHeight()/2);
					g2d.setColor(Color.RED.darker());
					couleurInitialisee = true;
				}
				g.fillPolygon(xBras1, yBras, 6);
			case 2 :
				if(!couleurInitialisee){
					g2d.setColor(Color.RED);
					couleurInitialisee = true;
				}
				g.fillPolygon(xJambe1, yJambes, 6);
				g.fillPolygon(xJambe2, yJambes, 6);
			case 3 :
				if(!couleurInitialisee){
					g2d.setColor(Color.ORANGE.darker());
					couleurInitialisee = true;
				}
				g.fillRoundRect(w - 200, (h/2) - 50, 50, 90, 100, 50);
			case 4 :
				if(!couleurInitialisee){
					g2d.setColor(Color.YELLOW.darker());
					couleurInitialisee = true;
				}
				g.fillOval(w - 200, (h/2) - 100, 50, 50);
			case 5 :
				if(!couleurInitialisee){
					g2d.setColor(Color.ORANGE);
					couleurInitialisee = true;
				}
				g.fillPolygon(xPotence, yPotence, 4);
			case 6 :
				if(!couleurInitialisee){
					g2d.setColor(Color.YELLOW);
					couleurInitialisee = true;
				}
				g.fillRect(w - 180, 50, 10,(h/2) - 145);
			case 7 :
				if(!couleurInitialisee){
					g2d.setColor(Color.GREEN);
					couleurInitialisee = true;
				}
				g.fillRect(w - 350, 50, 300,10);
			case 8 :
				if(!couleurInitialisee){
					g2d.setColor(Color.PINK);
					couleurInitialisee = true;
				}
				g.fillRect(w - 350, 50, 10, 350);
			default:
				if(!couleurInitialisee){
					g2d.setColor(Color.WHITE);
					couleurInitialisee = true;
				}
				g.fillRect(w - 400, h - 100, 350,10);
				break;
			
		}
	}
}
