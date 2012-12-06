package moteur.familles.felin;

import moteur.classes.Famille;

/**
 *
 * @author vernagaa
 */
public interface Felin extends Famille {

	public static final String nomArcher = "Ocelot";
	public static final int vieArcher = -2;
	public static final int forceArcher = 0;
	public static final int precisionArcher = 0;
	public static final int vitesseArcher = 0;
	public static final int defenseArcher = 3;
	public static final int chanceArcher = 2;
	public static final int porteeArcher = 0;
	public static final int mouvementArcher = 0;
	public static final String nomGuerrier = "Tigre";
	public static final int vieGuerrier = -2;
	public static final int forceGuerrier = 0;
	public static final int precisionGuerrier = 0;
	public static final int vitesseGuerrier = 0;
	public static final int defenseGuerrier = 3;
	public static final int chanceGuerrier = 2;
	public static final int porteeGuerrier = 0;
	public static final int mouvementGuerrier = 0;
	public static final String nomAssassin = "Lynx";
	public static final int vieAssassin = -2;
	public static final int forceAssassin = 0;
	public static final int precisionAssassin = 0;
	public static final int vitesseAssassin = 0;
	public static final int defenseAssassin = 3;
	public static final int chanceAssassin = 2;
	public static final int porteeAssassin = 0;
	public static final int mouvementAssassin = 0;
	public static final String nomTank = "Lion";
	public static final int vieTank = -2;
	public static final int forceTank = 0;
	public static final int precisionTank = 0;
	public static final int vitesseTank = 0;
	public static final int defenseTank = 3;
	public static final int chanceTank = 2;
	public static final int porteeTank = 0;
	public static final int mouvementTank = 0;
	public static final String nomTacticien = "Panthère";
	public static final int vieTacticien = -2;
	public static final int forceTacticien = 0;
	public static final int precisionTacticien = 0;
	public static final int vitesseTacticien = 0;
	public static final int defenseTacticien = 3;
	public static final int chanceTacticien = 2;
	public static final int porteeTacticien = 0;
	public static final int mouvementTacticien = 0;
	public static final int cooldown = 4;
	public static final String nomCapacite = "Rage";

	public void enrage();
}
