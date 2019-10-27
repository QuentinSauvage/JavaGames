package fr.univ_artois;

/**
 * Joueur represente les infos relatives au joueur.
 * @author quentin sauvage
 *
 */
public class Joueur {
	
	/**
	 * Un entier representant le nombre de vies du joueur.
	 * @see Joueur#Joueur(char)
	 * @see Joueur#getVies()
	 * @see Joueur#setVies(int)
	 * @see Joueur#decreaseVies()
	 */
	private int vies;
	
	/**
	 * Un entier representant le nombre de victoires d'affilee du joueur.
	 * @see Joueur#Joueur(char)
	 * @see Joueur#getVictoires()
	 * @see Joueur#setVictoires(int)
	 * @see Joueur#increaseVictoires()
	 */
	private int serieVictoires;
	
	/**
	 * Constructeur vide de Joueur. Le joueur est construit avec la difficulte facile.
	 */
	public Joueur() {
		this('f');
	}
	
	/**
	 * Constructeur de Joueur. serieVictoires est initialise a 0, vies est initialise par initVies(char).
	 * @param c Un caractere correspondant a la difficulte choisie.
	 * @see Joueur#vies
	 * @see Joueur#serieVictoires
	 * @see Joueur#initVies(char)
	 */
	public Joueur(char c) {
		vies = initVies(c);
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
	 * Modifie le nombre de vies actuel du joueur.
	 * @param nbVies Le nouveau nombre de vies du joueur.
	 * @see Joueur#vies
	 */
	public void setVies(int nbVies) {
		vies = nbVies;
	}
	
	/**
	 * Retourne le nombre de victoires consecutives du joueur.
	 * @return Le nombre de victoires d'affilee du joueur.
	 * @see Joueur#serieVictoires
	 */
	public int getVictoires(){
		return serieVictoires;
	}
	
	/**
	 * Met a jour la serie de victoires du Joueur.
	 * @param v Le nouveau nombre de victoires consecutives du joueur.
	 * @see Joueur#serieVictoires
	 */
	public void setVictoires(int v) {
		serieVictoires = v;
	}
	
	/**
	 * Augmente de 1 la serie de victoires du joueur.
	 * @see Joueur#serieVictoires
	 */
	public void increaseVictoires() {
		serieVictoires++;
	}
	
	/**
	 * Enleve une vie au joueur.
	 * @see Joueur#vies
	 */
	public void decreaseVies() {
		vies--;
	}
	
	/**
	 * Affiche l'etat du pendu dans la console.
	 * @see Joueur#vies
	 */
	public void afficherPendu(){
		switch(vies){
			case 1:
				System.out.println(" ╔╦═══╗");
				System.out.println(" ╠╝   ║ ");
				System.out.println(" ║    ☺");
				System.out.println(" ║    O/");
				System.out.println(" ║   / \\");
				System.out.println("═╩═══════");
				break;
			case 2:
				System.out.println(" ╔╦═══╗");
				System.out.println(" ╠╝   ║ ");
				System.out.println(" ║    ☺");
				System.out.println(" ║    O");
				System.out.println(" ║   / \\");
				System.out.println("═╩═══════");
				break;
			case 3:
				System.out.println(" ╔╦═══╗");
				System.out.println(" ╠╝   ║ ");
				System.out.println(" ║    ☺");
				System.out.println(" ║    O");
				System.out.println(" ║   /");
				System.out.println("═╩═══════");
				break;
			case 4:
				System.out.println(" ╔╦═══╗");
				System.out.println(" ╠╝   ║ ");
				System.out.println(" ║    ☺");
				System.out.println(" ║    O");
				System.out.println(" ║");
				System.out.println("═╩═══════");
				break;
			case 5:
				System.out.println(" ╔╦═══╗");
				System.out.println(" ╠╝   ║ ");
				System.out.println(" ║    ☺");
				System.out.println(" ║");
				System.out.println(" ║");
				System.out.println("═╩═══════");
				break;
			case 6:
				System.out.println(" ╔╦═══╗");
				System.out.println(" ╠╝   ║ ");
				System.out.println(" ║");
				System.out.println(" ║");
				System.out.println(" ║");
				System.out.println("═╩═══════");
				break;
			case 7:
				System.out.println(" ╔╦═══");
				System.out.println(" ╠╝");
				System.out.println(" ║");
				System.out.println(" ║");
				System.out.println(" ║");
				System.out.println("═╩═══════");
				break;
			case 8:
				System.out.println(" ╔════");
				System.out.println(" ║");
				System.out.println(" ║");
				System.out.println(" ║");
				System.out.println(" ║");
				System.out.println("═╩═══════");
				break;
			case 9:
				System.out.println("");
				System.out.println("");
				System.out.println("");
				System.out.println("");
				System.out.println("");
				System.out.println("═╩═══════");
				break;
			default:
				System.out.println(" ╔╦═══╗");
				System.out.println(" ╠╝   ║ ");
				System.out.println(" ║    ☺");
				System.out.println(" ║   \\O/");
				System.out.println(" ║   / \\");
				System.out.println("═╩═══════");
				break;
		}
	}
	
	/**
	 * Attribut au joueur un nombre de vies selon la difficulte choisie.
	 * @param c La difficulte choisie
	 * @return Le nombre de vies donnees au joueur.
	 */
	public int initVies(char c) {
		switch(c) {
			case 'm':
				System.out.println("Moyen.\n");
				return 7;
			case 'd':
				System.out.println("Difficile.\n");
				return 5;
			default:
				System.out.println("Facile.\n");
				return 9;
		}
	}
}
