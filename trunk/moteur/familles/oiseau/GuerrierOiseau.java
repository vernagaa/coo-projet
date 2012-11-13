package moteur.familles.oiseau;

import moteur.Case;
import moteur.classes.Guerrier;

/**
 *
 * @author vernagaa
 */
public class GuerrierOiseau extends Guerrier implements Oiseau {
	
	public GuerrierOiseau(Case c) {
		super(vieGuerrier, forceGuerrier, precisionGuerrier, vitesseGuerrier, defenseGuerrier, porteeGuerrier, mouvementGuerrier, c);
	}

	@Override
	public String getNom() {
		return nomGuerrier;
	}
}
