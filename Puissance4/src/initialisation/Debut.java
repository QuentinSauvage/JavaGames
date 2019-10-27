package initialisation;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.SwingConstants;

import controler.AbstractControler;
import controler.Puissance4Controler;
import model.AbstractModel;
import model.IA;
import model.Puissance4;
import vue.Vue;

/**
 * Crée le panneau de configuration de la partie.
 * @author Quentin Sauvage
 *
 */
@SuppressWarnings("serial")
public class Debut {
	/**
	 * La JFrame où sont contenus tous les éléments graphiques.
	 */
	private static JFrame fenetre = new JFrame();
	
	/**
	 * Le slider récupérant le choix du joueur pour la largeur de la grille.
	 * @see SlideChoix
	 */
	protected static SlideChoix slideTailleX;
	
	/**
	 * JLabel qui indique au joueur la largeur actuellement choisie.
	 */
	protected static JLabel labelTailleX;
	
	/**
	 * Le slider récupérant le choix du joueur pour la hauteur de la grille.
	 * @see SlideChoix
	 */
	protected static SlideChoix slideTailleY;
	
	/**
	 * JLabel qui indique au joueur la hauteur actuellement choisie.
	 */
	protected static JLabel labelTailleY;
	
	/**
	 * Le slider récupérant le choix du joueur pour le nombre de pions à aligner.
	 * @see SlideChoix
	 */
	protected static SlideChoix alignes;
	
	/**
	 * JLabel qui indique au joueur le nombre de pions à aligner actuellement choisi.
	 */
	protected static JLabel labelAlignes;
	
	/**
	 * Permet de savoir si le joueur veut jouer avec l'IA.
	 */
	protected static JCheckBox ia;
	
	/**
	 * Permet de savoir si le joueur veut activer le mode suicide.
	 */
	protected static JCheckBox mode;
	
	/**
	 * Une JComboBox qui indique les couleurs possibles pour le joueur 1.
	 * @see Couleurs
	 */
	protected static Couleurs j1;
	
	/**
	 * Une JComboBox qui indique les couleurs possibles pour le joueur 2.
	 * @see Couleurs
	 */
	protected static Couleurs j2;
	
