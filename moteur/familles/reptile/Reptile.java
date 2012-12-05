package moteur.familles.reptile;

import moteur.classes.Famille;

/**
 *
 * @author vernagaa
 */
public interface Reptile extends Famille {
	
	public static final String nomArcher = "Caméléon";
	public static final int vieArcher = 5;
	public static final int forceArcher = 3;
	public static final int precisionArcher = 0;
	public static final int vitesseArcher = 0;
	public static final int defenseArcher = 0;
	public static final int chanceArcher = 0;
	public static final int porteeArcher = 0;
	public static final int mouvementArcher = 0;
	
	public static final String nomGuerrier = "Crocodile";
	public static final int vieGuerrier = 5;
	public static final int forceGuerrier = 3;
	public static final int precisionGuerrier = 0;
	public static final int vitesseGuerrier = 0;
	public static final int defenseGuerrier = 0;
	public static final int chanceGuerrier = 0;
	public static final int porteeGuerrier = 0;
	public static final int mouvementGuerrier = 0;
	
	public static final String nomAssassin = "Cobra";
	public static final int vieAssassin = 5;
	public static final int forceAssassin = 3;
	public static final int precisionAssassin = 0;
	public static final int vitesseAssassin = 0;
	public static final int defenseAssassin = 0;
	public static final int chanceAssassin = 0;
	public static final int porteeAssassin = 0;
	public static final int mouvementAssassin = 0;
	
	public static final String nomTank = "Tortue";
	public static final int vieTank = 5;
	public static final int forceTank = 3;
	public static final int precisionTank = 0;
	public static final int vitesseTank = 0;
	public static final int defenseTank = 0;
	public static final int chanceTank = 0;
	public static final int porteeTank = 0;
	public static final int mouvementTank = 0;
	
	public static final String nomTacticien = "Salamandre";
	public static final int vieTacticien = 5;
	public static final int forceTacticien = 3;
	public static final int precisionTacticien = 0;
	public static final int vitesseTacticien = 0;
	public static final int defenseTacticien = 0;
	public static final int chanceTacticien = 0;
	public static final int porteeTacticien = 0;
	public static final int mouvementTacticien = 0;
	
	public static final int cooldown = 3;
	public void mue();
}
