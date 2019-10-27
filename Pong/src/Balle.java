/**
 * 
 * @author Quentin Sauvage
 */
public class Balle extends Objet implements Runnable {
	/**
	 * L'abscisse de la balle.
	 */
	private double dx;
	
	/**
	 * L'ordonnée de la balle.
	 */
	private double dy;
	
	/**
	 * Le compteur indiquant si la vitesse de la balle doit être augmentée.
	 */
	private int compteurVitesse;
	
	/**
	 * La limite informant que la vitesse doit être augmentée si le compteur l'a atteint.
	 */
	private int limiteVitesse;
	
	/**
	 * Le temps que le thread devra dormir à chaque passage.
	 */
	private int sleepTimer;
	
	/**
	 * Booléen indiquant si la balle est un bonus ou si elle est en jeu.
	 */
	private boolean bonus;
	
	/**
	 * Booléen indiquant si la balle doit être visible (bonus ou en jeu), ou si elle doit être cachée.
	 */
	private boolean visible;
	
	/**
	 * Crée la balle en initialisant sa position, sa direction, sa vitesse et ses états.
	 * @param x L'abscisse de la balle.
	 * @param y L'ordonnée de la balle.
	 * @param l La largeur de la balle.
	 * @param h La hauteur de la balle.
	 * @param direction La direction horizontale de la balle.
	 * @param direction2 La direction verticale de la balle.
	 * @param bonus Vaudra vrai si la balle est un bonus, faux sinon.
	 * @param visible Vaudra vrai si la balle doit être visible, faux sinon.
	 */
	public Balle(double x, double y, int l, int h, double direction, double direction2, boolean bonus, boolean visible) {
		super(x, y, l, h);
		dx = direction;
		dy = direction2;
		compteurVitesse = 0;
		sleepTimer = 10;
		setLimiteVitesse(100);
		this.bonus = bonus;
		this.visible = visible;
		Thread tBalle = new Thread(this);
		tBalle.start();
	}

	/**
	 * Indique si la balle est un bonus.
	 * @return L'état "bonus" de la balle.
	 */
	public boolean isBonus() {
		return bonus;
	}
	
	/**
	 * Met à jour l'état "bonus" de la balle.
	 * @param b Le nouvel état de la balle.
	 */
	public void setBonus(boolean b) {
		bonus = b;
	}
	
	/**
	 * Informe de la coordonnée horizontale de la balle.
	 * @return L'abscisse de la balle.
	 */
	public double getDx() {
		return dx;
	}
	
	/**
	 * Met à jour l'abscisse de la balle.
	 * @param dx La nouvelle abscisse de la balle.
	 */
	public void setDx(double dx) {
		this.dx = dx;
	}
	
	/**
	 * Informe de la coordonnée verticale de la balle.
	 * @return L'ordonnée de la balle.
	 */
	public double getDy() {
		return dy;
	}
	
	/**
	 * Met à jour l'ordonnée de la balle.
	 * @param b La nouvelle ordonnée de la balle.
	 */
	public void setDy(double dy) {
		this.dy = dy;
	}
	
	/**
	 * Permet d'accéder à la vitesse de la balle.
	 * @return la vitesse de la balle
	 */
	public int getSleepTimer() {
		return sleepTimer;
	}
	
	/**
	 * Met à jour la vitesse de la balle.
	 * @param s La nouvelle vitesse de la balle.
	 */
	public void setSleepTimer(int s) {
		sleepTimer = s;
	}
	
	/**
	 * Indique la valeur maximale pouvant être atteinte par le chrono.
	 * @return la valeur maximale du chrono.
	 */
	public int getLimiteVitesse() {
		return limiteVitesse;
	}

	/**
	 * Met à jour la vitesse de la balle.
	 * @param limiteVitesse La nouvelle vitesse de la balle.
	 */
	public void setLimiteVitesse(int limiteVitesse) {
		this.limiteVitesse = limiteVitesse;
	}
	
	/**
	 * Indique si la balle est visible ou non par les joueurs.
	 * @return L'état "visible" de la balle.
	 */
	public boolean isVisible() {
		return visible;
	}
	
