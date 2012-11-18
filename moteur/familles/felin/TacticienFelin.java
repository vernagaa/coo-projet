package moteur.familles.felin;

import java.awt.image.BufferedImage;
import moteur.Case;
import moteur.Textures;
import moteur.classes.Tacticien;

/**
 *
 * @author Kévin
 */
public final class TacticienFelin extends Tacticien implements Felin {

	public TacticienFelin(Case c) {
		super(vieTacticien, forceTacticien, precisionTacticien, vitesseTacticien, defenseTacticien, chanceTacticien, porteeTacticien, mouvementTacticien, c);
	}

	@Override
	public String getNom() {
		return nomTacticien;
	}

	@Override
	public BufferedImage getImage() {
		return Textures.getPersonnage(Textures.TACTICIENFELIN, orientation);
	}
}