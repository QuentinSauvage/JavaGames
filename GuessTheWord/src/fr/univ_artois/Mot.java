package fr.univ_artois;

/**
 * Mot représente toutes les informations correspondant au stockage du mot à trouver.
 * @author quentin sauvage
 *
 */
public class Mot {
	
	/**
	 * Une chaîne de caractère représentant le mot à trouver.
	 * @see Mot#Mot(String)
	 * @see Mot#setMot(String)
	 * @see Mot#getMot()
	 * @see Mot#motADeviner()
	 * @see Mot#actualiser(char)
	 */
	private String mot;
	
	/**
	 * Un tableau de booléen permettant de savoir quelles lettres ont été révélées.
	 * @see Mot#Mot(String)
	 * @see Mot#motADeviner()
	 * @see Mot#actualiser(char)
	 */
	private boolean[] lettresRevelees;
	
	/**
	 * Le nombre de lettres qui n'ont pas encore été trouvées.
	 * @see Mot#Mot(String)
	 * @see Mot#getLettresRestantes()
	 * @see Mot#actualiser(char)
	 */
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
