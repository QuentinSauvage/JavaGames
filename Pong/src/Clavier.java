import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

/**
 * Gère les entrées clavier réalisées par les joueurs, et les fait bouger en conséquence.
 * @author Quentin Sauvage
 *
 */
public class Clavier implements KeyListener, Runnable {
	/**
	 * La direction du joueur1.
	 */
	private int y1;
	
	/**
	 * La direction du joueur2.
	 */
	private int y2;
	
	/**
	 * Crée un nouveau thread qui informera le jeu de la nouvelle direction des joueurs.
	 */
	public Clavier() {
		y1 = 0;
		y2 = 0;
		Thread tClavier = new Thread(this);
		tClavier.start();
	}
	
	/**
	 * Modifie la direction des joueurs selon la touche appuyée, et débute la manche si celle-ci n'avait pas encore commencé.
	 * @see Jeu#getEnCours()
	 * @see Jeu#lancerManche(int)
	 * @see Jeu#getIA()
	 */
	@Override
	public void keyPressed(KeyEvent e) {
		if(!Fenetre.modele.getEnCours())
			Fenetre.modele.lancerManche(e.getKeyCode());
		if(Fenetre.modele.getEnCours()) {
			if(!Fenetre.modele.getIA()) {
				if(e.getKeyCode() == KeyEvent.VK_UP) {
					y2 = -1;
				}
				if(e.getKeyCode() == KeyEvent.VK_DOWN) {
					y2 = 1;
				}
			}
			if(e.getKeyCode() == KeyEvent.VK_Z) {
				y1 = -1;
			}
			if(e.getKeyCode() == KeyEvent.VK_S) {
				y1 = 1;
			}
		}
	}
	
	/**
	 * Met à zéro la direction de chaque joueur lorsque la touche leur correspondant est relâchée.
	 * @see Jeu#getIA()
	 */
	@Override
	public void keyReleased(KeyEvent e) {
		if(!Fenetre.modele.getIA()) {
			if(e.getKeyCode() == KeyEvent.VK_DOWN || e.getKeyCode() == KeyEvent.VK_UP) {
				y2 = 0;
			}
		}
		if(e.getKeyCode() == KeyEvent.VK_S || e.getKeyCode() == KeyEvent.VK_Z) {
			y1 = 0;
		}
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
	}

	/**
	 * Demande au jeu de faire bouger les joueurs selon leur nouvelle direction.
	 * @see Jeu#getIA()
	 * @see Jeu#bouger(int, int)
	 */
	@Override
	public void run() {
		try {
			Thread.sleep(20);
		} catch (InterruptedException e1) {
			e1.printStackTrace();
		}
		while(true) {
			Fenetre.modele.bouger(0, y1);
			if(!Fenetre.modele.getIA()) {
				Fenetre.modele.bouger(1, y2);
			}
			try {
				Thread.sleep(10);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

}
