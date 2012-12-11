package moteur;

import java.awt.image.BufferedImage;
import java.io.Serializable;
import java.util.ArrayList;

/**
 *
 * @author Kévin
 */
public abstract class Pion implements Serializable {

	protected static final long serialVersionUID = 1L;
	/*
	 * La partie Statistique
	 */
	private static final int CHANCEMIN = 20;
	private static final int CHANCEMAX = 30;
	private static final int TAUXRIPOSTE = 95;
	/**
	 * Determine la vie d'un pion
	 */
	protected int vie;
	/**
	 * Determine la force d'un pion, plus la force est grande, plus il infligera
	 * de degats.
	 */
	protected int force;
	/**
	 * Determine la precision, plus elle est grande, plus il aura de chance
	 * de toucher son adversaire et plus il aura de chance d'infliger un
	 * coup ciritque.
	 */
	protected int precision;
	/**
	 * Determine la vitesse, plus elle est grande, plus il aura de chance
	 * d'infliger un coup critique.
	 */
	protected int vitesse;
	/**
	 * Determine la defense, plus elle est grande, moins il recevra de degats.
	 */
	protected int defense;
	/**
	 * Determine la chance, elle est une des composantes du calcul d'esquive.
	 */
	protected int chance;
	/**
	 * Determine la portee maximale du pion. Il pourra attaquer tout element
	 * attaquable present dans cette portee.
	 */
	protected int portee;
	/**
	 * Determine le nombre de mouvement maximal d'un pion sur un terrain neutre.
	 */
	protected int mouvement;
	/**
	 * Determine si le pion est le commandant actuel.
	 */
	protected boolean commandant;
	/**
	 * Determine l'orientation du pion. Influe sur l'attaque d'un pion.
	 */
	protected Orientation orientation;
	/**
	 * Une liste de noeud qui permet d'obtenir tout les deplacements possibles
	 * du pion à partir de sa case c.
	 */
	public ArrayList<Noeud> listeDeplacementPossible;
	/**
	 * Une liste de Case qui contient toutes les cases attaquables 
	 * (qui contiennent un élément attaquable) par le pion.
	 */
	public ArrayList<Case> listeAttaquePossible;
	/**
	 * Une liste de Case qui contient toutes les cases que le pion pourrait 
	 * attaquer si elles etaient occupees.
	 */
	public ArrayList<Case> listeAttaqueAire;
	/**
	 * Une liste de Case qui, pour le commandant, permet de connaitre les cases
	 * qu'il peut conquerir depuis sa case.
	 */
	public ArrayList<Case> listeConquetePossible;
	/**
	 * Une liste de case qui contient le meilleur deplacement entre 2 cases.
	 */
	protected ArrayList<Case> deplacement;
	/**
	 * La case où se trouve le pion.
	 */
	protected Case c;
	/**
	 * La joueur à qui appartient le pion.
	 */
	protected Joueur joueur;
	/**
	 * Determine le nom de la capacite speciale du pion.
	 */
	protected String nomCapaciteSpeciale;
	/**
	 * Necessaire pour l'animation. Determine la sequence des degats infliges
	 * durant un combat.
	 */
	public ArrayList<Integer> degatsCombat;
	private static final int BONUSKILL = 20;

	private int special;
	private int tourspecial;
	private int mouvementBase;

	/**
	 * Constructeur de Pion.
	 * @param vie
	 * @param force
	 * @param precision
	 * @param vitesse
	 * @param defense
	 * @param bonusChance
	 * @param portee
	 * @param mouvement
	 * @param c
	 * 
	 * Les parametres sont associes aux variables portant le meme nom.
	 * Par defaut, le pion a une orientation vers le SUD.
	 * La creation d'un pion l'ajoute au plateau de c.
	 * Toutes les ArrayList sont créées.
	 * 
	 */
	public Pion(int vie, int force, int precision, int vitesse, int defense, int bonusChance, int portee, int mouvement, Case c) {
		this.vie = vie;
		this.force = force;
		this.precision = precision;
		this.vitesse = vitesse;
		this.defense = defense;
		this.chance = (int) (Math.random() * (CHANCEMAX - CHANCEMIN + 1) + CHANCEMIN) + bonusChance;
		this.portee = portee;
		this.mouvement = mouvement;
		this.mouvementBase = mouvement;
		this.c = c;
		this.special = 0;
		this.tourspecial = 0;
		orientation = Orientation.SUD;

		c.getPlateau().get(c).setPion(this);
		listeDeplacementPossible = new ArrayList<Noeud>();
		deplacement = new ArrayList<Case>();
		listeAttaquePossible = new ArrayList<Case>();
		listeAttaqueAire = new ArrayList<Case>();
		degatsCombat = new ArrayList<Integer>();
		listeConquetePossible = new ArrayList<Case>();

	}

