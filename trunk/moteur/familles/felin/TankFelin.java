package moteur.familles.felin;

import moteur.Case;
import moteur.classes.Tank;

/**
 *
 * @author Kévin
 */
public final class TankFelin extends Tank implements Felin {

	public TankFelin(Case c) {
		super(vieTank, forceTank, precisionTank, vitesseTank, defenseTank, chanceTank, porteeTank, mouvementTank, c);
	}

	@Override
	public String getNom() {
		return nomTank;
	}

}
