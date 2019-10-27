import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.KeyEvent;

public class Jeu {
	private Fenetre f;
	private PersonnageHumain p1;
	private PersonnageIA p2;
	private Direction mouvement1;
	private boolean jeuLance;
	private boolean jeuFini;
	private boolean pause;
	
	public Jeu(){
		jeuLance = false;
		jeuFini = false;
		pause = false;
	}
	
	public boolean getEtat() {
		return jeuFini;
	}
	
	public void setEtat(String s) {
		if(s == "Oui") {
			jeuFini = false;
			jeuLance = false;
			f.initialiserDebut();
		} else
			f.dispose();
	}
	
	public Personnage getPersonnage() {
		return p1;
	}
	
	public Personnage getPersonnage2() {
		return p2;
	}
	
	public void lancerJeu() {
		f = new Fenetre(this);
	}
	
	public void commencer() {
		p1 = new PersonnageHumain(1, 1, Color.BLUE);
		p2 = new PersonnageIA(Constantes.nbLignes - 2, Constantes.nbColonnes - 2, Color.RED);
		f.commencer();
	}
	
	public void calcul(){
		if(jeuLance && !pause) {
			if(p1.calcul(p2.getListe())) {
				pause = true;
			}
			else {
				p2.tourner(p1.getListe());
				p1.avancer();
			} if(p1.collisionJoueurs(p2.getFirst())) {
				p2.setDead();
			}
			if(p2.collisionJoueurs(p1.getFirst())) {
				p1.setDead();
			}
		}
	}

	public void gestion(KeyEvent e) {
		Controle(e);
		if(mouvement1 != null) {
			p1.tourner(mouvement1);
			mouvement1 = null;
		}
	}
	
	public void Controle(KeyEvent event) {
		Direction tmp = mouvement1;
		if(event.getKeyCode() == KeyEvent.VK_RIGHT) {
			mouvement1 = Direction.EST;
		} else if(event.getKeyCode() == KeyEvent.VK_LEFT) {
			mouvement1 = Direction.OUEST;
		} else if(event.getKeyCode() == KeyEvent.VK_UP) {
			mouvement1 = Direction.NORD;
		} else if(event.getKeyCode() == KeyEvent.VK_DOWN) {
			mouvement1 = Direction.SUD;
		}
		if(!jeuLance) {
			jeuLance = true;
			f.updateLabels();
		}
		if(pause) {
			p1.tourner(mouvement1);
			if(p1.getNext().estDedans()) {
				pause = false;
			}
			else
				mouvement1 = tmp;
		}
	}
	
	public void gameOver(Graphics g) {
		g.setColor(Color.WHITE);
		Font f = new Font("TimesRoman", Font.BOLD, 20);
		g.setFont(f);
		if(jeuFini && p1.getNbManches() == 3)
			g.drawString("Vous avez gagné !", (Constantes.nbColonnes * Constantes.nbPixels / 4), (Constantes.nbLignes * Constantes.nbPixels / 2));
		else
			g.drawString("GAME OVER !", (Constantes.nbColonnes * Constantes.nbPixels / 4), (Constantes.nbLignes * Constantes.nbPixels / 2));
	}
	
	public void recommencer() {
		jeuLance = false;
		p1.recommencer();
		p2.recommencer();
	}
	
	public void affichage(Graphics g) {
		p1.affichage(g);
		p2.affichage(g);
		g.setColor(Color.BLACK);
		boolean mort1 = p1.getDead();
		boolean mort2 = p2.getDead();
		if(mort1 || mort2) {
			if(mort1)
				p2.incNbManches();
			if(mort2)
				p1.incNbManches();
			if(p1.getNbManches() == 3 || p2.getNbManches() == 3) {
				jeuFini = true;
				gameOver(g);
				f.recommencer();
			}
			else {
				recommencer();
			}
			f.updateLabels(mort1, mort2);
		}
	}
}
