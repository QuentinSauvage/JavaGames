package personnages;

import java.awt.Image;

import javax.swing.ImageIcon;

import jeu.Main;
import objets.Objet;

public class Personnage {
	private int largeur;
	private int hauteur;
	private int x;
	private int y;
	public int compteur;
	public int compteurMort;
	protected boolean marche;
	protected boolean versDroite;
	protected boolean vivant;
	
	public Personnage(int x, int y, int largeur, int hauteur) {
		this.x = x;
		this.y = y;
		this.largeur = largeur;
		this.hauteur = hauteur;
		compteur = 0;
		compteurMort = 0;
		vivant = true;
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

	public boolean isMarche() {
		return marche;
	}

	public void setMarche(boolean marche) {
		this.marche = marche;
	}

	public boolean isVersDroite() {
		return versDroite;
	}

	public void setVersDroite(boolean versDroite) {
		this.versDroite = versDroite;
	}

	public boolean isVivant() {
		return vivant;
	}

	public void setVivant(boolean vivant) {
		this.vivant = vivant;
	}
	
	public int getCompteur() {
		return compteur;
	}

	public void setCompteur(int compteur) {
		this.compteur = compteur;
	}

	public int getCompteurMort() {
		return compteurMort;
	}

	public void setCompteurMort(int compteurMort) {
		this.compteurMort = compteurMort;
	}
	
	public boolean contactAvant(Personnage p) {
		if(p.getY() > y + hauteur + 5 || p.getY() + hauteur < y - 5)
			return false;
		return (p.getX() > x + largeur + 5);
	}
	
	public boolean contactAvant(Objet o) {
		if(o.getY() > y + hauteur + 5 || o.getY() + hauteur < y - 5)
			return false;
		return (o.getX() > x + largeur + 5);
	}
	
	public boolean contactArriere(Personnage p) {
		if(p.getY() > y + hauteur + 5 || p.getY() + hauteur < y - 5)
			return false;
		return (p.getX() + p.getLargeur() + 5 < x);
	}
	
	public boolean contactArriere(Objet o) {
		if(o.getY() > y + hauteur + 5 || o.getY() + hauteur < y - 5)
			return false;
		return (o.getX() + o.getLargeur() + 5 < x);
	}
	
	public boolean contactDessous(Personnage p) {
		if(x + largeur < p.getX() - 5 || p.getX() + p.getLargeur() < x - 5 || y > p.getY())
			return false;
		return (p.getY() < y + hauteur + 5);
	}
	
	public boolean contactDessous(Objet o) {
		if(x + largeur < o.getX() - 5 || o.getX() + o.getLargeur() < x - 5 || y > o.getY())
			return false;
		return (o.getY() < y + hauteur + 5);
	}
	
	public boolean contactDessus(Personnage p) {
		if(x + largeur < p.getX() - 5 || p.getX() + p.getLargeur() < x - 5 || y < p.getY())
			return false;
		return (y < p.getY() + hauteur + 5);
	}
	
	public boolean contactDessus(Objet o) {
		if(x + largeur < o.getX() - 5 || o.getX() + o.getLargeur() < x - 5 || y < o.getY())
			return false;
		return (y < o.getY() + hauteur + 5);
	}
	
	public boolean proche(Personnage p) {
		if(p.getX() > x)
			return (p.getX() - (x + largeur) < 10);
		else
			return (x - (p.getX() + p.getLargeur()) < 10);
	}
	
	public boolean proche(Objet o) {
		if(o.getX() > x)
			return (o.getX() - (x + largeur) < 10);
		else
			return (x - (o.getX() + o.getLargeur()) < 10);
	}
	
	public Image marche(String nom, int frequence) {
		String str = "";
		if(!marche && versDroite)
			str = "ArretDroite.png";
		else if(!marche && !versDroite)
			str = "ArretGauche.png";
		else {
			compteur++;
			if(compteur / frequence == 0) {
				if(versDroite)
					str = "ArretDroite.png";
				else
					str = "ArretGauche.png";
			} else {
				if(versDroite)
					str = "MarcheDroite.png";
				else
					str = "MarcheGauche.png";
			}
			if(compteur == 2 * frequence)
				compteur = 0;
		}
		return new ImageIcon(getClass().getResource("/images/" + nom + str)).getImage();
	}
	
	public void deplacement() {
		if(Main.scene.getxPos() >= 0){
			this.setX(this.getX() - Main.scene.getDx());
		}
	}
}
