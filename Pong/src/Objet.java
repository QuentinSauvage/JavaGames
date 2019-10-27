/**
 * Objet regroupe l'ensemble des éléments composant le jeu.
 * @author Quentin Sauvage
 *
 */
public class Objet {
	/**
	 * L'abscisse de l'objet.
	 */
	private double x;
	/**
	 * L'ordonnée de l'objet.
	 */
	private double y;
	/**
	 * La largeur de l'objet.
	 */
	private int largeur;
	/**
	 * La hauteur de l'objet.
	 */
	private int hauteur;
	
	/**
	 * Construit l'objet avec les bonnes positions et dimensions.
	 * @param x La nouvelle coordonnée horizontale de l'objet.
	 * @param y La nouvelle coordonnée verticale de l'objet.
	 * @param l La nouvelle largeur de l'objet.
	 * @param h	La nouvelle hauteur de l'objet.
	 */
	public Objet(double x, double y, int l, int h) {
		this.x = x;
		this.y = y;
		this.largeur = l;
		this.hauteur = h;
	}
	
	/**
	 * Donne accès à l'abscisse de l'objet.
	 * @return l'abscisse de l'objet.
	 */
	public double getX() {
		return x;
	}
	
	/**
	 * Modifie la valeur horizontale de l'objet.
	 * @param x La nouvelle coordonnée horizontale de l'objet.
	 */
	public void setX(double x) {
		this.x = x;
	}
	
	/**
	 * Donne accès à l'ordonnée de l'objet.
	 * @return l'ordonnée de l'objet.
	 */
	public double getY() {
		return y;
	}
	
	/**
	 * Modifie la valeur verticale de l'objet.
	 * @param y La nouvelle coordonnée verticale de l'objet.
	 */
	public void setY(double y) {
		this.y = y;
	}
	
	/**
	 * Donne accès à la largeur de l'objet.
	 * @return la largeur de l'objet.
	 */
	public int getLargeur() {
		return largeur;
	}

	/**
	 * Modifie la largeur de l'objet.
	 * @param largeur La nouvelle largeur de l'objet.
	 */
	public void setLargeur(int largeur) {
		this.largeur = largeur;
	}

	/**
	 * Donne accès à la hauteur de l'objet.
	 * @return la hauteur de l'objet.
	 */
	public int getHauteur() {
		return hauteur;
	}

	/**
	 * Modifie la hauteur de l'objet.
	 * @param hauteur La nouvelle hauteur de l'objet.
	 */
	public void setHauteur(int hauteur) {
		this.hauteur = hauteur;
	}
}