package personnages;

import java.awt.Image;

import javax.swing.ImageIcon;

import objets.Objet;

public class Tortue extends Personnage implements Runnable {

	private int dxTortue;
	private Image imgTortue;
	private ImageIcon icoTortue;
	
	public Tortue(int x, int y) {
		super(x, y, 27, 30);
		versDroite = false;
		marche = true;
		dxTortue = -1;
		icoTortue = new ImageIcon(getClass().getResource("/images/tortueArretGauche.png"));
		imgTortue = icoTortue.getImage();
		Thread chronoTortue = new Thread(this);
		chronoTortue.start();
	}
	
	public void bouge() {
		if(vivant) {
			if(versDroite)
				dxTortue = 1;
			else {
				dxTortue = -1;
			}
			super.setX(super.getX() + dxTortue);
		}
	}
	
	public Image meurt() {
		vivant = false;
		dxTortue = 0;
		icoTortue = new ImageIcon(getClass().getResource("/images/tortueFermee.png"));
		imgTortue = icoTortue.getImage();
		return imgTortue;
	}
	
	public void contact(Objet objet) {
		if(contactAvant(objet) && versDroite == true) {
			versDroite = !versDroite;
			dxTortue = -1;
		} else if(contactArriere(objet) && versDroite == false) {
			versDroite = !versDroite;
			dxTortue = 1;
		}
	}
	
	public void contact(Personnage personnage) {
		if(contactAvant(personnage) && versDroite == true) {
			personnage.setVersDroite(!personnage.isVersDroite());
			versDroite = !versDroite;
			dxTortue = -1;
		} else if(contactArriere(personnage) && versDroite == false) {
			personnage.setVersDroite(!personnage.isVersDroite());
			versDroite = !versDroite;
			dxTortue = 1;
		}
	}
	
	@Override
	public void run() {
		try {
			Thread.sleep(20);
		} catch (InterruptedException e1) {
			e1.printStackTrace();
		}
		while(true) {
			bouge();
			try {
				Thread.sleep(30);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

}
