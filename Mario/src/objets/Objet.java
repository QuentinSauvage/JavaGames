package objets;

import java.awt.Image;

import javax.swing.ImageIcon;

public class Objet {
	private int largeur;
	private int hauteur;
	private int x;
	private int y;
	protected Image imgObjet;
	ImageIcon icoObjet;
	
	public Objet() {
		largeur = 0;
		hauteur = 0;
		x = 0;
		y = 0;
	}
	
	public Objet(int l, int h, int x, int y) {
		largeur = l;
		hauteur = h;
		this.x = x;
		this.y = y;
	}
	
	public int getLargeur() {
		return largeur;
	}

	public void setLargeur(int largeur) {
		this.largeur = largeur;
	}

	public int getHauteur() {
		return hauteur;
	}

	public void setHauteur(int hauteur) {
		this.hauteur = hauteur;
	}

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	public Image getImgObjet() {
		return imgObjet;
	}

	public void setImgObjet(Image imgObjet) {
		this.imgObjet = imgObjet;
	}

	public ImageIcon getIcoObjet() {
		return icoObjet;
	}

	public void setIcoObjet(ImageIcon icoObjet) {
		this.icoObjet = icoObjet;
	}

	public void deplacement() {
		if(jeu.Main.scene.getxPos() >= 0 && jeu.Main.scene.getxPos() <= 4430)
			 x = x - jeu.Main.scene.getDx();
	}
}
