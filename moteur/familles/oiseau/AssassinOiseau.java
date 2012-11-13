/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package moteur.familles.oiseau;

import moteur.Case;
import moteur.Pion;
import moteur.classes.Assassin;
import moteur.classes.Tank;

/**
 *
 * @author Kévin
 */
public class AssassinOiseau extends Assassin implements Oiseau {

	public AssassinOiseau(Case c) {
		super(vieAssassin, forceAssassin, precisionAssassin, vitesseAssassin, defenseAssassin, porteeAssassin, mouvementAssassin, c);
	}

	@Override
	public String getNom() {
		return nomAssassin;
	}

	@Override
	protected float janken(Pion p) {
		if (p instanceof Tank) {
			return 20 / 100;
		}
		return 0;
	}
}