package moteur.classes;

import java.awt.Graphics;
import moteur.Case;
import moteur.Pion;

/**
 *
 * @author vernagaa
 */
public class Archer extends Pion {

	public Archer(int vie, int armure, int attaque, int precision, int esquive, int coupCritique, int depl, Case c) {
		super(vie, armure, attaque, precision, esquive, coupCritique, depl, c);
	}

	@Override
	public void attaquerPion(Case c) {
		throw new UnsupportedOperationException("Not supported yet.");
	}

	@Override
	public void seDeplacer(Case c) {
		throw new UnsupportedOperationException("Not supported yet.");
	}

	@Override
	public void paintComponent(Graphics g) {
		throw new UnsupportedOperationException("Not supported yet.");
	}
	
}
