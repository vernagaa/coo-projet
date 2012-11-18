package moteur.familles.felin;

import java.awt.image.BufferedImage;
import moteur.Case;
import moteur.Textures;
import moteur.classes.Assassin;

/**
 *
 * @author Kévin
 */
public class AssassinFelin extends Assassin implements Felin {

	public AssassinFelin(Case c) {
		super(vieAssassin, forceAssassin, precisionAssassin, vitesseAssassin, defenseAssassin, chanceAssassin, porteeAssassin, mouvementAssassin, c);
	}

	@Override
	public String getNom() {
		return nomAssassin;
	}

	@Override
	public BufferedImage getImage() {
		return Textures.getPersonnage(Textures.ASSASSINFELIN, orientation);
	}
}