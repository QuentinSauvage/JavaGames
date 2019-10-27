import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class PanneauChoix extends JPanel {

	private static final long serialVersionUID = 1L;
	private SlidePlateau slide;
	private JLabel label;
	private Jeu modele;
	
	public PanneauChoix(Jeu m) {
		this.setPreferredSize(new Dimension(450, 100));
		modele = m;
		JLabel bienvenue = new JLabel("Bienvenue ! Sélectionnez la taille du plateau de Tron : ");
		bienvenue.setForeground(Color.WHITE);
		this.add(bienvenue);
		slide = new SlidePlateau(5, 30, this);
		label = new JLabel("Valeur actuelle : " + String.valueOf(slide.getValue()));
		label.setForeground(Color.WHITE);
		this.add(label);
		this.add(slide);
		class BoutonValider extends JButton {
			private static final long serialVersionUID = 1L;
			private Jeu modele;
			
			public BoutonValider(String s, Jeu m) {
				super(s);
				modele = m;
				this.addActionListener(new ActionListener(){
					@Override
					public void actionPerformed(ActionEvent arg0) {
						PanneauChoix.this.recupererTaillePlateau();
						modele.commencer();
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
		this.add(new BoutonValider("OK", modele));
	}
	
	public void updateLabel() {
		label.setText("Valeur actuelle : " + String.valueOf(slide.getValue()));
	}
	
	public void recupererTaillePlateau() {
		Constantes.nbColonnes = Constantes.nbLignes = slide.getValue();
		if(Constantes.nbColonnes > 20)
			Constantes.nbPixels = 30;
		else if(Constantes.nbColonnes < 10) {
			Constantes.nbPixels = 90;
		}
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
