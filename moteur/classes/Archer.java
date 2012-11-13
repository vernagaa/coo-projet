package moteur.classes;

import java.awt.Graphics;
import moteur.Case;
import moteur.Pion;

/**
 *
 * @author vernagaa
 */
public class Archer extends Pion {

	public final static int VIE = 50;
	public final static int ARMURE = 50;
	public final static int ATTAQUE = 50;
	public final static int PRECISION = 50;
	public final static int ESQUIVE = 50;
	public final static int COUPCRITIQUE = 50;
	public final static int DEPL = 50;
	
	public Archer(int vie, int armure, int attaque, int precision, int esquive, int coupCritique, int depl, Case c) {
		super(VIE + vie, ARMURE + armure, ATTAQUE + attaque, PRECISION + precision, ESQUIVE + esquive, COUPCRITIQUE + coupCritique, DEPL + depl, c);
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

	@Override
	public String getNom() {
		throw new UnsupportedOperationException("Not supported yet.");
	}
	
}
