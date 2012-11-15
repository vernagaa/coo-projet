package moteur.familles.reptile;

import moteur.Case;
import moteur.classes.Tank;

/**
 *
 * @author Kévin
 */
public final class TankReptile extends Tank implements Reptile {

	public TankReptile(Case c) {
		super(vieTank, forceTank, precisionTank, vitesseTank, defenseTank, chanceTank, porteeTank, mouvementTank, c);
	}

	@Override
	public String getNom() {
		return nomTank;
	}

	
}
