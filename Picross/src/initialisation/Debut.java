package initialisation;

import java.awt.Color;
import java.awt.Dimension;
import java.io.File;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;
import javax.swing.SwingConstants;

import controler.AbstractControler;
import controler.CreationControler;
import controler.GrilleControler;
import model.AbstractModel;
import model.CreationModel;
import model.GrilleModel;
import view.GuiCreation;
import view.GuiGrille;

/**
 * Fenêtre d'accueil du Picross.
 * @author Quentin Sauvage
 *
 */
public class Debut {
	/**
	 * La fenêtre d'accueil.
	 */
	private static JFrame fenetre = new JFrame();
	/**
	 * Une ArrayList qui contient le nom de tous les fichiers dans le répertoire grilles.
	 */
	private static ArrayList<String> nomsFichiers = new ArrayList<String>();
	/**
	 * Une JComboBox qui permet au joueur de sélectionner un niveau.
	 */
	private static JComboBox<Object> choix;
	
	/**
	 * Crée les éléments graphiques de la fenêtre.
	 */
	public static void lancerJeu() {
		fenetre.setSize(new Dimension(400, 150));
		fenetre.setTitle("Picross");
		fenetre.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		fenetre.setLocationRelativeTo(null);
		fenetre.setResizable(false);
		JPanel container = new JPanel();
		JLabel bienvenue = new JLabel("Bienvenue ! Choisissez un niveau : ", SwingConstants.CENTER);
		JLabel creation = new JLabel("Ou créez-en un (en renseignant les dimensions à côté) : ", SwingConstants.CENTER);
		bienvenue.setForeground(Color.WHITE);
		container.add(bienvenue);
		ArrayList<String> listeNiveaux = new ArrayList<String>();
		File f = new File("src/grilles");
		String[] liste = f.list();
		for(int i = 0; i < liste.length; i++) {
			nomsFichiers.add(liste[i]);
			listeNiveaux.add("Grille " + (i + 1));
		}
		choix = new JComboBox<Object>(listeNiveaux.toArray());
		container.add(choix);
		JButton valider = new JButton("valider");
		valider.addActionListener(e -> valider());
		container.add(valider);
		creation.setForeground(Color.WHITE);
		container.add(creation);
		JButton nouveau = new JButton("Créer une grille");
		container.setBackground(Color.BLACK);
		JSpinner hauteur = new JSpinner(new SpinnerNumberModel(10, 4, 20, 1));
		JSpinner largeur = new JSpinner(new SpinnerNumberModel(10, 4, 20, 1));
		JLabel h = new JLabel("Hauteur : ", SwingConstants.CENTER);
		JLabel l = new JLabel("Largeur : ", SwingConstants.CENTER);
		h.setForeground(Color.WHITE);
		l.setForeground(Color.WHITE);
		nouveau.addActionListener(e -> creerGrille(((int)hauteur.getValue()), ((int)largeur.getValue())));
		container.add(nouveau);
		container.add(h);
		container.add(hauteur);
		container.add(l);
		container.add(largeur);
		fenetre.setContentPane(container);
		fenetre.setVisible(true);
	}
	
	/**
	 * Instancie les classes du MVC de manière à pouvoir gérer la création d'une grille.
	 * @param h La hauteur de la grille à créer.
	 * @param l La largeur de la grille à créer.
	 */
	public static void creerGrille(int h, int l) {
		AbstractModel grille = new CreationModel(h, l);
		AbstractControler controler = new CreationControler(grille);
		GuiCreation guiCreation = new GuiCreation(controler, h, l);
		grille.addObserver(guiCreation);
		fenetre.dispose();
	}
	
	/**
	 * Instancie les classes du MVC de manière à pouvoir gérer la découverte d'une grille.
	 */
	public static void valider() {
		AbstractModel grille = new GrilleModel("src/grilles/" + nomsFichiers.get(choix.getSelectedIndex()));
		AbstractControler controler = new GrilleControler(grille);
		GuiGrille guiGrille = new GuiGrille(controler, grille);
		grille.addObserver(guiGrille);
		fenetre.dispose();
	}
}
