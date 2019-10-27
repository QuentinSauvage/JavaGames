import java.awt.event.KeyEvent;

/**
 * L'IA joue à la place du joueur 2 quand celle-ci est activée.
 * @author quentin
 *
 */

public class IA implements Runnable {

	/**
	 * Le constructeur crée un nouveau thread qui fait bouger le joueur selon la direction calculée.
	 */
	public IA() {
		Thread tIA = new Thread(this);
		tIA.start();
	}
	
	/**
	 * Calcule la trajectoire de la balle et détermine si le joueur doit monter ou descendre.
	 * @see Jeu#getBalleProche()
	 * @see Jeu#simulerTrajet(Balle)
	 * @see Objet#getY()
	 * @see Objet#getHauteur()
	 * @return la direction devant être choisi par le joueur.
	 */
	public int calcul() {
		Balle b = Fenetre.modele.getBalleProche();
		if(b != null) {
			double y = Fenetre.modele.simulerTrajet(b);
			if(y > Fenetre.modele.joueur2.getY() + 5 && y + 10 < Fenetre.modele.joueur2.getY() + Fenetre.modele.joueur2.getHauteur() - 5) {
				if(Fenetre.modele.joueur1.getY() < Fenetre.modele.joueur1.getY())
					return 1;
				return -1;
			}
			else if(y > Fenetre.modele.joueur2.getY())
				return 1;
			return -1;
		}
		return 0;
	}

	/**
	 * Calcul la direction devant être choisie et fait bouger le joueur.
	 * @see IA#calcul()
	 * @see Jeu#bouger(int, int)
	 * @see Jeu#lancerManche(int)
	 */
	@Override
	public void run() {
		try {
			Thread.sleep(20);
		} catch (InterruptedException e1) {
			e1.printStackTrace();
		}
		while(true) {
			if(Fenetre.modele.getEnCours()) {
				Fenetre.modele.bouger(1, calcul());
			} else {
				try {
					Thread.sleep(500);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				Fenetre.modele.lancerManche(KeyEvent.VK_UP);
			}
			try {
				Thread.sleep(10);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
}
