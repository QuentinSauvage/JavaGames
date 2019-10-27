import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextArea;

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
	 * Le plateau gère les tuiles du jeu.
	 */
	private Plateau plateau;
	
	/**
	 * Le panneau score affiche le score du joueur.
	 */
	private PanneauScore score;
	
	/**
	 * content est le conteneur principal de la fenêtre.
	 */
	private JPanel content;
	
	/**
	 * fin, un panneau indiquant au joueur que la partie est finie.
	 */
	private PanneauFin fin;
	
	/**
	 * description informe le joueur des commandes mises à sa disposition.
	 */
	private JPanel description;
	
	/**
	 * Constructeur de fenêtre, créant également tous les autres éléments graphiques.
	 * @param m Le jeu contenant les informations à propos des joueurs, du plateau, etc...
	 * @see Jeu
	 * @see PanneauScore#PanneauScore(Jeu)
	 * @see PanneauFin#PanneauFin(Jeu)
	 * @see Plateau#Plateau(Jeu)
	 * @see Jeu#gestion(KeyEvent)
	 * @see Jeu#getEtat()
	 * @see Jeu#getIA()
	 * @see Jeu#jouerIA()
	 */
	public Fenetre(Jeu m) {
		modele = m;
		this.setTitle("2048");
		this.setSize(new Dimension(700, 700));
		this.setResizable(false);
		description = new JPanel();
		description.setBackground(new Color(250, 248, 239));
		JTextArea desc = new JTextArea("Dans 2048, l'objectif est de faire fusionner les tuiles de même valeur entre elles, jusqu'à atteindre la valeur 2048.\n"
		+ "\nCommandes : \n-Flèches directionnelles pour déplacer les tuiles.\n-H pour afficher/masquer cette description.\n-J pour réinitialiser le jeu.\n-Space pour activer/désactiver l'IA.\n-Echap pour quitter.");
		desc.setBackground(new Color(250, 248, 239));
		desc.setLineWrap(true);
		desc.setWrapStyleWord(true);
		desc.setPreferredSize(new Dimension(200, 500));
		desc.setEditable(false);
		description.add(desc);
		content = new JPanel();
		content.setLayout(new BorderLayout());
		score = new PanneauScore(modele);
		fin = new PanneauFin(modele);
		plateau = new Plateau(modele);
		plateau.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				Fenetre.this.modele.gestion(e);
				Fenetre.this.repaint();
			}
		});
		this.setFocusable(false);
		
		Thread thread = new Thread(new Runnable() {
			@Override
			public void run() {
				while (!modele.getEtat()) {
					if(modele.getIA())
						modele.jouerIA();
					plateau.repaint();
					Fenetre.this.repaint();
					try {
						Thread.sleep(400);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			}
		});
		thread.start();
		
		content.add(score, BorderLayout.NORTH);
		content.add(plateau, BorderLayout.CENTER);
		this.setContentPane(content);
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.pack();
		this.setVisible(true);
	}
	
	/**
	 * Informe le joueur de la fin de la partie.
	 * @see PanneauFin
	 * @see PanneauFin#estFini(boolean)
	 */
	public void recommencer() {
		fin.estFini(modele.getFini());
		content.add(fin, BorderLayout.SOUTH);
		this.repaint();
		this.revalidate();
	}
	
	/**
	 * Met à jour les scores du joueur.
	 */
	public void updateScore() {
		score.updateScore();
	}
	
	/**
	 * Masque le panneau affichant la fin du jeu.
	 */
	public void removeFin() {
		content.remove(fin);
		this.repaint();
		this.revalidate();
		plateau.requestFocusInWindow();
	}
	
	/**
	 * Affiche la description du jeu lorsque le joueur la demande.
	 * @param d Booléen indiquant si la description doit être masquée ou affichée
	 */
	public void afficherDescription(boolean d) {
		if(d) {
			content.remove(description);
		} else {
			content.add(description, BorderLayout.EAST);
		}
		this.repaint();
		this.revalidate();
			
	}
	
}
