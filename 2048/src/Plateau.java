import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;

import javax.swing.JPanel;

/**
 * Dessine les composants graphiques du plateau.
 * @author Quentin Sauvage
 *
 */
public class Plateau extends JPanel {
	/**
	 * L'ID du Plateau, générée automatiquement.
	 */
	private static final long serialVersionUID = 1L;
	
	/**
	 * Le jeu contenant les informations à propos des joueurs, du plateau, etc...
	 */
	private Jeu modele;
	
	/**
	 * Constructeur du Plateau, à partir du modèle et des caractéristiques du jeu.
	 * @param m Le jeu contenant les informations à propos des joueurs, du plateau, etc...
	 */
	public Plateau(Jeu m) {
		modele = m;
		int l = (Constantes.cote * Constantes.nbPixels) + ((Constantes.cote + 1) * Constantes.separateur);
		this.setPreferredSize(new Dimension(l, l));
		this.setFocusable(true);
	}
	
	/**
	 * Dessine les componsants graphiques du plateau.
	 * @see Jeu#affichage(Graphics)
	 * @see Jeu#getEtat()
	 */
	@Override
	public void paintComponent(Graphics g){
		g.setColor(new Color(187, 173, 160));
		g.fillRect(0,  0,  this.getWidth(), this.getHeight());
		modele.affichage(g);
		if(modele.getEtat()) {
			g.setFont(new Font("Tahoma", Font.BOLD, 50));
			g.setColor(Color.BLACK);
			g.drawString("GAME OVER", (this.getWidth() / 4), (this.getHeight() / 2) - 20);
		}
	}
}
