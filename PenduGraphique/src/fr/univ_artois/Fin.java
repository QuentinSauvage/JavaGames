package fr.univ_artois;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;

public class Fin extends JPanel{
	
	private Fenetre fenetre;
	
	public Fin(Fenetre f){
		fenetre = f;
		this.setPreferredSize(new Dimension(700, 700));
		
		Bouton oui = new Bouton("Oui !");
		Bouton non = new Bouton("Non...");
		ActionListener al = new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent arg0) {
				Bouton btn = (Bouton) arg0.getSource();
				notifierChoix(btn.getText());
			}
		};
		oui.addActionListener(al);
		non.addActionListener(al);
		this.add(oui);
		this.add(non);
	}
	
	public void notifierChoix(String s){
		fenetre.recommencer(s);
	}
	
	public void paintComponent(Graphics g){
		Graphics2D g2d = (Graphics2D)g;
		g2d.setBackground(Color.BLACK);
		g2d.fillRect(0, 0, this.getWidth(), this.getHeight());
		g2d.setColor(Color.YELLOW);
		g2d.drawString("Voulez-vous recommencer ?", this.getWidth()/10, this.getHeight()/10);
	}
}
