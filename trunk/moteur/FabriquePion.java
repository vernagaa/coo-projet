package moteur;

import moteur.familles.felin.*;
import moteur.familles.oiseau.*;
import moteur.familles.reptile.*;

/**
 *
 * @author vernagaa
 */
public class FabriquePion {

	public static final int FELIN = 0;
	public static final int OISEAU = 1;
	public static final int REPTILE = 2;
	public static final int ARCHER = 0;
	public static final int ASSASSIN = 1;
	public static final int GUERRIER = 2;
	public static final int TACTICIEN = 3;
	public static final int TANK = 4;

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