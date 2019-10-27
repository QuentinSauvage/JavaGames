import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class Fenetre extends JFrame {
	private static final long serialVersionUID = 1L;
	private Jeu modele;
	private JPanel contentJeu;
	private PanneauChoix panneauChoix;
	private Panneau plateau;
	private PanneauManches manches;
	private PanneauFin panneauFin;

	public Fenetre(Jeu m){
		modele = m;
		this.setTitle("Tron");
		this.setSize(new Dimension(700, 700));
		this.setResizable(false);
		panneauChoix = new PanneauChoix(modele);
		this.setFocusable(false);
		this.setContentPane(panneauChoix);
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.pack();
		this.setVisible(true);
	}
	
	public void initialiserDebut() {
		this.setContentPane(panneauChoix);
		this.setSize(this.getContentPane().getPreferredSize());
	}
	
	
	public void commencer() {
		plateau = new Panneau(modele);
		contentJeu = new JPanel();
		contentJeu.setLayout(new BorderLayout());
		plateau.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				Fenetre.this.modele.gestion(e);
			}
		});
		manches = new PanneauManches(modele);
		Thread thread = new Thread(new Runnable() {
			@Override
			public void run() {
				while (!modele.getEtat()) {
					Fenetre.this.modele.calcul();
					plateau.repaint();
					Fenetre.this.repaint();
					try {
						Thread.sleep(600);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					if(Fenetre.this.modele.getPersonnage().getDead() && Fenetre.this.modele.getPersonnage2().getDead()) {
						Thread.currentThread();
						Thread.interrupted();
					}
				}
			}
		});
		thread.start();
		contentJeu.add(plateau, BorderLayout.WEST);
		contentJeu.add(manches, BorderLayout.EAST);
		this.setContentPane(contentJeu);
		this.setSize(this.getContentPane().getPreferredSize());
		this.repaint();
		this.revalidate();
		plateau.requestFocusInWindow();
	}
	
	public void recommencer() {
		this.remove(plateau);
		panneauFin = new PanneauFin(modele);
		this.setContentPane(panneauFin);
		this.setSize(this.getContentPane().getPreferredSize());
		this.repaint();
		this.revalidate();
	}
	
	public void updateLabels() {
		manches.updateLabels();
	}
	
	public void updateLabels(boolean m1, boolean m2) {
		manches.updateLabels(modele, m1, m2);
	}
}
