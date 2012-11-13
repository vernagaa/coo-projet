package moteur.familles.oiseau;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import moteur.Case;
import moteur.Pion;
import moteur.classes.Archer;

/**
 *
 * @author vernagaa
 */
public final class ArcherOiseau extends Archer implements Oiseau {
	public ArcherOiseau(Case c) {
		super(vieArcher, forceArcher, precisionArcher, vitesseArcher, defenseArcher, porteeArcher, mouvementArcher, c);
	}

	@Override
	public String getNom() {
		return nomArcher;
	}
	
	@Override
	protected float janken(Pion p) {
		return 0;
	}

}
