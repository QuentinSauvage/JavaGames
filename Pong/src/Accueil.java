import java.awt.Color;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 * Panneau d'accueil demandant au joueur s'il veut jouer avec l'IA.
 * @author quentin
 */
@SuppressWarnings("serial")
public class Accueil extends JPanel {
	
	/**
	 * Le constructeur crée les éléments graphiques du panneau d'accueil.
	 * @see Fenetre#commencer(boolean)
	 */
	public Accueil() {
		JLabel bienvenue = new JLabel("Bienvenue dans Pong !");
		bienvenue.setForeground(Color.WHITE);
		this.add(bienvenue);
		JCheckBox box = new JCheckBox();
		box.setText("IA");
		box.setForeground(Color.WHITE);
		box.setBackground(Color.BLACK);
		this.add(box);
		
		/**
		 * Le bouton informe le jeu du choix du joueur.
		 * @author Quentin Sauvage
		 */
		class BoutonValider extends JButton {
			
			/**
			 * Crée le bouton et l'action qu'il doit faire lorsqu'il est cliqué.
			 * @param s Le nom du bouton.
			 */
			public BoutonValider(String s) {
				super(s);
				this.addActionListener(new ActionListener(){
					@Override
					public void actionPerformed(ActionEvent arg0) {
						Main.fenetre.commencer(box.isSelected());
					}  
				});
			}
			
			public void paintComponent(Graphics g){
				Graphics2D g2d = (Graphics2D)g;
				GradientPaint gp;
				gp = new GradientPaint(0, 0, Color.BLACK, 50, 50, Color.CYAN.darker(), true);
				g2d.setPaint(gp);
				g2d.fillRect(0, 0, this.getWidth(), this.getHeight());
				g2d.setColor(Color.YELLOW);
				g2d.drawString(this.getText(), this.getWidth() / 2 / 4, (this.getHeight() / 2 + 5));
			}
		}
		this.add(new BoutonValider("Jouer"));
	}
	
	
	public void paintComponent(Graphics g){
		g.setColor(Color.BLACK);
		g.fillRect(0, 0, this.getWidth(), this.getHeight());
	}
}
