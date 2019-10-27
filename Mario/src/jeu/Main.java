package jeu;

import java.awt.Dimension;

import javax.swing.JFrame;

public class Main {
	public static Scene scene;
	
	public static void main(String[] args) {
		JFrame fenetre = new JFrame();
		fenetre.setTitle("Mario");
		fenetre.setSize(new Dimension(700, 360));
		fenetre.setVisible(true);
		fenetre.setResizable(true);
		fenetre.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		fenetre.setAlwaysOnTop(true);
		scene = new Scene();
		fenetre.getContentPane().add(scene);
		scene.requestFocus(true);
		fenetre.validate();
	}
}