	/**
	 * Deplace le pion en c1.
	 * @param c1
	 * Pre-Cond : Le deplacement du pion de c à c1 est possible.
	 * Post-Cond : Le pion se trouve en c1 avec l'orientation obtenue
	 * lors de son deplacement. Et son nombre de deplacement a diminué
	 * d'un certain nombre.
	 */
	public void deplacerPion(Case c1) {
		double mouvementDepense = 0;
		for (Noeud n1 : listeDeplacementPossible) {
			if (n1.getC().compare(c1)) {
				mouvementDepense = n1.getCout();
				break;
			}
		}
		mouvement -= mouvementDepense / 2;

		c.setPion(null);
		c1.setPion(this);
		c = c1;

		listeAttaquePossible.clear();
		listeDeplacementPossible.clear();
		listeAttaqueAire.clear();
		deplacement.clear();
	}

	/**
	 * Pre-Cond : Le pion est sur une case comportant un teleporteur (c).
	 * Post-Cond : Le pion se trouve sur une case comportant un teleporteur (c1).
	 * Les teleporteurs utilisés doivent appartenir au joueur possedant le pion.
	 * Cette verifiaction est faite avant l'appel à la fonction.
	 * @param c1
	 */
	public void deplacerPionTeleportation(Case c1) {
		c.setPion(null);
		c1.setPion(this);
		c = c1;
		listeAttaquePossible.clear();
		listeDeplacementPossible.clear();
		listeAttaqueAire.clear();
		deplacement.clear();
	}

	/**
	 * Permet d'attaquer un pion. La list degatsCombat est necessaire pour afficher
	 * les degats infligés dans l'animation.
	 * Pre-Cond : Le pion p est non null. Le pion p est dans la portee du pion attaquant.
	 * Post-Cond : Les 2 pions peuvent avoir perdu de la vie. Ce n'est pas toujours
	 * le cas.
	 * @param p 
	 */
	public void attaquerPion(Pion p) {
		degatsCombat.clear();
		p.degatsCombat.clear();
		attaquerPion(p, TAUXRIPOSTE);
	}

	/**
	 * Cette methode est appelé par attaquerPion(Pion).
	 * Elle prend en compte un taux de riposte decroissant à chaque riposte.
	 * Le pion p est le pion qui va recevoir des degats.
	 * Cette methode va calculer le deroulement du combat entre les 2 pions.
	 * Pre-Cond : Le pion p est non null.
	 * @param p
	 * @param tauxRiposte 
	 */
	private void attaquerPion(Pion p, int tauxRiposte) {
		double orient = dosCoteFace(p);
		int degatInflige = (int) ((force + (int) (force * janken(p))) + force * orient + force * cooperation());
		int seDefend = p.seDefendre(p);
		int hit = hit();
		int esquive = esquiveEnnemi(p);
		int aleaEsquive = (int) (Math.random() * (101));
		int aleaRiposte = (int) (Math.random() * (101));
		int aleaCoupCritique = (int) (Math.random() * (101));
		int coupReel;
		if (esquive < 100 && aleaEsquive < (hit - esquive)) {
			if (aleaCoupCritique < coupCritiques()) {
				p.recevoirDegat(coupReel = (int) (1.7 * degatInflige - seDefend));
			} else if (degatInflige > seDefend) {
				p.recevoirDegat(coupReel = (degatInflige - seDefend));
			} else {
				p.recevoirDegat(coupReel = 1);
			}
			degatsCombat.add(new Integer((int) (coupReel)));
			if (estVivant(p)) {
				p.attaque();
				if (p.getListeAttaquePossible().contains(this.c)) {
					if (aleaRiposte < tauxRiposte) {
						p.attaquerPion(this, tauxRiposte / 2);
					}
				}
			}
		}
	}

