package moteur.familles.orc;

import moteur.Case;
import moteur.classes.Archer;

/**
 *
 * @author vernagaa
 */
public class ArcherOrc extends Archer implements Orc {

	public ArcherOrc(Case c) {
		super(vieArcher, armureArcher, attaqueArcher, precisionArcher, esquiveArcher, coupCritiqueArcher, deplArcher, c);
	}
	
}
