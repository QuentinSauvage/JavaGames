import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

/**
 * Gère le comportement de chacune des tuiles.
 * @author Quentin Sauvage
 *
 */
public class Tuile {
	/**
	 * Coordonnée horizontale de la tuile.
	 */
	private int x;
	
	/**
	 * Coordonnée verticale de la tuile.
	 */
	private int y;
	
	/**
	 * Valeur de la tuile.
	 */
	private int valeur;
	
	/**
	 * Couleur de fond de la tuile.
	 */
	private Color c;
	
	/**
	 * Booléen indiquant si la tuile a déjà fusionné pendant ce tour.
	 */
	private boolean aFusionne;
	
	/**
	 * Constructeur de Tuile, initialisant ses coordonnées et sa couleur.
	 * @see Tuile#setFusion(boolean)
	 * @see Tuile#updateColor()
	 * @param y La nouvelle coordonnée verticale de la tuile.
	 * @param x La nouvelle coordonnée horizontale de la tuile.
	 */
	public Tuile(int y, int x) {
		this.y = y;
		this.x = x;
		this.valeur = 0;
		setFusion(false);
		updateColor();
	}
	
	/**
	 * Constructeur de Tuile, initialisant ses coordonnées, sa valeur et sa couleur.
	 * @see Tuile#setFusion(boolean)
	 * @see Tuile#updateColor()
	 * @param y La nouvelle coordonnée verticale de la tuile.
	 * @param x La nouvelle coordonnée horizontale de la tuile.
	 * @param valeur La nouvelle valeur de la tuile.
	 */
	public Tuile(int y, int x, int valeur) {
		this.y = y;
		this.x = x;
		this.valeur = valeur;
		setFusion(false);
		updateColor();
	}
	
	/**
	 * Indique que la tuile a fusionné pendant le tour courant.
	 * @param f La nouvelle valeur de aFusionne.
	 */
	public void setFusion(boolean f) {
		aFusionne = f;
	}
	
	/**
	 * Retourne le booléen permettant de savoir si la tuile a fusionné pendant ce tour.
	 * @return booléen indiquant si la tuile a fusionné.
	 */
	public boolean getFusion() {
		return aFusionne;
	}
	
	/**
	 * Modifie la valeur horizontale de la tuile.
	 * @param x La nouvelle coordonnée horizontale de la tuile.
	 */
	public void setX(int x){
		this.x = x;
	}
	
	/**
	 * Modifie la valeur verticale de la tuile.
	 * @param y La nouvelle coordonnée verticale de la tuile.
	 */
	public void setY(int y){
		this.y = y;
	}
	
	/**
	 * Renvoie la valeur horizontale de la tuile.
	 * @return la coordonnée horizontale de la tuile.
	 */
	public int getX(){
		return x;
	}
	
	/**
	 * Renvoie la valeur verticale de la tuile.
	 * @return la coordonnée verticale de la tuile.
	 */
	public int getY(){
		return y;
	}
	
	/**
	 * Renvoie la position horizontale de la tuile dans la fenêtre.
	 * @return l'abscisse de la tuile.
	 */
	public int getPosX() {
		return (x * Constantes.nbPixels) + ((x + 1) * Constantes.separateur);
	}
	
	/**
	 * Renvoie la position verticale de la tuile dans la fenêtre.
	 * @return l'ordonnée de la tuile.
	 */
	public int getPosY() {
		return (y * Constantes.nbPixels) + ((y + 1) * Constantes.separateur);
	}
	
	/**
	 * Renvoie la valeur de la tuile.
	 * @return La valeur de la tuile.
	 */
	public int getValeur() {
		return valeur;
	}
	
	/**
	 * Change la couleur de la tuile en fonction de sa valeur.
	 */
	public void updateColor() {
		if(valeur == 0)
			c = new Color(205, 193, 180);
		else if(valeur == 2)
			c = new Color(238, 228, 218);
		else if(valeur == 4)
			c = new Color(237, 224, 200);
		else if(valeur == 8)
			c = new Color(242, 177, 121);
		else if(valeur == 16)
			c = new Color(245, 149, 99);
		else if(valeur == 32)
			c = new Color(246, 124, 95);
		else if(valeur == 64)
			c = new Color(228, 107, 76);
		else if(valeur == 128)
			c = new Color(234, 204, 121);
		else if(valeur == 256)
			c = new Color(223, 191, 100);
		else if(valeur == 512)
			c = new Color(233, 197, 91);
		else if(valeur == 1024)
			c = new Color(224, 181, 64);
		else if(valeur == 2048)
			c = new Color(236, 196, 0);
		else
			c = new Color(58, 59, 51);
	}
	
	/**
	 * Met à jour la valeur de la tuile.
	 * @see Tuile#updateColor()
	 * @param val La nouvelle valeur de la tuile.
	 */
	public void upgrade(int val) {
		if(valeur == 0)
			valeur = val;
		else
			valeur = val + val;
		updateColor();
	}
	
	/**
	 * Affiche les attributs de la tuile.
	 */
	public String toString() {
		return "X : " + x + " Y : " + y + " Valeur : " + valeur;
	}

	/**
	 * Indique si la tuile se trouverait au bord du plateau si elle se déplaçait selon x et y.
	 * @param y Le vecteur vertical.
	 * @param x Le vecteur horizontal.
	 * @return booléen indiquant si la tuile serait au bord.
	 */
	public boolean estAuBord(int y, int x) {
		return (this.x + x) < 0 || (this.x + x) == Constantes.cote || (this.y + y) < 0 || (this.y + y) == Constantes.cote;
	}
	
	/**
	 * Permet de contenir graphiquement la valeur de la tuile à l'intérieur de sa représentation graphique.
	 * @return la taille de la valeur de la tuile.
	 */
	private int tailleValeur() {
		int sz = valeur, cpt = 0;
		while (sz > 1) {
			sz /= 10;
			cpt++;
		}
		return cpt;
	}
	
	/**
	 * Dessine la tuile au bon endroit sur le plateau.
	 * @param g Lecontexte graphique.
	 */
	public void affichage(Graphics g) {
		int posX = getPosX();
		int posY = getPosY();
		g.setColor(this.c);
		g.fillRect(posX, posY, Constantes.nbPixels, Constantes.nbPixels);
		if(valeur > 0) {
			if(valeur > 4)
				g.setColor(Color.WHITE);
			else
				g.setColor(new Color(119, 110, 101));
			g.setFont(new Font("Tahoma", Font.BOLD, 30));
			g.drawString(Integer.toString(valeur), posX + (Constantes.nbPixels / 2) - (tailleValeur() * 10), posY + (Constantes.nbPixels / 2) + 10);
		}
	}
	
	@Override
	public boolean equals(Object arg0) {
		Tuile t = (Tuile) arg0;
		return t.getValeur() == valeur;
	}
}
