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

	public final static int VIE = 36;
	public final static int FORCE = 38;
	public final static int PRECISION = 41;
	public final static int VITESSE = 24;
	public final static int DEFENSE = 26;
	public final static int PORTEE = 3;
	public final static int MOUVEMENT = 5;

	public Archer(int vie, int force, int precision, int vitesse, int defense, int bonusChance, int portee, int mouvement, Case c) {
		super(vie + VIE, force + FORCE, precision + PRECISION, vitesse + VITESSE, defense + DEFENSE, bonusChance, portee + PORTEE, mouvement + MOUVEMENT, c);
	}

	@Override
	protected float janken(Pion p) {
		return 0;
	}

	@Override
	public int getNumClasse() {
		return FabriquePion.ARCHER;
	}
}
