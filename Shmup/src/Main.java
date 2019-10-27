import controler.AbstractControler;
import controler.GrilleControler;
import model.AbstractModel;
import model.Grille;
import view.GuiGrille;

public class Main {

	public static void main(String[] args) {
		 AbstractModel grille = new Grille();
		 AbstractControler controler = new GrilleControler(grille);
		 GuiGrille guiGrille = new GuiGrille(controler);
		 grille.addObserver(guiGrille);
	}

}
