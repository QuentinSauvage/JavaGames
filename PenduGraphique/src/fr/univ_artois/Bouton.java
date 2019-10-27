package fr.univ_artois;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JButton;

public class Bouton extends JButton {
	private String name;
	public Bouton(String str){
		super(str);
		this.name = str;
		this.setPreferredSize(new Dimension(50, 50));
	}
	
	public void paintComponent(Graphics g){
		Graphics2D g2d = (Graphics2D)g;
		GradientPaint gp;
		if(this.isSelected())
			gp = new GradientPaint(0, 0, Color.RED, 50, 50, Color.CYAN, true);
		else
			gp = new GradientPaint(0, 0, Color.BLACK, 50, 50, Color.CYAN, true);
		g2d.setPaint(gp);
		g2d.fillRect(0, 0, this.getWidth(), this.getHeight());
		g2d.setColor(Color.YELLOW);
		g2d.drawString(this.name, this.getWidth() / 2 / 4, (this.getHeight() / 2 + 5));
	}
}