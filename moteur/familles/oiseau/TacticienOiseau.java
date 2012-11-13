/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package moteur.familles.oiseau;

import moteur.Case;
import moteur.Pion;
import moteur.classes.Tacticien;

/**
 *
 * @author Kévin
 */
public final class TacticienOiseau extends Tacticien implements Oiseau {

	public TacticienOiseau(Case c) {
		super(vieTacticien, forceTacticien, precisionTacticien, vitesseTacticien, defenseTacticien, porteeTacticien, mouvementTacticien, c);
	}

	@Override
	public String getNom() {
		return nomTacticien;
	}

	@Override
	protected float janken(Pion p) {
		return 0;
	}
}