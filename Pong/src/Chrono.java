/**
 * Chrono lance un thread qui dessine la fenêtre toutes les 3ms.
 * @author Quentin Sauvage
 *
 */
public class Chrono implements Runnable {

	/**
	 * Le constructeur de Carres lance un nouveau thread.
	 */
	public Chrono() {
		Thread tBonus = new Thread(this);
		tBonus.start();
	}
	
	/**
	 * Dessine la fenêtre  à chaque fois que 3ms sont passées.
	 * @see Jeu#isFinDePartie()
	 */
	@Override
	public void run() {
		// TODO Auto-generated method stub
		while(!Fenetre.modele.isFinDePartie()) {
			Fenetre.modele.repaint();
			try {
				Thread.sleep(3);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
}