package moteur.familles.felin;

import java.awt.image.BufferedImage;
import moteur.Case;
import moteur.Textures;
import moteur.classes.Guerrier;

/**
 *
 * @author vernagaa
 */
public final class GuerrierFelin extends Guerrier implements Felin {

	private boolean enrage;
	int precisionBase;

	/**
	 * Constructeur du guerrier félin
	 * @param c
	 */
	public GuerrierFelin(Case c) {
		super(vieGuerrier, forceGuerrier, precisionGuerrier, vitesseGuerrier, defenseGuerrier, chanceGuerrier, porteeGuerrier, mouvementGuerrier, c);
		enrage = false;
		nomCapaciteSpeciale = nomCapacite;
	}

	@Override
	public String getNom() {
		return nomGuerrier;
	}

	@Override
	public BufferedImage getImage() {
		return Textures.getPersonnage(Textures.GUERRIERFELIN, orientation);
	}

	@Override
	public void capaciteSpeciale() {
		enrage();
		specialIndispo();
	}

	@Override
	public void specialIndispo() {
		setSpecial(cooldown);
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
		return Textures.getPersonnage(Textures.GUERRIERFELIN, orientation, i);
	}
}
