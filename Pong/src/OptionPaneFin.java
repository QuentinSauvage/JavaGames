import java.awt.Color;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JOptionPane;

/**
 * Une JOptionPane indiquant au joueur que la partie est finie.
 * @author Quentin Sauvage
 */
@SuppressWarnings("serial")
public class OptionPaneFin extends JOptionPane {

	/**
	 * A sa création, l'OptionPaneFin se dessine pour que le joueur puisse la voir.
	 */
	public OptionPaneFin() {
		this.repaint();
	}

	/**
	 * Informe le joueur du vainqueur de la partie et lui propose de recommencer.
	 * @param nom Le nom de la JOptionPane.
	 * @param info Le message qui sera affiché et lu par le joueur.
	 * @see Fenetre#commencer(boolean)
	 */
	public void updateText(String nom, String info) {
		Object[] options = {"Oui !", "Non."};
		int entree = JOptionPane.showOptionDialog(Main.fenetre.getContentPane(), info, nom, JOptionPane.PLAIN_MESSAGE, messageType, icon, options, options[0]);
		if(entree == 1)
			Main.fenetre.dispose();
		else
			Main.fenetre.recommencer();
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
