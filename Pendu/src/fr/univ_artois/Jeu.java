package fr.univ_artois;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Jeu est la classe ou sont gerees tous les elements en rapport avec le deroulement du jeu.
 * @author quentin sauvage
 *
 */
public class Jeu {
	
	/**
	 * Le joueur represente l'utilisateur.
	 * @see Joueur
	 * @see Jeu#Jeu()
	 * @see Jeu#getJoueur()
	 */
	private Joueur joueur;
	
	/**
	 * motActuel represente le mot que le joueur devra deviner.
	 * @see Mot
	 * @see Jeu#Jeu()
	 * @see Jeu#getMotActuel()
	 * @see Jeu#setMotActuel(Mot)
	 * @see Jeu#lancerJeu()
	 * @see Jeu#recommencer()
	 * @see Jeu#jouerTour()
	 */
	private Mot motActuel;
	
	/**
	 * scanner permet de lire les valeurs rentrees par l'utilisateur.
	 * @see Jeu#Jeu()
	 * @see Jeu#saisirEntier()
	 * @see Jeu#choisirRecommencer()
	 * @see Jeu#saisirLettre()
	 */
	private Scanner scanner;
	
	/**
	 * La liste des lettres deja proposees par le joueur.
	 * @see Jeu#Jeu()
	 * @see Jeu#recommencer()
	 * @see Jeu#saisirLettre()
	 */
	private ArrayList<Character>lettresProposees;
	
	/**
	 * Constructeur de Jeu.
	 * @see Jeu#scanner
	 * @see Jeu#lettresProposees
	 * @see Jeu#joueur
	 * @see Jeu#motActuel
	 */
	public Jeu(){
		scanner = new Scanner(System.in);
		lettresProposees = new ArrayList<Character>();
		joueur = new Joueur(choisirDifficulte());
		motActuel = initMot();
		lancerJeu();
	}
	
	/**
	 * Retourne le joueur.
	 * @return le joueur representant l'utilisateur.
	 * @see Joueur
	 * @see Jeu#joueur
	 */
	public Joueur getJoueur() {
		return joueur;
	}
	
	/**
	 * Retourne le mot a deviner.
	 * @return le mot devant etre devine.
	 * @see Mot
	 * @see Jeu#motActuel
	 */
	public Mot getMotActuel() {
		return motActuel;
	}
	
	/**
	 * Modifie le mot actuel.
	 * @param m Un nouveau mot.
	 * @see Mot
	 * @see Jeu#motActuel
	 */
	public void setMotActuel(Mot m) {
		motActuel = m;
	}
	
