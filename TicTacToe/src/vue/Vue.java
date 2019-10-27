package vue;

import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JFrame;
import javax.swing.JPanel;

import controler.AbstractControler;
import observer.Observer;


@SuppressWarnings("serial")
public class Vue extends JFrame implements Observer {
	private JPanel container = new JPanel();
	private AbstractControler controler;
	private Case[][] cases;
	
	public Vue(AbstractControler c) {
		this.setTitle("Tic Tac Toe");
		this.setSize(new Dimension(300, 300));
		this.setVisible(true);
		this.setResizable(false);
		controler = c;
		container = new JPanel();
		container.setLayout(new GridLayout(3, 3));
		cases = new Case[3][3];
		for(int i = 0; i < 3; i++) {
			for(int j = 0; j < 3; j++) {
				cases[i][j] = new Case();
				container.add(cases[i][j]);
			}
		}
		container.addMouseListener(new JeuListener());
		this.setContentPane(container);
	}
	
	class JeuListener implements MouseListener {
		@Override
		public void mouseClicked(MouseEvent me) {
			controler.setJeu(me.getX() / 100, me.getY() / 100);
		}

		@Override
		public void mouseEntered(MouseEvent arg0) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mouseExited(MouseEvent arg0) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mousePressed(MouseEvent arg0) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mouseReleased(MouseEvent arg0) {
			// TODO Auto-generated method stub
			
		}
	}
	
	@Override
	public void update(int x, int y, int tour) {
		cases[y][x].setValeur(tour);
		container.repaint();
	}
}
