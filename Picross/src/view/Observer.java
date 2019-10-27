package view;

/**
 * Met à jour les observateurs.
 * @author Quentin Suavage
 *
 */
public interface Observer {
	
	/**
	 * Demande aux observateurs de se mettre à jour.
	 * @param x La colonne du nouveau pion.
	 * @param y La ligne du nouveau pion.
	 * @param tour Le tour actuel
	 * @param str La chaîne de caractères indiquant l'état du modèle aux observateurs.
	 */
	public void update(int x, int y, int typeClic, String str);
	/**
	 * Demande aux observateurs de se mettre à jour.
	 * @param str Le nom du fichier à enregistrer.
	 */
	public void update(String str);
}
