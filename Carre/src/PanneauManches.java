import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 * Affiche le score d'au maximum deux joueurs, sur un des côtés du plateau.
 * @author Quentin Sauvage
 *
 */
public class PanneauManches extends JPanel {
	/**
	 * L'ID du PanneauManches, générée automatiquement.
	 */
	private static final long serialVersionUID = 1L;
	
	/**
	 * L'indice du premier joueur.
	 */
	private int indiceJoueur;
	
	/**
	 * Le jeu contenant toutes les informations nécessaires à son déroulement.
	 */
	private Jeu modele;
	
	/**
	 * Le score du premier joueur.
	 */
	private JLabel joueur1;
	
	/**
	 * Le score du deuxième joueur.
	 */
	private JLabel joueur2;
	
	/**
	 * Positionne le score des joueurs sur la fenêtre.
	 * @see Jeu#getPoints(int)
	 * @param i L'indice du premier joueur.
	 * @param m Le jeu contenant toutes les informations nécessaires à son déroulement.
	 */
	public PanneauManches(int i, Jeu m) {
		indiceJoueur = i;
		modele = m;
		this.setLayout(new BorderLayout());
		this.setPreferredSize(new Dimension(100, 0));
		setBackground(Color.WHITE);
		if(indiceJoueur < Constantes.nbJoueurs) {
			joueur1 = new JLabel("Points : " + modele.getPoints(indiceJoueur));
			this.add(joueur1, BorderLayout.NORTH);
		}
		if(indiceJoueur + 1 < Constantes.nbJoueurs) {
			joueur2 = new JLabel("Points : " + modele.getPoints(indiceJoueur + 1));
			this.add(joueur2, BorderLayout.SOUTH);
		}
	}
	
	/**
	 * Met à jour le score des joueurs.
	 * @see Jeu#getPoints(int)
	 */
	public void updateScore() {
		if(indiceJoueur == modele.getTour())
			joueur1.setText("Points : " + modele.getPoints(indiceJoueur));
		else
			joueur2.setText("Points : " + modele.getPoints(indiceJoueur + 1));
	}
}
