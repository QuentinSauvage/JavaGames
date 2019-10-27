package jeu;

public class Chrono implements Runnable {

	@Override
	public void run() {
		// TODO Auto-generated method stub
		while(!jeu.Main.scene.finDePartie()) {
			jeu.Main.scene.repaint();
			try {
				Thread.sleep(3);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
}
