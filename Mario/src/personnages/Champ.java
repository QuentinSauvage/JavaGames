package personnages;

import java.awt.Image;

import javax.swing.ImageIcon;

import objets.Objet;

public class Champ extends Personnage implements Runnable {

	private int dxChamp;
	private Image imgChamp;
	private ImageIcon icoChamp;
	
	public Champ(int x, int y) {
		super(x, y, 27, 30);
		versDroite = true;
		marche = true;
		dxChamp = 1;
		icoChamp = new ImageIcon(getClass().getResource("/images/champArretDroite.png"));
		imgChamp = icoChamp.getImage();
		Thread chronoChamp = new Thread(this);
		chronoChamp.start();
	}
	
	public void bouge() {
		if(vivant) {
			if(versDroite)
				dxChamp = 1;
			else {
				dxChamp = -1;
			}
			super.setX(super.getX() + dxChamp);
		}
	}
	
	public Image meurt() {
		String str;
		vivant = false;
		dxChamp = 0;
		if(versDroite)
			str ="/images/champEcraseDroite.png";
		else
			str = "/images/champEcraseGauche.png";
		icoChamp = new ImageIcon(getClass().getResource(str));
		imgChamp = icoChamp.getImage();
		return imgChamp;
	}
	
	public void contact(Objet objet) {
		if(contactAvant(objet) && versDroite == true) {
			versDroite = !versDroite;
			dxChamp = -1;
		} else if(contactArriere(objet) && versDroite == false) {
			versDroite = !versDroite;
			dxChamp = 1;
		}
	}
	
	public void contact(Personnage personnage) {
		if(contactAvant(personnage) && versDroite == true) {
			personnage.setVersDroite(!personnage.isVersDroite());
			versDroite = !versDroite;
			dxChamp = -1;
		} else if(contactArriere(personnage) && !versDroite == false) {
			personnage.setVersDroite(!personnage.isVersDroite());
			versDroite = !versDroite;
			dxChamp = 1;
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
				Thread.sleep(15);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

}
