package moteur.classes;

import java.awt.Point;
import moteur.Case;
import moteur.FabriquePion;
import moteur.Pion;

/**
 *
 * @author vernagaa
 */
public abstract class Archer extends Pion {

	/**
	 * Constante de vie d'un archer
	 */
	public final static int VIE = 76;
	/**
	 * Constante de force d'un archer
	 */
	public final static int FORCE = 36;
	/**
	 * Constante de precision d'un archer
	 */
	public final static int PRECISION = 41;
	/**
	 * Constante de vitesse d'un archer
	 */
	public final static int VITESSE = 24;
	/**
	 * Constante de défense d'un archer
	 */
	public final static int DEFENSE = 26;
	/**
	 * Constante de portée d'un archer
	 */
	public final static int PORTEE = 5;
	/**
	 * Constante de mouvement d'un archer
	 */
	public final static int MOUVEMENT = 3;

	/**
	 * Constructeur d'un archer
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
	public Archer(int vie, int force, int precision, int vitesse, int defense, int bonusChance, int portee, int mouvement, Case c) {
		super(vie + VIE, force + FORCE, precision + PRECISION, vitesse + VITESSE, defense + DEFENSE, bonusChance, portee + PORTEE, mouvement + MOUVEMENT, c);
	}

	/**
	 * Archer hors catégorie pas de force ni de faiblesse
	 * @param p
	 * @return 
	 */
	@Override
	protected double janken(Pion p) {
		return 0;
	}

	@Override
	public int getNumClasse() {
		return FabriquePion.ARCHER;
	}
}
