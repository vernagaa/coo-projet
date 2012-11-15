package moteur.familles.felin;

import moteur.Case;
import moteur.classes.Guerrier;

/**
 *
 * @author vernagaa
 */
public final class GuerrierFelin extends Guerrier implements Felin {

	public GuerrierFelin(Case c) {
		super(vieGuerrier, forceGuerrier, precisionGuerrier, vitesseGuerrier, defenseGuerrier, chanceGuerrier, porteeGuerrier, mouvementGuerrier, c);
	}

	@Override
	public String getNom() {
		return nomGuerrier;
	}
}
