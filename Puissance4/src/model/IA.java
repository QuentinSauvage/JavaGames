package model;

import java.util.ArrayList;
import java.util.Random;

import initialisation.Proprietes;

/**
 * Cette classe permet à l'ordinateur de jouer lui-même.
 * @author Quentin Sauvage
 *
 */
public class IA extends Puissance4 {
	
	/**
	 * Calcule l'endroit où l'IA doit jouer pour aligner X pions.
	 * @return Un entier supérieur à 0 si l'IA peut aligner le bon nombre de pions, 0 sinon.
	 */
	public int calculCoup() {
		for(int i = 0; i < Proprietes.nbColonnes; i++) {
			for(int j = this.getColonnes()[i]; j < Proprietes.nbLignes; j++) {
				if(plateau[i][j] == 0) {
					plateau[i][j] = tour;
					if(this.alignLignes() || this.alignColonnes() || (j > 0 && this.alignDiagonales() && plateau[i][j -1] != 0)) {
						plateau[i][j] = 0;
						return i;
					}
					plateau[i][j] = 0;
				}
			}
		}
		return 0;
	}
	
	/**
	 * Vérifie si poser un pion dans la colonne x fait perdre l'IA (mode suicide).
	 * @param x La colonne où l'IA veut jouer son pion.
	 * @return Vrai si l'IA perd en jouant à cet endroit, faux sinon.
	 */
	public boolean calculCoup(int x) {
		if(this.getColonnes()[x] == Proprietes.nbLignes)
			return false;
		this.getPlateau()[x][this.getColonnes()[x]] = tour;
		if(this.alignLignes() || this.alignColonnes() || this.alignDiagonales()) {
			this.getPlateau()[x][this.getColonnes()[x]] = 0;
			return false;
		}
		this.getPlateau()[x][this.getColonnes()[x]] = 0;
		return true;

	}
	
	/**
	 * Appelle les méthodes adaptées au mode de jeu choisi afin de déterminer où l'IA doit jouer.
	 * @return La colonne où l'IA a décidé de jouer.
	 * @see Proprietes
	 */
	public int jouer() {
		int x;
		x = calculCoup();
		if(!Proprietes.mode) {
			if(x > 0)
				return x;
			this.setTour(1);
			x = calculCoup();
			this.setTour(1);
			if(x > 0)
				return x;
			Proprietes.alignes--;
			x = calculCoup();
			Proprietes.alignes++;
			if(x > 0)
				return x;
			Random rd = new Random();
			while(true) {
				x = rd.nextInt(Proprietes.nbColonnes);
				if(this.getColonnes()[x] < Proprietes.nbLignes)
					return x;
			}
		} else {
			ArrayList<Integer> al = new ArrayList<Integer>();
			Random rd = new Random();
			while(true) {
				x = rd.nextInt(Proprietes.nbColonnes);
				if(calculCoup(x)) {
					return x;
				} else {
					if(!al.contains(x) && al.size() < Proprietes.nbColonnes -1)
						al.add(x);
					else if(!al.contains(x))
						return x;
				}
			}
		}
	}
}
