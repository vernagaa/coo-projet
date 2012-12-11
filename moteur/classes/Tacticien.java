package moteur.classes;

import moteur.Case;
import moteur.FabriquePion;
import moteur.Pion;
import moteur.Teleporteur;

/**
 *
 * @author Kévin
 */
public abstract class Tacticien extends Pion {

	/**
	 * Constante de vie d'un tacticien
	 */
	public final static int VIE = 67;
	/**
	 * Constante de force d'un tacticien
	 */
	public final static int FORCE = 54;
	/**
	 * Constante de précision d'un tacticien
	 */
	public final static int PRECISION = 33;
	/**
	 * Constante de vitesse d'un tacticien
	 */
	public final static int VITESSE = 22;
	/**
	 * Constante de défense d'un tacticien
	 */
	public final static int DEFENSE = 21;
	/**
	 * Constante de portée d'un tacticien
	 */
	public final static int PORTEE = 1;
	/**
	 * Constante de mouvement d'un tacticien
	 */
	public final static int MOUVEMENT = 3;
	
	private int cooldownTeleporteur;

	/**
	 * Constructeur d'un tacticien
	 * @param vie
	 * @param force
	 * @param precision
	 * @param vitesse
	 * @param defense
	 * @param bonusChance
	 * @param portee
	 * @param mouvement
	 * @param c
	 */
	public Tacticien(int vie, int force, int precision, int vitesse, int defense, int bonusChance, int portee, int mouvement, Case c) {
		super(vie + VIE, force + FORCE, precision + PRECISION, vitesse + VITESSE, defense + DEFENSE, bonusChance, portee + PORTEE, mouvement + MOUVEMENT, c);
		cooldownTeleporteur = 0;
	}

	/**
	 * Tacticien est hors catégorie il n'a ni force ni faiblesse
	 * @param p
	 * @return 
	 */
	@Override
	protected double janken(Pion p) {
		return 0;
	}

	/**
	 * Pose un téléporteur si la case le permet
	 * pré-cond : c != null
	 * @param c
	 */
	public void poserTeleporteur(Case c) {
			if (distanceManhattan(c) == 1 && c.getPion() == null && c.getObstacle() == null && c.getTeleporteur() == null) {
				new Teleporteur(joueur, c);
				cooldownTeleporteur = 1;
			}
			//TODO Ajouter un cooldown à la pose?
	}

	/**
	 * décrémente le cooldown s'il est supérieur à 0
	 * pré-cond : cooldownTeleporteur >= 0
	 * post-cond : cooldownTeleporteur >= 0
	 */
	public void decrementerCDTeleporteur() {
		if (!telePorterDisponible()) {
			cooldownTeleporteur--;
		}
	}
	
	/**
	 * Retourne vrai si la pose d'un téléporteur est permise
	 * @return
	 */
	public boolean telePorterDisponible(){
		return cooldownTeleporteur == 0;
	}

	@Override
	public int getNumClasse() {
		return FabriquePion.TACTICIEN;
	}
}