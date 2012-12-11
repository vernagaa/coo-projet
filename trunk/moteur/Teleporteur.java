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

	/**
	 * Constructeur de Téléporteur
	 * @param joueur
	 * @param c
	 */
	public Teleporteur(Joueur joueur, Case c) {
		this.joueur = joueur;
		vie = 50;
		this.c = c;
		
		c.setTeleporteur(this);
		joueur.ajouterTeleporteur(this);
	}

	/**
	 * Diminue la vie du téléporteur
	 * @param force
	 */
	public void diminuerVie(int force) {
		this.vie -= force / 2;
		if (vie < 0) {
			c.setTeleporteur(null);
			joueur.enleverTeleporteur(this);
		}
	}

	/**
	 * Renvoie la liste des téléporteur liés à this
	 * @return
	 */
	public ArrayList<Teleporteur> getListeTeleporteur() {
		return joueur.getTeleporteur();
	}

	@Override
	public String toString() {
		return c.toString();
	}

	/**
	 * Renvoie la case du téléporteur
	 * @return
	 */
	public Case getCase() {
		return c;
	}

	/**
	 * Renvoie si le téléporteur est utilisable
	 * @return
	 */
	public boolean isDisponible() {
		return c.getPion() == null;
	}
	
	/**
	 * Renvoie l'image du téléporteur
	 * @return
	 */
	public BufferedImage getImage() {
		return Textures.getObstacle(joueur.getBoolValue() ? Textures.TELEPORTEUR1 : Textures.TELEPORTEUR2);
	}
}