	/**
	 * Methode appelé lorsque qu'un pion doit recevoir des degats.
	 * Pre-Cond : Le pion a une vie égal à x.
	 * Pre-Cond : Le pion p est non null.
	 * Post-Cond : Le pion a une vite égal à x - degatInflige
	 * @param degatInflige 
	 */
	private void recevoirDegat(int degatInflige) {
		vie -= degatInflige;
	}

	/**
	 * Permet de calculer la defense du pion p
	 * Pre-Cond : Le pion p est non null.
	 * @param p
	 * @return la defense du pion p
	 */
	private int seDefendre(Pion p) {
		return defense + (int) (defense * cooperation());
	}

	/**
	 * Permet de calculer les bonus/malus en fonction de la famille du pion
	 * Pre-Cond : Le pion p est non null.
	 * @param p
	 * @return un pourcentage
	 */
	protected abstract double janken(Pion p);

	/**
	 * Permet de calculer le pourcentage en bonus d'attaque obtenu en fonction
	 * de l'orientation.
	 * Pre-Cond : Le pion p est non null.
	 * @param p
	 * @return un pourcentage
	 */
	private double dosCoteFace(Pion p) {
		if (orientation.equals(p.getOrientation())) {
			return 1/2;
		} else if (orientation.equalsOp(p.getOrientation())) {
			return 0;
		}
		return 1/3;
	}

	/**
	 * Permet de calculer la chance de toucher un pion
	 * @return entier
	 */
	private int hit() {
		return precision  * 4;
	}

	/**
	 * Permet de calculer l'esquive du pion p qui est attaqué.
	 * Pre-Cond : Le pion p est non null.
	 * @param p
	 * @return l'esquive du pion p
	 */
	private int esquiveEnnemi(Pion p) {
		return ((int) (p.vitesse * 1.5) + p.chance) + (int) (p.cooperation() * ((int) (p.vitesse * 1.5) + p.chance) / 4)
				+ (int) (janken(p) * ((int) (p.vitesse * 1.5) + p.chance) / 2);
	}

	/**
	 * Calcul la chance d'infliger un coup Critique
	 * @return
	 */
	protected float coupCritiques() {
		return precision * 4 * vitesse / 150;
	}

	/**
	 * Return un bonus en pourcentage suivant le nombre de pion à coté. Ces pions
	 * doivent appartenir au même joueur.
	 * @return un pourcentage
	 */
	private double cooperation() {
		double resultat = 0;
		if (c.getPlateau().get(c.getLigne() + 1, c.getColonne()) != null
				&& c.getPlateau().get(c.getLigne() + 1, c.getColonne()).getPion() != null 
				&& c.getPlateau().get(c.getLigne() + 1, c.getColonne()).getPion().getJoueur() == joueur) {
			resultat += 1;
		}
		if (c.getPlateau().get(c.getLigne() - 1, c.getColonne()) != null
				&& c.getPlateau().get(c.getLigne() - 1, c.getColonne()).getPion() != null 
				&& c.getPlateau().get(c.getLigne() - 1, c.getColonne()).getPion().getJoueur() == joueur) {
			resultat += 1;
		}
		if (c.getPlateau().get(c.getLigne(), c.getColonne() - 1) != null
				&& c.getPlateau().get(c.getLigne(), c.getColonne() - 1).getPion() != null 
				&& c.getPlateau().get(c.getLigne(), c.getColonne() - 1).getPion().getJoueur() == joueur) {
			resultat += 1;
		}
		if (c.getPlateau().get(c.getLigne(), c.getColonne() + 1) != null
				&& c.getPlateau().get(c.getLigne(), c.getColonne() + 1).getPion() != null 
				&& c.getPlateau().get(c.getLigne(), c.getColonne() + 1).getPion().getJoueur() == joueur) {
			resultat += 1;
		}
		return resultat / 8;
	}

	/**
	 * Permet de connaitre ke nom du pion
	 * @return le nom du pion
	 */
	public abstract String getNom();

	/**
	 * Permet d'appeller une fonction qui effectura la capacité spéciale
	 */
	public abstract void capaciteSpeciale();

	/**
	 * Permet de savoir si la capacité spéciale est disponible
	 */
	public abstract void specialIndispo();

