package view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import controler.AbstractControler;

/**
 * Contient les éléments graphiques de la création d'une grille.
 * @author Quentin Sauvage
 *
 */
@SuppressWarnings("serial")
public class GuiCreation extends JFrame implements Observer {
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
	 * La grille du jeu.
	 */
	protected Grille grille;
	/**
	 * La hauteur de la grille.
	 */
	protected int hauteur;
	/**
	 * La largeur de la grille.
	 */
	protected int largeur;
	
	/**
	 * Constructeur de GuiCreation.
	 * @param c Le controler gérant les actions du joueur.
	 * @param h La hauteur de la grille.
	 * @param l La largeur de la grille.
	 */
	public GuiCreation(AbstractControler c, int h, int l) {
		super();
		hauteur = h;
		largeur = l;
		this.setSize(new Dimension(850, 850));
		this.setTitle("Picross");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setLocationRelativeTo(null);
		this.setResizable(false);
		container.setPreferredSize(new Dimension(this.getWidth(), this.getHeight()));
		container.setBackground(Color.WHITE);
		grille = new Grille();
		grille.setPreferredSize(new Dimension(700, 700));
		cases = new Case[h][l];
		for(int i = 0; i < h; i++) {
			for(int j = 0; j < l; j++) {
				cases[i][j] = new Case();
				grille.add(cases[i][j]);
			}
		}
		grille.setBackground(Color.BLACK);
		controler = c;
		JPanel panFinir = new JPanel();
		panFinir.setBackground(Color.WHITE);
		JPanel pan2 = new JPanel();
		pan2.setBackground(Color.WHITE);
		JPanel pan3= new JPanel();
		pan3.setBackground(Color.WHITE);
		panFinir.setPreferredSize(new Dimension(100, 100));
		pan2.setPreferredSize(new Dimension(700, 100));
		pan3.setPreferredSize(new Dimension(100, 700));
		JTextField jtf = new JTextField();
		jtf.setColumns(8);
		panFinir.add(new JLabel("Nom : "));
		panFinir.add(jtf);
		JButton enregistrer = new JButton("Enregistrer");
		enregistrer.addActionListener(e -> enregistrerGrille(jtf.getText()));
		panFinir.add(enregistrer);
		container.add(panFinir);
		container.add(pan2);
		container.add(pan3);
		container.add(grille);
		this.setContentPane(container);
		this.pack();
		this.setVisible(true);
	}
	
	/**
	 * Demande au controler de gérer l'enregistrement d'un fichier.
	 * @param str Le nom du fichier à enregistrer.
	 */
	public void enregistrerGrille(String str) {
		controler.setJeu(str);
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
			this.setLayout(new GridLayout(hauteur, largeur));
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
					int x = me.getX() / (grille.getWidth() / largeur);
					int y = me.getY() / (grille.getHeight() / hauteur);
					controler.setJeu(x, y, cases[y][x].getValeur(), me.getButton());
				}
			});
		}
	}
	
	@Override
	public void update(int x, int y, int typeClic, String str) {
		cases[x][y].setValeur(typeClic);
		repaint();
	}
	
	public void update(String str) {
		if(str == "enregistrer") {
			JOptionPane.showMessageDialog(this, "Enregistrement effectu�", "Succ�s", JOptionPane.INFORMATION_MESSAGE);
			this.dispose();
		}
		else {
			JOptionPane.showMessageDialog(this, "Probl�me lors de l'enregistrement.", "Erreur", JOptionPane.ERROR_MESSAGE);
			}
	}

}
