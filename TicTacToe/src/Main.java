import controler.*;
import model.*;
import vue.*;

public class Main {

	public static void main(String[] args) {
		AbstractModel model = new TicTacToe();
		AbstractControler controler = new TicTacToeControler(model);
		Vue vue = new Vue(controler);
		model.addObserver(vue);
	}

}
