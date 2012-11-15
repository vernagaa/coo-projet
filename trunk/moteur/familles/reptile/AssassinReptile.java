package moteur.familles.reptile;

import moteur.Case;
import moteur.classes.Assassin;

/**
 *
 * @author Kévin
 */
public class AssassinReptile extends Assassin implements Reptile {

	public AssassinReptile(Case c) {
		super(vieAssassin, forceAssassin, precisionAssassin, vitesseAssassin, defenseAssassin, chanceAssassin, porteeAssassin, mouvementAssassin, c);
	}

	@Override
	public String getNom() {
		return nomAssassin;
	}

}