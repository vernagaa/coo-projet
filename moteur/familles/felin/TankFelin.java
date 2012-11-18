package moteur.familles.felin;

import java.awt.image.BufferedImage;
import moteur.Case;
import moteur.Textures;
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

	@Override
	public BufferedImage getImage() {
		return Textures.getPersonnage(Textures.TANKFELIN, orientation);
	}

}
