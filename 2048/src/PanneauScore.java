import java.awt.Color;
import java.awt.Font;

import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 * Dessine le score du joueur.
 * @author Quentin Sauvage
 *
 */
public class PanneauScore extends JPanel{
	/**
	 * L'ID du PanneauScore, générée automatiquement.
	 */
	private static final long serialVersionUID = 1L;
	
	/**
	 * Le jeu contenant les informations à propos des joueurs, du plateau, etc...
	 */
	private Jeu modele;
	
	/**
	 * Contient le score du joueur.
	 */
	private JLabel score;
	
	/**
	 * Crée le panneau contenant le score du joueur.
	 * @param m	Le jeu contenant les informations à propos des joueurs, du plateau, etc...
	 */
	public PanneauScore(Jeu m) {
		modele = m;
		this.setBackground(new Color(250, 248, 239));
		score = new JLabel("Score : 0");
		score.setFont(new Font("Tahoma", Font.BOLD, 40));
		score.setForeground(new Color(119, 110, 101));
		this.add(score);
	}
	
	/**
	 * Met à jour visuellement le score du joueur.
	 */
	public void updateScore() {
		score.setText("Score : " + modele.getScore());
	}
}
