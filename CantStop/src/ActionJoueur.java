import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

@SuppressWarnings("serial")
public class ActionJoueur extends JFrame {
	public ActionJoueur(PanelDe liste[]) {
		this.setPreferredSize(new Dimension(250, 150));
		this.setTitle("Sélectionnez une action");
		this.getContentPane().setLayout(new FlowLayout());
		this.getContentPane().add(new JLabel("Choississez une combinaison : "));
		this.setAlwaysOnTop(true);
		this.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		class Bouton extends JButton implements ActionListener {
			private int x;
			private int y;
			
			public Bouton(int x, int y) {
				super("(" + Integer.toString(x) + ", " + Integer.toString(y) + ")");
				this.x = x;
				this.y = y;
				this.addActionListener(this);
			}

			@Override
			public void actionPerformed(ActionEvent arg0) {
				Main.fenetre.modele.grimper(x, y);
				Main.fenetre.modifierLancer(false);
				ActionJoueur.this.dispose();
			}
		}
		ArrayList<Pair> al = new ArrayList<Pair>();
		Pair p;
		for(int j = 1; j < 4; j++) {
			if(j == 1) {
				p = new Pair(liste[0].getDe().getValeur() + liste[1].getDe().getValeur(), liste[2].getDe().getValeur() + liste[3].getDe().getValeur());
			} else if(j == 2) {
				p = new Pair(liste[0].getDe().getValeur() + liste[2].getDe().getValeur(), liste[1].getDe().getValeur() + liste[3].getDe().getValeur());
			} else {
				p = new Pair(liste[0].getDe().getValeur() + liste[3].getDe().getValeur(), liste[1].getDe().getValeur() + liste[2].getDe().getValeur());
			}
			if(!al.contains(p)) {
				this.getContentPane().add(new Bouton(p.getX(), p.getY()));
				al.add(p);
			}
		}
		this.pack();
		this.setResizable(false);
		this.setLocationRelativeTo(Main.fenetre);
		this.setVisible(true);
	}
}
