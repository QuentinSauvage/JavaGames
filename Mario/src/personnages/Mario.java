package personnages;

import java.awt.Image;

import javax.swing.ImageIcon;

import jeu.Main;
import objets.Objet;
import objets.Piece;

public class Mario extends Personnage {
	
	private Image imgMario;
	private ImageIcon icoMario;
	private boolean saut;
	private int dxMario;
	private int compteurSaut;
	private int compteurMort;
	
	public Mario(int x, int y) {
		super(x, y, 28, 50);
		saut = false;
		marche = false;
		versDroite = true;
		dxMario = 0;
		icoMario = new ImageIcon(getClass().getResource("/images/marioArretDroite.png"));
		imgMario = icoMario.getImage();
		compteurSaut = 0;
		compteurMort = 0;
	}
	
	public int getDxMario() {
		return dxMario;
	}

	public void setDxMario(int dxMario) {
		this.dxMario = dxMario;
	}
	
	public boolean isSaut() {
		return saut;
	}

	public void setSaut(boolean saut) {
		this.saut = saut;
	}
	
	public Image meurt() {
		vivant = false;
		compteurMort++;
		String str;
		dxMario = 0;
		if(compteurMort > 100)
			str = "/images/marioMeurt.png";
		else
			str = "/images/boom.png";
		icoMario = new ImageIcon(getClass().getResource(str));
		imgMario = icoMario.getImage();
		return imgMario;
	}
	
	public void contact(Personnage personnage) {
		if(contactAvant(personnage) || contactArriere(personnage)) {
			marche = false;
			versDroite = false;
			saut = false;
			meurt();
		}
		if(contactDessous(personnage)) {
			personnage.vivant = false;
		}
	}
	
	public boolean contactPiece(Piece piece) {
		return contactAvant(piece) || contactArriere(piece) || contactDessus(piece) || contactDessous(piece);
	}
	
	public Image marche(String nom, int frequence) {
		if (Main.scene.getxPos() >= 0 && Main.scene.getxPos() < 4430) {
			return super.marche(nom , frequence);
		}
		return imgMario;
	}
	
	
	public Image saute(){
		String str;
		this.compteurSaut++;
		// Montee du saut
		if(this.compteurSaut <= 40){
			if(this.getY() > Main.scene.getHautPlafond()){this.setY(this.getY() - 4);}
			else{this.compteurSaut = 41;}
			if(this.isVersDroite() == true){str = "/images/marioSautDroite.png";}
			else{str = "/images/marioSautGauche.png";}
		// Retombee du saut
		}else if(this.getY() + this.getHauteur() < Main.scene.getySol()){this.setY(this.getY() + 1);
			if(this.isVersDroite() == true){str = "/images/marioSautDroite.png";}
			else{str = "/images/marioSautGauche.png";}
		// Saut termine
		}else{
			if(this.isVersDroite() == true){str = "/images/marioArretDroite.png";}
			else{str = "/images/marioArretGauche.png";}
			this.saut = false;
			this.compteurSaut = 0;
		}
		// Affichage de l'image de mario
		icoMario = new ImageIcon(getClass().getResource(str));
		imgMario = icoMario.getImage();
		return imgMario;
	}
	
	public void contact(Objet objet) {
		if((super.contactAvant(objet) == true && this.isVersDroite() == true) || (super.contactArriere(objet) == true && this.isVersDroite() == false)){
			Main.scene.setDx(0);
			this.setMarche(false);
		}
		if(super.contactDessous(objet) == true && this.saut == true){
			Main.scene.setySol(objet.getY());
		}else if(super.contactDessous(objet) == false){
			Main.scene.setySol(293); // altitude du sol initial
			if(this.saut == false){this.setY(243);}
		}
		if(super.contactDessus(objet) == true){
			Main.scene.setHautPlafond(objet.getY() + objet.getHauteur()); // le plafond devient le dessous de l'objet
		}else if(super.contactDessus(objet) == false && this.saut == false){
			Main.scene.setHautPlafond(0);
		}
	}
}