	/**
	 * Met à jour l'état "visible" de la balle.
	 * @param b Le nouvel état "visible" de la balle.
	 */
	public void setVisible(boolean b) {
		visible = b;
	}
	
	/**
	 * Indique s'il y a collision entre la balle et l'objet au-dessus d'elle.
	 * @param o L'objet potentiellement en collision avec la balle.
	 * @see Objet#getLargeur()
	 * @see Objet#getHauteur()
	 * @see Objet#getX()
	 * @see Objet#getY()
	 * @return Vrai s'il y a collision au-dessus, faux sinon.
	 */
	public boolean collisionDessus(Objet o) {
		if(getX() + getLargeur() < o.getX() || o.getX() + o.getLargeur() < getX() || getY() < o.getY())
			return false;
		return getY() <= o.getY() + o.getHauteur() + 1;
	}
	
	/**
	 * Indique s'il y a collision entre la balle et l'objet au-dessous d'elle.
	 * @param o L'objet potentiellement en collision avec la balle.
	 * @see Objet#getLargeur()
	 * @see Objet#getHauteur()
	 * @see Objet#getX()
	 * @see Objet#getY()
	 * @return Vrai s'il y a collision au-dessous, faux sinon.
	 */
	public boolean collisionDessous(Objet o) {
		if(getX() + getLargeur() < o.getX() || o.getX() + o.getLargeur() < getX() || getY() > o.getY())
			return false;
		return (getY() + getHauteur()) >= o.getY() - 1;
	}
	
	/**
	 * Indique s'il y a collision entre la balle et l'objet à sa gauche.
	 * @param o L'objet potentiellement en collision avec la balle.
	 * @see Objet#getLargeur()
	 * @see Objet#getHauteur()
	 * @see Objet#getX()
	 * @see Objet#getY()
	 * @return Vrai s'il y a collision à gauche, faux sinon.
	 */
	public boolean collisionGauche(Objet o) {
		if(getY() > (o.getY() + o.getHauteur()) || (getY() + getHauteur()) < o.getY())
			return false;
		return getX() >= o.getX() + o.getLargeur() + 1;
	}
	
	/**
	 * Indique s'il y a collision entre la balle et l'objet à sa droite.
	 * @param o L'objet potentiellement en collision avec la balle.
	 * @see Objet#getLargeur()
	 * @see Objet#getHauteur()
	 * @see Objet#getX()
	 * @see Objet#getY()
	 * @return Vrai s'il y a collision à droite, faux sinon.
	 */
	public boolean collisionDroite(Objet o) {
		if(getY() > (o.getY() + o.getHauteur()) || (getY() + getHauteur()) < o.getY())
			return false;
		return getX() + getLargeur() <= o.getX() - 1;
	}
	
	/**
	 * Modifie la direction de la balle et selon les cas, de l'objet, s'il y a collision entre eux.
	 * @param o L'objet potentiellement en collision avec la balle.
	 * @see Balle#collisionDessous(Objet)
	 * @see Balle#collisionDessus(Objet)
	 * @see Balle#collisionDroite(Objet)
	 * @see Balle#collisionGauche(Objet)
	 * @see Balle#isBonus()
	 * @see Balle#isVisible()
	 * @see Balle#setBonus(boolean)
	 * @see Balle#getDx()
	 * @see Balle#getDy()
	 * @see Balle#setDx(double)
	 * @see Balle#setDy(double)
	 * @see Objet#getHauteur()
	 * @see Objet#getLargeur()
	 * @see Objet#setHauteur(int)
	 * @see Objet#setLargeur(int)
	 */
	public void contact(Objet o) {
		double reflection;
		if(o.getClass().getName() == "Balle") {
			if(((Balle)o).isVisible() == true) {
				if(((Balle)o).isBonus()) {
					if(collisionGauche(o) || collisionDroite(o) || collisionDessus(o) || collisionDessous(o)) {
						((Balle)o).setBonus(false);
						((Balle)o).setLargeur(10);
						((Balle)o).setHauteur(10);
						if(getDx() == 1) {
							((Balle)o).setDx(1);
							o.setX(Fenetre.modele.joueur1.getX() + Fenetre.modele.joueur1.getLargeur() + 4);
							o.setY(Fenetre.modele.joueur1.getY() + (Fenetre.modele.joueur1.getHauteur() / 2));
						}
						else {
							((Balle)o).setDx(-1);
							o.setX(Fenetre.modele.joueur2.getX()  - (getLargeur() + 4));
							o.setY(Fenetre.modele.joueur2.getY() + (Fenetre.modele.joueur2.getHauteur() / 2));
						}
					}
				} else {
					if(collisionGauche(o) || collisionDroite(o) || collisionDessus(o) || collisionDessous(o)) {
						setDx(-1 * getDx());
						setDy(-1 * getDy());
						((Balle)o).setDx(-1 * ((Balle)o).getDx());
						((Balle)o).setDy(-1 * ((Balle)o).getDy());
					}
				}
			}
		} else {
			if(o.getLargeur() == 5) {
				if((collisionGauche(o) && getDx() < 0) || (collisionDroite(o) && getDx() >= 0)) {
					setDx(-dx);
					reflection = (o.getY() + (o.getHauteur() / 2)) - getY();
					setDy(-reflection / 30);
				}
			}
			if((collisionDessus(o) && getDy() < 0) || (collisionDessous(o) && getDy() >= 0))
				setDy(-dy);
		}
	}
	
