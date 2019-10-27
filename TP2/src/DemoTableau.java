
public class DemoTableau {

	public static void main(String[] args) {
		Tableau tab = new Tableau(15);
		tab.remplir();
		System.out.println("Max : " + tab.max());
		tab.affiche();
	}

}