	/**
	 * Permet de modifier la variable special. 
	 * @param special
	 */
	public void setSpecial(int special) {
		this.special = special;
	}

	@Override
	public String toString() {
		return getNom() + " (" + getClass().getSimpleName() + ", " + vie + "pv)";
	}

	/**
	 * 
	 * @return la case du pion
	 */
	public Case getCase() {
		return c;
	}

	/**
	 * 
	 * @return l'orientation du pion
	 */
	public Orientation getOrientation() {
		return orientation;
	}

	/**
	 * Permet de changer l'orientation du pion
	 * @param orientation
	 */
	public void setOrientation(Orientation orientation) {
		this.orientation = orientation;
	}

	/**
	 * Pre-Cond : c != null
	 * @param c
	 * @return vrai si c appartient au déplacement calculé du pion sinon faux
	 */
	public boolean deplacementPossible(Case c) {
		return getDeplacement().contains(c);
	}

	/**
	 * Methode qui va calculer tout les deplacements possible du pion depuis c.
	 * Ces deplacements sont stocké dans listeDeplacementPossible, une liste de noeud.
	 * Chaque noeud de cette liste connait le meilleur deplacement possible pour
	 * l'atteindre.
	 */
	public void calculDeplacementPossible() {
		listeDeplacementPossible.clear();
		deplacement.clear();
		ArrayList<Noeud> listeFerme = new ArrayList<Noeud>();
		ArrayList<Noeud> listeOuverte = new ArrayList<Noeud>();

		Noeud tmp;
		Noeud tmp2;
		Case caseVerif;
		Noeud noeudContenu = new Noeud(c, 0);
		tmp = new Noeud(c, 0);
		listeOuverte.add(tmp);
		listeDeplacementPossible.add(tmp);
		while (!listeOuverte.isEmpty()) {
			tmp = listeOuverte.remove(0);
			listeFerme.add(tmp);
			caseVerif = c.getPlateau().get(tmp.getC().getLigne() + 1, tmp.getC().getColonne());
			if (caseVerif != null) {
				tmp2 = new Noeud(caseVerif, tmp.getCout() + Terrain.effetDeplacement(caseVerif.getTypeTerrain()));
			} else {
				tmp2 = new Noeud(caseVerif, tmp.getCout());
			}
			recopierNoeudDansNoeud(tmp, tmp2);
			tmp2.getListeNoeud().add(tmp);
			if (caseVerif != null && contient(tmp2, listeDeplacementPossible, noeudContenu)) {
				if (tmp2.getCout() < noeudContenu.getCout()) {
					noeudContenu.setCout(tmp2.getCout());
					noeudContenu.setListeNoeud(tmp2.getListeNoeud());
				}
			} else if (caseVerif != null && !tmp2.getC().isObstacleDeplacement() && !tmp2.getC().contientPion() && !listeFerme.contains(tmp2)
					&& !listeOuverte.contains(tmp2) && tmp2.getCout() <= Terrain.CoutDefaut * mouvement) {
				listeDeplacementPossible.add(tmp2);
				listeOuverte.add(tmp2);
			}
			caseVerif = c.getPlateau().get(tmp.getC().getLigne() - 1, tmp.getC().getColonne());
			if (caseVerif != null) {
				tmp2 = new Noeud(caseVerif, tmp.getCout() + Terrain.effetDeplacement(caseVerif.getTypeTerrain()));
			} else {
				tmp2 = new Noeud(caseVerif, tmp.getCout());
			}
			recopierNoeudDansNoeud(tmp, tmp2);
			tmp2.getListeNoeud().add(tmp);
			if (caseVerif != null && contient(tmp2, listeDeplacementPossible, noeudContenu)) {
				if (tmp2.getCout() < noeudContenu.getCout()) {
					noeudContenu.setCout(tmp2.getCout());
					noeudContenu.setListeNoeud(tmp2.getListeNoeud());
				}
			} else if (caseVerif != null && !tmp2.getC().isObstacleDeplacement() && !tmp2.getC().contientPion() && !listeFerme.contains(tmp2)
					&& !listeOuverte.contains(tmp2) && tmp2.getCout() <= Terrain.CoutDefaut * mouvement) {
				listeDeplacementPossible.add(tmp2);
				listeOuverte.add(tmp2);
			}
			caseVerif = c.getPlateau().get(tmp.getC().getLigne(), tmp.getC().getColonne() + 1);
			if (caseVerif != null) {
				tmp2 = new Noeud(caseVerif, tmp.getCout() + Terrain.effetDeplacement(caseVerif.getTypeTerrain()));
			} else {
				tmp2 = new Noeud(caseVerif, tmp.getCout());
			}
			recopierNoeudDansNoeud(tmp, tmp2);
			tmp2.getListeNoeud().add(tmp);
			if (caseVerif != null && contient(tmp2, listeDeplacementPossible, noeudContenu)) {
				if (tmp2.getCout() < noeudContenu.getCout()) {
					noeudContenu.setCout(tmp2.getCout());
					noeudContenu.setListeNoeud(tmp2.getListeNoeud());
				}
			} else if (caseVerif != null && !tmp2.getC().isObstacleDeplacement() && !tmp2.getC().contientPion() && !listeFerme.contains(tmp2)
					&& !listeOuverte.contains(tmp2) && tmp2.getCout() <= Terrain.CoutDefaut * mouvement) {
				listeDeplacementPossible.add(tmp2);
				listeOuverte.add(tmp2);
			}
			caseVerif = c.getPlateau().get(tmp.getC().getLigne(), tmp.getC().getColonne() - 1);
			if (caseVerif != null) {
				tmp2 = new Noeud(caseVerif, tmp.getCout() + Terrain.effetDeplacement(caseVerif.getTypeTerrain()));
			} else {
				tmp2 = new Noeud(caseVerif, tmp.getCout());
			}
			recopierNoeudDansNoeud(tmp, tmp2);
			tmp2.getListeNoeud().add(tmp);
			if (caseVerif != null && contient(tmp2, listeDeplacementPossible, noeudContenu)) {
				if (tmp2.getCout() < noeudContenu.getCout()) {
					noeudContenu.setCout(tmp2.getCout());
					noeudContenu.setListeNoeud(tmp2.getListeNoeud());
				}
			} else if (caseVerif != null && !tmp2.getC().isObstacleDeplacement() && !tmp2.getC().contientPion() && !listeFerme.contains(tmp2)
					&& !listeOuverte.contains(tmp2) && tmp2.getCout() <= Terrain.CoutDefaut * mouvement) {
				listeDeplacementPossible.add(tmp2);
				listeOuverte.add(tmp2);
			}
		}
	}

