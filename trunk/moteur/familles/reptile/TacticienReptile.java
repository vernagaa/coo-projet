package moteur.familles.reptile;

import java.awt.image.BufferedImage;
import moteur.Case;
import moteur.Textures;
import moteur.classes.Tacticien;

/**
 *
 * @author KÃ©vin
 */
public final class TacticienReptile extends Tacticien implements Reptile {

	/**
	 * Constructeur du tacticien reptile
	 * @param c
	 */
	public TacticienReptile(Case c) {
		super(vieTacticien, forceTacticien, precisionTacticien, vitesseTacticien, defenseTacticien, chanceTacticien, porteeTacticien, mouvementTacticien, c);
		nomCapaciteSpeciale = nomCapacite;
	}

	@Override
	public String getNom() {
		return nomTacticien;
	}

	@Override
	public BufferedImage getImage() {
		return Textures.getPersonnage(Textures.TACTICIENREPTILE, orientation);
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
		return Textures.getPersonnage(Textures.TACTICIENREPTILE, orientation, i);
	}
	
	@Override
	public void finDeTour(){
		decrementerCDTeleporteur();
		setMouvement(getMouvementBase());
		recuperationCapacite();
	}
}