package model;

import java.util.ArrayList;

import observer.*;

public abstract class AbstractModel implements Observable {
	protected ArrayList<Observer> listObserver = new ArrayList<Observer>();
	
	public void addObserver(Observer obs) {
		this.listObserver.add(obs);
	}
	
	public abstract void notifyObserver(int x, int y);
	
	public void removeObserver() {
		listObserver = new ArrayList<Observer>();
	}
}
