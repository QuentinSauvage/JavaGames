package objets;

import java.awt.Image;

import javax.swing.ImageIcon;

public class Piece extends Objet implements Runnable {

	private int compteur;
	
	public Piece(int x, int y) {
		super(30, 30, x, y);
		icoObjet = new ImageIcon(getClass().getResource("/images/piece1.png"));
		imgObjet = icoObjet.getImage();
		compteur = 0;
	}
	
	public Image bouge() {
		compteur++;
		String str;
		ImageIcon ico;
		Image img;
		if(compteur == 200)
			compteur = 0;
		if(compteur / 100 == 0)
			str = "/images/piece1.png";
		else
			str = "/images/piece2.png";
		ico = new ImageIcon(getClass().getResource(str));
		img = ico.getImage();
		return img;
	}
	
	@Override
	public void run() {
		try {
			Thread.sleep(20);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		while(true) {
			imgObjet = bouge();
			try {
				Thread.sleep(15);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

}
