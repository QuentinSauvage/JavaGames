import java.awt.Color;
import java.util.LinkedList;
import java.util.Random;

public class PersonnageIA extends Personnage {
	public PersonnageIA(int x, int y, Color c) {
		super(x, y, c);
	}
	
	public void tourner(LinkedList<Case> l) {
		Random rd = new Random();
		int d;
		int rdTourner = rd.nextInt(2);
		Direction tmp = direction;
		if(testerMort(l))
			setDead();
		else if(rdTourner == 1 || !peutAvancer(l) || l.contains(getNext())) {
			do {
				direction = tmp;
				d = rd.nextInt(4);
				if((Direction.values()[d] != tmp) && (Direction.values()[(d + 2) % 4] != tmp))
					direction = Direction.values()[d];
			} while(!peutAvancer(l));
		}
		avancer();
	}
	
}
