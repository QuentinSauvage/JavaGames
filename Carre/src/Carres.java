import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;

import javax.swing.JPanel;

/**
 * Un panneau contenant l'ensemble des carrés remplis, ainsi que leurs couleurs.
 * @author Quentin Sauvage
 *
 */
public class Carres extends JPanel {
	/**
	 * L'ID de Carres, générée automatiquement.
	 */
	private static final long serialVersionUID = 1L;
	
	/**
	 * La liste des carrés remplis.
	 */
	private ArrayList<Trait> carresRemplis;
	
	/**
	 * La liste des couleurs des carrés remplis.
	 */
	private ArrayList<Couleurs> couleurCarres;
	
	/**
	 * Le constructeur de Carres, qui initialise le tableau de carrés remplis et de couleurs.
	 * @param m Le jeu contenant les informations à propos des joueurs, du plateau, etc...
	 */
	public Carres(Jeu m) {
		carresRemplis = new ArrayList<Trait>();
		couleurCarres = new ArrayList<Couleurs>();
		this.setFocusable(true);
	}
	
	/**
	 * Ajoute les informations du carrés qui vient d'être rempli.
	 * @param t La position du carré.
	 * @param c La couleur du carré.
	 */
	public void addCarre(Trait t, Couleurs c) {
		carresRemplis.add(t);
		couleurCarres.add(c);
	}
	
	@Override
	public void paintComponent(Graphics g) {
		int x, y;
		g.setColor(Color.WHITE);
		g.fillRect(0,  0,  this.getWidth(), this.getHeight());
		int i = 0;
		for(Trait t : carresRemplis) {
			String s = couleurCarres.get(i).toString();
			try {
				g.setColor((Color)Color.class.getField(s).get(null));
			} catch (IllegalArgumentException | IllegalAccessException | NoSuchFieldException | SecurityException e) {
				e.printStackTrace();
			}
			if(t.getAlignement() == 'h') {
				x = t.getCoordonnesY();
				y = t.getCoordonnesX();
			} else {
				x = t.getCoordonnesX();
				y = t.getCoordonnesY();
			}
			g.fillRect(x * Constantes.nbPixels, y * Constantes.nbPixels, Constantes.nbPixels, Constantes.nbPixels);
			i++;
		}
	}

}
