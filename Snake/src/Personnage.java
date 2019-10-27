import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.util.LinkedList;

public class Personnage implements Constantes{
	private LinkedList<Case> listeCases;
	private Direction direction;
	private boolean dead;
	private int nbPiecesAttrapees;
	
	public Personnage(){
		nbPiecesAttrapees = 0;
		listeCases = new LinkedList<Case>();
		listeCases.add(new Case(2, 2));
		listeCases.add(new Case(3, 2));
		listeCases.add(new Case(4, 2));
		direction = Direction.OUEST;
		dead = false;
	}
	
	public boolean getDead() {
		return dead;
	}
	
	public Case getFirst() {
		return listeCases.getFirst();
	}
	
	public int getNbPieces() {
		return nbPiecesAttrapees;
	}
	
	public void incNbPieces() {
		nbPiecesAttrapees++;
	}
	
	public void setDead() {
		dead = true;
	}
	
	public Case getNext() {
		int x = 0;
		int y = 0;
		if(direction == Direction.SUD) { 
			x =  getFirst().getX();
			y = getFirst().getY() + 1;
		} else if(direction == Direction.EST) {
			x = getFirst().getX() + 1;
			y = getFirst().getY();
		} else if(direction == Direction.NORD) {
			x = getFirst().getX();
			y = getFirst().getY() - 1;
		} else { 
			x = getFirst().getX() - 1;
			y = getFirst().getY();
		}
		return new Case(x, y);
	}
	
	public void tourner(Direction d) {
		boolean tourne = true;
		if(d == direction)
			tourne = false;
		if((d == Direction.EST && direction == Direction.OUEST))
			tourne = false;
		if((d == Direction.OUEST && direction == Direction.EST))
			tourne = false;
		if((d == Direction.NORD && direction == Direction.SUD))
			tourne = false;
		if((d == Direction.SUD && direction == Direction.NORD))
			tourne = false;
		if(tourne)
			direction = d;
	}
	
	public void afficherListe() {
		System.out.println(listeCases);
	}
	
	public boolean peutAvancer() {
		Case c = getNext();
		if(c.estDedans()) {
			if(listeCases.contains(getNext()))
				return false;
			return true;
		}
		return false;
	}
	
	public void avancer() {
		listeCases.removeLast();
		listeCases.addFirst(getNext());
	}
	
	public void calcul(Piece p){
		if(peutAttraper(p)) {
			attraper();
			p.generer();
		}
		else if(peutAvancer())
			avancer();
		else
			setDead();
	}
	
	public boolean peutAttraper(Piece p) {
		if(getNext().getX() == p.getX() && getNext().getY() == p.getY())
			return true;
		return false;
	}
	
	public void attraper() {
		listeCases.addFirst(getNext());
		incNbPieces();
	}
	
	public void affichage(Graphics g) {
		Graphics2D g2d = (Graphics2D)g;
		g2d.setColor(Color.GREEN);
		for(Case c: listeCases){
			g2d.fillOval(c.getPositionX(), c.getPositionY(), c.getLongueur(), c.getLargeur());
		}
		g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
	}
}
