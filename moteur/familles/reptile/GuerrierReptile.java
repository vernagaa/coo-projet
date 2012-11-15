package moteur.familles.reptile;

import moteur.Case;
import moteur.classes.Guerrier;

/**
 *
 * @author vernagaa
 */
public final class GuerrierReptile extends Guerrier implements Reptile {

	public GuerrierReptile(Case c) {
		super(vieGuerrier, forceGuerrier, precisionGuerrier, vitesseGuerrier, defenseGuerrier, chanceGuerrier, porteeGuerrier, mouvementGuerrier, c);
	}

	@Override
	public String getNom() {
		return nomGuerrier;
	}
}
