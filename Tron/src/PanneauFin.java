import java.awt.Color;
import java.awt.Font;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class PanneauFin extends JPanel {

	private static final long serialVersionUID = 1L;
	private Jeu modele;

	public PanneauFin(Jeu m) {
		modele = m;
		this.setBackground(Color.BLACK);
		JLabel questionFin;
		if(m.getPersonnage().getDead())
			questionFin = new JLabel("GAME OVER ! Voulez-vous recommencer ?");
		else
			questionFin = new JLabel("Vous avez gagné ! Voulez-vous recommencer ?");
		questionFin.setForeground(Color.WHITE);
		this.add(questionFin);
		class Bouton extends JButton {
			private static final long serialVersionUID = 1L;
			private Jeu modele;
			
			public Bouton(String s, Jeu m) {
				super(s);
				modele = m;
				this.addActionListener(new ActionListener(){
					@Override
					public void actionPerformed(ActionEvent arg0) {
						modele.setEtat(s);
					}  
				});
			}
			
			public void paintComponent(Graphics g){
				Graphics2D g2d = (Graphics2D)g;
				GradientPaint gp;
				gp = new GradientPaint(0, 0, Color.BLACK, 50, 50, Color.CYAN.darker(), true);
				g2d.setPaint(gp);
				g2d.fillRect(0, 0, this.getWidth(), this.getHeight());
				g2d.setColor(Color.YELLOW);
				g2d.drawString(this.getText(), this.getWidth() / 2 / 4, (this.getHeight() / 2 + 5));
			}
		}
		this.add(new Bouton("Oui", modele));
		this.add(new Bouton("Non", modele));
	}
	
	public void paintComponent(Graphics g){
		Graphics2D g2d = (Graphics2D)g;
		g2d.setFont(new Font(g2d.getFont().getFontName(), Font.PLAIN, 15));
		GradientPaint gp;
		gp = new GradientPaint(0, 0, Color.BLACK, 50, 50, Color.DARK_GRAY, true);
		g2d.setPaint(gp);
		g2d.fillRect(0, 0, this.getWidth(), this.getHeight());
	}
}
