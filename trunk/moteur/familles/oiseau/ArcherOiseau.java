package moteur.familles.oiseau;

import moteur.Case;
import moteur.Pion;
import moteur.classes.Archer;

/**
 *
 * @author vernagaa
 */
public final class ArcherOiseau extends Archer implements Oiseau {
	public ArcherOiseau(Case c) {
		super(vieArcher, forceArcher, precisionArcher, vitesseArcher, defenseArcher, porteeArcher, mouvementArcher, c);
	}

	@Override
	public String getNom() {
		return nomArcher;
	}
	
	@Override
	protected float janken(Pion p) {
		return 0;
	}

}
