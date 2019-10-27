public class Pair {
	private int x;
	private int y;
	
	public Pair(int x, int y) {
		this.x = x;
		this.y = y;
	}
	
	public int getX() {
		return x;
	}
	
	public int getY() {
		return y;
	}
	
	@Override
	public boolean equals(Object obj) {
		Pair p = (Pair) obj;
		return (p.getX() == x && p.getY() == y) || (p.getX() == y && p.getY() == x);
	}
}