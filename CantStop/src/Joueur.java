import java.awt.Color;
import java.util.Random;

public class Joueur {
	private int id;
	private Pion listePions[];
	private Grimpeur listeGrimpeurs[];
	private De listeDes[];
	private Color color;
	private boolean tombe;
	private int nbPions;
	
	public Joueur(int id, Color c) {
		this.id = id;
		color = c;
		listePions = new Pion[9];
		for(int i = 0; i < 9; i++) {
			listePions[i] = new Pion();
		}
		listeGrimpeurs = new Grimpeur[3];
		for(int i = 0; i < 3; i++) {
			listeGrimpeurs[i] = new Grimpeur();
		}
		listeDes = new De[4];
		for(int i = 0; i < 4; i++) {
			listeDes[i] = new De(1);
		}
		tombe = false;
		nbPions = 0;
	}
	
	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	public int getNbPions() {
		return nbPions;
	}
	
	public void setNbPions(int nbPions) {
		this.nbPions = nbPions;
	}
	
	public Pion[] getListePions() {
		return listePions;
	}
	
	public void setListePions(Pion[] listePions) {
		this.listePions = listePions;
	}
	
	public De[] getListeDes() {
		return listeDes;
	}
	
	public void setListeDes(De[] listeDes) {
		this.listeDes = listeDes;
	}
	
	public Grimpeur[] getListeGrimpeurs() {
		return listeGrimpeurs;
	}
	
	public void setListeGrimpeurs(Grimpeur[] listeGrimpeurs) {
		this.listeGrimpeurs = listeGrimpeurs;
	}
	
	public Color getColor() {
		return color;
	}

	public void setColor(Color color) {
		this.color = color;
	}
	
	public boolean isTombe() {
		return tombe;
	}
	
	public void setTombe(boolean b) {
		tombe = b;
	}
	
	public void genererDes() {
		Random rd = new Random();
		int v;
		for(int i = 0; i < 4; i++) {
			v = rd.nextInt(6);
			listeDes[i].setValeur(v + 1);
			listeDes[i].setLance(true);
		}
		Main.fenetre.metAJour(listeDes);
	}
	
	public void grimper(int x, int y) {
		tombe = false;
		int nbGrimpeursPoses = 0;
		boolean poseX = false;
		boolean poseY = false;
		for(int i = 0; i < 3; i++) {
			if(nbGrimpeursPoses < 2) {
				if(listeGrimpeurs[i].getVoie().getId() == -1) {
					if(nbGrimpeursPoses == 0) {
						if(i < 2) {
							listeGrimpeurs[i].setVoie(Main.fenetre.modele.getListeVoies().get(x - 2));
							poseX = true;
						} else {
							if(x == y) {
								listeGrimpeurs[i].setVoie(Main.fenetre.modele.getListeVoies().get(x - 2));
								poseX = true;
							} else {
								new ChoixJoueur(x, y);
								Main.fenetre.modifierLancer(true);
								poseX = true;
							}
						}
					} else {
						if(poseX) {
							listeGrimpeurs[i].setVoie(Main.fenetre.modele.getListeVoies().get(y - 2));
							poseY = true;
						} else {
							listeGrimpeurs[i].setVoie(Main.fenetre.modele.getListeVoies().get(x - 2));
							poseX = true;
						}
					}
				}
				if(listeGrimpeurs[i].getPosition() == 0) {
					for(int j = 0; j < 9; j++) {
						if(listePions[j].getVoie().getId() == listeGrimpeurs[i].getVoie().getId()) {
							listeGrimpeurs[i].setPosition(listePions[j].getPosition());
						}
					}
				}
				if((listeGrimpeurs[i].getVoie().getId() == x)  && listeGrimpeurs[i].getVoie().getTaille() > listeGrimpeurs[i].getPosition()) {
					listeGrimpeurs[i].setPosition(listeGrimpeurs[i].getPosition() + 1);
					nbGrimpeursPoses++;
					poseX = true;
				}
				if((listeGrimpeurs[i].getVoie().getId() == y) && listeGrimpeurs[i].getVoie().getTaille() > listeGrimpeurs[i].getPosition()) {
					listeGrimpeurs[i].setPosition(listeGrimpeurs[i].getPosition() + 1);
					nbGrimpeursPoses++;
					poseY = true;
				}
			}
		}
		if(!poseX && !poseY) {
			chuter();
		}
	}
	
	public void chuter() {
		tombe = true;
		for(int i = 0; i < 3; i++) {
			boolean dejaPresent = false;
			for(int j = 0; j < 3; j++) {
				if(listePions[j].getVoie().getId() == listeGrimpeurs[i].getVoie().getId()) {
					listeGrimpeurs[i].setPosition(listePions[j].getPosition());
					dejaPresent = true;
				}
			}
			if(!dejaPresent) {
				listeGrimpeurs[i].setPosition(0);
				listeGrimpeurs[i].setVoie(new Voie(-1, 1));
			}
		}
	}
	
	public void grimperChoix(int x) {
		listeGrimpeurs[2].setVoie(Main.fenetre.modele.getListeVoies().get(x - 2));
		if(listeGrimpeurs[2].getPosition() == 0) {
			for(int j = 0; j < 9; j++) {
				if(listePions[j].getVoie().getId() == listeGrimpeurs[2].getVoie().getId()) {
					listeGrimpeurs[2].setPosition(listePions[j].getPosition());
				}
			}
		}
		if(listeGrimpeurs[2].getVoie().getTaille() > listeGrimpeurs[2].getPosition())
		listeGrimpeurs[2].setPosition(listeGrimpeurs[2].getPosition() + 1);
	}
	
	public boolean calculerSommets() {
		int nbSommets = 0;
		for(int i = 0; i < 9; i++) {
			if(listePions[i].getPosition() == listePions[i].getVoie().getTaille()) {
				nbSommets++;
			}
		}
		return nbSommets >= 3;
	}
	
	public void finirTour() {
		for(int i = 0; i < 3; i++) {
			boolean place = false;
			for(int j = 0; j < 9; j++) {
				if(listePions[j].getPosition() == 0 &&  !place) {
					listePions[j].setVoie(listeGrimpeurs[i].getVoie());
					nbPions++;
					place = true;
				} if (listePions[j].getVoie().getId() == listeGrimpeurs[i].getVoie().getId()) {
					listePions[j].setPosition(listeGrimpeurs[i].getPosition());
					listeGrimpeurs[i].setPosition(0);
					listeGrimpeurs[i].setVoie(new Voie(-1, 1));
				}
			}
			if(nbPions > 9) {
				listeGrimpeurs[i].setPosition(0);
				listeGrimpeurs[i].setVoie(new Voie(-1, 1));
			}
		}
		if(calculerSommets()) {
			Main.fenetre.afficherFinDePartie();
		}
	}
}
