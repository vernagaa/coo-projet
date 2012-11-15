package moteur.familles.oiseau;

import moteur.Case;
import moteur.classes.Tacticien;

/**
 *
 * @author Kévin
 */
public final class TacticienOiseau extends Tacticien implements Oiseau {

	public TacticienOiseau(Case c) {
		super(vieTacticien, forceTacticien, precisionTacticien, vitesseTacticien, defenseTacticien, chanceTacticien, porteeTacticien, mouvementTacticien, c);
	}

	@Override
	public String getNom() {
		return nomTacticien;
	}
}