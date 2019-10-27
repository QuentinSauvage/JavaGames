package fr.univ_artois;

public class Mot {
	private String mot;
	private boolean[] lettresRevelees;
	private int lettresRestantes;
	
	public Mot(){
		this("");
	}
	
	public Mot(String m) {
		mot = m;
		lettresRevelees = new boolean[m.length()];
		lettresRestantes = m.length();
	}
	
	public int getLettresRestantes() {
		return lettresRestantes;
	}
	
	public String getMot(){
		return mot;
	}
	
	public String motADeviner() {
		String s = "";
		for(int i = 0; i < mot.length(); i++) {
			if(lettresRevelees[i])
				s += mot.charAt(i);
			else
				s += "*";
		}
		return s;
	}
	
	public boolean actualiser(char c) {
		boolean present = false;
		for(int i = 0; i < mot.length(); i++) {
			if(mot.charAt(i) == c) {
				present = true;
				lettresRevelees[i] = true;
				lettresRestantes--;
			}
		}
		return present;
	}
}
