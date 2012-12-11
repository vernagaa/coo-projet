package moteur;

import moteur.familles.felin.*;
import moteur.familles.oiseau.*;
import moteur.familles.reptile.*;

/**
 *
 * @author vernagaa
 */
public class FabriquePion {

	/**
	 * Constante famille félin
	 */
	public static final int FELIN = 0;
	/**
	 * Constante famille oiseau
	 */
	public static final int OISEAU = 1;
	/**
	 * Constante famille reptile
	 */
	public static final int REPTILE = 2;
	/**
	 * Constante classe archer
	 */
	public static final int ARCHER = 0;
	/**
	 * Constante classe assassin
	 */
	public static final int ASSASSIN = 1;
	/**
	 * Constante classe guerrier
	 */
	public static final int GUERRIER = 2;
	/**
	 * Constante classe tacticien
	 */
	public static final int TACTICIEN = 3;
	/**
	 * Constante classe tank
	 */
	public static final int TANK = 4;

	/**
	 * Construction du pion selon sa famille et sa classe à la case donnée
	 * @param famille
	 * @param classe
	 * @param c
	 * @return
	 */
	public static Pion getPion(int famille, int classe, Case c) {
		switch (famille) {
			case FELIN:
				return pionFelin(classe, c);
			case OISEAU:
				return pionOiseau(classe, c);
			case REPTILE:
				return pionReptile(classe, c);
			default:
				return null;
		}
	}

	private static Pion pionFelin(int classe, Case c) {
		switch (classe) {
			case ARCHER:
				return new ArcherFelin(c);
			case ASSASSIN:
				return new AssassinFelin(c);
			case GUERRIER:
				return new GuerrierFelin(c);
			case TACTICIEN:
				return new TacticienFelin(c);
			case TANK:
				return new TankFelin(c);
			default:
				return null;
		}
	}

	private static Pion pionOiseau(int classe, Case c) {
		switch (classe) {
			case ARCHER:
				return new ArcherOiseau(c);
			case ASSASSIN:
				return new AssassinOiseau(c);
			case GUERRIER:
				return new GuerrierOiseau(c);
			case TACTICIEN:
				return new TacticienOiseau(c);
			case TANK:
				return new TankOiseau(c);
			default:
				return null;
		}
	}

	private static Pion pionReptile(int classe, Case c) {
		switch (classe) {
			case ARCHER:
				return new ArcherReptile(c);
			case ASSASSIN:
				return new AssassinReptile(c);
			case GUERRIER:
				return new GuerrierReptile(c);
			case TACTICIEN:
				return new TacticienReptile(c);
			case TANK:
				return new TankReptile(c);
			default:
				return null;
		}
	}
}