package fr.univ_artois;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.naming.AuthenticationNotSupportedException;
import javax.swing.AbstractButton;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
 
public class Fenetre extends JFrame implements ActionListener{
	private JPanel conteneur;
	private JPanel clavier;
	private Fin fin;
	private PanneauDifficulte d;
	private Jeu jeu;
	private PanneauPendu ctnMot;
	private JPanelImage image;
	
	public Fenetre(Jeu j){ 
		jeu = j;
		conteneur = new JPanel();
		image = new JPanelImage();
		clavier = new JPanel();
		fin = new Fin(this);
		d = new PanneauDifficulte(this);
		this.setTitle("Pendu");
		this.setSize(new Dimension(700, 700));
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setLocationRelativeTo(null);
		this.setResizable(false);
		conteneur.setLayout(new BorderLayout());
		this.setContentPane(conteneur);
		//conteneur.add(image, BorderLayout.NORTH);
		conteneur.add(d,  BorderLayout.SOUTH);
		this.setVisible(true);
	}
	
	public void lancerJeu(String s){
		int vies;
		switch(s){
		case "DIFFICILE" :
			vies = 5;
			break;
		case "MOYEN" :
			vies = 7;
			break;
		default :
			vies = 9;
			break;
		}
		String m = jeu.genererMot();
		d.setVisible(false);
		construireClavier();
		ctnMot = new PanneauPendu(new Mot(m), vies);
		conteneur.add(clavier, BorderLayout.SOUTH);
		conteneur.add(ctnMot, BorderLayout.NORTH);
	}
	
	public void construireClavier(){
		clavier.setPreferredSize(new Dimension(700, 200));
		clavier.setLayout(new GridLayout(4, 7));
		
		char c = 'a';
		for(int i = 0; i < 26; i++){
			Bouton btn = new Bouton(Character.toString(c));
			btn.addActionListener(new ActionListener(){
	
				@Override
				public void actionPerformed(ActionEvent arg0) {
					Bouton btn = (Bouton) arg0.getSource();
					if(!btn.isSelected()){
						btn.setSelected(true);
						if(!ctnMot.getMot().actualiser(btn.getText().charAt(0))){
							ctnMot.decrease();
							if(ctnMot.getNbVies() == 0)
								demanderRecommencer();
						}
						conteneur.repaint();
					}
				}
				  
			});
			clavier.add(btn);
			c++;
		}
		clavier.add(new Bouton(""));
		clavier.add(new Bouton(""));
	}
	
	public void demanderRecommencer(){
		clavier.setVisible(false);
		ctnMot.setVisible(false);
		conteneur.add(fin);
	}
	
	public void recommencer(String s){
		jeu.recommencer(s.charAt(0) == 'O');
	}
	
	public void auRevoir(){
		Graphics2D g2d = (Graphics2D)fin.getGraphics();
		g2d.setColor(Color.WHITE);
		g2d.drawString("Au revoir !", this.getWidth()/2, this.getHeight()/2);
		fin.repaint();
		try{
			Thread.sleep(800);
		} catch(InterruptedException e){
			e.printStackTrace();
		}
		this.dispose();
	}
	
	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		
	}
}