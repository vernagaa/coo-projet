package moteur.classes;

import moteur.Case;
import moteur.Pion;

/**
 *
 * @author Kévin
 */
public abstract class Tacticien extends Pion {
	
	public final static int VIE = 22;
	public final static int FORCE = 54;
	public final static int PRECISION = 33;
	public final static int VITESSE = 22;
	public final static int DEFENSE = 20;
	public final static int PORTEE = 1;
	public final static int MOUVEMENT = 3;
	
	public Tacticien(int vie, int force, int precision, int vitesse, int defense, int bonusChance, int portee, int mouvement, Case c) {
		super(vie + VIE, force + FORCE, precision + PRECISION, vitesse + VITESSE, defense + DEFENSE, bonusChance, portee + PORTEE, mouvement + MOUVEMENT, c);
	}
	
	@Override
	protected float janken(Pion p) {
		return 0;
	}

}