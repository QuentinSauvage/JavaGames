package objets;

import javax.swing.ImageIcon;

public class Bloc extends Objet {
	
	public Bloc(int x, int y) {
		super(30, 30, x, y);
		icoObjet = new ImageIcon(getClass().getResource("/images/bloc.png"));
		imgObjet = icoObjet.getImage();
	}
}
