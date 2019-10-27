public class Ligne {
	private Point p1;
	private Point p2;
	
	Ligne(){
		p1 = new Point();
		p2 = new Point();
	}
	
	Ligne(Point p1, Point p2){
		this.p1 = new Point(p1.getAbscisse(), p2.getOrdonnee());
		this.p2 = new Point(p2.getAbscisse(), p2.getOrdonnee());
	}
	
	Point getP1(){
		return p1;
	}
	
	Point getP2(){
		return p2;
	}
	
	public void setP1(int u, int v){
		p1.set(u, v);
	}
	
	public void setP2(int u, int v){
		p2.set(u,v);
	}
	
	public double distance(){
		return Math.sqrt(Math.pow(p2.getAbscisse() - p1.getAbscisse(), 2) + Math.pow(p2.getOrdonnee() - p2.getOrdonnee(), 2));
	}
}
