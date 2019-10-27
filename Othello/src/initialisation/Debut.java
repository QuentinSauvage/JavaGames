package initialisation;

import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import controler.AbstractControler;
import controler.GrilleControler;
import model.AbstractModel;
import model.Grille;
import view.GuiGrille;

/**
 * Demande au joueur de définir les paramètres de la partie.
 * @author Quentin Sauvage
 *
 */
public class Debut {
	/**
	 * La fenêtre d'accueil.
	 */
	private static JFrame fenetre = new JFrame();
	
	/**
	 * Crée les éléments graphiques de la fenêtre.
	 */
	public static void lancerJeu() {
		fenetre.setSize(new Dimension(350, 100));
		fenetre.setTitle("Othello");
		fenetre.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		fenetre.setLocationRelativeTo(null);
		fenetre.setResizable(false);
		JPanel container = new JPanel();
		JLabel bienvenue = new JLabel("Bienvenue ! Voulez-vous jouer avec l'IA ?", SwingConstants.CENTER);
		bienvenue.setForeground(Color.WHITE);
		container.setBackground(Color.BLACK);
		container.add(bienvenue);
		JButton sans = new JButton("Sans IA");
		sans.addActionListener(e -> valider(false));
		JButton avec = new JButton("Avec IA");
		avec.addActionListener(e -> valider(true));
		container.add(sans);
		container.add(avec);
		fenetre.setContentPane(container);
		fenetre.setVisible(true);
	}
	
	/**
	 * Instancie les classes du MVC de manière à pouvoir gérer le déroulement d'une partie.
	 */
	public static void valider(boolean ia) {
		AbstractModel grille = new Grille(ia);
		AbstractControler controler = new GrilleControler(grille);
		GuiGrille guiGrille = new GuiGrille(controler);
		grille.addObserver(guiGrille);
		fenetre.dispose();
	}
}
