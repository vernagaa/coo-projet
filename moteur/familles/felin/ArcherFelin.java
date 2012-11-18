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
	public ArcherFelin(Case c) {
		super(vieArcher, forceArcher, precisionArcher, vitesseArcher, defenseArcher, chanceArcher, porteeArcher, mouvementArcher, c);
	}

	@Override
	public String getNom() {
		return nomArcher;
	}

	@Override
	public BufferedImage getImage() {
		return Textures.getPersonnage(Textures.ARCHERFELIN, orientation);
	}

}