	/**
	 * Cette methode calcule 2 choses :
	 *		- L'aire d'attaque du pion à partir de c et de sa portee
	 *			(listeAttaqueAire)
	 *		- Les elements attaquables (teleporteurs ennemis, pions ennemis et
	 *			obstacle destructible) compris dans cette aire
	 *			(listeAttaquePossible)
	 */
	public void attaque() {
		listeAttaquePossible.clear();
		listeAttaqueAire.clear();
		ArrayList<Case> listeFerme = new ArrayList<Case>();
		ArrayList<Case> listeOuverte = new ArrayList<Case>();

		Case tmp;
		Case caseVerif;

		tmp = c;
		listeOuverte.add(tmp);
		listeAttaquePossible.add(tmp);
		while (!listeOuverte.isEmpty()) {
			tmp = listeOuverte.remove(0);
			listeFerme.add(tmp);
			caseVerif = c.getPlateau().get(tmp.getLigne() + 1, tmp.getColonne());

			if (caseVerif != null && !caseVerif.isObstacleAttaque() && !listeFerme.contains(caseVerif)
					&& !listeOuverte.contains(caseVerif) && distanceManhattan(caseVerif) <= portee
					&& !joueur.getListeDePions().contains(caseVerif.getPion())) {
				if (caseVerif.getPion() != null || (caseVerif.getObstacle() != null && caseVerif.getObstacle().isDestructible())
						|| (caseVerif.getTeleporteur() != null && !joueur.getTeleporteur().contains(caseVerif.getTeleporteur()))) {
					listeAttaquePossible.add(caseVerif);
				}
				listeAttaqueAire.add(caseVerif);
				listeOuverte.add(caseVerif);
			}
			caseVerif = c.getPlateau().get(tmp.getLigne() - 1, tmp.getColonne());
			if (caseVerif != null && !caseVerif.isObstacleAttaque() && !listeFerme.contains(caseVerif)
					&& !listeOuverte.contains(caseVerif) && distanceManhattan(caseVerif) <= portee
					&& !joueur.getListeDePions().contains(caseVerif.getPion())) {
				if (caseVerif.getPion() != null || (caseVerif.getObstacle() != null && caseVerif.getObstacle().isDestructible())
						|| (caseVerif.getTeleporteur() != null && !joueur.getTeleporteur().contains(caseVerif.getTeleporteur()))) {
					listeAttaquePossible.add(caseVerif);
				}
				listeAttaqueAire.add(caseVerif);
				listeOuverte.add(caseVerif);
			}
			caseVerif = c.getPlateau().get(tmp.getLigne(), tmp.getColonne() + 1);
			if (caseVerif != null && !caseVerif.isObstacleAttaque() && !listeFerme.contains(caseVerif)
					&& !listeOuverte.contains(caseVerif) && distanceManhattan(caseVerif) <= portee
					&& !joueur.getListeDePions().contains(caseVerif.getPion())) {
				if (caseVerif.getPion() != null || (caseVerif.getObstacle() != null && caseVerif.getObstacle().isDestructible())
						|| (caseVerif.getTeleporteur() != null && !joueur.getTeleporteur().contains(caseVerif.getTeleporteur()))) {
					listeAttaquePossible.add(caseVerif);
				}
				listeAttaqueAire.add(caseVerif);
				listeOuverte.add(caseVerif);
			}
			caseVerif = c.getPlateau().get(tmp.getLigne(), tmp.getColonne() - 1);
			if (caseVerif != null && !caseVerif.isObstacleAttaque() && !listeFerme.contains(caseVerif)
					&& !listeOuverte.contains(caseVerif) && distanceManhattan(caseVerif) <= portee
					&& !joueur.getListeDePions().contains(caseVerif.getPion())) {
				if (caseVerif.getPion() != null || (caseVerif.getObstacle() != null && caseVerif.getObstacle().isDestructible())
						|| (caseVerif.getTeleporteur() != null && !joueur.getTeleporteur().contains(caseVerif.getTeleporteur()))) {
					listeAttaquePossible.add(caseVerif);
				}
				listeAttaqueAire.add(caseVerif);
				listeOuverte.add(caseVerif);
			}
		}
		listeAttaquePossible.remove(c);
	}

