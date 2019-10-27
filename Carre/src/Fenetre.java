import java.awt.BorderLayout;
import java.awt.Dimension;
import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 * Fenetre contient tous les éléments graphiques du jeu, et fait le lien entre les éléments graphiques et ce dernier.
 * @author Quentin Sauvage
 *
 */
public class Fenetre extends JFrame {
	/**
	 * L'ID de la Fenetre, générée automatiquement.
	 */
	private static final long serialVersionUID = 1L;
	
	/**
	 * Le jeu contenant les informations à propos des joueurs, du plateau, etc...
	 */
	private Jeu modele;
	
	/**
	 * Le panneau demandant les caractéristiques de la partie.
	 */
	private PanneauChoix panneauChoix;
	
	/**
	 * Le plateau contient la grille du jeu.
	 */
	private Plateau plateau;
	
	/**
	 * content est le conteneur principal de la fenêtre.
	 */
	private JPanel contentJeu;
	
	/**
	 * Contient la liste des carrés dessinnés.
	 */
	private Carres carres;
	
	/**
	 * Affiche les points des joueurs 1 et 2.
	 */
	private PanneauManches pointsGauche;
	
	/**
	 * Affiche les points des joueurs 3 et 4.
	 */
	private PanneauManches pointsDroite;
	
	/**
	 * Permet d'agir sur le déroulement du jeu.
	 */
	private Thread thread;

	/**
	 * Constructeur de fenêtre, créant également tous les autres éléments graphiques.
	 * @param m Le jeu contenant les informations à propos des joueurs, du plateau, etc...
	 * @see Jeu
	 * @see PanneauChoix#PanneauChoix(Jeu)
	 * @see Carres#Carres(Jeu)
	 */
	public Fenetre(Jeu m){
		modele = m;
		this.setTitle("Dots and boxes");
		this.setSize(new Dimension(700, 700));
		this.setResizable(false);
		panneauChoix = new PanneauChoix(modele);
		carres = new Carres(modele);
		this.setFocusable(false);
		this.setContentPane(panneauChoix);
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.pack();
		this.setVisible(true);
	}
	
	/**
	 * Lance le thread et commence la partie grâce aux caractéristiques rentrées.
	 * @see Jeu#getEtat()
	 * @see Jeu#getTour()
	 * @see Jeu#jouerIA()
	 */
	public void commencer() {
		plateau = new Plateau(modele);
		contentJeu = new JPanel();
		contentJeu.setLayout(new BorderLayout());
		pointsGauche = new PanneauManches(0, modele);
		pointsDroite = new PanneauManches(2, modele);
		thread = new Thread(new Runnable() {
			@Override
			public void run() {
				while (!modele.getEtat()) {
					if (Constantes.IA && modele.getTour() > 0)
						modele.jouerIA();
					Fenetre.this.repaint();
					try {
						Thread.sleep(500);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			}
		});
		thread.start();
		contentJeu.add(pointsGauche, BorderLayout.WEST);
		contentJeu.add(plateau, BorderLayout.CENTER);
		contentJeu.add(pointsDroite, BorderLayout.EAST);
		this.setContentPane(contentJeu);
		this.setSize(this.getContentPane().getPreferredSize());
		this.repaint();
		this.revalidate();
		plateau.requestFocusInWindow();
	}
	
	/**
	 * Affiche les traits dans la fenêtre.
	 * @param traitsHorizontaux Le tableau contenant les traits horizontaux.
	 * @param traitsVerticaux Le tableau contenant les traits verticaux.
	 */
	public void ajouter(Trait[][] traitsHorizontaux, Trait[][] traitsVerticaux) {
		carres.setPreferredSize(new Dimension(Constantes.nbPixels * Constantes.nbLignes + 5, Constantes.nbPixels * Constantes.nbColonnes + 5));
		carres.setLayout(null);
		for(int i = 0; i < Constantes.nbLignes + 1; i++) {
			for(int j = 0; j < Constantes.nbLignes; j++) {
				carres.add(traitsHorizontaux[i][j]);
				carres.add(traitsVerticaux[i][j]);
			}
		}
		plateau.add(carres);
	}
	
	/**
	 * Informe carres qu'un carré vient d'être rempli.
	 * @param t Le trait se trouvant en haut (s'il est horizontal), ou à gauche (s'il est vertical) du carré à dessiner.
	 * @see Jeu#getTour()
	 * @see PanneauManches#updateScore()
	 */
	public void remplirCarre(Trait t) {
		carres.addCarre(t, Couleurs.values()[modele.getTour()]);
		if(modele.getTour() <= 1)
			pointsGauche.updateScore();
		else
			pointsDroite.updateScore();	
	}
	
	/**
	 * Recommence une nouvelle partie.
	 * @see Jeu#Jeu()
	 */
	public void recommencer() {
		this.dispose();
		modele = new Jeu();
	}
	
	/**
	 * Affiche la pop-up informant le joueur que la partie est finie. Indique aussi le vainqueur.
	 * @see Jeu#getTour()
	 * @see Jeu#getMaxPoints()
	 * @param plateauRempli booléen permettant de savoir si le plateau est rempli ou si un joueur a plus de la moitié des carrés disponibles.
	 */
	public void afficherFin(boolean plateauRempli) {
		OptionPaneFin fin = new OptionPaneFin();
		if(!plateauRempli) {
			fin.updateText("Fin du jeu", "Le joueur " + (modele.getTour() + 1) + " a gagné !\nVoulez-vous recommencer ?", this);
		}
		else {
			fin.updateText("Fin du jeu", "Le joueur " + (modele.getMaxPoints() + 1) + " a gagné !\nVoulez-vous recommencer ?", this);
		}
	}
}