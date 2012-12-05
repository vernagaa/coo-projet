package moteur.familles.oiseau;

import moteur.classes.Famille;

/**
 *
 * @author vernagaa
 */
public interface Oiseau extends Famille {
	
	public static final String nomArcher = "Hirondelle";
	public static final int vieArcher = 0;
	public static final int forceArcher = 0;
	public static final int precisionArcher = 3;
	public static final int vitesseArcher = 2;
	public static final int defenseArcher = -2;
	public static final int chanceArcher = 0;
	public static final int porteeArcher = 1;
	public static final int mouvementArcher = 0;
	
	public static final String nomGuerrier = "Aigle";
	public static final int vieGuerrier = 0;
	public static final int forceGuerrier = 0;
	public static final int precisionGuerrier = 3;
	public static final int vitesseGuerrier = 2;
	public static final int defenseGuerrier = -2;
	public static final int chanceGuerrier = 0;
	public static final int porteeGuerrier = 1;
	public static final int mouvementGuerrier = 0;
	
	public static final String nomAssassin = "Faucon";
	public static final int vieAssassin = 0;
	public static final int forceAssassin = 0;
	public static final int precisionAssassin = 3;
	public static final int vitesseAssassin = 2;
	public static final int defenseAssassin = -2;
	public static final int chanceAssassin = 0;
	public static final int porteeAssassin = 1;
	public static final int mouvementAssassin = 0;
	
	public static final String nomTank = "Condor";
	public static final int vieTank = 0;
	public static final int forceTank = 0;
	public static final int precisionTank = 3;
	public static final int vitesseTank = 2;
	public static final int defenseTank = -2;
	public static final int chanceTank = 0;
	public static final int porteeTank = 1;
	public static final int mouvementTank = 0;
	
	public static final String nomTacticien = "Perroquet";
	public static final int vieTacticien = 0;
	public static final int forceTacticien = 0;
	public static final int precisionTacticien = 3;
	public static final int vitesseTacticien = 2;
	public static final int defenseTacticien = -2;
	public static final int chanceTacticien = 0;
	public static final int porteeTacticien = 1;
	public static final int mouvementTacticien = 0;
	
	public static final int cooldown = 5;
	public boolean isEnvole();
	public void enEnvole();
}
