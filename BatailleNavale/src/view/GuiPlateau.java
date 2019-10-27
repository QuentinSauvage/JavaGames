package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import controler.AbstractControler;
import initialisation.Debut;
import model.AbstractModel;
import model.CaseJeu;

/**
 * Contient les éléments graphiques du plateau.
 * @author Quentin Sauvage
 *
 */
@SuppressWarnings("serial")
public class GuiPlateau extends JFrame implements Observer {
	/**
	 * Le controler qui gère les actions du joueur.
	 */
	private AbstractControler controler;
	/**
	 * Une matrice qui contient toutes les cases de la grille du joueur 1.
	 */
	private Case[][] cases1;
	/**
	 * Le model contenant les infos du jeu nécessaires à la fenêtre.
	 */
	private AbstractModel model;
	/**
	 * JLabel représentant l'historique de la partie.
	 */
	private JLabel label;
	/**
	 * Une matrice qui contient toutes les cases de la grille du joueur 2.
	 */
	private Case[][] cases2;
	/**
	 * La grille du joueur 1.
	 */
	protected Grille grille1;
	/**
	 * La grille du joueur 2.
	 */
	protected Grille grille2;
	
	/**
	 * Constructeur de GuiPlateau.
	 * @param c Le controler gérant les clics des joueurs.
	 * @param m Le model permettant d'accéder à certaines infos.
	 */
	public GuiPlateau(AbstractControler c, AbstractModel m) {
		super();
		model = m;
		this.setSize(new Dimension(1240, 510));
		this.setTitle("Bataille navale");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setLocationRelativeTo(null);
		this.setResizable(false);
		JPanel container = new JPanel();
		container.setPreferredSize(new Dimension(this.getWidth(), this.getHeight()));
		container.setBackground(Color.WHITE);
		JPanel plateau = new JPanel();
		plateau.setBackground(Color.WHITE);
		plateau.setLayout(new BorderLayout());
		plateau.setPreferredSize(new Dimension(1200, 500));
		grille1 = new Grille();
		grille2 = new Grille();
		cases1 = new Case[10][10];
		cases2 = new Case[10][10];
		CaseJeu[][] grilleJeu1 = model.getJoueur1().getGrille();
		CaseJeu[][] grilleJeu2 = model.getJoueur2().getGrille();
		for(int i = 0; i < 10; i++) {
			for(int j = 0; j < 10; j++) {
				cases1[i][j] = new Case(grilleJeu1[i][j].getValue());
				cases2[i][j] = new Case(grilleJeu2[i][j].getValue());
				grille1.add(cases1[i][j]);
				grille2.add(cases2[i][j]);
			}
		}
		controler = c;
		JPanel historique = new JPanel();
		historique = new JPanel();
		label = new JLabel("<html></html>");
		historique.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		historique.setBackground(Color.ORANGE);
		historique.add(label);
		plateau.add(grille1, BorderLayout.WEST);
		plateau.add(historique, BorderLayout.CENTER);
		plateau.add(grille2, BorderLayout.EAST);
		container.add(plateau);
		this.setContentPane(container);
		this.pack();
		this.setVisible(true);
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
			this.setLayout(new GridLayout(10, 10));
			this.setPreferredSize(new Dimension(500, 500));
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
					boolean gauche;
					if(arg0.getSource().equals(grille1))
						gauche = true;
					else
						gauche = false;
					controler.setJeu(arg0.getX() / 50, arg0.getY() / 50, gauche);
				}
			});
		}
	}
	
	/**
	 * Indique quel joueur à gagner la partie et demande de recommencer.
	 * @param joueur Le vainqueur de la partie.
	 */
	public void gagner(boolean joueur) {
		String str;
		Object[] options = {"Oui !", "Non."};
		if(joueur) {
			str = "Le joueur 2";
		} else {
			str = "Le joueur 1";
		}
		str += " a gagné ! Voulez-vous recommencer ?";
		int choix = JOptionPane.showOptionDialog(this, str, "Félicitations !", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, options[0]);
		this.dispose();
		if(choix == 0)
			Debut.lancerJeu();
	}

	@Override
	public void update(int x, int y, boolean gauche, String str) {
		if(str.startsWith("touché") || str.startsWith("coulé") || str == "victoire")  {
			if(gauche)
				cases1[x][y].setValeur(2);
			else
				cases2[x][y].setValeur(2);
		} else if(str == "raté") {
			if(gauche)
				cases1[x][y].setValeur(3);
			else
				cases2[x][y].setValeur(3);
		}
		if(label.getHeight() > this.getHeight() - 100)
			label.setText("<html></html>");
		if(str != "non" && str != "victoire") {
			label.setText(label.getText().substring(0, label.getText().length() - 7));
			if(gauche)
				label.setText(label.getText() + "<br>J1 : ");
			else
				label.setText(label.getText() + "<br>J2 : ");
			label.setText(label.getText() + str + "</html>");
		}
		repaint();
		if(str == "victoire") {
			gagner(gauche);
		}
	}

}
