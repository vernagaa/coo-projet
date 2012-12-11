package moteur.familles.reptile;

import java.awt.image.BufferedImage;
import moteur.Case;
import moteur.Textures;
import moteur.classes.Guerrier;

/**
 *
 * @author vernagaa
 */
public final class GuerrierReptile extends Guerrier implements Reptile {

	/**
	 * Constructeur du guerrier reptile
	 * @param c
	 */
	public GuerrierReptile(Case c) {
		super(vieGuerrier, forceGuerrier, precisionGuerrier, vitesseGuerrier, defenseGuerrier, chanceGuerrier, porteeGuerrier, mouvementGuerrier, c);
		nomCapaciteSpeciale = nomCapacite;
	}

	@Override
	public String getNom() {
		return nomGuerrier;
	}

	@Override
	public BufferedImage getImage() {
		return Textures.getPersonnage(Textures.GUERRIERREPTILE, orientation);
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
		return Textures.getPersonnage(Textures.GUERRIERREPTILE, orientation, i);
	}
}
