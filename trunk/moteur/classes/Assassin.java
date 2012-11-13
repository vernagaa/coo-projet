/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package moteur.classes;

import moteur.Case;
import moteur.Pion;

/**
 *
 * @author Kévin
 */
public abstract class Assassin extends Pion{
	public final static int VIE = 30;
	public final static int FORCE = 39;
	public final static int PRECISION = 38;
	public final static int VITESSE = 29;
	public final static int DEFENSE = 25;
	public final static int PORTEE = 1;
	public final static int MOUVEMENT = 5;
	
	public Assassin(int vie, int force, int precision, int vitesse, int defense, int portee, int mouvement, Case c) {
		super(vie + VIE, force + FORCE, precision + PRECISION, vitesse + VITESSE, defense + DEFENSE, portee + PORTEE, mouvement + MOUVEMENT, c);
	}
}
