package objets;

import javax.swing.ImageIcon;

public class TuyauRouge extends Objet {
	
	public TuyauRouge(int x, int y) {
		super(43, 65, x, y);
        icoObjet = new ImageIcon(getClass().getResource("/images/tuyauRouge.png"));
        imgObjet = icoObjet.getImage();
	}
}
