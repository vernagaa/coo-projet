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
	public ArcherReptile(Case c) {
		super(vieArcher, forceArcher, precisionArcher, vitesseArcher, defenseArcher, chanceArcher, porteeArcher, mouvementArcher, c);
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
}
