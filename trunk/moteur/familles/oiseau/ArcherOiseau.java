package moteur.familles.oiseau;

import moteur.Case;
import moteur.classes.Archer;

/**
 *
 * @author vernagaa
 */
public class ArcherOiseau extends Archer implements Oiseau {

	public ArcherOiseau(Case c) {
		super(vieArcher, forceArcher, precisionArcher, vitesseArcher, defenseArcher, porteeArcher, mouvementArcher, c);
	}

	@Override
	public String getNom() {
		return nomArcher;
	}

}
