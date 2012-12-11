package moteur.familles.felin;

import java.awt.image.BufferedImage;
import moteur.Case;
import moteur.Textures;
import moteur.classes.Tank;

/**
 *
 * @author KÃ©vin
 */
public final class TankFelin extends Tank implements Felin {

	private boolean enrage;
	int precisionBase;

	/**
	 * Constructeur tank félin
	 * @param c
	 */
	public TankFelin(Case c) {
		super(vieTank, forceTank, precisionTank, vitesseTank, defenseTank, chanceTank, porteeTank, mouvementTank, c);
		enrage = false;
		nomCapaciteSpeciale = nomCapacite;
	}

	@Override
	public String getNom() {
		return nomTank;
	}

	@Override
	public BufferedImage getImage() {
		return Textures.getPersonnage(Textures.TANKFELIN, orientation);
	}

	@Override
	public void specialIndispo() {
		setSpecial(cooldown);
	}

	@Override
	public void capaciteSpeciale() {
		enrage();
		specialIndispo();
	}

	@Override
	public void enrage() {
		enrage = true;
	}

	@Override
	protected float coupCritiques() {
		if (enrage) {
			enrage = false;
			return 9999 / 150;
		} else {
			if (getTourspecial() == 1) {
				setTourspecial(getTourspecial() + 1);
				precisionBase = precision;
				precision = 0;
			} else if (getTourspecial() == 2) {
				precision = precisionBase;
				setTourspecial(0);
			}
			return precision * 4 * vitesse / 150;
		}
	}

	public BufferedImage getImage(int i) {
		return Textures.getPersonnage(Textures.TANKFELIN, orientation, i);
	}
}
