package moteur.familles.felin;

import java.awt.image.BufferedImage;
import moteur.Case;
import moteur.Textures;
import moteur.classes.Archer;

/**
 *
 * @author vernagaa
 */
public final class ArcherFelin extends Archer implements Felin {

	private boolean enrage;
	int precisionBase;

	/**
	 * Constructeur d'un archer félin
	 * @param c
	 */
	public ArcherFelin(Case c) {
		super(vieArcher, forceArcher, precisionArcher, vitesseArcher, defenseArcher, chanceArcher, porteeArcher, mouvementArcher, c);
		enrage = false;
		nomCapaciteSpeciale = nomCapacite;
	}

	@Override
	public String getNom() {
		return nomArcher;
	}

	@Override
	public BufferedImage getImage() {
		return Textures.getPersonnage(Textures.ARCHERFELIN, orientation);
	}

	@Override
	public void capaciteSpeciale() {
		enrage();
		specialIndispo();
		setTourspecial(getTourspecial() + 1);
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
	

	@Override
	public BufferedImage getImage(int i) {
		return Textures.getPersonnage(Textures.ARCHERFELIN, orientation, i);
	}
}
