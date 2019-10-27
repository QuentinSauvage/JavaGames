package fr.univ_artois;

/**
 * Joueur représente les infos relatives au joueur.
 * @author quentin sauvage
 *
 */
public class Joueur {
	
	/**
	 * Un entier représentant le nombre de vies du joueur.
	 * @see Joueur#Joueur(char)
	 * @see Joueur#getVies()
	 * @see Joueur#setVies(String)
	 * @see Joueur#decreaseVies()
	 */
	private int vies;
	
	/**
	 * Un entier représentant le nombre de victoires d'affilée du joueur.
	 * @see Joueur#Joueur(char)
	 * @see Joueur#getVictoires()
	 * @see Joueur#setVictoires(int)
	 * @see Joueur#increaseVictoires()
	 */
	private int serieVictoires;

	/**
	 * Constructeur vide de Joueur. Le joueur est construit avec la difficulte facile.
	 * @see Joueur#Joueur(int)
	 */
	public Joueur() {
		this(0);
	}

	/**
	 * Constructeur vide de Joueur.
	 * @param nbVictoires un entier correspondant au nombre de victoires consécutives du joueur.
	 * @see Joueur#vies
	 * @see Joueur#serieVictoires
	 */
	public Joueur(int nbVictoires) {
		serieVictoires = nbVictoires;
	}
	
	/**
	 * Constructeur de Joueur. serieVictoires est initialié à 0, vies est initialisé par setVies(s).
	 * @param s Une chaîne de caractère correspondant à la difficulté choisie.
	 * @see Joueur#vies
	 * @see Joueur#serieVictoires
	 * @see Joueur#setVies(String)
	 */
	public Joueur(String s) {
		setVies(s);
		serieVictoires = 0;
	}
	
	/**
	 * Retourne le nombre de vies restantes du joueur.
	 * @return Le nombre de vies du joueur.
	 * @see Joueur#vies
	 */
	public int getVies() {
		return vies;
	}
	
	/**
	 * Modifie le nombre de vies actuel du joueur à partir de la difficulté.
	 * @param s La difficulté choisie.
	 * @see Joueur#vies
	 */
	public void setVies(String s) {
		switch(s) {
		case "FACILE":
			vies = 9;
			break;
		case "MOYEN":
			vies =  7;
			break;
		default:
			vies =  5;
			break;
		};
	}

	/**
	 * Retourne le nombre de victoires consécutives du joueur.
	 * @return Le nombre de victoires d'affilée du joueur.
	 * @see Joueur#serieVictoires
	 */
	public int getVictoires(){
		return serieVictoires;
	}

	/**
	 * Met à jour la série de victoires du Joueur.
	 * @param v Le nouveau nombre de victoires consécutives du joueur.
	 * @see Joueur#serieVictoires
	 */
	public void setVictoires(int v) {
		serieVictoires = v;
	}

	/**
	 * Incrémente de 1 la série de victoires du joueur.
	 * @see Joueur#serieVictoires
	 */
	public void increaseVictoires() {
		serieVictoires++;
	}
	
	/**
	 * Enlève une vie au joueur.
	 * @see Joueur#vies
	 */
	public void decreaseVies() {
		vies--;
	}
}
