import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;

@SuppressWarnings("serial")
public class PanelJoueur extends JPanel {
	private Joueur joueur;
	private JButton lancer;
	private JButton stop;
	private boolean estLance;
	
	public PanelJoueur(Joueur j) {
		super();
		joueur = j;
		if(j.getClass().getName() == "IA")
			joueur = (IA) joueur;
		estLance = false;
		this.setPreferredSize(new Dimension(400, 175));
		lancer = new JButton("Lancer");
		stop = new JButton("Stop");
		lancer.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				if(!estLance)
					joueur.genererDes();
			}
		});
		stop.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				if(!estLance) {
					joueur.finirTour();
					joueur.setTombe(false);
					Main.fenetre.changerTour();
				}
			}
			
		});
		if(joueur.getClass().getName() == "IA") {
			lancer.setEnabled(false);
			stop.setEnabled(false);
		}
		this.add(lancer);
		this.add(stop);
		this.setBackground(Color.WHITE.brighter());
	}
	
	public boolean getEstLance() {
		return estLance;
	}
	
	public void setEstLance(boolean b) {
		estLance = b;
	}
	
	public void paintComponent(Graphics g) {
 		super.paintComponent(g);
 		if(Main.fenetre.modele.getControle() == joueur.getId() && joueur.getClass().getName() == "Joueur") {
 			if(joueur.isTombe()) {
 				lancer.setEnabled(false);
 			} else {
 				lancer.setEnabled(true);
 			}
 			stop.setEnabled(true);
 		} else {
 			lancer.setEnabled(false);
 			stop.setEnabled(false);
 		}
 		g.setColor(Color.BLACK);
 		g.drawLine(0, this.getHeight() - 1, this.getWidth(), this.getHeight() - 1);
 		g.drawLine(0, 40, 80, 40);
 		g.drawLine(80, 0, 80, 40);
 		g.setColor(joueur.getColor());
 		Font font = new Font ("Arial", Font.BOLD , 30);
 		g.setFont(font);
 		g.drawString("J " + (joueur.getId() + 1), 20, 30);
	}
}
