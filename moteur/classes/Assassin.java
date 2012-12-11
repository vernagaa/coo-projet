package moteur.classes;

import java.awt.Point;
import moteur.Case;
import moteur.FabriquePion;
import moteur.Pion;

/**
 *
 * @author Kévin
 */
public abstract class Assassin extends Pion {

	/**
	 * Constante de vie d'un assassin
	 */
	public final static int VIE = 70;
	/**
	 * Constante de force d'un assassin
	 */
	public final static int FORCE = 39;
	/**
	 * Constante de précision d'un assassin
	 */
	public final static int PRECISION = 38;
	/**
	 * Constante de vitesse d'un assassin
	 */
	public final static int VITESSE = 29;
	/**
	 * Constante de défense d'un assassin
	 */
	public final static int DEFENSE = 25;
	/**
	 * Constante de portée d'un assassin
	 */
	public final static int PORTEE = 1;
	/**
	 * Constante de mouvement d'un assassin
	 */
	public final static int MOUVEMENT = 7;

	/**
	 * Constructeur de l'assassin
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
	public Assassin(int vie, int force, int precision, int vitesse, int defense, int bonusChance, int portee, int mouvement, Case c) {
		super(vie + VIE, force + FORCE, precision + PRECISION, vitesse + VITESSE, defense + DEFENSE, bonusChance, portee + PORTEE, mouvement + MOUVEMENT, c);
	}

	/**
	 * Assassin vulnérable contre les tank
	 * @param p
	 * @return
	 * pré-cond : p != null
	 */
	@Override
	protected double janken(Pion p) {
		if (p.getNumClasse() == FabriquePion.TANK) {
			return 0.2;
		}
		return 0;
	}

	@Override
	public int getNumClasse() {
		return FabriquePion.ASSASSIN;
	}
}
