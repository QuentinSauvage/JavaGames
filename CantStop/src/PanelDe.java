import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JPanel;

@SuppressWarnings("serial")
public class PanelDe extends JPanel {
	private De de;
	Image[] imgDe = new Image[6];
	
	public PanelDe(De de) {
		this.de = de;
		for(int i = 0; i < 6; i++) {
			imgDe[i] = new ImageIcon(getClass().getResource("/images/dice-0" + (i + 1) + ".png")).getImage().getScaledInstance(100, 100, Image.SCALE_DEFAULT);;
		}
		this.setPreferredSize(new Dimension(100, 100));
		this.setBackground(Color.WHITE.brighter());
	}
	
	public De getDe() {
		return de;
	}

	public void setDe(De de) {
		this.de = de;
	}
	
	public void paintComponent(Graphics g) {
 		super.paintComponent(g);
 		g.setColor(Color.BLACK);
 		if(de.isLance())
 			g.drawImage(imgDe[de.getValeur() - 1], 0, 0, Main.fenetre);
	}
}
