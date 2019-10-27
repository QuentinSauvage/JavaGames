import java.util.Random;

/**
 * Cette classe fait apparaitre les bonus du jeu.
 * @author Quentin Sauvage
 */
public class ChronoBonus implements Runnable {
	/**
	 * Lorsque ce chrono tombe à 0, un bonus peut alors apparaître.
	 */
	private int chrono;
	
	/**
	 * A sa création, ChronoBonus lance un thread qui décrémente chrono et fait apparaître un bonus.
	 */
	public ChronoBonus() {
		chrono = 25;
		Thread tBonus = new Thread(this);
		tBonus.start();
	}
	
	/**
	 * Modifie la valeur du chrono.
	 * @param x La nouvelle valeur du chrono.
	 */
	public void setChrono(int x) {
		chrono = x;
	}
	
	/**
	 * Permet d'accéder à la valeur du chrono.
	 * @return la valeur de chrono.
	 */
	public int getChrono() {
		return chrono;
	}
	
	/**
	 * Décrémente le chrono et crée un nouveau bonus selon l'aléatoire créé.
	 * @see ChronoBonus#setChrono(int)
	 * @see ChronoBonus#getChrono()
	 * @see Jeu#getEnCours()
	 * @see Jeu#getNbBalles()
	 * @see Jeu#addBalle(int, int, double)
	 */
	@Override
	public void run() {
		int chronoBase = getChrono();
		// TODO Auto-generated method stub
		while(true) {
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			if(Fenetre.modele.getEnCours() && Fenetre.modele.getNbBalles() < 5) {
				if(chrono > 0)
					chrono--;
				else {
					Random rd = new Random();
					if(rd.nextInt(10) > 5) {
						int x = 50 + rd.nextInt((Main.fenetre.getWidth() - 50) - 50);
						int y = 20 + rd.nextInt((Main.fenetre.getHeight() - 20) - 20);
						int pos2 = rd.nextInt(1);
						double dy = rd.nextFloat();
						if(pos2 == 0)
							dy = dy * -1;
						Fenetre.modele.addBalle(x, y, dy);
						setChrono(chronoBase);
					}
				}
			}
		}
	}

}
