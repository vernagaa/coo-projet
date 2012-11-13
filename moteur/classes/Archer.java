package moteur.classes;

import moteur.Case;
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
	public final static int MOUVEMENT = 3;
	
	public Archer(int vie, int force, int precision, int vitesse, int defense, int portee, int mouvement, Case c) {
		super(vie + VIE, force + FORCE, precision + PRECISION, vitesse + VITESSE, defense + DEFENSE, portee + PORTEE, mouvement + MOUVEMENT, c);
	}

}