	/**
	 * Lance le jeu et crée le panneau de configuration.
	 * @see BoutonValider
	 * @see SlideChoix
	 * @see Couleurs
	 */
	public static void lancerJeu() {
		fenetre.setSize(new Dimension(500, 500));
		fenetre.setTitle("Puissance 4");
		fenetre.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		fenetre.setLocationRelativeTo(null);
		fenetre.setResizable(false);
		JPanel container = new JPanel();
		container.setBackground(Color.BLACK);
		GridLayout grid = new GridLayout(7, 2);
		grid.setVgap(5);
		grid.setHgap(5);
		container.setLayout(grid);
		
		JLabel bienvenue = new JLabel("Bienvenue !", SwingConstants.CENTER);
		bienvenue.setForeground(Color.WHITE);
		container.add(bienvenue);
		container.add(new BoutonValider("Valider les choix"));
		slideTailleX = new SlideChoix(5, 12, 1);
		slideTailleY = new SlideChoix(5, 12, 1);
		alignes = new SlideChoix(3, 7, 1);
		slideTailleX.setValue(7);
		slideTailleY.setValue(6);
		alignes.setValue(4);
		labelTailleX = new JLabel("Longueur : " + String.valueOf(slideTailleX.getValue()), SwingConstants.CENTER);
		labelTailleX.setForeground(Color.WHITE);
		labelTailleY = new JLabel("Hauteur : " + String.valueOf(slideTailleY.getValue()), SwingConstants.CENTER);
		labelTailleY.setForeground(Color.WHITE);
		labelAlignes = new JLabel("Pions à aligner : " + String.valueOf(alignes.getValue()), SwingConstants.CENTER);
		labelAlignes.setForeground(Color.WHITE);
		container.add(labelTailleX);
		container.add(slideTailleX);
		container.add(labelTailleY);
		container.add(slideTailleY);
		container.add(labelAlignes);
		container.add(alignes);
		
		String[] textCouleursj1 = {"Rouge", "Vert", "Noir", "Orange"};
		Color[] valCouleursj11 = { Color.RED, Color.GREEN, Color.BLACK, Color.ORANGE};
		Color[] valCouleursj12 = {Color.YELLOW, Color.MAGENTA, Color.GRAY, Color.BLUE};
		String[] textCouleursj2 = {"Jaune", "Magenta", "Gris", "Bleu"};
		Color[] valCouleursj21 = {Color.YELLOW, Color.MAGENTA, Color.GRAY, Color.BLUE};
		Color[] valCouleursj22 = { Color.RED, Color.GREEN, Color.BLACK, Color.ORANGE};
		j1 = new Couleurs(textCouleursj1, valCouleursj11, valCouleursj12);
		j2 = new Couleurs(textCouleursj2, valCouleursj21, valCouleursj22);
		JLabel labelJoueur1 = new JLabel("Couleur du joueur 1", SwingConstants.CENTER);
		labelJoueur1.setForeground(Color.WHITE);
		JLabel labelJoueur2 = new JLabel("Couleur du joueur 2", SwingConstants.CENTER);
		labelJoueur2.setForeground(Color.WHITE);
		container.add(labelJoueur1);
		container.add(labelJoueur2);
		container.add(j1);
		container.add(j2);
		
		ia = new JCheckBox();
		ia.setText("Jouer avec l'IA");
		ia.setHorizontalTextPosition(SwingConstants.LEFT);
		ia.setForeground(Color.WHITE);
		ia.setBackground(Color.RED);
		container.add(ia);
		mode = new JCheckBox();
		mode.setText("Mode Suicide");
		mode.setHorizontalTextPosition(SwingConstants.RIGHT);
		mode.setForeground(Color.WHITE);
		mode.setBackground(Color.RED);
		container.add(mode);
		fenetre.setContentPane(container);
		fenetre.setVisible(true);
	}
	
	/**
	 * Met à jour les différents indicateurs des sliders
	 */
	public static void updateLabels() {
		labelTailleX.setText("Longueur : " + String.valueOf(slideTailleX.getValue()));
		labelTailleY.setText("Hauteur : " + String.valueOf(slideTailleY.getValue()));
		alignes.setMaximum(getMinValue() - 2);
		labelAlignes.setText("Pions à aligner : " + String.valueOf(alignes.getValue()));
	}
	
	/**
	 * Récupère la plus petite valeur choisie entre le slider pour la largeur et le slider pour la hauteur.
	 * @return La taille minimum sélectionnée.
	 */
	public static int getMinValue() {
		if(slideTailleX.getValue() < slideTailleY.getValue())
			return slideTailleX.getValue();
		return slideTailleY.getValue();
	}

	/**
	 * Récupère les valeurs sélectionnées par le joueur et crée la fenêtre de jeu.
	 */
	public static void recupererValeurs() {
		Proprietes.setValeurs(slideTailleY.getValue(), slideTailleX.getValue(), alignes.getValue(), ia.isSelected(), mode.isSelected(), j1.getColor1(), j1.getColor2(), j2.getColor1(), j2.getColor2());
		initialiser();
	}
	
	/**
	 * Permet de modifier une JComboBox en fonction de sa valeur actuelle.
	 * @author Quentin Sauvage
	 */
	static class Couleurs extends JComboBox<String> implements ActionListener {
		/**
		 * Les couleurs possibles pour le joueur 1.
		 */
		private Color[] couleurs1;
		
		/**
		 * Les couleurs possibles pour le joueur 2.
		 */
		private Color[] couleurs2;
		
		/**
		 * Crée la JComboBox Couleurs en fonction des valeurs possibles, et associe pour chaque entrée un couple de couleurs.
		 * @param str La valeur affichée dans la JComboBox.
		 * @param couleurs1 La première couleur du joueur.
		 * @param couleurs2 La deuxième couleur du joueur.
		 */
		public Couleurs(String[] str, Color[] couleurs1, Color[] couleurs2) {
			super(str);
			((JLabel)this.getRenderer()).setHorizontalAlignment(SwingConstants.CENTER);
			this.setFocusable(false);
			this.setForeground(Color.WHITE);
			this.couleurs1 = couleurs1;
			this.couleurs2 = couleurs2;
			addActionListener(this);  
			update();
		}
		
