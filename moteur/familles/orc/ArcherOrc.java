package moteur.familles.orc;

import moteur.Case;
import moteur.classes.Archer;

/**
 *
 * @author vernagaa
 */
public class ArcherOrc extends Archer implements Orc {

	public ArcherOrc(int vie, int armure, int attaque, int precision, int esquive, int coupCritique, int depl, Case c) {
		super(vie, armure, attaque, precision, esquive, coupCritique, depl, c);
	}
	
}
