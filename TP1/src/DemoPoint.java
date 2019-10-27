import java.util.ArrayList;

public class DemoPoint {
	public static void main(String[] args) {
		Point p = new Point(2,8);
		p.affiche();
		Point q = new Point(3,5);
		q.affiche();
		System.out.println("P origine ? " + p.origine());
		System.out.println("P = Q ? " + p.egale(q));
		q.translate(-1, 3);
		System.out.println("P = Q ? " + p.egale(q));
		q = p.symetrie();
		q.affiche();
		p.set(0, 0);
		p.affiche();
		System.out.println("P origine ? " + p.origine());
		ArrayList<Point> l = new ArrayList<Point>();
		for(int i = 0; i < 100; i++){
			l.add(new Point(-10 + (int)(Math.random() * 21),-10 + (int)(Math.random() * 21)));
		}
		java.util.Collections.sort(l);
		for(int i = 0; i < l.size(); i++){
			l.get(i).affiche();
		}
	}
}
