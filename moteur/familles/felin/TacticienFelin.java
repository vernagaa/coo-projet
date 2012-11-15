package moteur.familles.felin;

import moteur.Case;
import moteur.classes.Tacticien;

/**
 *
 * @author Kévin
 */
public final class TacticienFelin extends Tacticien implements Felin {

	public TacticienFelin(Case c) {
		super(vieTacticien, forceTacticien, precisionTacticien, vitesseTacticien, defenseTacticien, chanceTacticien, porteeTacticien, mouvementTacticien, c);
	}

	@Override
	public String getNom() {
		return nomTacticien;
	}
}