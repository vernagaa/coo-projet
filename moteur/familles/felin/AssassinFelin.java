package moteur.familles.felin;

import moteur.Case;
import moteur.classes.Assassin;

/**
 *
 * @author Kévin
 */
public class AssassinFelin extends Assassin implements Felin {

	public AssassinFelin(Case c) {
		super(vieAssassin, forceAssassin, precisionAssassin, vitesseAssassin, defenseAssassin, chanceAssassin, porteeAssassin, mouvementAssassin, c);
	}

	@Override
	public String getNom() {
		return nomAssassin;
	}
}