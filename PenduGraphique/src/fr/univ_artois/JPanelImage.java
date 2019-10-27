package fr.univ_artois;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

public class JPanelImage extends JPanel{
	public JPanelImage(){
		this.setPreferredSize(new Dimension(700, 500));
	}
	
	public void paintComponent(Graphics g){
		try {
			Image img = ImageIO.read(new File("fleur.jpg"));
			g.drawImage(img, 0, 0, 700, 500, this);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
