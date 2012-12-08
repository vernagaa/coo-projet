package moteur.classes;

import java.awt.Point;
import moteur.Case;
import moteur.Pion;

/**
 *
 * @author Kévin
 */
public abstract class Assassin extends Pion {

	public final static int VIE = 30;
	public final static int FORCE = 39;
	public final static int PRECISION = 38;
	public final static int VITESSE = 29;
	public final static int DEFENSE = 25;
	public final static int PORTEE = 1;
	public final static int MOUVEMENT = 7;

	public Assassin(int vie, int force, int precision, int vitesse, int defense, int bonusChance, int portee, int mouvement, Case c) {
		super(vie + VIE, force + FORCE, precision + PRECISION, vitesse + VITESSE, defense + DEFENSE, bonusChance, portee + PORTEE, mouvement + MOUVEMENT, c);
		id = new Point(1, 0);

	}

	@Override
	protected float janken(Pion p) {
		if (p instanceof Tank) {
			return 20 / 100;
		}
		return 0;
	}
}
