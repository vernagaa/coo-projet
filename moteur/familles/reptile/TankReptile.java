package moteur.familles.reptile;

import java.awt.image.BufferedImage;
import moteur.Case;
import moteur.Textures;
import moteur.classes.Tank;

/**
 *
 * @author KÃ©vin
 */
public final class TankReptile extends Tank implements Reptile {

	/**
	 * Constructeur du tank reptile
	 * @param c
	 */
	public TankReptile(Case c) {
		super(vieTank, forceTank, precisionTank, vitesseTank, defenseTank, chanceTank, porteeTank, mouvementTank, c);
		nomCapaciteSpeciale = nomCapacite;
	}

	@Override
	public String getNom() {
		return nomTank;
	}

	@Override
	public BufferedImage getImage() {
		return Textures.getPersonnage(Textures.TANKREPTILE, orientation);
	}

	@Override
	public void mue() {
		vie += 7;
	}

	@Override
	public void capaciteSpeciale() {
		mue();
		specialIndispo();
	}

	@Override
	public void specialIndispo() {
		setSpecial(cooldown);
	}

	@Override
	public BufferedImage getImage(int i) {
		return Textures.getPersonnage(Textures.TANKREPTILE, orientation, i);
	}
}
