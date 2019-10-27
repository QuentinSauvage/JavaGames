package jeu;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Clavier implements KeyListener {
	
	@Override
	public void keyPressed(KeyEvent e) {
		if(jeu.Main.scene.mario.isVivant() && !jeu.Main.scene.finDePartie()) {
			if(e.getKeyCode() == KeyEvent.VK_RIGHT) {
				if(jeu.Main.scene.getxPos() == -1) {
					jeu.Main.scene.setxPos(0);
					jeu.Main.scene.setxFond1(-50);
					jeu.Main.scene.setxFond2(750);
				}
				jeu.Main.scene.mario.setMarche(true);
				jeu.Main.scene.mario.setVersDroite(true);
				jeu.Main.scene.mario.setDxMario(1);
			} else if(e.getKeyCode() == KeyEvent.VK_LEFT) {
				if(jeu.Main.scene.getxPos() == 4431) {
					jeu.Main.scene.setxPos(4430);
					jeu.Main.scene.setxFond1(-50);
					jeu.Main.scene.setxFond2(750);
				}
				jeu.Main.scene.mario.setMarche(true);
				jeu.Main.scene.mario.setVersDroite(false);
				jeu.Main.scene.mario.setDxMario(-1);
			} if(e.getKeyCode() == KeyEvent.VK_SPACE) {
				jeu.Main.scene.mario.setSaut(true);
			}
		}
		jeu.Main.scene.setDx(jeu.Main.scene.mario.getDxMario());
	}

	@Override
	public void keyReleased(KeyEvent e) {
		if(e.getKeyCode() == KeyEvent.VK_RIGHT || e.getKeyCode() == KeyEvent.VK_LEFT) {
			jeu.Main.scene.mario.setMarche(false);
			jeu.Main.scene.mario.setDxMario(0);
			jeu.Main.scene.setDx(0);
		}
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
	}

}
