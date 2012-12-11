package moteur.classes;

import java.awt.Point;
import moteur.Case;
import moteur.FabriquePion;
import moteur.Pion;

/**
 *
 * @author Kévin
 */
public abstract class Tank extends Pion {

	/**
	 * Constante de vie d'un tank
	 */
	public final static int VIE = 98;
	/**
	 * Constante de force d'un tank
	 */
	public final static int FORCE = 35;
	/**
	 * Constante de précision d'un tank
	 */
	public final static int PRECISION = 32;
	/**
	 * Constante de vitesse d'un tank
	 */
	public final static int VITESSE = 20;
	/**
	 * Constante de défense d'un tank
	 */
	public final static int DEFENSE = 32;
	/**
	 * Constante de portée d'un tank
	 */
	public final static int PORTEE = 1;
	/**
	 * Constante de mouvement d'un tank
	 */
	public final static int MOUVEMENT = 3;

	/**
	 * Constructeur d'un tank
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
	public Tank(int vie, int force, int precision, int vitesse, int defense, int bonusChance, int portee, int mouvement, Case c) {
		super(vie + VIE, force + FORCE, precision + PRECISION, vitesse + VITESSE, defense + DEFENSE, bonusChance, portee + PORTEE, mouvement + MOUVEMENT, c);
	}

	/**
	 * Le tank est vulnérable contre les guerriers
	 * @param p
	 * @return 
	 */
	@Override
	protected double janken(Pion p) {
		if (p.getNumClasse() == FabriquePion.GUERRIER) {
			return 0.2;
		}
		return 0;
	}

	@Override
	public int getNumClasse() {
		return FabriquePion.TANK;
	}
}