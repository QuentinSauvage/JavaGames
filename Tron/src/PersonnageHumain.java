import java.awt.Color;

public class PersonnageHumain  extends Personnage {
	public PersonnageHumain(int x, int y, Color c) {
		super(x, y, c);
	}
	
	public void tourner(Direction d) {
		Direction tmp = direction;
		direction = d;
		if(listeCases.contains(getNext()))
			direction = tmp;
	}
}
