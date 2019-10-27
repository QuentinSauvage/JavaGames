
public class Complexe {
	private int reelle;
	private int imaginaire;
	
	Complexe(){
		reelle = 0;
		imaginaire = 0;
	}
	
	Complexe(int r, int i){
		imaginaire = i;
		reelle = r;
	}
	
	int getReelle(){
		return reelle;
	}
	
	int getImaginaire(){
		return imaginaire;
	}
	
	void setReelle(int r){
		reelle = r;
	}
	
	void setImaginaire(int i){
		imaginaire = i;
	}

	public String affiche() {
		return "Complexe [reelle=" + reelle + ", imaginaire=" + imaginaire
				+ "]";
	}
	
	public double module(){
		return Math.sqrt(Math.pow(reelle,2));
	}
	
	public Complexe somme(Complexe c){
		return new Complexe(reelle + c.getReelle(), imaginaire + c.getImaginaire());
	}
	
	public Complexe multiplication(Complexe c){
		return new Complexe(reelle*c.getReelle() - imaginaire*c.getImaginaire(), reelle*c.getImaginaire() - imaginaire*c.getReelle());
	}
}