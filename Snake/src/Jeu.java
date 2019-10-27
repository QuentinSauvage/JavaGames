import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.KeyEvent;

public class Jeu implements Constantes {
	private Fenetre f;
	private Personnage p;
	private Direction mouvement;
	private Piece piece;
	private int lvl;
	
	public Jeu(){
		f = new Fenetre(this);
		p = new Personnage();
		mouvement = Direction.OUEST;
		piece = new Piece();
		lvl = 0;
	}
	
	public void calcul(){
		if(p != null && !p.getDead()) {
			calculNiveau();
			if(mouvement != null) {
				p.tourner(mouvement);
				mouvement = null;
			}
			p.calcul(piece);
		}
	}
	
	public Personnage getPersonnage() {
		return p;
	}
	
	public int getNiveau() {
		return lvl;
	}
	
	public int getVitesse() {
		return vitesses[lvl];
	}
	
	public void calculNiveau() {
		lvl = (p.getNbPieces() / 3);
	}
	
	public void gestion(KeyEvent e) {
		Controle(e);
	}
	
	public void Controle(KeyEvent event) {
		if(event.getKeyCode() == KeyEvent.VK_RIGHT)
			mouvement = Direction.EST;
		else if(event.getKeyCode() == KeyEvent.VK_LEFT)
			mouvement = Direction.OUEST;
		else if(event.getKeyCode() == KeyEvent.VK_UP)
			mouvement = Direction.NORD;
		else if(event.getKeyCode() == KeyEvent.VK_DOWN)
			mouvement = Direction.SUD;
	}
	
	public void gameOver(Graphics g) {
		g.setColor(Color.RED.darker());
		Font f = new Font("TimesRoman", Font.BOLD, 30);
		g.setFont(f);
		g.drawString("GAME OVER !", (nbColonnes * nbPixels / 4), (nbLignes * nbPixels / 2));
	}
	
	public void affichage(Graphics g) {
		p.affichage(g);
		piece.affichage(g);
		g.setColor(Color.BLACK);
		g.drawString("Niveau : " + lvl, 10, 10);
		if(p.getDead())
			gameOver(g);
	}
}
