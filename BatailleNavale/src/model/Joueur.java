package model;

/**
 * Définit l'état et le comportement d'un joueur.
 * @author Quentin Sauvage
 *
 */
public class Joueur {
	/**
	 * Représente la grille du joueur.
	 */
	protected CaseJeu[][] grille;
	/**
	 * Liste les bateaux du joueur et leur nombre de "vies".
	 */
	private int[] bateaux;
	
	/**
	 * Constructeur vide de Joueur.
	 */
	public Joueur() {
		grille = new CaseJeu[10][10];
		for(int i = 0; i < 10; i++)
			for(int j = 0; j < 10; j++)
				grille[i][j] = new CaseJeu();
		bateaux = new int[5];
		bateaux[0] = 5;
		bateaux[1] = 4;
		bateaux[2] = 3;
		bateaux[3] = 3;
		bateaux[4] = 2;
	}
	
	/**
	 * Getter de grille.
	 * @return La grille du joueur.
	 */
	public CaseJeu[][] getGrille() {
		return grille;
	}
	
	/**
	 * Setter de grille.
	 * @param grille La nouvelle grille du joueur.
	 */
	public void setGrille(CaseJeu[][] grille) {
		this.grille = grille;
	}

	/**
	 * Getter de bateaux.
	 * @return La liste des bateaux du joueur.
	 */
	public int[] getBateaux() {
		return bateaux;
	}
	
	/**
	 * Setter de bateaux.
	 * @param bateaux La nouvelle liste de bateaux du joueur.
	 */
	public void setBateaux(int[] bateaux)  {
		this.bateaux = bateaux;
	}
	
	/**
	 * Calcule le nombre de points de vie restant du joueur.
	 * @return La somme des points de vie des bateaux du joueur.
	 */
	public int getSomme() {
		int somme = 0;
		for(int i = 0; i < 5; i++) {
			somme += bateaux[i];
		}
		return somme;
	}
}
