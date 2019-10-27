package fr.univ_artois;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JPanel;

public class PanneauDifficulte extends JPanel{
	
	private Fenetre fenetre;
	private JPanel btnDiff;
	
	public PanneauDifficulte(Fenetre f){
		fenetre = f;
		btnDiff = new JPanel();
		this.setLayout(new BorderLayout());
		btnDiff.setLayout(new BorderLayout());
		class BoutonDifficulte extends JButton implements ActionListener{
			
			private Color color;
			
			public BoutonDifficulte(String str, Color c){
				super(str);
				this.setPreferredSize(new Dimension(700/3, 200));
				color = c;
				this.addActionListener(new ActionListener(){
					@Override
					public void actionPerformed(ActionEvent arg0) {
						BoutonDifficulte btn = (BoutonDifficulte) arg0.getSource();
						initDifficulte(btn.getText());
					}  
				});
				
			}

			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				
			}
			
			public void paintComponent(Graphics g){
				Graphics2D g2d = (Graphics2D)g;
				GradientPaint gp;
				gp = new GradientPaint(0, 0, Color.BLACK, 50, 50, color.darker(), true);
				g2d.setPaint(gp);
				g2d.fillRect(0, 0, this.getWidth(), this.getHeight());
				g2d.setColor(color);
				g2d.drawString(this.getText(), this.getWidth() / 2, (this.getHeight() / 2 + 5));
			}
		}
		btnDiff.add(new BoutonDifficulte("FACILE", Color.GREEN), BorderLayout.WEST);
		btnDiff.add(new BoutonDifficulte("MOYEN", Color.YELLOW), BorderLayout.CENTER);
		btnDiff.add(new BoutonDifficulte("DIFFICILE", Color.RED), BorderLayout.EAST);
		this.add(btnDiff, BorderLayout.SOUTH);
	}
	
	public void initDifficulte(String s){
		fenetre.lancerJeu(s);
	}
}
