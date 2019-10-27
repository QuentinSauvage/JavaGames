package model;

import observer.*;

public class TicTacToe extends AbstractModel {
	private int tour;
	private int[][] plateau;
	private boolean jeuFini;
	
	public TicTacToe() {
		plateau = new int[3][3];
		for(int i = 0; i < 3; i++) {
			for(int j = 0; j < 3; j++) {
				plateau[i][j] = -1;
			}
		}
		tour = 0;
		jeuFini = false;
	}
	
	public boolean isJeuFini() {
		return jeuFini;
	}

	public void setJeuFini(boolean jeuFini) {
		this.jeuFini = jeuFini;
	}
	
	public int getTour() {
		return tour;
	}
	
	public void setTour(int t) {
		tour = (tour + t) % 2;
	}

	public int[][] getPlateau() {
		return plateau;
	}
	
	public boolean gagner() {
		for(int i = 0; i < 3; i++) {
			if(plateau[i][0] != -1 && plateau[i][0] == plateau[i][1] && plateau[i][0] == plateau[i][2]) { //lignes et colonnes
				return true;
			}
		}
		return ((plateau[0][0] != -1 && plateau[0][0] == plateau[1][1] && plateau[0][0] == plateau[2][2]) || (plateau[1][1] != -1 && plateau[2][0] == plateau[1][1] && plateau[2][0] == plateau[0][2]));
	}
	
	
	public void notifyObserver(int x, int y) {
		plateau[x][y] = tour;
		if(gagner()) {
			jeuFini = true;
		}
		for(Observer obs : listObserver)
			obs.update(x, y, tour);
		setTour(1);
	}
}
