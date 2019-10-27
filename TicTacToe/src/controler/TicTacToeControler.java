package controler;

import model.AbstractModel;
import model.TicTacToe;

public class TicTacToeControler extends AbstractControler {
	
	public TicTacToeControler(AbstractModel m) {
		super(m);
	}

	@Override
	void control(int x, int y) {
		if(modele instanceof TicTacToe) {
			TicTacToe m = (TicTacToe) modele;
			if(!m.isJeuFini()) {
				if(m.getPlateau()[x][y] == -1)
					modele.notifyObserver(x,  y);
			}
		}
	}
}
