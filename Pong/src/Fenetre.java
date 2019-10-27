import java.awt.Dimension;
import javax.swing.JFrame;

/**
 * La Fenêtre contient les différents panneaux du jeu à afficher.
 * @author Quentin Sauvage
 */
@SuppressWarnings("serial")
public class Fenetre extends JFrame {
	/**
	 * modele contient toutes les informations relatives au déroulement du jeu.
	 */
	public static Jeu modele;
	
	/**
	 * Constructeur de fenêtre, amenant le joueur sur le panneau de création de partie.
	 * @see Accueil
	 */
	public Fenetre() {
		this.setTitle("Pong");
		this.setSize(new Dimension(400, 75));
		this.setVisible(true);
		this.setResizable(true);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setAlwaysOnTop(true);
		this.setContentPane(new Accueil());
		this.validate();
	}
	
	/**
	 * Recrée le jeu, avec le panneau demandant le choix de l'IA.
	 */
	public void recommencer() {
		this.setSize(new Dimension(400, 75));
		this.setContentPane(new Accueil());
		this.validate();

	}
	
	/**
	 * Affiche le plateau de jeu avec une ia/sans ia selon le choix du joueur.
	 * @param ia Le choix du joueur déterminant si l'IA est présente ou non.
	 * @see Jeu
	 */
	public void commencer(boolean ia) {
		this.setSize(new Dimension(700, 360));
		modele = new Jeu(ia);
		this.setContentPane((modele));
		modele.requestFocus(true);
	}
	
	/**
	 * Informe le joueur du vainqueur de la partie, et lui demande s'il veut en recommencer une autre.
	 * @param vainqueur Un entier correspondant à l'indice du joueur ayant gagné.
	 * @see OptionPaneFin
	 */
	public void afficherFin(int vainqueur) {
		OptionPaneFin fin = new OptionPaneFin();
		fin.updateText("Fin du jeu", "Le joueur " + vainqueur + " a gagné !\nVoulez-vous recommencer ?");
	}
}