	/**
	 * Cette methode ne peut etre appelé que par un commandant.
	 * Elle permet de calculer les chateaux a portés.
	 */
	public void conquerir() {
		listeConquetePossible.clear();
		ArrayList<Case> listeFerme = new ArrayList<Case>();
		ArrayList<Case> listeOuverte = new ArrayList<Case>();

		Case tmp;
		Case caseVerif;

		tmp = c;
		listeOuverte.add(tmp);
		while (!listeOuverte.isEmpty()) {
			tmp = listeOuverte.remove(0);
			listeFerme.add(tmp);
			caseVerif = c.getPlateau().get(tmp.getLigne() + 1, tmp.getColonne());

			if (caseVerif != null && !listeFerme.contains(caseVerif)
					&& !listeOuverte.contains(caseVerif) && distanceManhattan(caseVerif) <= portee) {
				if (caseVerif.getObstacle() != null && caseVerif.getObstacle().isChateau() && !joueur.chateauPresent(caseVerif) && !((Chateau) caseVerif.getObstacle()).isConquis()) {
					listeConquetePossible.add(caseVerif);
				}
				listeOuverte.add(caseVerif);
			}
			caseVerif = c.getPlateau().get(tmp.getLigne() - 1, tmp.getColonne());
			if (caseVerif != null && !listeFerme.contains(caseVerif)
					&& !listeOuverte.contains(caseVerif) && distanceManhattan(caseVerif) <= portee) {
				if (caseVerif.getObstacle() != null && caseVerif.getObstacle().isChateau() && !joueur.chateauPresent(caseVerif) && !((Chateau) caseVerif.getObstacle()).isConquis()) {
					listeConquetePossible.add(caseVerif);
				}
				listeOuverte.add(caseVerif);
			}
			caseVerif = c.getPlateau().get(tmp.getLigne(), tmp.getColonne() + 1);
			if (caseVerif != null && !listeFerme.contains(caseVerif)
					&& !listeOuverte.contains(caseVerif) && distanceManhattan(caseVerif) <= portee) {
				if (caseVerif.getObstacle() != null && caseVerif.getObstacle().isChateau() && !joueur.chateauPresent(caseVerif) && !((Chateau) caseVerif.getObstacle()).isConquis()) {
					listeConquetePossible.add(caseVerif);
				}
				listeOuverte.add(caseVerif);
			}
			caseVerif = c.getPlateau().get(tmp.getLigne(), tmp.getColonne() - 1);
			if (caseVerif != null && !listeFerme.contains(caseVerif)
					&& !listeOuverte.contains(caseVerif) && distanceManhattan(caseVerif) <= portee) {
				if (caseVerif.getObstacle() != null && caseVerif.getObstacle().isChateau() && !joueur.chateauPresent(caseVerif) && !((Chateau) caseVerif.getObstacle()).isConquis()) {
					listeConquetePossible.add(caseVerif);
				}
				listeOuverte.add(caseVerif);
			}
		}
	}

