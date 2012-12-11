package moteur;

import java.awt.image.BufferedImage;
import java.util.ArrayList;

/**
 *
 * @author chappelk
 */
public class Teleporteur {

	private Joueur joueur;
	private Case c;
	private int vie;

	public Teleporteur(Joueur joueur, Case c) {
		this.joueur = joueur;
		vie = 50;
		this.c = c;
		
		c.setTeleporteur(this);
		System.out.println(joueur.getTeleporteur());
		joueur.ajouterTeleporteur(this);
		System.out.println("Liste des teleporteurs " + joueur.getTeleporteur());
	}

	public void diminuerVie(int force) {
		this.vie -= force / 2;
		if (vie < 0) {
			c.setTeleporteur(null);
			joueur.enleverTeleporteur(this);
		}
	}

	public ArrayList<Teleporteur> getListeTeleporteur() {
		return joueur.getTeleporteur();
	}

	@Override
	public String toString() {
		return c.toString();
	}

	public Case getCase() {
		return c;
	}

	public boolean isDisponible() {
		return c.getPion() == null;
	}
	
	public BufferedImage getImage() {
		return Textures.getObstacle(joueur.getBoolValue() ? Textures.TELEPORTEUR1 : Textures.TELEPORTEUR2);
	}
}