	/**
	 * Affecte un mot aleatoire, stocke dans un fichier, au mot actuel.
	 * @return le mot choisi aleatoirement.
	 * @see Mot
	 */
	public Mot initMot(){
		String line = "";
		int nbLignes = 0;
		File f = new File("mots.txt");
		int i = 0;
		try{
			FileReader fr = new FileReader(f);
			BufferedReader br = new BufferedReader(fr);
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
		return new Mot(line);
	}
	
	/**
	 * Une exception en rapport avec le choix de la difficulte donne par le joueur.
	 * @author quentin sauvage
	 *
	 */
	public class ChoixDifficulteException extends Exception {
		
		/**
		 * Retourne un message d'erreur relatif au choix de la difficulte.
		 * @return un message d'erreur.
		 */
		public String toString() {
			return "Entrez f, m, ou d.";
		}
	}
	
	/**
	 * Gere la saisie de la difficulte par l'utilisateur.
	 * @return un caractere correspondant a une difficulte.
	 * @throws Exception Si le caractere n'est pas valide.
	 * @see Jeu#scanner
	 */
	public char saisirEntier() throws Exception {
		char difficulte;
		String line = "";
		line = scanner.next();
		difficulte = line.charAt(0);
		if(difficulte != 'f' && difficulte != 'm' && difficulte != 'd' || line.length() != 1) throw new ChoixDifficulteException();
		return difficulte;
	}
	
	/**
	 * Gestionnaire d'erreurs en rapport avec la saisie de la difficulte.
	 * @return un caractere correspondant a la difficulte.
	 */
	public char choisirDifficulte() {
		System.out.println("\nChoississez votre difficulte : Facile(f), Moyen(m) ou Difficile(d).");
		char c = 0;
		while(true){
			try{
				c = saisirEntier();
				break;
			} catch (ChoixDifficulteException e) {
				System.out.println("Saisie incorrecte : " + e);
			} catch(Exception e) {
				System.out.println(e.getMessage());
			}
		}
		System.out.print("Vous avez choisi le niveau de difficulte ");
		return c;
	}
	
	/**
	 * Une exception en rapport avec le choix de recommencer ou non la partie.
	 * @author quentin sauvage
	 *
	 */
	public class ChoixRecommencerException extends Exception {
		
		/**
		 * Retourne un message d'erreur relatif au choix de recommencer ou non la partie.
		 * @return un message d'erreur.
		 */
		public String toString() {
			return "La reponse est incorrecte (saississez o ou n)";
		}
	}
	
	/**
	 * Gere la saisie du choix de recommencer ou non la partie.
	 * @return un caractere correspondant au choix de recommencer ou non la partie.
	 * @throws Exception Si le caractere n'est pas valide.
	 * @see Jeu#scanner
	 */
	public char choisirRecommencer() throws Exception {
		char c;
		String line = "";
		line = scanner.next();
		c = line.charAt(0);
		if((c != 'o' && c != 'n') || line.length() != 1) throw new ChoixRecommencerException();
		return c;
	}
	
	/**
	 * Gestionnaire d'erreurs en rapport avec la saisie de fin de partie.
	 * @see Jeu#motActuel
	 * @see Jeu#joueur
	 * @see Jeu#lettresProposees
	 * @see Jeu#scanner
	 */
	public void recommencer() {
		char c;
		while(true) {
			try {
				c = choisirRecommencer();
				break;
			} catch(ChoixRecommencerException e){
				System.out.println("Saisie incorrecte : " + e);
			} catch(Exception e) {
				System.out.println(e.getMessage());
			}
		} if(c == 'o') {
			motActuel = initMot();
			joueur.setVies(joueur.initVies(choisirDifficulte()));
			lettresProposees = new ArrayList<Character>();
			lancerJeu();
		} else {
			System.out.println("Fin du jeu.");
			scanner.close();
		}
	}
	
	/**
	 * Gere le deroulement de chaque tour de la partie.
	 * @see Joueur#getVies()
	 * @see Joueur#getVictoires()
	 * @see Joueur#setVictoires(int)
	 * @see Joueur#afficherPendu()
	 * @see Mot#getMot()
	 * @see Mot#getLettresRestantes()
	 * @see Mot#motADeviner()
	 */
	public final void lancerJeu() {
		while(joueur.getVies() > 0 && motActuel.getLettresRestantes() > 0){
			System.out.println("Mot a deviner : " + motActuel.motADeviner());
			joueur.afficherPendu();
			System.out.println("Vies restantes : " + joueur.getVies());
			jouerTour();
		}
		if(joueur.getVies() == 0) {
			joueur.setVictoires(0);
			joueur.afficherPendu();
			System.out.println("GAME OVER, le mot etait " + motActuel.getMot().toUpperCase() + ".");
		}
		else {
			System.out.println("Mot a deviner : " + motActuel.motADeviner());
			System.out.println("Bravo, vous avez gagne !");
			joueur.increaseVictoires();
		}
		System.out.println("Serie de victoires : " + joueur.getVictoires());
		System.out.print("Voulez-vous recommencer ? (o/n)");
		recommencer();
	}
	
	/**
	 * Une exception en rapport avec la lettre donnee par l'utilisateur.
	 * @author quentin sauvage
	 *
	 */
	public class ChoixLettreException extends Exception {
		public String toString() {
			return "Veuillez choisir une lettre valide.";
		}
	}
	
	/**
	 * Une exception qui a lieu si la lettre a deja ete donnee.
	 * @author quentin sauvage
	 *
	 */
	public class PresenceLettreException extends Exception {
		public String toString() {
			return "Lettre deja proposee";
		}
	}
	
	/**
	 * Gere la saisie d'une lettre par l'utilisateur.
	 * @return un caractere correspondant a la lettre choisie par l'utilisateur.
	 * @throws Exception Si le caractere n'est pas valide.
	 * @see Jeu#scanner
	 * @see Jeu#lettresProposees
	 */
	public char saisirLettre() throws Exception {
		System.out.print("Saississez une lettre : ");
		char c = 0;
		String line = "";
		line = scanner.next();
		c = line.charAt(0);
		if(c < 97 || c > 122 || line.length() != 1) throw new ChoixLettreException();
		boolean dedans = false;
		for(char tmp : lettresProposees) {
			if(tmp == c)
				dedans = true;
		} if(dedans) throw new PresenceLettreException();
		return c;
	}
	
	/**
	 * Gestionnaire d'erreurs en rapport avec la saisie d'une lettre a trouver.
	 * @see Jeu#lettresProposees
	 * @see Mot#actualiser(char)
	 * @see Joueur#decreaseVies()
	 */
	public void jouerTour() {
		char c;
		c = 0;
		while(true) {
			try {
				c = saisirLettre();
				break;
			} catch(ChoixLettreException e) {
				c = 0;
				System.out.println("Saisie incorrecte : " + e);
			} catch(PresenceLettreException e) {
				c = 0;
				System.out.println("Saisie incorrecte : " + e);
			} catch(Exception e) {
				System.out.println(e.getMessage());
			}
		}
		lettresProposees.add(c);
		System.out.println("");
		if(!motActuel.actualiser(c)) {
			joueur.decreaseVies();
			System.out.println("La lettre n'est pas dans le mot, vous perdez une vie.");
		}
	}
}
