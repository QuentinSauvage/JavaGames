import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.Random;

public class Piece extends Case {

	private int x;
	private int y;
	private final static Random RND = new Random();
	public Piece() {
		generer();
	}
	
	public void generer() {
		setX();
		setY();
	}
	
	public void setX() {
		super.setX(RND.nextInt(nbColonnes));
	}
	
	public void setY() {
		super.setY(RND.nextInt(nbColonnes));
	}
	
	public void afficher() {
		System.out.println(x + " " + y);
	}
	
	public void affichage(Graphics g) {
		Graphics2D g2d = (Graphics2D)g;
		g2d.setColor(Color.YELLOW);
		g2d.fillOval(this.getPositionX(), this.getPositionY(), this.getLongueur(), this.getLargeur());
	}
}
