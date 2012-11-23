package moteur;

import editeur.FenetreEditeur;
import java.io.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import moteur.familles.oiseau.AssassinOiseau;

/**
 *
 * @author Kévin
 */
public class Plateau implements Serializable {

	protected static final long serialVersionUID = 1L;
	/**
	 * Nombre de colonne dans le plateau
	 */
	private int colonne = 35;
	/**
	 * Nombre de ligne dans le plateau
	 */
	private int ligne = 20;
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
		/*
		 * Initialisation des cases blanches
		 */
		for (int i = 0; i < ligne; i++) {
			for (int j = 0; j < colonne; j++) {
				plateau[i][j] = new Case(i, j, this);
			}
		}
	}

	/**
	 * Constructeur avec une carte prédéfinie
	 *
	 * @param f
	 */
	public Plateau(String str) {
		plateau = new Case[ligne][colonne];
		File fichier = new File(str);
		FileInputStream fis = null;
		try {
			fis = new FileInputStream(fichier);
		} catch (FileNotFoundException ex) {
			Logger.getLogger(FenetreEditeur.class.getName()).log(Level.SEVERE, null, ex);
		}

		if (fis != null) {
			ObjectInputStream ois = null;
			try {
				ois = new ObjectInputStream(fis);
			} catch (IOException ex) {
				Logger.getLogger(FenetreEditeur.class.getName()).log(Level.SEVERE, null, ex);
			} finally {
				try {
					Plateau p = (Plateau) ois.readObject();
					for (int i = 0; i < plateau.length; i++) {
						for (int j = 0; j < plateau[i].length; j++) {
							plateau[i][j] = new Case(i, j, this);
							plateau[i][j].setBordure(p.get(i, j).getBordure());
							plateau[i][j].setObstacle(p.get(i, j).getObstacle());
							plateau[i][j].setTypeTerrain(p.get(i, j).getTypeTerrain());
						}
					}
					ois.close();
					fis.close();
				} catch (IOException ex) {
					Logger.getLogger(FenetreEditeur.class.getName()).log(Level.SEVERE, null, ex);
				} catch (ClassNotFoundException ex) {
					Logger.getLogger(Plateau.class.getName()).log(Level.SEVERE, null, ex);
				}
			}
		}

		AssassinOiseau o = new AssassinOiseau(plateau[7][9]);
		AssassinOiseau o1 = new AssassinOiseau(plateau[12][7]);
		AssassinOiseau o2 = new AssassinOiseau(plateau[7][7]);
		AssassinOiseau o3 = new AssassinOiseau(plateau[5][6]);
		AssassinOiseau o4 = new AssassinOiseau(plateau[5][7]);


	}

	/**
	 * Retourne la case situé à la même position, c'est-à-dire même ligne et
	 * même colonne.
	 *
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
	 *
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

	public Case[][] get() {
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

	public int getNbColonne() {
		return colonne;
	}

	public int getNbLigne() {
		return ligne;
	}

	public boolean deplacementPossible(Case c1, Case c2) {
		return c1.getPion().deplacementPossible(c2);
	}
}
