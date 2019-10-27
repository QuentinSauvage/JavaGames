package controler;

import model.AbstractModel;

public abstract class AbstractControler {
	protected AbstractModel modele;
	
	public AbstractControler(AbstractModel m) {
		modele = m;
	}
	
	public void setJeu(int x, int y) {
		control(x, y);
	}
	
	abstract void control(int x, int y);
	
}
