
public class De {
	private int valeur;
	private boolean lance;
	
	public De(int valeur) {
		this.valeur = valeur;
		lance = false;
	}

	public int getValeur() {
		return valeur;
	}

	public void setValeur(int valeur) {
		this.valeur = valeur;
	}

	public boolean isLance() {
		return lance;
	}

	public void setLance(boolean lance) {
		this.lance = lance;
	}
}
