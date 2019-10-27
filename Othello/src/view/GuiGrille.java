package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import controler.AbstractControler;
import initialisation.Debut;

/**
 * Contient les éléments graphiques de la grille.
 * @author Quentin Sauvage
 *
 */
@SuppressWarnings("serial")
public class GuiGrille extends JFrame implements Observer {
	/**
	 * Le controler qui gère les actions du joueur.
	 */
	private AbstractControler controler;
	/**
	 * Une matrice qui contient toutes les cases de la grille.
	 */
	private Case[][] cases;
	/**
	 * Le score du joueur 1.
	 */
	private JLabel score1;
	/**
	 * Le score du joueur 2.
	 */
	private JLabel score2;
	/**
	 * La grille du jeu.
	 */
	protected Grille grille;
	
	/**
	 * Constructeur de GuiGrille.
	 * @param c Le controler qui gère les actions du joueur.
	 */
	public GuiGrille(AbstractControler c) {
		this.setSize(new Dimension(1100, 900));
		this.setTitle("Othello");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setLocationRelativeTo(null);
		this.setResizable(false);
		JPanel container = new JPanel();
		container.setPreferredSize(new Dimension(this.getWidth(), this.getHeight()));
		container.setBackground(new Color(100, 50, 0));
		JPanel plateau = new JPanel();
		plateau.setBackground(new Color(100, 50, 0));
		plateau.setLayout(new BorderLayout());
		plateau.setPreferredSize(new Dimension(800, 800));
		grille = new Grille();
		cases = new Case[8][8];
		for(int i = 0; i < 8; i++) {
			for(int j = 0; j < 8; j++) {
				cases[i][j] = new Case();
				grille.add(cases[i][j]);
			}
		}
		cases[3][4].setValeur(1);
		cases[4][4].setValeur(2);
		cases[3][3].setValeur(2);
		cases[4][3].setValeur(1);
		controler = c;
		plateau.add(grille);
		JPanel panScore1 = new JPanel();
		JPanel panScore2 = new JPanel();
		panScore1.setPreferredSize(new Dimension(100, 800));
		panScore2.setPreferredSize(new Dimension(100, 800));
		panScore1.setBackground(new Color(100, 50, 0));
		panScore2.setBackground(new Color(100, 50, 0));
		Font f = new Font("Kai", Font.BOLD, 40);
		score1 = new JLabel("2");
		score1.setForeground(Color.WHITE);
		score1.setFont(f);
		score2 = new JLabel("2");
		score2.setForeground(Color.WHITE);
		score2.setFont(f);
		panScore1.add(score1);
		panScore2.add(score2);
		container.add(panScore1);
		container.add(plateau);
		container.add(panScore2);
		JButton passer = new JButton("Passer le tour");
		passer.addActionListener(e -> passerTour());
		container.add(passer);
		this.setContentPane(container);
		this.pack();
		this.setVisible(true);
	}
	
	/**
	 * Un GridLayout dessinant la grille.
	 * @author Quentin Sauvage
	 *
	 */
	class Grille extends JPanel {
		
		/**
		 * Constructeur vide de la grille.
		 */
		public Grille() {
			super();
			this.setLayout(new GridLayout(8, 8));
			this.setPreferredSize(new Dimension(800, 800));
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
				public void mouseClicked(MouseEvent arg0) {
					controler.setJeu(arg0.getX() / 100, arg0.getY() / 100);
				}
			});
		}
	}
	
	/**
	 * Demande au controler de gérer le passage de tour.
	 */
	public void passerTour() {
		controler.setJeu();
	}
	
	/**
	 * Indique le gagnant et demande aux joueurs s'ils veulent recommencer une partie.
	 * @param j Un nombre qui indique quel joueur a gagné ou s'il y a eu égalité.
	 */
	public void demanderRecommencer(int j) {
		String str;
		Object[] options = {"Oui !", "Non."};
		if(j == 1 || j == 2)
			str = "Le joueur " + j + " a gagné !";
		else
			str = "Égalité !";
		str +=  " Voulez-vous recommencer ?";
		int choix = JOptionPane.showOptionDialog(this, str, "Félicitations !", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, options[0]);
		this.dispose();
		if(choix == 0)
			Debut.lancerJeu();
	}

	@Override
	public void update(int x, int y, int tour, ArrayList<int[]> al, int s1, int s2, String str) {
		score1.setText(Integer.toString(s1));
		score2.setText(Integer.toString(s2));
		if(str == "fini") {
			for(int[] tab : al) {
				cases[tab[1]][tab[0]].setValeur(tour);
			}
			repaint();
			if(s1 > s2)
				demanderRecommencer(1);
			else if(s2 > s1)
				demanderRecommencer(2);
			else
				demanderRecommencer(0);
		} if(str == "oui") {
			for(int[] tab : al) {
				cases[tab[1]][tab[0]].setValeur(tour);
				repaint();
			}
		}
	}
	
	@Override
	public void update(String str) {
		if(str == "non")
			JOptionPane.showMessageDialog(this, "Vous pouvez jouer.", "Impossible de passer le tour", JOptionPane.WARNING_MESSAGE);
	}

}