		/**
		 * Permet d'accéder à la première couleur du joueur.
		 * @return La première couleur du joueur.
		 */
		public Color getColor1() {
			return couleurs1[this.getSelectedIndex()];
		}
		
		/**
		 * Permet d'accéder à la deuxième couleur du joueur.
		 * @return La deuxième couleur du joueur.
		 */
		public Color getColor2() {
			return couleurs2[this.getSelectedIndex()];
		}
		
		/**
		 * Met à jour les couleurs de la JComboBox.
		 */
		public void update() {
			if(this.getSelectedItem() == "Jaune" || this.getSelectedItem() == "Vert" || this.getSelectedItem() == "Orange")
				this.setForeground(Color.BLACK);
			else
				this.setForeground(Color.WHITE);
			this.setBackground(couleurs1[this.getSelectedIndex()]);
		}
		
		/**
		 * Met à jour la JComboBox suite à un clic.
		 */
		public void actionPerformed(ActionEvent arg0) {
			update();
		}
	}
	
	/**
	 * Crée un Slider permettant au joueur de choisir une valeur entre deux bornes.
	 * @author Quentin Sauvage
	 */
	static class SlideChoix extends JSlider implements MouseListener {

		/**
		 * Crée le slider à partir de deux bornes et d'un espacement.
		 * @param i La borne minimum.
		 * @param j La borne maximum.
		 * @param space L'espacement entre deux valeurs.
		 */
		public SlideChoix(int i, int j, int space) {
			super(i, j);
			this.setForeground(Color.WHITE);
			this.setBackground(new Color(0, 0, 102));
			addMouseListener(this);
			this.setMajorTickSpacing(space);
			this.setMinorTickSpacing(1);
			this.setPaintTicks(true);
			this.setPaintLabels(true);
		}

		/**
		 * Met à jour les labels de la fenêtre à partir des nouvelles valeurs.
		 */
		@Override
		public void mouseReleased(MouseEvent e) {
			Debut.updateLabels();
		}
		
		@Override
		public void mousePressed(MouseEvent e) {}
		
		@Override
		public void mouseExited(MouseEvent e) {	}
		
		@Override
		public void mouseEntered(MouseEvent e) {}
		
		@Override
		public void mouseClicked(MouseEvent e) {}
	}
	
	/**
	 * Indique à la classe Début qu'elle doit créer la fenêtre de jeu.
	 * @author Quentin Sauvage
	 */
	static class BoutonValider extends JButton {
		
		/**
		 * Crée le bouton à partir d'un nom donné en paramètres.
		 * @param s Le nom du bouton.
		 */
		public BoutonValider(String s) {
			super(s);
			this.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent arg0) {
					Debut.recupererValeurs();
				}  
			});
		}
		
		public void paintComponent(Graphics g){
			Graphics2D g2d = (Graphics2D)g;
			GradientPaint gp;
			gp = new GradientPaint(0, 0, Color.BLUE, 50, 50, Color.CYAN.darker(), true);
			g2d.setPaint(gp);
			g2d.fillRect(0, 0, this.getWidth(), this.getHeight());
			g2d.setColor(Color.YELLOW);
			g2d.drawString(this.getText(), this.getWidth() / 2 / 4, (this.getHeight() / 2 + 5));
		}
	}
	
	/**
	 * Crée une nouvelle fenêtre de jeu.
	 */
	public static void initialiser() {
		AbstractModel model;
		if(Proprietes.ia)
			model = new IA();
		else
			model = new Puissance4();
		AbstractControler controler = new Puissance4Controler(model);
		Vue vue = new Vue(controler, model);
		model.addObserver(vue);
		fenetre.dispose();
	}
}
