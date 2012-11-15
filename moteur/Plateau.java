package moteur;

import java.awt.Dimension;

/**
 *
 * @author Kévin
 */
public class Plateau {

	/**
	 * Nombre de colonne dans le plateau
	 */
	private static int colonne = 24;
	/**
	 * Nombre de ligne dans le plateau
	 */
	private static int ligne = 18;
	/**
	 * Tableau de Cases symbolisant la plateau.
	 */
	private Case[][] plateau;

	/**
	 * Constructeur de la classe Plateau.
	 */
	public Plateau() {
		super();
		plateau = new Case[ligne][colonne];
		/* Initialisation des cases blanches */
		for (int i = 0; i < ligne; i++) {
			for (int j = 0; j < colonne; j++) {
					plateau[i][j] = new Case(i, j, this);
			}
		}
	}

	/**
	 * Retourne la case situé à la même position, c'est-à-dire même ligne et même colonne. 
	 * @param c Case qui est retournée
	 * @return Retourne la case c.
	 */
	public Case get(Case c) {
		if (c != null) {
			return plateau[c.getLigne()][c.getColonne()];
		}
		return null;
	}

	/**
	 * Retourne la case se situant à la ligne l,et à la colonne c.
	 * @param l ligne de la case.
	 * @param c colonne de la case.
	 * @return Case situé en position l,c
	 */
	public Case get(int l, int c) {
		//Arguments sont valides
		if (l >= 0 && l < ligne && c >= 0 && c < colonne) {
			return plateau[l][c];
		}

		return null;

	}
	
	public Case[][] get(){
		return plateau;
	}

	/**
	 * Supprime les pieces se situant sur la plateau.
	 *
	 */
	public void vider() {
		for (int i = 0; i < ligne; i++) {
			for (int j = 0; j < colonne; j++) {
				get(i, j).remove();
			}
		}
	}

	public static int getColonne() {
		return colonne;
	}

	public static int getLigne() {
		return ligne;
	}


}