	/**
	 * Permet de savoir si la balle se trouve à moins de 3.5pixels de l'objet.
	 * @param o L'objet potentiellement proche de la balle.
	 * @see Objet#getLargeur()
	 * @see Objet#getHauteur()
	 * @see Objet#getX()
	 * @see Objet#getY()
	 * @return Vrai si la balle est à moins de 3.5px de l'objet, faux sinon.
	 */
	public boolean proche(Objet o) {
		if(o.getX() >= (getX() + getLargeur()))
			return o.getX() - (getX() + getLargeur()) <= 3.5;
		else if(o.getX() + o.getLargeur() <= getX())
			return getX() - (o.getX() + o.getLargeur()) <= 3.5;
		if(o.getY() >= (getY() + getHauteur()))
			return o.getY() - (getY() + getHauteur()) <= 3.5;
		return getY() - (o.getY() + o.getHauteur()) <= 3.5;
	}
	
	/**
	 * Déplace la balle en fonction de sa direction verticale et de sa direction horizontale. Informe le jeu si celle-ci sort du plateau.
	 * @see Objet#setX(double)
	 * @see Objet#getX()
	 * @see Objet#setY(double)
	 * @see Objet#getY()
	 * @see Jeu#getEnCours()
	 * @see Jeu#terminerManche(int)
	 */
	public void bouge() {
		setX(getX() + dx);
		setY(getY() + dy);
		if(Fenetre.modele.getEnCours() == true) {
			if(getX() < 0) {
				Fenetre.modele.terminerManche(1);
			}
			else if(getX() + getLargeur() > Fenetre.modele.getWidth()) {
				System.out.println(getX() + getLargeur() - Fenetre.modele.getWidth());
				Fenetre.modele.terminerManche(0);
			}
		}
	}
	
	/**
	 * Bouge la balle et met à jour sa vitesse.
	 * @see Jeu#getEnCours()
	 * @see Balle#bouge()
	 * @see Balle#isBonus()
	 * @see Balle#setLimiteVitesse(int)
	 * @see Balle#getLimiteVitesse()
	 * @see Balle#setSleepTimer(int)
	 * @see Balle#getSleepTimer()
	 */
	@Override
	public void run() {
		try {
			Thread.sleep(50);
		} catch (InterruptedException e1) {
			e1.printStackTrace();
		}
		while(true) {
			if(Fenetre.modele.getEnCours() && !isBonus()) {
				bouge();
				compteurVitesse++;
				if(compteurVitesse == getLimiteVitesse()) {
					if(getSleepTimer() > 2) {
						setSleepTimer(getSleepTimer() - 1);
						setLimiteVitesse(getLimiteVitesse()*2);
					}
					compteurVitesse = 0;
				}
			}
			try {
				Thread.sleep(getSleepTimer());
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

}
