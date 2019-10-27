package view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import controler.AbstractControler;
import initialisation.Debut;
import model.AbstractModel;

/**
 * Contient les éléments graphiques de la découverte d'une grille.
 * @author Quentin Sauvage
 *
 */
@SuppressWarnings("serial")
public class GuiGrille extends JFrame implements Observer {

	/**
	 * Le JPanel contenant tous les éléments de la fenêtre.
	 */
	private JPanel container = new JPanel();
	/**
	 * Une matrice qui contient toutes les cases de la grille.
	 */
	private Case[][] cases;
	/**
	 * Le controler qui gère les actions du joueur.
	 */
	private AbstractControler controler;
	/**
	 * La grille à deviner.
	 */
	protected Grille grille;
	/**
	 * Le model donnant accès à certaines informations nécessaires à propos de la création d'une grille.
	 */
	protected AbstractModel model;
	
	/**
	 * Constructeur de GuiGrille.
	 * @param c Le controler qui gère les actions du joueur.
	 * @param m Le modele donnant accès à certaines informations nécessaires à propos de la création d'une grille.
	 */
	public GuiGrille(AbstractControler c, AbstractModel m) {
		super();
		model = m;
		this.setSize(new Dimension(900, 900));
		this.setTitle("Picross");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setLocationRelativeTo(null);
		this.setResizable(false);
		container.setPreferredSize(new Dimension(this.getWidth(), this.getHeight()));
		container.setBackground(Color.WHITE);
		grille = new Grille();
		grille.setPreferredSize(new Dimension(700, 700));
		cases = new Case[model.getHauteur()][model.getLargeur()];
		for(int i = 0; i < model.getHauteur(); i++) {
			for(int j = 0; j < model.getLargeur(); j++) {
				cases[i][j] = new Case();
				grille.add(cases[i][j]);
			}
		}
		grille.setBackground(Color.BLACK);
		controler = c;
		JPanel panSolution = new JPanel();
		panSolution.setBackground(Color.WHITE);
		JPanel pan2 = new JPanel();
		pan2.setBackground(Color.WHITE);
		JPanel pan3= new JPanel();
		pan3.setBackground(Color.WHITE);
		panSolution.setPreferredSize(new Dimension(150, 150));
		pan2.setPreferredSize(new Dimension(700, 150));
		pan3.setPreferredSize(new Dimension(150, 700));
		pan2.setLayout(new GridLayout(1, model.getLargeur()));
		pan3.setLayout(new GridLayout(model.getHauteur(), 1));
		Font f = new Font("Kai", Font.BOLD, 20);
		ArrayList<String> al = model.getLignes();
		for(String s : al) {
			JLabel label = new JLabel();
			label.setFont(f);
			label.setText(s);
			pan3.add(label);
		}
		al = model.getColonnes();
		for(String s : al) {
			JLabel label = new JLabel();
			label.setFont(f);
			label.setText(s);
			label.setBorder(BorderFactory.createEmptyBorder(0, 10, 0, 0));
			pan2.add(label);
		}
		JButton solution = new JButton("Solution");
		solution.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                solution.setSelected(!solution.isSelected());
                afficherSolution(solution.isSelected());
            }
        });
		panSolution.add(solution);
		container.add(panSolution);
		container.add(pan2);
		container.add(pan3);
		container.add(grille);
		this.setContentPane(container);
		this.pack();
		this.setVisible(true);
	}
	
	/**
	 * Modifie la grille pour afficher celle à obtenir, ou inversement.
	 * @param selectionne Indique si la grille à obtenir est actuellement affichée.
	 */
	public void afficherSolution(Boolean selectionne) {
		if(selectionne) {
			int[][] grilleObjectif = model.getGrilleObjectif();
			for(int i = 0; i < model.getHauteur(); i++) {
				for(int j = 0; j < model.getLargeur(); j++) {
					cases[i][j].setValeur(grilleObjectif[i][j]);
				}
			}
		} else {
			int[][] grilleJeu = model.getGrilleJeu();
			for(int i = 0; i < model.getHauteur(); i++) {
				for(int j = 0; j < model.getLargeur(); j++) {
					cases[i][j].setValeur(grilleJeu[i][j]);
				}
			}
		}
		repaint();
	}

	/**
	 * Un GridLayout dessinant la grille.
	 * @author quentin
	 *
	 */
	class Grille extends JPanel {
		
		/**
		 * Constructeur vide de la grille.
		 */
		public Grille() {
			super();
			this.setLayout(new GridLayout(model.getHauteur(), model.getLargeur()));
			this.addMouseListener(new MouseListener() {
				
				@Override
				public void mouseReleased(MouseEvent arg0) {
					// TODO Auto-generated method stub
					
				}
				
				@Override
				public void mousePressed(MouseEvent arg0) {
					// TODO Auto-generated method stub
					
				}
				
				@Override
				public void mouseExited(MouseEvent arg0) {
					// TODO Auto-generated method stub
					
				}
				
				@Override
				public void mouseEntered(MouseEvent arg0) {
					// TODO Auto-generated method stub
					
				}
				
				/**
				 * Demande au controler de gérer le clic du joueur.
				 */
				@Override
				public void mouseClicked(MouseEvent me) {
					int x = me.getX() / (grille.getWidth() / model.getLargeur());
					int y = me.getY() / (grille.getHeight() / model.getHauteur());
					controler.setJeu(x, y, cases[y][x].getValeur(), me.getButton());
				}
			});
		}
	}
	
	/**
	 * Permet au joueur de recommencer une partie.
	 * @param gagner Un booléen indiquant si le joueur souhaite recommencer une partie.
	 */
	public void recommencer(boolean gagner) {
		String str1, str2;
		Object[] options = {"Oui !", "Non."};
		if(gagner) {
			str1 = "Vous avez gagné";
			str2 = "Félicitations !";
		} else {
			str1 = "Vous avez perdu";
			str2 = "GAME OVER";
		}
		str1 += ", voulez-vous recommencer ?";
		int choix = JOptionPane.showOptionDialog(this, str1, str2, JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, options[0]);
		this.dispose();
		if(choix == 0)
			Debut.lancerJeu();
	}
	
	@Override
	public void update(int x, int y, int typeClic, String str) {
		cases[x][y].setValeur(typeClic);
		repaint();
		if(str == "gagner")
			recommencer(true);
		else if(str == "perdu")
			recommencer(false);
	}

	@Override
	public void update(String str) {
		// TODO Auto-generated method stub
		
	}
	
}
