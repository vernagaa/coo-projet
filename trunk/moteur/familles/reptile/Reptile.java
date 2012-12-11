package moteur.familles.reptile;

import moteur.classes.Famille;

/**
 *
 * @author vernagaa
 */
public interface Reptile extends Famille {

	/**
	 * nom de l'archer reptile
	 */
	public static final String nomArcher = "Caméléon";
	/**
	 * bonus de vie de l'archer reptile
	 */
	public static final int vieArcher = 5;
	/**
	 * bonus de force de l'archer reptile
	 */
	public static final int forceArcher = 3;
	/**
	 * bonus de précision de l'archer reptile
	 */
	public static final int precisionArcher = 0;
	/**
	 * bonus de vitesse de l'archer reptile
	 */
	public static final int vitesseArcher = 0;
	/**
	 * bonus de défense de l'archer reptile
	 */
	public static final int defenseArcher = 0;
	/**
	 * bonus de chance de l'archer reptile
	 */
	public static final int chanceArcher = 0;
	/**
	 * bonus de portée de l'archer reptile
	 */
	public static final int porteeArcher = 0;
	/**
	 * bonus de mouvement de l'archer reptile
	 */
	public static final int mouvementArcher = 0;
	/**
	 * nom du guerrier reptile
	 */
	public static final String nomGuerrier = "Crocodile";
	/**
	 * bonus de vie du guerrier reptile
	 */
	public static final int vieGuerrier = 5;
	/**
	 * bonus de force du guerrier reptile
	 */
	public static final int forceGuerrier = 3;
	/**
	 * bonus de précision du guerrier reptile
	 */
	public static final int precisionGuerrier = 0;
	/**
	 * bonus de vitesse du guerrier reptile
	 */
	public static final int vitesseGuerrier = 0;
	/**
	 * bonus de défense du guerrier reptile
	 */
	public static final int defenseGuerrier = 0;
	/**
	 * bonus de chance du guerrier reptile
	 */
	public static final int chanceGuerrier = 0;
	/**
	 * bonus de portée du guerrier reptile
	 */
	public static final int porteeGuerrier = 0;
	/**
	 * bonus de mouvement du guerrier reptile
	 */
	public static final int mouvementGuerrier = 0;
	/**
	 * nom de l'assassin reptile
	 */
	public static final String nomAssassin = "Cobra";
	/**
	 * bonus de vie de l'assassin reptile
	 */
	public static final int vieAssassin = 5;
	/**
	 * bonus de force de l'assassin reptile
	 */
	public static final int forceAssassin = 3;
	/**
	 * bonus de précision de l'assassin reptile
	 */
	public static final int precisionAssassin = 0;
	/**
	 * bonus de vitesse de l'assassin reptile
	 */
	public static final int vitesseAssassin = 0;
	/**
	 * bonus de défense de l'assassin reptile
	 */
	public static final int defenseAssassin = 0;
	/**
	 * bonus de chance de l'assassin reptile
	 */
	public static final int chanceAssassin = 0;
	/**
	 * bonus de portée de l'assassin reptile
	 */
	public static final int porteeAssassin = 0;
	/**
	 * bonus de mouvement de l'assassin reptile
	 */
	public static final int mouvementAssassin = 0;
	/**
	 * nom du tank reptile
	 */
	public static final String nomTank = "Tortue";
	/**
	 * bonus de vie du tank reptile
	 */
	public static final int vieTank = 5;
	/**
	 * bonus de force du tank reptile
	 */
	public static final int forceTank = 3;
	/**
	 * bonus de précision du tank reptile
	 */
	public static final int precisionTank = 0;
	/**
	 * bonus de vitesse du tank reptile
	 */
	public static final int vitesseTank = 0;
	/**
	 * bonus de défense du tank reptile
	 */
	public static final int defenseTank = 0;
	/**
	 * bonus de chance du tank reptile
	 */
	public static final int chanceTank = 0;
	/**
	 * bonus de portée du tank reptile
	 */
	public static final int porteeTank = 0;
	/**
	 * bonus de mouvement du tank reptile
	 */
	public static final int mouvementTank = 0;
	/**
	 * nom du tacticien reptile
	 */
	public static final String nomTacticien = "Grenouille";
	/**
	 * bonus de vie du tacticien 
	 */
	public static final int vieTacticien = 5;
	/**
	 * bonus de force du tacticien 
	 */
	public static final int forceTacticien = 3;
	/**
	 * bonus de précision du tacticien 
	 */
	public static final int precisionTacticien = 0;
	/**
	 * bonus de vitesse du tacticien 
	 */
	public static final int vitesseTacticien = 0;
	/**
	 * bonus de défense du tacticien 
	 */
	public static final int defenseTacticien = 0;
	/**
	 * bonus de chance du tacticien 
	 */
	public static final int chanceTacticien = 0;
	/**
	 * bonus de portée du tacticien 
	 */
	public static final int porteeTacticien = 0;
	/**
	 * bonus de mouvement du tacticien 
	 */
	public static final int mouvementTacticien = 0;
	/**
	 * nom de la capacité des reptiles
	 */
	public static final String nomCapacite = "Mue";
	/**
	 * Cooldown de la capacité
	 */
	public static final int cooldown = 3;

	/**
	 * Capacité spéciale des reptiles
	 */
	public void mue();
}
