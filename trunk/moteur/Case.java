package moteur;

import java.io.Serializable;

/**
 *
 * @author Kévin
 */
public class Case implements Serializable {

	/**
	 * ID de serrializable
	 */
	protected static final long serialVersionUID = 1L;
	/**
	 * Constante qui définit la taille en pixel de la case
	 */
	public static final int TAILLE = 30;
	private int col;
	private int lig;
	private Plateau plateau;
	private boolean select;
	private Pion pion;
	private Obstacle obstacle;
	private Bordure bordure;
	private Teleporteur teleporteur;
	private int typeTerrain;

	/**
	 * Constructeur de base de la classe Case. Prend 3 paramètres : param l entier
	 * désignant la ligne param c entier désignant la colonne param terrain où se
	 * trouve la case
	 * 
	 * @param l 
	 * @param c 
	 * @param p 
	 */
	public Case(int l, int c, Plateau p) {
		lig = l;
		col = c;
		plateau = p;
		select = false;
		pion = null;
		obstacle = null;
		teleporteur = null;
		typeTerrain = Textures.ROC;
	}


	/**
	 * Méthode qui compare 2 case grace à la ligne et à la colonne
	 *
	 * @param c la case à comparé
	 * @return false si les cases sont différentes, sinon true
	 */
	public boolean compare(Case c) {
		if (c.getLigne() == lig && c.getColonne() == col) {
			return true;
		}
		return false;
	}



	/**
	 * Retourne le numéro de la colonne de la case
	 *
	 * @return Colonne de la case
	 */
	public int getColonne() {
		return col;
	}

	/**
	 * Retourne le numéro de la ligne de la case
	 *
	 * @return Ligne de la case
	 */
	public int getLigne() {
		return lig;
	}

	/**
	 * Accesseur du pion de la case
	 * @return pion
	 */
	public Pion getPion() {
		return pion;
	}

	/**
	 * Accesseur de l'obstacle de la case
	 */
	public Obstacle getObstacle() {
		return obstacle;
	}

	/**
	 * Retourne le plateau
	 *
	 * @return Plateau où est la case
	 */
	public Plateau getPlateau() {
		return plateau;
	}

	/**
	 * Accesseur du téléporteur de la case
	 * @return
	 */
	public Teleporteur getTeleporteur() {
		return teleporteur;
	}

	/**
	 * Affecteur de teleporteur
	 * @param teleporteur
	 */
	public void setTeleporteur(Teleporteur teleporteur) {
		this.teleporteur = teleporteur;
	}

	/**
	 * Méthode qui enlève l'élément se trouvant sur la case.
	 *
	 */
	public void remove() {
		pion = null;
		obstacle = null;
		teleporteur = null;
	}

	/**
	 * Méthode permettant de sélectionner une case
	 * 
	 * @param b 
	 */
	public void setSelect(boolean b) {
		select = b;
	}

	/**
	 * Affecteur du pion de la case
	 * @param pion 
	 */
	public void setPion(Pion pion) {
		this.pion = pion;
	}

	/**
	 * Accesseur du type de terrain de la case
	 * @return
	 */
	public int getTypeTerrain() {
		return typeTerrain;
	}

	/**
	 * Affecteur du type de terrain de la case
	 * @param typeTerrain
	 */
	public void setTypeTerrain(int typeTerrain) {
		this.typeTerrain = typeTerrain;
	}

	/**
	 * Affecteur de l'obstacle de la case
	 * @param obstacle
	 */
	public void setObstacle(Obstacle obstacle) {
		this.obstacle = obstacle;
	}

	/**
	 * Affecteur de la bordure de la case
	 * @param bordure
	 */
	public void setBordure(Bordure bordure) {
		this.bordure = bordure;
	}

	/**
	 * Accesseur de la bordure de la case
	 * @return
	 */
	public Bordure getBordure() {
		return bordure;
	}

	/**
	 * Afficher le couple (ligne,colonne) de la case
	 */
	@Override
	public String toString() {
		return "(" + col + "," + lig + ")";
	}

	/**
	 * Accesseur de select
	 * @return
	 */
	public boolean getSelect() {
		return select;
	}

	/**
	 * Renvoie vrai si la case contient un obstacle aux déplacements
	 * @return
	 */
	public boolean isObstacleDeplacement() {
		return obstacle != null;
	}

	/**
	 * Renvoie vrai si la case contient un pion
	 * @return
	 */
	public boolean contientPion() {
		return pion != null;
	}

	/**
	 * Renvoie vrai si la case contient un obstacle indestructible
	 * @return
	 */
	public boolean isObstacleAttaque() {
		if (obstacle != null) {
			if (obstacle.isDestructible()) {
				return false;
			} else {
				return true;
			}
		}
		return obstacle != null;
	}

	/**
	 * Supprime l'obstacle de la case
	 */
	public void supprimerObstacle() {
		obstacle = null;
	}

	/**
	 * Renvoie vrai si la case contient un téléporteur du joueur
	 * @param joueur
	 * @return 
	 */
	public boolean contientTeleporteur(Joueur joueur) {
		if (teleporteur != null) {
			if (joueur.getTeleporteur().contains(teleporteur)) {
				return true;
			}
		}
		return false;
	}

}