	/**
	 * Retourne la distance de Manhattan entre c et c1.
	 * Pre-Cond : c1 != null
	 * @param c1
	 * @return
	 */
	public int distanceManhattan(Case c1) {
		return Math.abs(c1.getLigne() - c.getLigne()) + Math.abs(c1.getColonne() - c.getColonne());
	}

	/**
	 * Methode permettant de verifier si un la case du noeud n est présent dans la liste de
	 * noeud ln. Si c'est le cas, on retourne vrai et n2 est le noeud de ln contenant c.
	 * Si ce n'est pas le cas, n2 n'est pas pertinent.
	 * Pre-Cond : n, ln et n2 != null
	 * @param n
	 * @param ln
	 * @return
	 */
	protected boolean contient(Noeud n, ArrayList<Noeud> ln, Noeud n2) {
		for (Noeud n1 : ln) {
			if (n1.getC().compare(n.getC())) {
				n2 = n1;
				return true;
			}
		}
		return false;
	}

	/**
	 * Permet de recopier la liste de noeud contenu dans n1 dans la liste de n2.
	 * Pre-Cond : n1 et n2 != null
	 * @param n1
	 * @param n2
	 */
	protected void recopierNoeudDansNoeud(Noeud n1, Noeud n2) {
		for (Noeud nk : n1.getListeNoeud()) {
			n2.getListeNoeud().add(nk);
		}
	}

	/**
	 * Recupère le deplacement entre c et c2 depuis listeDeplacementPossible et
	 * le stock dans deplacement
	 * Pre-Cond : c2 != 2
	 * @param c2
	 */
	public void afficherDeplacement(Case c2) {
		deplacement.clear();
		for (Noeud n1 : listeDeplacementPossible) {
			if (n1.getC().compare(c2)) {
				for (Noeud n2 : n1.getListeNoeud()) {
					deplacement.add(n2.getC());
				}
				deplacement.add(c2);
			}
		}
	}

	/**
	 * Modifier la variable commandant
	 * @param commandant
	 */
	public void setCommandant(boolean commandant) {
		this.commandant = commandant;
	}

	/**
	 * Retourn la variable deplacement
	 * @return
	 */
	public ArrayList<Case> getDeplacement() {
		return deplacement;
	}

	/**
	 * Retourne la variable listeAttaquePossible
	 * @return
	 */
	public ArrayList<Case> getListeAttaquePossible() {
		return listeAttaquePossible;
	}

	/**
	 * Retourn la variable listeAttaqueAire
	 * @return
	 */
	public ArrayList<Case> getListeAttaqueAire() {
		return listeAttaqueAire;
	}

	/**
	 * Retourne la variable vie
	 * @return
	 */
	public int getVieRestante() {
		return vie;
	}

	/**
	 * Pre-Cond :  pion != null
	 * Post-Cond : pion = null, vie a été augmenté, pion a été enlevé de
	 * la liste de pion de son joueur et pion est enlevé de sa case.
	 * @param pion
	 * @return
	 */
	public boolean tuer(Pion pion) {
		boolean tmp = pion.isCommandant();
		vie += BONUSKILL;
		pion.meurt();
		pion = null;
		return tmp;
	}

