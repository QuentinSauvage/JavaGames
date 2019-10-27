import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.util.LinkedList;

public class Personnage {
	protected LinkedList<Case> listeCases;
	protected Direction direction;
	private boolean dead;
	private Color couleur;
	private int nbManches;
	
	public Personnage(int x, int y, Color c){
		listeCases = new LinkedList<Case>();
		listeCases.add(new Case(x, y));
		dead = false;
		couleur = c;
		nbManches = 0;
	}
	
	public void incNbManches() {
		nbManches++;
	}
	
	public int getNbManches() {
		return nbManches;
	}
	
	public boolean getDead() {
		return dead;
	}
	
	public Case getFirst() {
		return listeCases.getFirst();
	}
	
	public LinkedList<Case> getListe() {
		return listeCases;
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
	
	public void afficherListe() {
		System.out.println(listeCases);
	}
	
	public boolean collisionJoueurs(Case c) {
		return listeCases.contains(c);
	}
	
	public boolean testerMort(LinkedList<Case> liste) {
		boolean mort = true;
		Direction tmp = direction;
		direction = Direction.NORD;
		if(peutAvancer(liste))
			mort = false;
		direction = Direction.OUEST;
		if(peutAvancer(liste))
			mort = false;
		direction = Direction.SUD;
		if(peutAvancer(liste))
			mort = false;
		direction = Direction.EST;
		if(peutAvancer(liste))
			mort = false;
		direction = tmp;
		return mort;
	}
	
	public boolean peutAvancer(LinkedList<Case> l) {
		Case c = getNext();
		if(c.estDedans()) {
			if(listeCases.contains(c) || l.contains(c))
				return false;
			return true;
		}
		return false;
	}
	
	public void avancer() {
		listeCases.addFirst(getNext());
	}
	
	public boolean calcul(LinkedList<Case> l){
		if(testerMort(l))
			setDead();
		else if(peutAvancer(l))
			return false;
		else if(getNext().estDedans())
			setDead();
		else
			return true;
		return false;
	}
	
	public void recommencer() {
		Case c = listeCases.getLast();
		listeCases = new LinkedList<Case>();
		listeCases.add(c);
		dead = false;
	}
	
	public void affichage(Graphics g) {
		Graphics2D g2d = (Graphics2D)g;
		g2d.setColor(couleur);
		for(Case c: listeCases){
			g2d.fillRect(c.getPositionX() + 1, c.getPositionY() + 1, c.getLongueur() - 1, c.getLargeur() - 1);
		}
		g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
	}
}
