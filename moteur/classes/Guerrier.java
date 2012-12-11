package moteur.classes;

import java.awt.Point;
import moteur.Case;
import moteur.FabriquePion;
import moteur.Pion;

/**
 *
 * @author vernagaa
 */
public abstract class Guerrier extends Pion {

	/**
	 * Constante de vie d'un guerrier
	 */
	public final static int VIE = 82;
	/**
	 * Constante de force d'un guerrier
	 */
	public final static int FORCE = 41;
	/**
	 * Constante de précision d'un guerrier
	 */
	public final static int PRECISION = 34;
	/**
	 * Constante de vitesse d'un guerrier
	 */
	public final static int VITESSE = 23;
	/**
	 * Constante de défense d'un guerrier
	 */
	public final static int DEFENSE = 28;
	/**
	 * Constante de portée d'un guerrier
	 */
	public final static int PORTEE = 1;
	/**
	 * Constante de mouvement d'un guerrier
	 */
	public final static int MOUVEMENT = 4;

	/**
	 * Constructeur du guerrier
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
	public Guerrier(int vie, int force, int precision, int vitesse, int defense, int bonusChance, int portee, int mouvement, Case c) {
		super(vie + VIE, force + FORCE, precision + PRECISION, vitesse + VITESSE, defense + DEFENSE, bonusChance, portee + PORTEE, mouvement + MOUVEMENT, c);
	}

	/**
	 * Guerrier vulnérable contre les assassin
	 * @param p
	 * @return 
	 * pré-cond p != null
	 */
	@Override
	protected double janken(Pion p) {
		if (p.getNumClasse() == FabriquePion.ASSASSIN) {
			return 0.2;
		}
		return 0;
	}

	@Override
	public int getNumClasse() {
		return FabriquePion.GUERRIER;
	}
}