	/**
	 * Post-Cond : le pion a été enlevé de la liste de son joueur.
	 * Si c'est un tacticien il est enlevé.
	 * Si c'est un commandant, il est enlevé.
	 * Le pion est enlevé de sa case
	 */
	public void meurt() {
		if (this == joueur.getTacticien()) {
			joueur.setTacticien(null);
		}
		if (this == joueur.getCommandant()) {
			joueur.setCommandant(null);
		}
		c.setPion(null);
		joueur.enleverPion(this);
	}

	/**
	 * Verifie si le pion est encore vivant
	 * Pre-Cond : p != null
	 * @param p
	 * @return vrai si p.vie > 0
	 */
	public boolean estVivant(Pion p) {
		return p.vie > 0;
	}

	/**
	 * Verifie si il est vivant
	 * @return vie > 0
	 */
	public boolean estVivant() {
		return vie > 0;
	}

	/**
	 * Modifie la valeur de tourspecial
	 * @param tourspecial
	 */
	public void setTourspecial(int tourspecial) {
		this.tourspecial = tourspecial;
	}

	/**
	 * Retourne la valeur de tourspecial
	 * @return
	 */
	public int getTourspecial() {
		return tourspecial;
	}

	/**
	 * Reinitialise le nombre de mouvement et appelle recuperationCapacite
	 */
	public void finDeTour() {
		mouvement = mouvementBase;
		recuperationCapacite();
	}

	/**
	 * Diminue de 1 special si il est > 0
	 */
	public void recuperationCapacite() {
		if (special > 0) {
			special--;
		}
	}

	/**
	 * Retourne le nombre de mouvement disponible pour le pion au debut de tour.
	 * @return
	 */
	public int getMouvementBase() {
		return mouvementBase;
	}

	/**
	 * Modifie la variable mouvement
	 * @param mouvement
	 */
	public void setMouvement(int mouvement) {
		this.mouvement = mouvement;
	}

	/**
	 * Retourne joueur
	 * @return
	 */
	public Joueur getJoueur() {
		return joueur;
	}

	/**
	 * Modifie la variable joueur
	 * @param joueur
	 */
	public void setJoueur(Joueur joueur) {
		this.joueur = joueur;
	}

	/**
	 * Permet de savoir si une capacite est utilisable ou non
	 * @return
	 */
	public boolean capaciteActive() {
		return special == 0;
	}

	/**
	 * Retourne le nom de la capcite speciale
	 * @return
	 */
	public String getNomCapacite() {
		return nomCapaciteSpeciale;
	}

	/**
	 * Pre-Cond : c != null et c contient un obstacle destructible.
	 * Post-Cond : l'obstacle destructible de c à perdu de la vie,
	 * si cette vie est inferieur à 0 il est supprimé de la case c.
	 * @param c
	 */
	public void attaquerObstacle(Case c) {
		((Destructible) c.getObstacle()).setVie(force / 2);
		if (((Destructible) c.getObstacle()).getVie() < 0) {
			c.supprimerObstacle();
		}
	}

	/**
	 * Pre-Cond : c != null et c contient un teleporteur.
	 * Post-Cond : le teleporteur dans c a perdu de la vie, peut avoir disparu.
	 * @param c
	 */
	public void attaquerTeleporteur(Case c) {
		c.getTeleporteur().diminuerVie(force / 2);
	}

	/**
	 * Retourne listeConquetePossible
	 * @return
	 */
	public ArrayList<Case> getListeConquetePossible() {
		return listeConquetePossible;
	}

	/**
	 * Permet de savoir si une conquete est possible
	 * @return
	 */
	public boolean conquetePossible() {
		conquerir();
		return !listeConquetePossible.isEmpty();
	}

	/**
	 * Retourne une image suivant l'entier i qui correspond au mouvement en cours
	 * @param i
	 * @return
	 */
	public abstract BufferedImage getImage(int i);

	/**
	 * Retourne une image avec i = 1 de getImage(i)
	 * @return
	 */
	public abstract BufferedImage getImage();

	/**
	 * Retourne la variable commandant
	 * @return
	 */
	public boolean isCommandant() {
		return commandant;
	}

	/**
	 * Lors de la mort subite le pion perd 2pv à chaque tour
	 */
	public void mortSubite(){
		vie -=2;
	}

	
	/**
	 * Retourne le numero de classe
	 * @return
	 */
	public abstract int getNumClasse();

}
