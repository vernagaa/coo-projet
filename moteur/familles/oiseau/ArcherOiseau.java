package moteur.familles.oiseau;

import moteur.Case;
import moteur.classes.Archer;

/**
 *
 * @author vernagaa
 */
public final class ArcherOiseau extends Archer implements Oiseau {
	public ArcherOiseau(Case c) {
		super(vieArcher, forceArcher, precisionArcher, vitesseArcher, defenseArcher, chanceArcher, porteeArcher, mouvementArcher, c);
	}

	@Override
	public String getNom() {
		return nomArcher;
	}

}
