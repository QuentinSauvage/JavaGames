import java.awt.Color;
import java.awt.Dimension;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;


@SuppressWarnings("serial")
public class PanneauChoix extends JPanel {
	private SlideChoix nbHumains;
	private JLabel labelNbHumains;
	
	public PanneauChoix() {
		this.setSize(new Dimension(450, 150));
		JLabel bienvenue = new JLabel("Bienvenue ! Sélectionnez le nombre de joueurs humains : ");
		bienvenue.setForeground(Color.WHITE);
		this.add(bienvenue);
		nbHumains = new SlideChoix(1, 4, this, 1);
		labelNbHumains = new JLabel("Valeur actuelle : " + String.valueOf(nbHumains.getValue()));
		labelNbHumains.setForeground(Color.WHITE);
		this.add(labelNbHumains);
		this.add(nbHumains);
		
		class BoutonValider extends JButton {
			public BoutonValider(String s) {
				super(s);
				this.addActionListener(new ActionListener(){
					@Override
					public void actionPerformed(ActionEvent arg0) {
						Main.fenetre.lancer(nbHumains.getValue());
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
		this.add(new BoutonValider("OK"));
	}

	public void updateLabel() {
		labelNbHumains.setText("Valeur actuelle : " + String.valueOf(nbHumains.getValue()));
	}
	
	public void paintComponent(Graphics g){
		g.fillRect(0, 0, this.getWidth(), this.getHeight());

	}
}
