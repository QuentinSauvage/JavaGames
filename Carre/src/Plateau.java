import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;

import javax.swing.JPanel;

/**
 * Le plateau contenant toute la grille.
 * @author Quentin Sauvage
 *
 */
public class Plateau extends JPanel {
	/**
	 * L'ID du Plateau, générée automatiquement
	 */
	private static final long serialVersionUID = 1L;
	
	/**
	 * Constructeur du Plateau selon les données entrées au début du jeu.
	 * @param j Le jeu contenant toutes les informations nécessaires à son déroulement.
	 */
	public Plateau(Jeu j) {
		int h = (Constantes.nbColonnes * Constantes.nbPixels) + 20;
		int l = (Constantes.nbLignes * Constantes.nbPixels) + 20;

		this.setPreferredSize(new Dimension(l, h));
		this.setFocusable(true);
	}
	
	@Override
	public void paintComponent(Graphics g){
		g.setColor(Color.WHITE);
		g.fillRect(0,  0,  this.getWidth(), this.getHeight());
	}
}
