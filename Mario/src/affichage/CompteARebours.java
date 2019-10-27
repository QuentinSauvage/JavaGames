package affichage;

public class CompteARebours implements Runnable {

	private int compteurTemps;
	private String str;
	
	public CompteARebours() {
		compteurTemps = 100;
		str = "Temps restant : 100";
		Thread compteARebours = new Thread(this);
		compteARebours.start();
	}
	
	@Override
	public void run() {
		while(compteurTemps > 0) {
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			compteurTemps--;
			str = "Temps restant : " + compteurTemps;
		}
	}

	public int getCompteurTemps() {
		return compteurTemps;
	}

	public void setCompteurTemps(int compteurTemps) {
		this.compteurTemps = compteurTemps;
	}

	public String getStr() {
		return str;
	}

	public void setStr(String str) {
		this.str = str;
	}

}
