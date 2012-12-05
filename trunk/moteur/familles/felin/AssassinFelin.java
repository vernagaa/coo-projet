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
	private boolean enrage;
	int precisionBase;

	public AssassinFelin(Case c) {
		super(vieAssassin, forceAssassin, precisionAssassin, vitesseAssassin, defenseAssassin, chanceAssassin, porteeAssassin, mouvementAssassin, c);
		enrage = false;
	}

	@Override
	public String getNom() {
		return nomAssassin;
	}

	@Override
	public BufferedImage getImage() {
		return Textures.getPersonnage(Textures.ASSASSINFELIN, orientation);
	}
	
	@Override
	protected float coupCritiques() {
		if (enrage) {
			enrage = false;
			return 9999 / 150;
		} else {
			if(getTourspecial()==1){
				setTourspecial(getTourspecial()+1);
				precisionBase = precision;
				precision = 0;
			}else if(getTourspecial() == 2){
				precision = precisionBase;
				setTourspecial(0);
			}
			return precision * 4 * vitesse / 150;
		}
	}
	
	
	
	@Override
	public void capaciteSpeciale() {
		enrage();
		specialIndispo();
	}

	@Override
	public void specialIndispo() {
		setSpecial(cooldown);
	}

	@Override
	public void enrage() {
		enrage = true;
	}
}