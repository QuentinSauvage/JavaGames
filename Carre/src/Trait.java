import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JButton;

/**
 * Le trait présent dans la grille et délimitant les carrés.
 * @author Quentin Sauvage
 *
 */
public class Trait extends JButton implements MouseListener {
	/**
	 * L'ID du trait, générée automatiquement.
	 */
	private static final long serialVersionUID = 1L;
	
	/**
	 * L'abscisse du trait.
	 */
	private int x;
	
	/**
	 * L'ordonnée du trait.
	 */
	private int y;
	
	/**
	 * La couleur du trait.
	 */
	private Couleurs c;
	
	/**
	 * L'alignement du trait.
	 */
	public char alignement;
	
	/**
	 * Le jeu contenant les informations à propos des joueurs, du plateau, etc...
	 */
	private Jeu modele;
	
	/**
	 * Constructeur de trait qui le positionne sur la grille.
	 * @see Trait#positionner(char)
	 * @param i abscisse du trait.
	 * @param j ordonnée du trait.
	 * @param align L'alignement du trait, horizontal ou vertical.
	 * @param m Le jeu contenant les informations à propos des joueurs, du plateau, etc...
	 */
	public Trait(int i, int j, char align, Jeu m) {
		super("");
		x = i;
		y = j;
		modele = m;
		positionner(align);
		this.setPreferredSize(new Dimension(100, 100));
		c = null;
		this.setBackground(Color.BLACK);
		this.setBorderPainted(false);
		this.addMouseListener(this);
	}
	
	/**
	 * Retourn la couleur correspondant au trait sélectionné.
	 * @return La couleur associée au trait.
	 */
	public Couleurs getCouleur() {
		return c;
	}
	
	/**
	 * Retourne la coordonnée horizontale du trait.
	 * @return l'abscisse du trait.
	 */
	public int getCoordonnesX() {
		return x;
	}
	
	/**
	 * Retourne la coordonnée horizontale du trait.
	 * @return l'abscisse du trait.
	 */
	public int getCoordonnesY() {
		return y;
	}
	
	/**
	 * L'alignement du trait, horizontal ou vertical.
	 * @return l'alignement du trait.
	 */
	public char getAlignement() {
		return alignement;
	}
	
	/**
	 * Indique si le trait a été cliqué.
	 * @return vrai si le trait a été cliqué, faux sinon.
	 */
	public boolean getColorie() {
		if(c == null)
			return false;
		return true;
	}
	
	/**
	 * Modifie la couleur du trait.
	 * @param c La nouvelle couleur du trait.
	 */
	public void setCouleur(Couleurs c) {
		this.c = c;
	}
	
	/**
	 * Modifie la coordonnée horizontale du trait.
	 * @param x La nouvelle abscisse du trait.
	 */
	public void setCoordonnesX(int x) {
		this.x = x;
	}
	
	/**
	 * Modifie la coordonnée verticale du trait.
	 * @param x La nouvelle ordonnée du trait.
	 */
	public void setCoordonnesY(int y) {
		this.y = y;
	}
	
	/**
	 * Modifie l'alignement du trait.
	 * @param c Le nouvel alignement du trait.
	 */
	public void setAlignement(char c) {
		alignement = c;
	}
	
	/**
	 * Affiche les attributs du trait.
	 * @return les attributs du trait sous forme de String.
	 */
	public String toString() {
		return "Couleur : " + c + " X : " + x + " Y : " + y;
	}
	
	/**
	 * Positionne le trait dans la fenêtre.
	 * @param c L'alignement du trait.
	 */
	public void positionner(char c) {
		if(c == 'v')
			this.setBounds((x * Constantes.nbPixels), (y * Constantes.nbPixels), 5, Constantes.nbPixels + 5);
		else
			this.setBounds((y * Constantes.nbPixels), 0 + (x * Constantes.nbPixels), Constantes.nbPixels + 5, 5);
		alignement = c;
	}
	
	/**
	 * Récupère la couleur du joueur actuel, et désactive le clic sur le trait.
	 * @see Jeu#getTour()
	 */
	public void clicAction() {
		c = Couleurs.values()[modele.getTour()];
		String s = c.toString();
		try {
			this.setBackground((Color)Color.class.getField(s).get(null));
		} catch (IllegalArgumentException | IllegalAccessException | NoSuchFieldException | SecurityException e) {
			e.printStackTrace();
		}
		this.removeMouseListener(this);
	}

	@Override
	public void mouseClicked(MouseEvent arg0) {
	}

	@Override
	public void mouseEntered(MouseEvent arg0) {
		String s = Couleurs.values()[modele.getTour()].toString();
		try {
			this.setBackground((Color)Color.class.getField(s).get(null));
		} catch (IllegalArgumentException | IllegalAccessException | NoSuchFieldException | SecurityException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void mouseExited(MouseEvent arg0) {
		this.setBackground(Color.BLACK);
	}

	@Override
	public void mousePressed(MouseEvent arg0) {
		if((Constantes.IA && modele.getTour() == 0) || !Constantes.IA) {
			clicAction();
			modele.calcul(this);
		}
	}

	@Override
	public void mouseReleased(MouseEvent arg0) {
	}
}
