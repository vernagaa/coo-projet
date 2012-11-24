package moteur.familles.oiseau;

import java.awt.image.BufferedImage;
import moteur.Case;
import moteur.Textures;
import moteur.classes.Tank;

/**
 *
 * @author Kévin
 */
public final class TankOiseau extends Tank implements Oiseau {

	public TankOiseau(Case c) {
		super(vieTank, forceTank, precisionTank, vitesseTank, defenseTank, chanceTank, porteeTank, mouvementTank, c);
	}

	@Override
	public String getNom() {
		return nomTank;
	}

	@Override
	public BufferedImage getImage() {
		return Textures.getPersonnage(Textures.ASSASSINOISEAU, orientation);
	}

}
