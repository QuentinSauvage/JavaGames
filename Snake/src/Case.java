
public class Case implements Constantes{
	private int x;
	private int y;
	
	public Case(){
		
	}
	
	public Case(int x, int y) {
		this.x = x;
		this.y = y;
	}
	
	public void setX(int x){
		this.x = x;
	}
	
	public void setY(int y){
		this.y = y;
	}
	
	public int getX(){
		return x;
	}
	
	public int getY(){
		return y;
	}
	
	public int getLargeur(){
		return nbPixels;
	}
	
	public int getLongueur(){
		return nbPixels;
	}
	
	public int getPositionX(){
		return getLongueur() * x;
	}
	
	public int getPositionY(){
		return getLargeur() * y;
	}
	
	public String toString() {
		return "X : " + x + " Y : " + y;
	}
	
	public boolean estDedans() {
		if(x < 0 || x > nbColonnes || y < 0 || y > nbLignes)
			return false;
		return true;
	}

	@Override
	public boolean equals(Object arg0) {
		Case c = (Case) arg0;
		return c.getX() == this.getX() && c.getY() == this.getY();
	}
	

}
