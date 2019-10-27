import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;

import javax.swing.JLabel;
import javax.swing.JPanel;

public class PanneauManches extends JPanel {
	private static final long serialVersionUID = 1L;
	private JPanel ctn1;
	private JPanel ctn2;
	private JLabel joueur1;
	private JLabel joueur2;
	private JLabel mancheGagneeJ1;
	private JLabel mancheGagneeJ2;
	
	public PanneauManches(Jeu m) {
		this.setLayout(new BorderLayout());
		this.setBackground(Color.WHITE.darker());
		this.setPreferredSize(new Dimension(200, 100));
		ctn1 = new JPanel();
		ctn1.setLayout(new BorderLayout());
		ctn1.setBackground(Color.WHITE.darker());
		ctn2 = new JPanel();
		ctn2.setLayout(new BorderLayout());
		ctn2.setBackground(Color.WHITE.darker());
		Font f = new Font("Tahoma", Font.BOLD, 25);
		Font f2 = new Font("Tahoma", Font.BOLD, 15);
		joueur1 = new JLabel("Joueur 1 : " + m.getPersonnage().getNbManches());
		joueur1.setForeground(Color.BLUE.brighter());
		joueur1.setFont(f);
		joueur2 = new JLabel("Joueur 2 : " + m.getPersonnage2().getNbManches());
		joueur2.setForeground(Color.RED.darker());
		joueur2.setFont(f);
		mancheGagneeJ1 = new JLabel("");
		mancheGagneeJ2 = new JLabel("");
		mancheGagneeJ1.setForeground(Color.BLUE.darker());
		mancheGagneeJ1.setFont(f2);
		mancheGagneeJ2.setForeground(Color.RED.darker());
		mancheGagneeJ2.setFont(f2);
		ctn1.add(joueur1, BorderLayout.NORTH);
		ctn1.add(mancheGagneeJ1, BorderLayout.SOUTH);
		ctn2.add(joueur2, BorderLayout.SOUTH);
		ctn2.add(mancheGagneeJ2, BorderLayout.NORTH);
		this.add(ctn1, BorderLayout.NORTH);
		this.add(ctn2, BorderLayout.SOUTH);
	}
	
	public void updateLabels() {
		mancheGagneeJ1.setText("");
		mancheGagneeJ2.setText("");
	}
	
	public void updateLabels(Jeu m, boolean m1, boolean m2) {
		joueur1.setText("Joueur 1 : " + m.getPersonnage().getNbManches());
		joueur2.setText("Joueur 2 : " + m.getPersonnage2().getNbManches());
		if(m1)
			mancheGagneeJ2.setText("Point pour le joueur 2 !");
		if(m2)
			mancheGagneeJ1.setText("Point pour le joueur 1 !");
	}
}
