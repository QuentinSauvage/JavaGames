import java.awt.Color;
import java.util.ArrayList;

public class Modele {
	private Joueur listeJoueurs[];
	private ArrayList<Voie> listeVoies;
	private int controle;
	private int nbHumains;
	
	public Modele(int nbHumains) {
		this.nbHumains = nbHumains;
		listeJoueurs = new Joueur[4];
		Color couleurs[] = new Color[4];
		couleurs[0] = Color.BLUE;
		couleurs[1] = Color.RED;
		couleurs[2] = Color.ORANGE;
		couleurs[3] = Color.GREEN;
		for(int i = 0; i < nbHumains; i++) {
			listeJoueurs[i] = new Joueur(i, couleurs[i]);
		}
		for(int i = nbHumains; i < 4; i++) {
			listeJoueurs[i] = new IA(i, couleurs[i]);
		}
		listeVoies = new ArrayList<Voie>();
		listeVoies.add(new Voie(2, 3));
		listeVoies.add(new Voie(3, 5));
		listeVoies.add(new Voie(4, 7));
		listeVoies.add(new Voie(5, 9));
		listeVoies.add(new Voie(6, 11));
		listeVoies.add(new Voie(7, 13));
		listeVoies.add(new Voie(8, 11));
		listeVoies.add(new Voie(9, 9));
		listeVoies.add(new Voie(10, 7));
		listeVoies.add(new Voie(11, 5));
		listeVoies.add(new Voie(12, 3));
		controle = 0;
	}
	
	public int getControle() {
		return controle;
	}
	
	public void setControle(int c) {
		controle = c % 4;
	}
	
	public Joueur[] getListeJoueurs() {
		return listeJoueurs;
	}
	
	public void setListeJoueurs(Joueur[] l) {
		listeJoueurs = l;
	}
	
	public ArrayList<Voie> getListeVoies() {
		return listeVoies;
	}
	
	public void setListeVoies(ArrayList<Voie> l) {
		listeVoies = l;
	}
	
	public int getNbHumains() {
		return nbHumains;
	}

	public void setNbHumains(int nbHumains) {
		this.nbHumains = nbHumains;
	}
	
	public void grimper(int x, int y) {
		if(y == -1)
			listeJoueurs[controle].grimperChoix(x);
		else
			listeJoueurs[controle].grimper(x, y);
	}
}
