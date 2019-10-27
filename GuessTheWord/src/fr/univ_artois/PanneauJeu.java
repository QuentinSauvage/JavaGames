package fr.univ_artois;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import javax.swing.JPanel;

/**
 * La classe PanneauJeu gère les éléments graphiques relatifs à l'écran de jeu principal.
 * @author quentin sauvage
 *
 */
public class PanneauJeu extends JPanel {
	
	/**
	 * L'ID du panneau.
	 */
	private static final long serialVersionUID = 1L;
	
	/**
	 * Les informations relatives au jeu.
	 * @see Jeu
	 * @see PanneauJeu#PanneauJeu(Jeu)
	 */
	private Jeu jeu;
	
	/**
	 * L'interface du pendu
	 * @see PanneauPendu
	 * @see PanneauJeu#PanneauJeu(Jeu)
	 */
	private PanneauPendu dessinPendu;
	
	/**
	 * Le clavier permettant au joueur de choisir des lettres.
	 * @see PanneauJeu#PanneauJeu(Jeu)
	 */
	private JPanel clavier;
	
	/**
	 * Construit le panneau en lui donnant accès au jeu.
	 * @param j Le jeu permettant d'accéder au mot et au joueur.
	 * @see PanneauPendu
	 */
	public PanneauJeu(Jeu j){
		jeu = j;
		this.setLayout(new BorderLayout());
		dessinPendu = new PanneauPendu(jeu);
		this.setSize(new Dimension(700, 700));
		clavier = new JPanel();
		clavier.setPreferredSize(new Dimension(700, 200));
		dessinPendu.setPreferredSize(new Dimension(700, 500));
		construireClavier();
		this.add(dessinPendu, BorderLayout.NORTH);
		this.add(clavier, BorderLayout.SOUTH);
	}
	
	/**
	 * Initialise le JPanel clavier ainsi que ses touches.
	 * @see PanneauJeu#clavier
	 * @see ToucheClavier
	 * @see PanneauJeu#jeu
	 */
	public void construireClavier(){
		clavier.setLayout(new GridLayout(4, 7));
		
		char c = 'A';
		for(int i = 0; i < 26; i++){
			ToucheClavier btn = new ToucheClavier(jeu, Character.toString(c));
			clavier.add(btn);
			c++;
		}
		clavier.add(new ToucheClavier(jeu, "ABANDON"));
		clavier.add(new ToucheClavier(jeu, ""));
	}
}
