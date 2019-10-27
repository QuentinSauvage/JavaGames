import java.util.LinkedList;

public class Voie {
	private int id;
	private int taille;
	private LinkedList<Integer> niveau;
	
	public Voie(int i, int t) {
		id = i;
		taille = t;
		niveau = new LinkedList<Integer>();
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getTaille() {
		return taille;
	}

	public void setTaille(int taille) {
		this.taille = taille;
	}

	public LinkedList<Integer> getNiveau() {
		return niveau;
	}

	public void setNiveau(LinkedList<Integer> niveau) {
		this.niveau = niveau;
	}
}
