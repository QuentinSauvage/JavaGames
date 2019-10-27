import java.awt.Color;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 * PanneauFin informe le joueur de la fin de la partie.
 * @author Quentin Sauvage
 *
 */
public class PanneauFin extends JPanel {
	/**
	 * L'ID du PanneauFin, générée automatiquement.
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * Le jeu contenant les informations à propos des joueurs, du plateau, etc...
	 */
	private Jeu modele;
	
	/**
	 * JLabel contenant le texte demandant au joueur de recommencer, et l'informant sur sa victoire/défaite.
	 */
	private JLabel demande;
	
	/**
	 * Constructeur du panneau indiquant la fin de la partie.
	 * @param m	Le jeu contenant les informations à propos des joueurs, du plateau, etc...
	 */
	public PanneauFin(Jeu m) {
		modele = m;
		this.setFocusable(true);
		this.setBackground(new Color(250, 248, 239));
		demande = new JLabel("");
		this.add(demande);
		
		/**
		 * Définit le comportement des boutons du panneau de fin de partie.
		 * @author Quentin Sauvage
		 *
		 */
		class Bouton extends JButton {
			/**
			 * L'ID du PanneauFin, générée automatiquement.
			 */
			private static final long serialVersionUID = 1L;
			
			/**
			 * @param m	Le jeu contenant les informations à propos des joueurs, du plateau, etc...
			 */
			private Jeu modele;
			
			/**
			 * Constructeur du bouton contenant le choix du joueur.
			 * @param name Le nom du bouton.
			 * @param m	Le jeu contenant les informations à propos des joueurs, du plateau, etc...
			 */
			public Bouton(String name, Jeu m) {
				super(name);
				modele = m;
				this.addActionListener(new ActionListener(){
					
					@Override
					public void actionPerformed(ActionEvent arg0) {
						modele.recommencer(Bouton.this.getText());
					}
					  
				});
			}
			
			public void paintComponent(Graphics g){
				Graphics2D g2d = (Graphics2D)g;
				GradientPaint gp;
				if(this.getText() == "OUI")
					gp = new GradientPaint(0, 0, Color.GREEN, 50, 50, Color.RED, true);
				else
					gp = new GradientPaint(0, 0, Color.RED, 50, 50, Color.GREEN, true);
				g2d.setPaint(gp);
				g2d.fillRect(0, 0, this.getWidth(), this.getHeight());
				g2d.setColor(Color.WHITE);
				g2d.drawString(this.getText(), this.getWidth() / 4, 15);
			}
		}
		this.add(new Bouton("OUI", modele));
		this.add(new Bouton("NON", modele));
	}
	
	/**
	 * Affiche le texte dans la fenêtre
	 * @param fini Le booléen indiquant si le joueur a gagné ou non.
	 * @see Jeu#setPause()
	 */
	public void estFini(boolean fini) {
		if(fini) {
			demande.setText("Vous avez gagné ! Voulez-vous recommencer ?");
		}
		else
			demande.setText("Perdu ! Voulez-vous recommencer ?");
		modele.setPause();
	}
}
