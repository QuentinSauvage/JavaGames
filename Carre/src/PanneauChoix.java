import java.awt.Color;
import java.awt.Dimension;
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
 * Le panneau demandant au joueur de choisir les paramètres de la partie.
 * @author Quentin Sauvage
 *
 */
public class PanneauChoix extends JPanel {

	/**
	 * L'ID du PanneauChoix, générée automatiquement.
	 */
	private static final long serialVersionUID = 1L;
	
	/**
	 * Un slider demandant la taille du plateau.
	 */
	private SlideChoix slideTaille;
	
	/**
	 * Un slider demandant le nombre de joueurs.
	 */
	private SlideChoix slideNbJoueurs;
	
	/**
	 * Informe le joueur sur la valeur de la taille actuellement choisie.
	 */
	private JLabel labelTaille;
	
	/**
	 * Informe le joueur sur la valeur du nombre de joueurs actuellement choisie.
	 */
	private JLabel labelNbJoueurs;
	
	/**
	 * Demande au joueur de choisir de jouer avec ou sans l'IA.
	 */
	private JCheckBox box;
	
	/**
	 * Le jeu contenant toutes les informations nécessaires à son déroulement.
	 */
	private Jeu modele;
	
	/**
	 * Le constructeur de PanneauChoix, positionne les éléments sur la fenêtre pour recueillir les caractéristiques de la partie.
	 * @param m Le jeu contenant toutes les informations nécessaires à son déroulement.
	 */
	public PanneauChoix(Jeu m) {
		this.setPreferredSize(new Dimension(450, 150));
		modele = m;
		JLabel bienvenue = new JLabel("Bienvenue ! Sélectionnez la taille du plateau : ");
		bienvenue.setForeground(Color.WHITE);
		this.add(bienvenue);
		slideTaille = new SlideChoix(5, 30, this, 5);
		slideNbJoueurs = new SlideChoix(2, 4, this, 1);
		labelTaille = new JLabel("Valeur actuelle : " + String.valueOf(slideTaille.getValue()));
		labelTaille.setForeground(Color.WHITE);
		labelNbJoueurs = new JLabel("Valeur actuelle : " + String.valueOf(slideNbJoueurs.getValue()));
		labelNbJoueurs.setForeground(Color.WHITE);
		this.add(labelTaille);
		this.add(slideTaille);
		this.add(labelNbJoueurs);
		this.add(slideNbJoueurs);
		box = new JCheckBox();
		box.setText("IA");
		box.setForeground(Color.WHITE);
		box.setBackground(Color.RED);
		this.add(box);
		
		/**
		 * Définit l'action exécuté lors du clic sur le bouton.
		 * @author Quentin Sauvage
		 *
		 */
		class BoutonValider extends JButton {
			/**
			 * L'ID de BoutonValider, générée automatiquement.
			 */
			private static final long serialVersionUID = 1L;
			
			/**
			 * Le jeu contenant toutes les informations nécessaires à son déroulement.
			 */
			private Jeu modele;
			
			/**
			 * Le constructeur de BoutonValider, définissant son action.
			 * @param s Le nom du bouton.
			 * @param m Le jeu contenant toutes les informations nécessaires à son déroulement.
			 */
			public BoutonValider(String s, Jeu m) {
				super(s);
				modele = m;
				this.addActionListener(new ActionListener(){
					@Override
					public void actionPerformed(ActionEvent arg0) {
						PanneauChoix.this.recupererValeurs();
						modele.commencer();
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
		this.add(new BoutonValider("OK", modele));
	}
	
	/**
	 * Met à jour les informations sur les valeurs choisies.
	 */
	public void updateLabel() {
		labelTaille.setText("Valeur actuelle : " + String.valueOf(slideTaille.getValue()));
		labelNbJoueurs.setText("Valeur actuelle : " + String.valueOf(slideNbJoueurs.getValue()));
	}
	
	/**
	 * Récupère les valeurs entrées par le joueur.
	 */
	public void recupererValeurs() {
		Constantes.IA = box.isSelected();
		Constantes.nbJoueurs = slideNbJoueurs.getValue();
		Constantes.nbColonnes = Constantes.nbLignes = slideTaille.getValue();
		if(Constantes.nbColonnes > 20)
			Constantes.nbPixels = 30;
		else if(Constantes.nbColonnes < 10) {
			Constantes.nbPixels = 90;
		}
	}
	
	public void paintComponent(Graphics g){
		g.fillRect(0, 0, this.getWidth(), this.getHeight());

	}
}
