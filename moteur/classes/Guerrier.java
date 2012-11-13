package moteur.classes;

import moteur.Case;
import moteur.Pion;

/**
 *
 * @author vernagaa
 */
public abstract class Guerrier extends Pion {
	
	public final static int VIE = 41;
	public final static int FORCE = 41;
	public final static int PRECISION = 34;
	public final static int VITESSE = 23;
	public final static int DEFENSE = 29;
	public final static int PORTEE = 4;
	public final static int MOUVEMENT = 1;
	
	public Guerrier(int vie, int force, int precision, int vitesse, int defense, int portee, int mouvement, Case c) {
		super(vie + VIE, force + FORCE, precision + PRECISION, vitesse + VITESSE, defense + DEFENSE, portee + PORTEE, mouvement + MOUVEMENT, c);
	}

}
