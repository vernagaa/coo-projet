package moteur.familles.orc;

import moteur.Case;
import moteur.classes.Archer;

/**
 *
 * @author vernagaa
 */
public class ArcherOiseau extends Archer implements Oiseau {

	public ArcherOiseau(Case c) {
		super(vieArcher, armureArcher, attaqueArcher, precisionArcher, esquiveArcher, coupCritiqueArcher, deplArcher, c);
	}
	
}
