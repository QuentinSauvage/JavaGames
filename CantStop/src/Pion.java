
public class Pion {
	private Voie voie;
	private int position;
	
	public Pion() {
		position = 0;
		voie = new Voie(-1, 1);
	}

	public Voie getVoie() {
		return voie;
	}

	public void setVoie(Voie voie) {
		this.voie = voie;
	}

	public int getPosition() {
		return position;
	}

	public void setPosition(int position) {
		this.position = position;
	}
}
