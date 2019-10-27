import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.JFrame;

public class Fenetre extends JFrame{
	private static final long serialVersionUID = 1L;
	private Jeu modele;

	public Fenetre(Jeu m){
		modele = m;
		this.setTitle("Jeu");
		this.setSize(new Dimension(700, 700));
		this.setResizable(false);
		Panneau content = new Panneau(modele);
		content.setBackground(Color.BLACK);
		content.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				Fenetre.this.modele.gestion(e);
			}
		});
		setFocusable(false);
		content.setFocusable(true);
		this.setContentPane(content);
		
		Thread thread = new Thread(new Runnable() {
			@Override
			public void run() {
				while (true) {
					Fenetre.this.modele.calcul();
					content.repaint();
					Fenetre.this.repaint();
					try {
						int lvl = Fenetre.this.modele.getNiveau();
						if(lvl > 9)
							lvl = 9;
						Thread.sleep(Fenetre.this.modele.getVitesse());
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					if(Fenetre.this.modele.getPersonnage().getDead())
						Thread.currentThread().interrupted();
				}
			}
		});
		thread.start();
		
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.pack();
		this.setVisible(true);
	}
}
