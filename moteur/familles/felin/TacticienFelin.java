package moteur.familles.felin;

import java.awt.image.BufferedImage;
import moteur.Case;
import moteur.Textures;
import moteur.classes.Tacticien;

/**
 *
 * @author KÃ©vin
 */
public final class TacticienFelin extends Tacticien implements Felin {

	private boolean enrage;
	int precisionBase;

	/**
	 * Constructeur du tacticien félin
	 * @param c
	 */
	public TacticienFelin(Case c) {
		super(vieTacticien, forceTacticien, precisionTacticien, vitesseTacticien, defenseTacticien, chanceTacticien, porteeTacticien, mouvementTacticien, c);
		enrage = false;
		nomCapaciteSpeciale = nomCapacite;
	}

	@Override
	public String getNom() {
		return nomTacticien;
	}

	@Override
	public BufferedImage getImage() {
		return Textures.getPersonnage(Textures.TACTICIENFELIN, orientation);
	}

	@Override
	public void specialIndispo() {
		setSpecial(cooldown);
	}

	@Override
	public void capaciteSpeciale() {
		enrage();
		specialIndispo();
	}

	@Override
	public void enrage() {
		enrage = true;
	}

	@Override
	protected float coupCritiques() {
		if (enrage) {
			enrage = false;
			return 9999 / 150;
		} else {
			if (getTourspecial() == 1) {
				setTourspecial(getTourspecial() + 1);
				precisionBase = precision;
				precision = 0;
			} else if (getTourspecial() == 2) {
				precision = precisionBase;
				setTourspecial(0);
			}
			return precision * 4 * vitesse / 150;
		}
	}

	@Override
	public BufferedImage getImage(int i) {
		return Textures.getPersonnage(Textures.TACTICIENFELIN, orientation, i);
	}
	
	@Override
	public void finDeTour(){
		decrementerCDTeleporteur();
		setMouvement(getMouvementBase());
		recuperationCapacite();
	}
}