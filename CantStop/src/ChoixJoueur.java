import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

@SuppressWarnings("serial")
public class ChoixJoueur extends JFrame {
			public ChoixJoueur(int x, int y) {
				this.setPreferredSize(new Dimension(250, 150));
				this.setTitle("Sélectionnez une action");
				this.getContentPane().setLayout(new FlowLayout());
				this.getContentPane().add(new JLabel("Choississez une combinaison : "));
				this.setAlwaysOnTop(true);
				this.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
				
				class Bouton extends JButton implements ActionListener {
					private int x;
					
					public Bouton(int x) {
						super(Integer.toString(x));
						this.x = x;
						this.addActionListener(this);
					}

					@Override
					public void actionPerformed(ActionEvent arg0) {
						Main.fenetre.modele.grimper(x, -1);
						Main.fenetre.modifierLancer(false);
						ChoixJoueur.this.dispose();
					}
				}
				this.getContentPane().add(new Bouton(x));
				this.getContentPane().add(new Bouton(y));
				this.pack();
				this.setResizable(false);
				this.setLocationRelativeTo(Main.fenetre);
				this.setVisible(true);
			}
		}
