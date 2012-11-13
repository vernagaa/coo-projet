/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package moteur.familles.oiseau;

import moteur.Case;
import moteur.Pion;
import moteur.classes.Guerrier;
import moteur.classes.Tank;

/**
 *
 * @author Kévin
 */
public final class TankOiseau extends Tank implements Oiseau {

	public TankOiseau(Case c) {
		super(vieTank, forceTank, precisionTank, vitesseTank, defenseTank, porteeTank, mouvementTank, c);
	}

	@Override
	public String getNom() {
		return nomTank;
	}

	@Override
	protected float janken(Pion p) {
		if (p instanceof Guerrier) {
			return 20 / 100;
		}
		return 0;
	}
}
