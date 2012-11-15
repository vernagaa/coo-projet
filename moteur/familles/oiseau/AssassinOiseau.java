package moteur.familles.oiseau;

import moteur.Case;
import moteur.classes.Assassin;

/**
 *
 * @author Kévin
 */
public class AssassinOiseau extends Assassin implements Oiseau {

	public AssassinOiseau(Case c) {
		super(vieAssassin, forceAssassin, precisionAssassin, vitesseAssassin, defenseAssassin, chanceAssassin, porteeAssassin, mouvementAssassin, c);
	}

	@Override
	public String getNom() {
		return nomAssassin;
	}
}