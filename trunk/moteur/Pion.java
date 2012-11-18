package moteur;

import java.awt.image.BufferedImage;

/**
 *
 * @author Kévin
 */
public abstract class Pion {

	/*
	 * La partie Statistique
	 */
	private static final int CHANCEMIN = 20;
	private static final int CHANCEMAX = 30;
	private static final int TAUXRIPOSTE = 95;
	
	protected int vie;
	protected int force;
	protected int precision;
	protected int vitesse;
	protected int defense;
	protected int chance;
	protected int portee;
	protected int mouvement;
	protected Orientation orientation;
	
	private Case c;

	public Pion(int vie, int force, int precision, int vitesse, int defense, int bonusChance, int portee, int mouvement, Case c) {
		this.vie = vie;
		this.force = force;
		this.precision = precision;
		this.vitesse = vitesse;
		this.defense = defense;
		this.chance = (int) (Math.random() * (CHANCEMAX - CHANCEMIN + 1) + CHANCEMIN) + bonusChance;
		this.portee = portee;
		this.mouvement = mouvement;
		this.c = c;
		if (c.getColonne() < 5) {
			orientation = Orientation.OUEST;
		} else {
			orientation = Orientation.EST;
		}
		//TODO pour les tests
		orientation = Orientation.SUD;
	}

	public void deplacerPion(Case c) {
		//TODO déplacement
	}

	/**
	 *
	 */
	public void attaquerPion(Pion p) {
		attaquerPion(p, TAUXRIPOSTE);
	}

	private void attaquerPion(Pion p, int tauxRiposte) {
		float orient = dosCoteFace(p);
		int degatInflige = (int) ((force + (int) (force * janken(p))) * orient);
		int seDefend = p.seDefendre(p);
		int hit = hit();
		int esquive = esquiveEnnemi(p);
		int aleaEsquive = (int) (Math.random() * (201));
		int aleaRiposte = (int) (Math.random() * (201));
                System.out.println(aleaEsquive+" "+esquive);
		if (esquive < 100 && aleaEsquive > esquive) {
			if (degatInflige > seDefend) {
				System.out.println("J'attaque avec " + (degatInflige - seDefend));
				p.recevoirDegat(degatInflige - seDefend);
			} else {
				System.out.println("Attaque de 1");
				p.recevoirDegat(1);
			}

			if (aleaRiposte < tauxRiposte) {
				System.out.println("Il riposte");
				p.attaquerPion(this, tauxRiposte / 2);
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
				 * + (int) (p.getCase().getType.getBonus()*(p.vitesse * 1.5) + p.chance)/3)
				 */;
	}

	private float coupCritiques() {
		return precision * ((float) chance / 20);
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

	public abstract String getNom();

	@Override
	public String toString() {
		return getNom() + " possède " + vie + ".";
	}

	public Case getCase() {
		return c;
	}

	public Orientation getOrientation() {
		return orientation;
	}
	
	public abstract BufferedImage getImage();
}
