import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;

import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JPanel;

@SuppressWarnings("serial")
public class Fenetre extends JFrame {
	public Modele modele;
	private PanelDe listePanelDe[];
	private JPanel panelJoueurs;
	private Chrono chrono;
	
	public Fenetre() {
		this.setTitle("Can't Stop");
		this.setSize(new Dimension(450, 150));
		this.setVisible(true);
		this.setResizable(false);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		PanneauChoix pc = new PanneauChoix();
		this.setContentPane(pc);
	}
	
	public void lancer(int nbHumains) {
		modele = new Modele(nbHumains);
		JPanel ctn = new JPanel();
		ctn.setSize(new Dimension(1200, 800));
		this.setContentPane(ctn);
		this.getContentPane().setLayout(new BorderLayout());
		this.getContentPane().add(new Plateau(modele), BorderLayout.WEST);
		panelJoueurs = new JPanel();
		panelJoueurs.setLayout(new BoxLayout(panelJoueurs, BoxLayout.PAGE_AXIS));
		for(int i = 0; i < 4; i++) {
			panelJoueurs.add(new PanelJoueur(modele.getListeJoueurs()[i]));
		}
		JPanel des = new JPanel();
		des.setLayout(new GridLayout(1, 4));
		listePanelDe = new PanelDe[4];
		for(int i = 0; i < 4; i++) {
			listePanelDe[i] = new PanelDe(modele.getListeJoueurs()[0].getListeDes()[i]);
			des.add(listePanelDe[i]);
		}
		panelJoueurs.add(des);
		chrono = new Chrono();
		this.getContentPane().add(panelJoueurs, BorderLayout.EAST);
		this.pack();
	}
	
	public void updatePanelDe(De listeDes[]) {
		for(int i = 0; i < 4; i++) {
			listePanelDe[i].setDe(listeDes[i]);
		}
	}
	
	public void changerTour() {
		if(modele != null) {
			modele.setControle(modele.getControle() + 1);
			if(modele.getControle() > modele.getNbHumains() - 1) {
				System.out.println(chrono.getInterval());
				if(chrono.getInterval() == 3) {
					IA ia = (IA) modele.getListeJoueurs()[modele.getControle()];
					ia.jouer();
				}
				chrono.dormir();
			}
		}
	}
	
	public void metAJour(De listeDes[]) {
		modifierLancer(true);
		updatePanelDe(listeDes);
		new ActionJoueur(listePanelDe);
	}
	
	public void modifierLancer(boolean b) {
		PanelJoueur p = (PanelJoueur) panelJoueurs.getComponent(modele.getControle());
		p.setEstLance(b);
	}
	
	public void afficherFinDePartie() {
		OptionPaneFin fin = new OptionPaneFin();
		fin.updateText("Fin du jeu", "Le joueur " + (modele.getControle() + 1) + " a gagné !\nVoulez-vous recommencer ?");
	}
}
