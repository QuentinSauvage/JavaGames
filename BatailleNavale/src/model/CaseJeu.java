package model;

/**
 * Définit les informations d'une case du jeu.
 * @author Quentin Sauvage
 *
 */
public class CaseJeu {
	/**
	 * Le type de bateau présent sur cette case.
	 */
	private Bateau bateau;
	/**
	 * Indique si la case est vide, occupée par un bateau, ou a déjà été cliquée.
	 */
	private int value;
	
	/**
	 * Constructeur vide de CaseJeu.
	 */
	public CaseJeu() {
		bateau = Bateau.VIDE;
		value = 0;
	}
	
	/**
	 * Getter de bateau.
	 * @return Le type de la case.
	 */
	public Bateau getBateau() {
		return bateau;
	}
	
	/**
	 * Setter de bateau.
	 * @param bateau Le nouveau type de la case.
	 */
	public void setBateau(Bateau bateau) {
		this.bateau = bateau;
	}
	
	/**
	 * Getter de value.
	 * @return La valeur de la case.
	 */
	public int getValue() {
		return value;
	}
	
	/**
	 * Setter de value.
	 * @param value La nouvelle valeur de la case.
	 */
	public void setValue(int value) {
		this.value = value;
	}
	
}
