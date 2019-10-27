package fr.univ_artois;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

/**
 * Jeu contient tous les éléments nécessaires au déroulement du jeu.
 * @author quentin sauvage
 *
 */
public class Jeu {
	/**
	 * fenetre représente la fenêtre du jeu.
	 * @see Fenetre
	 * @see Jeu#Jeu()
	 * @see Jeu#verifierLettre(String)
	 * @see Jeu#creerJeu(String)
	 * @see Jeu#demanderRecommencer(boolean)
	 * @see Jeu#choixRecommencer(String)
	 */
	Fenetre fenetre;
	
	/**
	 * mot représente le mot que le joueur devra deviner.
	 * @see Mot
	 * @see Jeu#Jeu()
	 * @see Jeu#getMot()
	 */
	private Mot mot;
	
	/**
	 * joueur représente l'utilisateur.
	 * @see Joueur
	 * @see Jeu#Jeu()
	 * @see Jeu#getJoueur()
	 * @see Jeu#creerJeu(String)
	 */
	private Joueur joueur;
	
	/**
	 * fr est un lecteur de fichier utilisé pour récupérer les mots du pendu.
	 * @see Jeu#genererMot()
	 * @see Jeu#choixRecommencer(String)
	 */
	private FileReader fr;
	
	/**
	 * br permet de lire les mots présents dans le fichier.
	 * @see Jeu#genererMot()
	 * @see Jeu#choixRecommencer(String)
	 */
	private BufferedReader br;
	
	/**
	 * Constructeur de Jeu.
	 * @see Jeu#fenetre
	 * @see Jeu#joueur
	 * @see Jeu#mot
	 * @see Jeu#fr
	 * @see Jeu#br
	 */
	public Jeu(){
		fenetre = new Fenetre(this);
		joueur = new Joueur();
		mot = new Mot(genererMot());
	}
	
	/**
	 * Permet d'accéder au mot à deviner.
	 * @return le mot de type Mot devant etre deviné par le joueur.
	 * @see Mot
	 * @see Jeu#mot
	 */
	public Mot getMot(){
		return mot;
	}
	
	/**
	 * Permet d'accéder au joueur.
	 * @return le joueur représentant l'utilisateur.
	 * @see Joueur
	 * @see Jeu#joueur
	 */
	public Joueur getJoueur(){
		return joueur;
	}
	
	/**
	 * Génére un mot au hasard, à partir de ceux stockés dans le fichier txt.
	 * @return une chaîne de caractères correspondant au mot caché.
	 * @see Jeu#br
	 * @see Jeu#fr
	 */
	public String genererMot(){
		String line = "";
		int nbLignes = 0;
		File f = new File("mots.txt");
		int i = 0;
		try{
			fr = new FileReader(f);
			br = new BufferedReader(fr);
			while(br.readLine() != null)
				nbLignes++;
			i = (int)(Math.random()*(nbLignes));
			br.close();
			br = new BufferedReader(new FileReader(f));
			while(i >= 0){
				i--;
				line = br.readLine();
			}
			br.close();
			fr.close();
		} catch(Exception e){
			e.printStackTrace();
		}
		return line.toUpperCase();
	}
	
	/**
	 * Vérifie si la lettre cliquée est présente dans le mot à deviner.
	 * Actualise les éléments du jeu en fonction de cette réponse.
	 * @param s Le caractère cliqué par le joueur.
	 * @see Jeu#joueur
	 * @see Jeu#mot
	 * @see Jeu#fenetre
	 * @see Mot#actualiser(char)
	 * @see Mot#getLettresRestantes()
	 * @see Joueur#increaseVictoires()
	 * @see Joueur#decreaseVies()
	 * @see Joueur#getVies()
	 * @see Joueur#setVictoires(int)
	 * @see Jeu#demanderRecommencer(boolean)
	 */
	public void verifierLettre(String s){
		boolean fin = mot.actualiser(s.charAt(0));
		if(fin){
			if(mot.getLettresRestantes() == 0){
				joueur.increaseVictoires();
				demanderRecommencer(true);
			}
		}
		else{
			joueur.decreaseVies();
			if(joueur.getVies() == 0){
				joueur.setVictoires(0);
				demanderRecommencer(false);
			}
		}
		fenetre.repaint();
	}
	
	/**
	 * Initialise la partie principale du jeu.
	 * @param s Une chaîne de caractères correspondant à la difficulté cliquée.
	 * @see Jeu#joueur
	 * @see Jeu#fenetre
	 * @see Joueur#setVies(String)
	 * @see Fenetre#lancerJeu(String)
	 */
	public void creerJeu(String s){
		joueur.setVies(s);
		fenetre.lancerJeu();
	}
	
	/**
	 * Demande à la fenêtre d'afficher l'écran de fin de partie, en fonction du résultat du joueur.
	 * @param gagne Un booléen indiquant si le joueur a trouvé ou non le mot.
	 * @see Jeu#fenetre
	 * @see Fenetre#afficherFin(boolean)
	 */
	public void demanderRecommencer(boolean gagne){
		fenetre.afficherFin(gagne);
		fenetre.repaint();
		fenetre.revalidate();
	}
	
	/**
	 * Recommence une partie ou quitte le jeu, selon le choix du joueur.
	 * @param s Une chaîne de caractère correspondant au choix du joueur.
	 * @see Jeu#fenetre
	 * @see Jeu#mot
	 * @see Jeu#joueur
	 * @see Jeu#fr
	 * @see Jeu#br
	 * @see Jeu#genererMot()
	 * @see Joueur#getVictoires()
	 * @see Fenetre#quitter()
	 */
	public void choixRecommencer(String s){
		if(s == "Oui !"){
			fenetre.dispose();
			mot = new Mot(genererMot());
			joueur = new Joueur(joueur.getVictoires());
			fenetre = new Fenetre(this);
		} else {
			fenetre.quitter();
			fenetre.repaint();
			fenetre.revalidate();
		}
		try {
			fr.close();
			br.close();
		} catch (IOException e){
			e.printStackTrace();
		}
	}
}
