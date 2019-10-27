import java.awt.Color;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JOptionPane;

/**
 * Une pop-up demandant au joueur s'il veut recommencer une partie.
 * @author Quentin Sauvage
 *
 */
public class OptionPaneFin extends JOptionPane {

	/**
	 * L'ID d'OtionPaneFin, générée automatiquement.
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Constructeur d'OptionPaneFin.
	 */
	public OptionPaneFin() {
		this.repaint();
	}
	
	/**
	 * Affiche le texte de la pop-up selon les paramètres données.
	 * @see Fenetre#recommencer()
	 * @param nom Le nom de la pop-up.
	 * @param info Le message d'information de la pop-up.
	 * @param f La fenêtre dans laquelle sera affichée la pop-up.
	 */
	public void updateText(String nom, String info, Fenetre f) {
		Object[] options = {"Oui !", "Non."};
		int entree = JOptionPane.showOptionDialog(f.getContentPane(), info, nom, JOptionPane.PLAIN_MESSAGE, messageType, icon, options, options[0]);
		if(entree == 1)
			f.dispose();
		else
			f.recommencer();
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
