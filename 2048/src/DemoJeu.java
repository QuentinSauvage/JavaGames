
/**
 * DemoJeu instancie un Objet de type Jeu et permet son lancement.
 * @author Quentin Sauvage
 *
 */
public class DemoJeu {

	/**
	 * @see Jeu
	 * @see Jeu#creerTuiles()
	 * @param args Les arguments entrés en ligne de commande.
	 */
	public static void main(String[] args) {
		Jeu j = new Jeu();
		j.creerTuiles();
	}
}
