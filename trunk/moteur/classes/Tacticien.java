package moteur.classes;

import moteur.Case;
import moteur.Joueur;
import moteur.Pion;
import moteur.Textures;

/**
 *
 * @author Kévin
 */
public abstract class Tacticien extends Pion {

    public final static int VIE = 22;
    public final static int FORCE = 54;
    public final static int PRECISION = 33;
    public final static int VITESSE = 22;
    public final static int DEFENSE = 20;
    public final static int PORTEE = 1;
    public final static int MOUVEMENT = 5;
    private int cooldownTeleporteur;

    public Tacticien(int vie, int force, int precision, int vitesse, int defense, int bonusChance, int portee, int mouvement, Case c) {
	super(vie + VIE, force + FORCE, precision + PRECISION, vitesse + VITESSE, defense + DEFENSE, bonusChance, portee + PORTEE, mouvement + MOUVEMENT, c);
	cooldownTeleporteur = 0;
    }

    @Override
    protected float janken(Pion p) {
	return 0;
    }

    public void poserTeleporteur(Case c) {
	if (cooldownTeleporteur == 0) {
	    System.out.println("Je pose un teleporteur en " + c);
	    if (distanceManhattan(c) == 1) {
		c.setTypeTerrain(Textures.TELEPORTEUR);
	    }
	    joueur.getTeleporteur().add(c);
	    cooldownTeleporteur = 2;
	    //TODO Ajouter un cooldown à la pose?
	}
    }

    public void decrementerCDTeleporteur() {
	if (cooldownTeleporteur > 0) {
	    cooldownTeleporteur--;
	}
    }
}