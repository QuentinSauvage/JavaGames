import java.util.ArrayList;

public class Point implements Comparable{
	
	private int abscisse;
	private int ordonnee;
	public static int nombre = 0;
	private int numero;
	
	Point(){
		abscisse = 0;
		ordonnee = 0;
		numero = nombre;
		nombre++;
	}
	
	Point(int u, int v){
		abscisse = u;
		ordonnee = v;
		numero = nombre;
		nombre++;
	}
	
	int getAbscisse(){
		return abscisse;
	}
	
	int getOrdonnee(){
		return ordonnee;
	}
	
	void set(int u, int v){
		abscisse = u;
		ordonnee = v;
	}
	
	void set(Point q){
		abscisse = q.abscisse;
		ordonnee = q.ordonnee;
	}
	
	void translate(int u, int v){
		abscisse = abscisse + u;
		ordonnee = ordonnee + v;
	}
	
	void affiche(){
		System.out.println("Abscisse : " + abscisse + ", Ordonnée : " + ordonnee + ", Nombre : " + nombre + ", Numéro : " + numero);
	}
	
	boolean origine(){
		return abscisse == 0 && ordonnee == 0;
	}
	
	boolean egale(Point q){
		return abscisse == q.abscisse && ordonnee == q.ordonnee;
	}
	
	Point symetrie(){
		return new Point(-abscisse, -ordonnee);
	}
	

	@Override
	public int compareTo(Object o) {
		Point p = (Point) o;
		if((abscisse < p.getAbscisse()) || (abscisse == p.getAbscisse() && ordonnee < p.getOrdonnee()))
			return -1;
		else if(this.egale(p))
			return 0;
		else
			return 1;
	}
}
