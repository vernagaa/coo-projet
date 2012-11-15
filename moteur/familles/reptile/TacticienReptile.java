package moteur.familles.reptile;

import moteur.Case;
import moteur.classes.Tacticien;

/**
 *
 * @author Kévin
 */
public final class TacticienReptile extends Tacticien implements Reptile {

	public TacticienReptile(Case c) {
		super(vieTacticien, forceTacticien, precisionTacticien, vitesseTacticien, defenseTacticien, chanceTacticien, porteeTacticien, mouvementTacticien, c);
	}

	@Override
	public String getNom() {
		return nomTacticien;
	}
}