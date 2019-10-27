import java.awt.Color;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JOptionPane;

@SuppressWarnings("serial")
public class OptionPaneFin extends JOptionPane {

	public OptionPaneFin() {
		this.repaint();
	}

	public void updateText(String nom, String info) {
		Object[] options = {"Oui !", "Non."};
		int entree = JOptionPane.showOptionDialog(Main.fenetre.getContentPane(), info, nom, JOptionPane.PLAIN_MESSAGE, messageType, icon, options, options[0]);
		Main.fenetre.dispose();
		if(entree == 0) {
			Main.fenetre = new Fenetre();
		}
	}
	
	public void paintComponent(Graphics g){
		Graphics2D g2d = (Graphics2D)g;
		GradientPaint gp;
		gp = new GradientPaint(0, 0, Color.BLACK, 50, 50, Color.CYAN.darker(), true);
		g2d.setPaint(gp);
		g2d.fillRect(0, 0, this.getWidth(), this.getHeight());
		g2d.setColor(Color.YELLOW);
	}
}
