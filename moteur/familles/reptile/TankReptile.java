package moteur.familles.reptile;

import java.awt.image.BufferedImage;
import moteur.Case;
import moteur.Textures;
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

	@Override
	public BufferedImage getImage() {
		return Textures.getPersonnage(Textures.TANKREPTILE, orientation);
	}

	
}
