package moteur.familles.reptile;

import moteur.Case;
import moteur.classes.Archer;

/**
 *
 * @author vernagaa
 */
public final class ArcherReptile extends Archer implements Reptile {
	public ArcherReptile(Case c) {
		super(vieArcher, forceArcher, precisionArcher, vitesseArcher, defenseArcher, chanceArcher, porteeArcher, mouvementArcher, c);
	}

	@Override
	public String getNom() {
		return nomArcher;
	}
	
}
