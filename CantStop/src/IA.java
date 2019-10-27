import java.awt.Color;
import java.util.ArrayList;
import java.util.Random;

public class IA extends Joueur {

	public IA(int id, Color c) {
		super(id, c);
	}
	
	public void genererDes() {
		Random rd = new Random();
		int v;
		for(int i = 0; i < 4; i++) {
			v = rd.nextInt(6);
			getListeDes()[i].setValeur(v + 1);
			getListeDes()[i].setLance(true);
		}
		Main.fenetre.updatePanelDe(getListeDes());
	}
	
	public boolean choisirCouple() {
		boolean peutJouer = false;
		De[] listeDes = getListeDes();
		Pion[] listePions = getListePions();
		ArrayList<Pair> al = new ArrayList<Pair>();
		Pair paire;
		for(int j = 1; j < 4; j++) {
			if(j == 1) {
				paire = new Pair(listeDes[0].getValeur() + listeDes[1].getValeur(), listeDes[2].getValeur() + listeDes[3].getValeur());
			} else if(j == 2) {
				paire = new Pair(listeDes[0].getValeur() + listeDes[2].getValeur(), listeDes[1].getValeur() + listeDes[3].getValeur());
			} else {
				paire = new Pair(listeDes[0].getValeur() + listeDes[3].getValeur(), listeDes[1].getValeur() + listeDes[2].getValeur());
			}
			if(!al.contains(paire)) {
				al.add(paire);
			}
		}
		for(int i = 0; i < 8; i++) {
			for(int j = i; j < 9; j++) {
				if(listePions[i].getPosition() < listePions[j].getPosition()) {
					Pion p = listePions[i];
					listePions[i] = listePions[j];
					listePions[j] = p;
				}
			}
		}
		int nbPlaces = 0;
		for(int i = 0; i < 9; i++) {
			if(nbPlaces < 2) {
				for(Pair p : al) {
					boolean init = false;
					if(p.getX() == listePions[i].getVoie().getId() && listePions[i].getVoie().getTaille() > listePions[i].getPosition()) {
						this.getListeGrimpeurs()[nbPlaces].setVoie(listePions[i].getVoie());
						this.getListeGrimpeurs()[nbPlaces].setPosition(listePions[i].getPosition() + 1);
						peutJouer = true;
						init = true;
					}
					if(p.getY() == listePions[i].getVoie().getId() && listePions[i].getVoie().getTaille() > listePions[i].getPosition()) {
						this.getListeGrimpeurs()[nbPlaces].setVoie(listePions[i].getVoie());
						this.getListeGrimpeurs()[nbPlaces].setPosition(listePions[i].getPosition() + 1);
						peutJouer = true;
						init = true;
					}
					if(init)
						nbPlaces++;
				}
			}
		}
		return peutJouer;
	}
	
	public void jouer() {
		int nbCoups = 0;
		boolean peutJouer = true;
		Random rd = new Random();
		int x = 3 + rd.nextInt(4);
		while(nbCoups < x && peutJouer) {
			genererDes();
			peutJouer = choisirCouple();
			nbCoups++;
		}
		if(!peutJouer) {
			chuter();
		}
		finirTour();
		setTombe(false);
		Main.fenetre.changerTour();
	}
}
