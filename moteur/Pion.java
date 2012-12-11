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
	 * d'un 
	 */
	public void deplacerPion(Case c1) {
		mouvement -= deplacement.size() / 2;

		c.setPion(null);
		c1.setPion(this);
		c = c1;

		listeAttaquePossible.clear();
		listeDeplacementPossible.clear();
		listeAttaqueAire.clear();
		deplacement.clear();
	}

	/**
	 * 
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
	 *
	 * 
	 * @param p 
	 */
	public void attaquerPion(Pion p) {
		degatsCombat.clear();
		p.degatsCombat.clear();
		attaquerPion(p, TAUXRIPOSTE);
	}

	private void attaquerPion(Pion p, int tauxRiposte) {
		System.out.println(degatsCombat);
		float orient = dosCoteFace(p);
		int degatInflige = (int) ((force + (int) (force * janken(p))) * orient);
		int seDefend = p.seDefendre(p);
		int hit = hit();
		int esquive = esquiveEnnemi(p);
		int aleaEsquive = (int) (Math.random() * (101));
		int aleaRiposte = (int) (Math.random() * (101));
		int aleaCoupCritique = (int) (Math.random() * (101));
		int coupReel;
		System.out.println(this.toString() + " attaque " + p.toString());
		System.out.println("Il a %chance de toucher " + (hit - esquive));
		if (esquive < 100 && aleaEsquive < (hit - esquive)) {
			System.out.println("%chance coup critique " + coupCritiques());
			if (aleaCoupCritique < coupCritiques()) {
				System.out.println("Coup Critique");
				System.out.println("J'attaque avec " + (int) (1.2 * degatInflige - seDefend));
				p.recevoirDegat(coupReel = (int) (1.2 * degatInflige - seDefend));
			} else if (degatInflige > seDefend) {
				System.out.println("J'attaque avec " + (degatInflige - seDefend));
				p.recevoirDegat(coupReel = (degatInflige - seDefend));
			} else {
				System.out.println("Attaque de 1");
				p.recevoirDegat(coupReel = 1);
			}
			degatsCombat.add(new Integer((int) (coupReel)));
			if (estVivant(p)) {
				p.attaque();
				if (p.getListeAttaquePossible().contains(this.c)) {
					System.out.println("Riposte");
					if (aleaRiposte < tauxRiposte) {
						System.out.println("Il riposte");
						p.attaquerPion(this, tauxRiposte / 2);
					}
				}
			}
		} else {
			System.out.println("J'esquive");
		}
	}

	private void recevoirDegat(int degatInflige) {
		vie -= degatInflige;
	}

	private int seDefendre(Pion p) {
		return defense + (int) (defense * cooperation());
	}

	/*
	 * Les methodes suivantes sont destines a calculer les paramatres du combat.
	 */
	/**
	 * 
	 * @param p
	 * @return
	 */
	protected abstract float janken(Pion p);

	private float dosCoteFace(Pion p) {
		if (orientation.equals(p.getOrientation())) {
			return 15 / 10;
		} else if (orientation.equalsOp(p.getOrientation())) {
			return 1;
		}
		return 12 / 10;
	}

	private int hit() {
		return precision * 4;
	}

	private int esquiveEnnemi(Pion p) {
		return ((int) (p.vitesse * 1.5) + p.chance) + (int) (p.cooperation() * ((int) (p.vitesse * 1.5) + p.chance) / 4)
				+ (int) (janken(p) * ((int) (p.vitesse * 1.5) + p.chance) / 2) /*
				 * + (int) (p.getCase().getType.getBonus()*(p.vitesse * 1.5) +
				 * p.chance)/3)
				 */;
	}

	/**
	 * 
	 * @return
	 */
	protected float coupCritiques() {
		return precision * 4 * vitesse / 150;
	}

	private float cooperation() {
		int resultat = 0;
		if (c.getPlateau().get(c.getLigne() + 1, c.getColonne() + 1) != null) {
			resultat += 1;
		}
		if (c.getPlateau().get(c.getLigne() - 1, c.getColonne() + 1) != null) {
			resultat += 1;
		}
		if (c.getPlateau().get(c.getLigne() + 1, c.getColonne() - 1) != null) {
			resultat += 1;
		}
		if (c.getPlateau().get(c.getLigne() - 1, c.getColonne() - 1) != null) {
			resultat += 1;
		}
		return (resultat * 7) / 100;
	}

	/**
	 * 
	 * @return
	 */
	public abstract String getNom();

	/**
	 * 
	 */
	public abstract void capaciteSpeciale();

	/**
	 * 
	 */
	public abstract void specialIndispo();

	/**
	 * 
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
	 * @return
	 */
	public Case getCase() {
		return c;
	}

	/**
	 * 
	 * @return
	 */
	public Orientation getOrientation() {
		return orientation;
	}

	/**
	 * 
	 * @param orientation
	 */
	public void setOrientation(Orientation orientation) {
		this.orientation = orientation;
	}

	/**
	 * 
	 * @param c
	 * @return
	 */
	public boolean deplacementPossible(Case c) {
		return getDeplacement().contains(c);
	}

	/**
	 * 
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
			caseVerif = c.getPlateau().get(tmp.c.getLigne() + 1, tmp.c.getColonne());
			if (caseVerif != null) {
				tmp2 = new Noeud(caseVerif, tmp.cout + Terrain.effetDeplacement(caseVerif.getTypeTerrain()));
			} else {
				tmp2 = new Noeud(caseVerif, tmp.cout);
			}
			recopierNoeudDansNoeud(tmp, tmp2);
			tmp2.listeNoeud.add(tmp);
			if (caseVerif != null && contient(tmp2, listeDeplacementPossible, noeudContenu)) {
				if (tmp2.cout < noeudContenu.cout) {
					noeudContenu.cout = tmp2.cout;
					noeudContenu.listeNoeud = tmp2.listeNoeud;
				}
			} else if (caseVerif != null && !tmp2.c.isObstacleDeplacement() && !tmp2.c.contientPion() && !listeFerme.contains(tmp2)
					&& !listeOuverte.contains(tmp2) && tmp2.cout <= 2 * mouvement) {
				listeDeplacementPossible.add(tmp2);
				listeOuverte.add(tmp2);
			}
			caseVerif = c.getPlateau().get(tmp.c.getLigne() - 1, tmp.c.getColonne());
			if (caseVerif != null) {
				tmp2 = new Noeud(caseVerif, tmp.cout + Terrain.effetDeplacement(caseVerif.getTypeTerrain()));
			} else {
				tmp2 = new Noeud(caseVerif, tmp.cout);
			}
			recopierNoeudDansNoeud(tmp, tmp2);
			tmp2.listeNoeud.add(tmp);
			if (caseVerif != null && contient(tmp2, listeDeplacementPossible, noeudContenu)) {
				if (tmp2.cout < noeudContenu.cout) {
					noeudContenu.cout = tmp2.cout;
					noeudContenu.listeNoeud = tmp2.listeNoeud;
				}
			} else if (caseVerif != null && !tmp2.c.isObstacleDeplacement() && !tmp2.c.contientPion() && !listeFerme.contains(tmp2)
					&& !listeOuverte.contains(tmp2) && tmp2.cout <= 2 * mouvement) {
				listeDeplacementPossible.add(tmp2);
				listeOuverte.add(tmp2);
			}
			caseVerif = c.getPlateau().get(tmp.c.getLigne(), tmp.c.getColonne() + 1);
			if (caseVerif != null) {
				tmp2 = new Noeud(caseVerif, tmp.cout + Terrain.effetDeplacement(caseVerif.getTypeTerrain()));
			} else {
				tmp2 = new Noeud(caseVerif, tmp.cout);
			}
			recopierNoeudDansNoeud(tmp, tmp2);
			tmp2.listeNoeud.add(tmp);
			if (caseVerif != null && contient(tmp2, listeDeplacementPossible, noeudContenu)) {
				if (tmp2.cout < noeudContenu.cout) {
					noeudContenu.cout = tmp2.cout;
					noeudContenu.listeNoeud = tmp2.listeNoeud;
				}
			} else if (caseVerif != null && !tmp2.c.isObstacleDeplacement() && !tmp2.c.contientPion() && !listeFerme.contains(tmp2)
					&& !listeOuverte.contains(tmp2) && tmp2.cout <= 2 * mouvement) {
				listeDeplacementPossible.add(tmp2);
				listeOuverte.add(tmp2);
			}
			caseVerif = c.getPlateau().get(tmp.c.getLigne(), tmp.c.getColonne() - 1);
			if (caseVerif != null) {
				tmp2 = new Noeud(caseVerif, tmp.cout + Terrain.effetDeplacement(caseVerif.getTypeTerrain()));
			} else {
				tmp2 = new Noeud(caseVerif, tmp.cout);
			}
			recopierNoeudDansNoeud(tmp, tmp2);
			tmp2.listeNoeud.add(tmp);
			if (caseVerif != null && contient(tmp2, listeDeplacementPossible, noeudContenu)) {
				if (tmp2.cout < noeudContenu.cout) {
					noeudContenu.cout = tmp2.cout;
					noeudContenu.listeNoeud = tmp2.listeNoeud;
				}
			} else if (caseVerif != null && !tmp2.c.isObstacleDeplacement() && !tmp2.c.contientPion() && !listeFerme.contains(tmp2)
					&& !listeOuverte.contains(tmp2) && tmp2.cout <= 2 * mouvement) {
				listeDeplacementPossible.add(tmp2);
				listeOuverte.add(tmp2);
			}
		}
	}

	/**
	 * 
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
	 * 
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
	 * 
	 * @param c1
	 * @return
	 */
	protected int distanceManhattan(Case c1) {
		return Math.abs(c1.getLigne() - c.getLigne()) + Math.abs(c1.getColonne() - c.getColonne());
	}

	/**
	 * 
	 * @param n
	 * @param ln
	 * @return
	 */
	protected boolean contient(Noeud n, ArrayList<Noeud> ln, Noeud n2) {
		for (Noeud n1 : ln) {
			if (n1.c.compare(n.c)) {
				n2 = n1;
				return true;
			}
		}
		return false;
	}

	/**
	 * 
	 * @param n1
	 * @param n2
	 */
	protected void recopierNoeudDansNoeud(Noeud n1, Noeud n2) {
		for (Noeud nk : n1.listeNoeud) {
			n2.listeNoeud.add(nk);
		}
	}

	/**
	 * 
	 * @param c2
	 */
	public void afficherDeplacement(Case c2) {
		deplacement.clear();
		System.out.println(c2);
		for (Noeud n1 : listeDeplacementPossible) {
			if (n1.c.compare(c2)) {
				System.out.println(n1.listeNoeud);
				for (Noeud n2 : n1.listeNoeud) {
					deplacement.add(n2.c);
				}
				deplacement.add(c2);
			}
		}
	}

	/**
	 * 
	 * @param commandant
	 */
	public void setCommandant(boolean commandant) {
		this.commandant = commandant;
	}

	/**
	 * 
	 * @return
	 */
	public ArrayList<Case> getDeplacement() {
		return deplacement;
	}

	/**
	 * 
	 * @return
	 */
	public ArrayList<Case> getListeAttaquePossible() {
		return listeAttaquePossible;
	}

	/**
	 * 
	 * @return
	 */
	public ArrayList<Case> getListeAttaqueAire() {
		return listeAttaqueAire;
	}

	/**
	 * 
	 * @return
	 */
	public int getVieRestante() {
		return vie;
	}

	/**
	 * 
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
	 * 
	 */
	public void meurt() {
		//TODO Animation mort
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
	 * 
	 * @param p
	 * @return
	 */
	public boolean estVivant(Pion p) {
		return p.vie > 0;
	}

	/**
	 * 
	 * @return
	 */
	public boolean estVivant() {
		return vie > 0;
	}

	/**
	 * 
	 * @param tourspecial
	 */
	public void setTourspecial(int tourspecial) {
		this.tourspecial = tourspecial;
	}

	/**
	 * 
	 * @return
	 */
	public int getTourspecial() {
		return tourspecial;
	}

	/**
	 * 
	 */
	public void finDeTour() {
		mouvement = mouvementBase;
		recuperationCapacite();
	}

	/**
	 * 
	 */
	public void recuperationCapacite() {
		if (special > 0) {
			special--;
		}
	}

	/**
	 * 
	 * @return
	 */
	public int getMouvementBase() {
		return mouvementBase;
	}

	/**
	 * 
	 * @param mouvement
	 */
	public void setMouvement(int mouvement) {
		this.mouvement = mouvement;
	}

	/**
	 * 
	 * @return
	 */
	public Joueur getJoueur() {
		return joueur;
	}

	/**
	 * 
	 * @param joueur
	 */
	public void setJoueur(Joueur joueur) {
		this.joueur = joueur;
	}

	/**
	 * 
	 * @return
	 */
	public boolean capaciteActive() {
		return special == 0;
	}

	/**
	 * 
	 * @return
	 */
	public String getNomCapacite() {
		return nomCapaciteSpeciale;
	}

	/**
	 * 
	 * @param c
	 */
	public void attaquerObstacle(Case c) {
		((Destructible) c.getObstacle()).setVie(force / 2);
		if (((Destructible) c.getObstacle()).getVie() < 0) {
			c.supprimerObstacle();
		}
	}

	/**
	 * 
	 * @param c
	 */
	public void attaquerTeleporteur(Case c) {
		c.getTeleporteur().diminuerVie(force / 2);
	}

	/**
	 * 
	 * @return
	 */
	public ArrayList<Case> getListeConquetePossible() {
		return listeConquetePossible;
	}

	/**
	 * 
	 * @return
	 */
	public boolean conquetePossible() {
		conquerir();
		System.out.println(listeConquetePossible.size());
		return !listeConquetePossible.isEmpty();
	}

	/**
	 * 
	 * @param i
	 * @return
	 */
	public abstract BufferedImage getImageMouvement(int i);

	/**
	 * 
	 * @return
	 */
	public abstract BufferedImage getImage();

	/**
	 * 
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
	 * 
	 * @return
	 */
	public abstract int getNumClasse();

}
