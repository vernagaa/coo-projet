package moteur.familles.reptile;

import java.awt.image.BufferedImage;
import moteur.Case;
import moteur.Textures;
import moteur.classes.Archer;

/**
 *
 * @author vernagaa
 */
public final class ArcherReptile extends Archer implements Reptile {

	/**
	 * Constructeur de l'archer reptile
	 * @param c
	 */
	public ArcherReptile(Case c) {
		super(vieArcher, forceArcher, precisionArcher, vitesseArcher, defenseArcher, chanceArcher, porteeArcher, mouvementArcher, c);
		nomCapaciteSpeciale = nomCapacite;
	}

	@Override
	public String getNom() {
		return nomArcher;
	}

	@Override
	public BufferedImage getImage() {
		return Textures.getPersonnage(Textures.ARCHERREPTILE, orientation);
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
		return Textures.getPersonnage(Textures.ARCHERREPTILE, orientation, i);
	}
}
