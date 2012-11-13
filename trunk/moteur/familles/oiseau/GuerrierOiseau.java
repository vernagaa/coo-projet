package moteur.familles.oiseau;

import moteur.Case;
import moteur.Pion;
import moteur.classes.Assassin;
import moteur.classes.Guerrier;

/**
 *
 * @author vernagaa
 */
public final class GuerrierOiseau extends Guerrier implements Oiseau {

	public GuerrierOiseau(Case c) {
		super(vieGuerrier, forceGuerrier, precisionGuerrier, vitesseGuerrier, defenseGuerrier, porteeGuerrier, mouvementGuerrier, c);
	}

	@Override
	public String getNom() {
		return nomGuerrier;
	}

	@Override
	protected float janken(Pion p) {
		if (p instanceof Assassin) {
			return 20 / 100;
		}
		return 0;
	}
}
