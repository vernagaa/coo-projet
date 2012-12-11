package moteur;

import exception.MapException;
import java.io.*;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author KÃ©vin
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
	 * Constructeur avec une carte prÃ©dÃ©finie
	 *
	 * @param f
	 */
	public Plateau(String str) {
		try {
			plateau = new Case[ligne][colonne];
			init(str);
		} catch (MapException ex) {}
	}

	public final void init(String str) throws MapException{
		try {
			FileInputStream fis = new FileInputStream(str);
			ObjectInputStream ois = new ObjectInputStream(fis);
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

		} catch (ClassNotFoundException ex) {
			Logger.getLogger(Plateau.class.getName()).log(Level.SEVERE, null, ex);
		} catch (IOException ex) {
				throw new MapException(str);
		}
	}

	/**
	 * Retourne la case situÃ© Ã  la mÃªme position, c'est-Ã -dire mÃªme ligne et
	 * mÃªme colonne.
	 *
	 * @param c Case qui est retournÃ©e
	 * @return Retourne la case c.
	 */
	public Case get(Case c) {
		if (c != null) {
			return plateau[c.getLigne()][c.getColonne()];
		}
		return null;
	}

	/**
	 * Retourne la case se situant Ã  la ligne l,et Ã  la colonne c.
	 *
	 * @param l ligne de la case.
	 * @param c colonne de la case.
	 * @return Case situÃ© en position l,c
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
	
	public ArrayList<Case> listeChateaux(){
		ArrayList<Case> l = new ArrayList<Case>();
		for(int i=0;i<colonne;i++){
			for(int j=0;j<ligne;j++){
				if(plateau[j][i].getObstacle()!=null && plateau[j][i].getObstacle().isChateau()){
					l.add(plateau[j][i]);
				}
			}
		}
		return l;
	}
}
