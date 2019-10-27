package fr.univ_artois;

/**
 * Mot represente toutes les informations correspondant au stockage du mot a trouver.
 * @author quentin sauvage
 *
 */
public class Mot {
	
	/**
	 * Une chaîne de caractere representant le mot a trouver.
	 * @see Mot#Mot(String)
	 * @see Mot#setMot(String)
	 * @see Mot#getMot()
	 */
	private String mot;
	
	/**
	 * Un tableau de booleen permettant de savoir quelles lettres ont ete revelees.
	 * @see Mot#Mot(String)
	 */
	private boolean[] lettresRevelees;
	
	/**
	 * Le nombre de lettres qui n'ont pas encore ete trouvees.
	 * @see Mot#Mot(String)
	 * @see Mot#getLettresRestantes()
	 */
	private int lettresRestantes;
	
	/**
	 * Constructeur vide de Mot. Une chaine de caractere vide est passee en parametre de l'appel a l'autre constructeur.
	 */
	public Mot(){
		this("");
	}
	
	/**
	 * Constructeur de Mot. lettresRevelees et lettresRestantes sont initialises selon la taille de la chaine de caracteres en parametres.
	 * @param m La chaine de caracteres correspondant au mot a trouver.
	 * @see Mot#mot
	 * @see Mot#lettresRestantes
	 * @see Mot#lettresRevelees
	 */
	public Mot(String m) {
		mot = m;
		lettresRevelees = new boolean[m.length()];
		lettresRestantes = m.length();
	}
	
	/**
	 * Retourne le mot a trouver.
	 * @return un String mot, correspondant au mot a trouver.
	 * @see Mot#mot
	 */
	public String getMot() {
		return mot;
	}
	
	/**
	 * Remplace le mot actuel par un autre mot de type String.
	 * @param s le nouveau mot a trouver.
	 * @see Mot#mot
	 * @see Mot#lettresRevelees
	 */
	public void setMot(String s) {
		mot = s;
		lettresRevelees = new boolean[s.length()];
	}
	
	/**
	 * Retourne le nombre de lettres qui n'ont pas encore ete trouvees.
	 * @return le nombre de lettres a trouver.
	 * @see Mot#lettresRestantes
	 */
	public int getLettresRestantes() {
		return lettresRestantes;
	}
	
	/**
	 * Code le mot a deviner.
	 * @return le mot code selon les lettres deja trouvees.
	 * @see Mot#mot
	 * @see Mot#lettresRevelees
	 */
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
	
	/**
	 * Met à jour la classe selon le caractere entre.
	 * @param c le caractere entre par l'utilisateur.
	 * @return un booleen indiquant si la lettre est presente ou non dans le mot.
	 */
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
