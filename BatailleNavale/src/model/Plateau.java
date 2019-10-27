package model;

import java.util.Random;

import view.Observer;

public class Plateau extends AbstractModel {

	public Plateau(boolean ia) {
		joueur1 = new Joueur();
		if(!ia)
			joueur2 = new Joueur();
		else
			joueur2 = new IA();
		genererBateaux(5, Bateau.PORTEAVIONS);
		genererBateaux(4, Bateau.CROISEUR);
		genererBateaux(3, Bateau.CONTRETORPILLEUR);
		genererBateaux(3, Bateau.SOUSMARIN);
		genererBateaux(2, Bateau.TORPILLEUR);
	}
	
	public void genererBateaux(int valeur, Bateau bateau) {
		genererPositions(valeur, bateau, joueur1.getGrille());
		genererPositions(valeur, bateau, joueur2.getGrille());
	}
	
	public void genererPositions(int valeur, Bateau bateau, CaseJeu[][] grille) {
		Random rd = new Random();
		int x, y, direction;
		boolean place = false;
		while(!place) {
			x = rd.nextInt(10);
			y = rd.nextInt(10);
			direction = rd.nextInt(4);
			for(int i = 0; i < 4; i++) {
				if(direction == 0)
					place = verifierHaut(valeur, bateau, x, y, grille);
				else if(direction == 1)
					place = verifierBas(valeur, bateau, x, y, grille);
				else if(direction == 2)
					place = verifierGauche(valeur, bateau, x, y, grille);
				else if(direction == 3)
					place = verifierDroite(valeur, bateau, x, y, grille);
				if(place)
					break;
				direction = (direction + 1) % 4;
			}
		}
	}
	
	public boolean verifierHaut(int valeur, Bateau bateau, int x, int y, CaseJeu[][] grille) {
		boolean ok = true;
		if (y < valeur - 1)
			return false;
		for(int i = 0; i < valeur; i++) {
			if(grille[x][y - i].getValue() == 1)
				ok = false;
		}
		if(ok) {
			for(int i = 0; i < valeur; i++) {
				grille[x][y - i].setValue(1);
				grille[x][y - i].setBateau(bateau);
			}
			return true;
		}
		return false;
	}
	
	public boolean verifierBas(int valeur, Bateau bateau, int x, int y, CaseJeu[][] grille) {
		boolean ok = true;
		if (y > 10 - valeur)
			return false;
		for(int i = 0; i < valeur; i++) {
			if(grille[x][y + i].getValue() == 1)
				ok = false;
		}
		if(ok) {
			for(int i = 0; i < valeur; i++) {
				grille[x][y + i].setValue(1);
				grille[x][y + i].setBateau(bateau);
			}
			return true;
		}
		return false;
	}
	
	public boolean verifierGauche(int valeur, Bateau bateau, int x, int y, CaseJeu[][] grille) {
		boolean ok = true;
		if (x < valeur - 1)
			return false;
		for(int i = 0; i < valeur; i++) {
			if(grille[x - i][y].getValue() == 1)
				ok = false;
		}
		if(ok) {
			for(int i = 0; i < valeur; i++) {
				grille[x - i][y].setValue(1);
				grille[x - i][y].setBateau(bateau);
			}
			return true;
		}
		return false;
	}

	public boolean verifierDroite(int valeur, Bateau bateau, int x, int y, CaseJeu[][] grille) {
		boolean ok = true;
		if (x > 10 - valeur)
			return false;
		for(int i = 0; i < valeur; i++) {
			if(grille[x + i][y].getValue() == 1)
				ok = false;
		}
		if(ok) {
			for(int i = 0; i < valeur; i++) {
				grille[x + i][y].setValue(1);
				grille[x + i][y].setBateau(bateau);
			}
			return true;
		}
		return false;
	}
	
	@Override
	public void notifyObserver(int x, int y, boolean gauche, String str) {
		for(Observer obs : listObserver)
			obs.update(x, y, gauche, str);
	}

}
