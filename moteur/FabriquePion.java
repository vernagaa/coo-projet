package moteur;

import moteur.familles.reptile.*;

/**
 *
 * @author vernagaa
 */
public class FabriquePion {

	public final static int FELIN = 0;
	public final static int OISEAU = 1;
	public final static int REPTILE = 2;
	
	public final static int ARCHER = 0;
	public final static int ASSASSIN = 1;
	public final static int GUERRIER = 2;
	public final static int TACTICIEN = 3;
	public final static int TANK = 4;
	
	public Pion getPion(int famille, int classe, Case c) {
		switch (famille) {
			case FELIN:
				break;
			case OISEAU:
				break;
			case REPTILE:
				return pionReptile(classe, c);
			default:
		}
		return null;
	}

	private Pion pionReptile(int classe, Case c) {
		switch (classe) {
			case ARCHER:
				return new ArcherReptile(c);
			case GUERRIER:
				return new GuerrierReptile(c);
		}
		return null;
	}
}