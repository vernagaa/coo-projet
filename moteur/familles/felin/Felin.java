package moteur.familles.felin;

import moteur.classes.Famille;

/**
 *
 * @author vernagaa
 */
public interface Felin extends Famille {

	/**
	 * Nom de l'archer félin
	 */
	public static final String nomArcher = "Ocelot";
	/**
	 * bonus de vie pour l'archer félin
	 */
	public static final int vieArcher = -2;
	/**
	 * bonus de force pour l'archer félin
	 */
	public static final int forceArcher = 0;
	/**
	 * bonus de precision archer félin
	 */
	public static final int precisionArcher = 0;
	/**
	 * bonus de vitesse archer félin
	 */
	public static final int vitesseArcher = 0;
	/**
	 * bonus de défense archer félin
	 */
	public static final int defenseArcher = 3;
	/**
	 * bonus de chance d'archer félin
	 */
	public static final int chanceArcher = 2;
	/**
	 * bonus de portée d'archer félin
	 */
	public static final int porteeArcher = 0;
	/**
	 * bonus de mouvement d'archer félin
	 */
	public static final int mouvementArcher = 0;
	/**
	 * nom du guerrier félin
	 */
	public static final String nomGuerrier = "Tigre";
	/**
	 * bonus vie guerrier félin
	 */
	public static final int vieGuerrier = -2;
	/**
	 * bonus force guerrier félin
	 */
	public static final int forceGuerrier = 0;
	/**
	 * bonus précision guerrier félin
	 */
	public static final int precisionGuerrier = 0;
	/**
	 * bonus vitesse guerrier félin
	 */
	public static final int vitesseGuerrier = 0;
	/**
	 * bonus défense guerrier félin
	 */
	public static final int defenseGuerrier = 3;
	/**
	 * bonus chance guerrier félin
	 */
	public static final int chanceGuerrier = 2;
	/**
	 * bonus portée guerrier félin
	 */
	public static final int porteeGuerrier = 0;
	/**
	 * bonus mouvement guerrier félin
	 */
	public static final int mouvementGuerrier = 0;
	/**
	 * nom de l'assassin félin
	 */
	public static final String nomAssassin = "Lynx";
	/**
	 * bonus vie assassin félin
	 */
	public static final int vieAssassin = -2;
	/**
	 * bonus force assassin félin
	 */
	public static final int forceAssassin = 0;
	/**
	 * bonus précision assassin félin
	 */
	public static final int precisionAssassin = 0;
	/**
	 * bonus vitesse assassin félin
	 */
	public static final int vitesseAssassin = 0;
	/**
	 * bonus défense assassin félin
	 */
	public static final int defenseAssassin = 3;
	/**
	 * bonus chance assassin félin
	 */
	public static final int chanceAssassin = 2;
	/**
	 * bonus portée assassin félin
	 */
	public static final int porteeAssassin = 0;
	/**
	 * bonus mouvement assassin félin
	 */
	public static final int mouvementAssassin = 0;
	/**
	 * nom du tank félin
	 */
	public static final String nomTank = "Lion";
	/**
	 * bonus vie du tank félin
	 */
	public static final int vieTank = -2;
	/**
	 * bonus force du tank félin
	 */
	public static final int forceTank = 0;
	/**
	 * bonus précision du tank félin
	 */
	public static final int precisionTank = 0;
	/**
	 * bonus vitesse du tank félin
	 */
	public static final int vitesseTank = 0;
	/**
	 * bonus défense du tank félin
	 */
	public static final int defenseTank = 3;
	/**
	 * bonus chance du tank félin
	 */
	public static final int chanceTank = 2;
	/**
	 * bonus portée du tank félin
	 */
	public static final int porteeTank = 0;
	/**
	 * bonus mouvement du tank félin
	 */
	public static final int mouvementTank = 0;
	/**
	 * nom du tacticien félin
	 */
	public static final String nomTacticien = "Panthère";
	/**
	 * bonus vie du tacticien félin
	 */
	public static final int vieTacticien = -2;
	/**
	 * bonus force du tacticien félin
	 */
	public static final int forceTacticien = 0;
	/**
	 * bonus précision du tacticien félin
	 */
	public static final int precisionTacticien = 0;
	/**
	 * bonus vitesse du tacticien félin
	 */
	public static final int vitesseTacticien = 0;
	/**
	 * bonus défense du tacticien félin
	 */
	public static final int defenseTacticien = 3;
	/**
	 * bonus chance du tacticien félin
	 */
	public static final int chanceTacticien = 2;
	/**
	 * bonus portée du tacticien félin
	 */
	public static final int porteeTacticien = 0;
	/**
	 * bonus mouvement du tacticien félin
	 */
	public static final int mouvementTacticien = 0;
	/**
	 * Cooldown de la capacité
	 */
	public static final int cooldown = 4;
	/**
	 * nom de capacité des félin
	 */
	public static final String nomCapacite = "Rage";

	/**
	 * Capacité des félin
	 */
	public void enrage();
}
