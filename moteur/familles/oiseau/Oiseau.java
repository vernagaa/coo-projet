package moteur.familles.oiseau;

import moteur.classes.Famille;

/**
 *
 * @author vernagaa
 */
public interface Oiseau extends Famille {

	/**
	 * nom de l'archer oiseau
	 */
	public static final String nomArcher = "Hirondelle";
	/**
	 * bonus de vie de l'archer oiseau
	 */
	public static final int vieArcher = 0;
	/**
	 * bonus de force de l'archer oiseau
	 */
	public static final int forceArcher = 0;
	/**
	 * bonus de précision de l'archer oiseau
	 */
	public static final int precisionArcher = 3;
	/**
	 * bonus de vitesse de l'archer oiseau
	 */
	public static final int vitesseArcher = 2;
	/**
	 * bonus de défense de l'archer oiseau
	 */
	public static final int defenseArcher = -2;
	/**
	 * bonus de chance de l'archer oiseau
	 */
	public static final int chanceArcher = 0;
	/**
	 * bonus de portée de l'archer oiseau
	 */
	public static final int porteeArcher = 1;
	/**
	 * bonus de mouvement de l'archer oiseau
	 */
	public static final int mouvementArcher = 0;
	/**
	 * nom du guerrier oiseau
	 */
	public static final String nomGuerrier = "Aigle";
	/**
	 * bonus de vie du guerrier oiseau
	 */
	public static final int vieGuerrier = 0;
	/**
	 * bonus de force du guerrier oiseau
	 */
	public static final int forceGuerrier = 0;
	/**
	 * bonus de précision du guerrier oiseau
	 */
	public static final int precisionGuerrier = 3;
	/**
	 * bonus de vitesse du guerrier oiseau
	 */
	public static final int vitesseGuerrier = 2;
	/**
	 * bonus de défense du guerrier oiseau
	 */
	public static final int defenseGuerrier = -2;
	/**
	 * bonus de chance du guerrier oiseau
	 */
	public static final int chanceGuerrier = 0;
	/**
	 * bonus de portée du guerrier oiseau
	 */
	public static final int porteeGuerrier = 1;
	/**
	 * bonus de mouvement du guerrier oiseau
	 */
	public static final int mouvementGuerrier = 0;
	/**
	 * nom de l'assassin oiseau
	 */
	public static final String nomAssassin = "Faucon";
	/**
	 * bonus de vie de l'assassin oiseau
	 */
	public static final int vieAssassin = 0;
	/**
	 * bonus de force de l'assassin oiseau
	 */
	public static final int forceAssassin = 5;
	/**
	 * bonus de précision de l'assassin oiseau
	 */
	public static final int precisionAssassin = 3;
	/**
	 * bonus de vitesse de l'assassin oiseau
	 */
	public static final int vitesseAssassin = 2;
	/**
	 * bonus de défense de l'assassin oiseau
	 */
	public static final int defenseAssassin = -2;
	/**
	 * bonus de chance de l'assassin oiseau
	 */
	public static final int chanceAssassin = 0;
	/**
	 * bonus de portée de l'assassin oiseau
	 */
	public static final int porteeAssassin = 1;
	/**
	 * bonus de mouvement de l'assassin oiseau
	 */
	public static final int mouvementAssassin = 0;
	/**
	 * nom du tank oiseau
	 */
	public static final String nomTank = "Condor";
	/**
	 * bonus de vie du tank oiseau
	 */
	public static final int vieTank = 0;
	/**
	 * bonus de force du tank oiseau
	 */
	public static final int forceTank = 0;
	/**
	 * bonus de précision du tank oiseau
	 */
	public static final int precisionTank = 3;
	/**
	 * bonus de vitesse du tank oiseau
	 */
	public static final int vitesseTank = 2;
	/**
	 * bonus de défense du tank oiseau
	 */
	public static final int defenseTank = -2;
	/**
	 * bonus de chance du tank oiseau
	 */
	public static final int chanceTank = 0;
	/**
	 * bonus de portée du tank oiseau
	 */
	public static final int porteeTank = 1;
	/**
	 * bonus de mouvement du tank oiseau
	 */
	public static final int mouvementTank = 0;
	/**
	 * nom du tacticien oiseau
	 */
	public static final String nomTacticien = "Perroquet";
	/**
	 * bonus de vie du tacticien oiseau
	 */
	public static final int vieTacticien = 0;
	/**
	 * bonus de force du tacticien oiseau
	 */
	public static final int forceTacticien = 0;
	/**
	 * bonus de précision du tacticien oiseau
	 */
	public static final int precisionTacticien = 3;
	/**
	 * bonus de vitesse du tacticien oiseau
	 */
	public static final int vitesseTacticien = 2;
	/**
	 * bonus de défense du tacticien oiseau
	 */
	public static final int defenseTacticien = -2;
	/**
	 * bonus de chance du tacticien oiseau
	 */
	public static final int chanceTacticien = 0;
	/**
	 * bonus de portée du tacticien oiseau
	 */
	public static final int porteeTacticien = 1;
	/**
	 * bonus de mouvement du tacticien oiseau
	 */
	public static final int mouvementTacticien = 0;
	/**
	 * nom de la capacité spéciale
	 */
	public static final String nomCapacite = "Envol";
	/**
	 * Cooldown de la capacité spéciale
	 */
	public static final int cooldown = 5;

	/**
	 * Renvoie vrai si l'oiseau est en mode vol
	 * @return 
	 */
	public boolean isEnvole();

	/**
	 * Capacité des oiseaux
	 */
	public void enEnvole();
}
